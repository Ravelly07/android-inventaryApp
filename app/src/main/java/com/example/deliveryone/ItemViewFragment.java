package com.example.deliveryone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.deliveryone.backend.DataBaseHelper;
import com.example.deliveryone.utilidades.Utilidades;


public class ItemViewFragment extends Fragment {
    Double codebar; //Esta bariable almacena el codigo de barras del producto (su primary key)
    //Declaramos los elementos del view
    EditText descriptionBox, precio1Box, precio2Box,codigoBox;
    //Declaramos la instancia del helper
    DataBaseHelper dbhelper;

    public ItemViewFragment() {
        // Required empty public constructor
    }



    public static ItemViewFragment newInstance(String param1, String param2) {
        ItemViewFragment fragment = new ItemViewFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recibimos el codigo de barras (primary key) del produto que se mostrará
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Recibimos el valor del codebar (nuestro primarykey)
                codebar = result.getDouble("codebar");
                //Toast.makeText(getActivity(), "El BarCode del producto es : " + codebar ,Toast.LENGTH_LONG).show();

                //Reconocemos los elementos del view
                descriptionBox = (EditText) getView().findViewById(R.id.descriptionBox);
                precio1Box = (EditText) getView().findViewById(R.id.precio1Box);
                precio2Box = (EditText) getView().findViewById(R.id.precio2Box);
                codigoBox = (EditText) getView().findViewById(R.id.codigoBox);
                //Conexión
                dbhelper = new DataBaseHelper(getActivity());

                SearchItem();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_view, container, false);
    }

    //Metodos-------
    private void SearchItem(){
        //Variable local para la operación
        double costo, precio1,precio2;
        String impuesto;

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //Select * from t_item where bar_code = codebar
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_ITEMS + " WHERE " + Utilidades.CAMPO_BAR_CODE + " = " + codebar, null);
        cursor.moveToFirst();
        //Obteniendo los 4 campos del registro
        codigoBox.setText(cursor.getString(0));
        descriptionBox.setText(cursor.getString(1));
        impuesto = cursor.getString(2);
        costo = Double.parseDouble(cursor.getString(3));

        switch (impuesto){
            case "IVA":
                precio1 = costo * 1.16;
                precio1Box.setText("$"+String.format("%.2f",precio1));
                precio2 = costo * 1.10;
                precio2Box.setText("$"+String.format("%.2f",precio2));
                break;
            case "IV8":
                precio1 = costo * 1.08;
                precio1Box.setText("$"+String.format("%.2f",precio1));
                precio2 = costo * 1.05;
                precio2Box.setText("$"+String.format("%.2f",precio2));
                break;
            case "SYS":
                precio1 = costo * 1.05;
                precio1Box.setText("$"+String.format("%.2f",precio1));
                precio2 = costo * 1.02;
                precio2Box.setText("$"+String.format("%.2f",precio2));
                break;
            default:
                break;
        }


    }

}