package com.example.deliveryone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.deliveryone.backend.DataBaseHelper;
import com.example.deliveryone.backend.DataBaseProductSchema;
import com.example.deliveryone.utilidades.Utilidades;

import java.util.ArrayList;


public class ItemsFragment extends Fragment {
    ArrayAdapter<String> arrayAdapter;

    //Conexión con la BD
    DataBaseHelper dbhelper;

    //Declaración de las listas
    ArrayList<String> listaItems;
    ArrayList<String> listaBarCodes;

   public static String roll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("key1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Recibimos el valor
                roll = result.getString("userType");
                Toast.makeText(getActivity(), "El usuario es de tipo: " + roll ,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Conección de la base
        dbhelper = new DataBaseHelper(getActivity());
        //Declaración de los elementos del fragment
        View fragmentView = inflater.inflate(R.layout.fragment_items, container, false);
        ListView listView = (ListView) fragmentView.findViewById(R.id.list_item);
        SearchView searchView = (SearchView) fragmentView.findViewById(R.id.search_bar);

        //Obtenemos los items
        ArrayList<String> listaItems = createListItems();

        //Cargamos la lista de items dentro del listView
        //arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listaItems);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row,listaItems);
        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), "BarCode: " + listaBarCodes.get(i) ,Toast.LENGTH_LONG).show();

                //Enviamos el codigo de barras
                Bundle bundle = new Bundle();
                bundle.putDouble("codebar", Double.parseDouble(listaBarCodes.get(i)));
                getParentFragmentManager().setFragmentResult("key", bundle);


                if(roll.equals("Regular")) {
                    //Navegamos a la vista del item
                    NavHostFragment.findNavController(ItemsFragment.this)
                            .navigate(R.id.action_ItemsFragment_to_itemViewFragment);
                }else if(roll.equals("Admin")){
                    //Navegamos a la vista admin item
                    NavHostFragment.findNavController(ItemsFragment.this)
                            .navigate(R.id.action_ItemsFragment_to_adminViewFragment);
                }
            }
        });

        //Implementación del Search item, que nos permite filtar la lista.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ItemsFragment.this.arrayAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ItemsFragment.this.arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

        return fragmentView;
    }

    //Metodos-------
    private ArrayList<String> createListItems(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //Select * From t_items
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_ITEMS + " ORDER BY " + Utilidades.CAMPO_DESCRIPTION ,null);

        if(cursor == null ){
            return new ArrayList<String>();
        }

        ArrayList list = new ArrayList();
        listaBarCodes = new ArrayList();
        while (cursor.moveToNext()){
            listaBarCodes.add(cursor.getString(0));
            list.add(cursor.getString(1));
        }
        return list;
    }
    //---------------
}