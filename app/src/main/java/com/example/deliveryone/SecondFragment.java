package com.example.deliveryone;

import android.content.ContentValues;
import android.content.SearchRecentSuggestionsProvider;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.deliveryone.backend.DataBaseHelper;
import com.example.deliveryone.databinding.FragmentSecondBinding;
import com.example.deliveryone.utilidades.Utilidades;

//-----------------Sign Up View
public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    //Declaramos los elementos del view
    EditText fullnamebox, emailbox, usernamebox, passwordbox;
    //Declaramos la instacia del helper
    DataBaseHelper dbhelper;
    //Este es una variable que nos ayuda a saber si los cmapos se llenaron correctamente
    boolean compleate = false;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fullnamebox = (EditText) getView().findViewById(R.id.fullnamebox);
        emailbox = (EditText) getView().findViewById(R.id.emailbox);
        usernamebox = (EditText) getView().findViewById(R.id.usernamebox);
        passwordbox = (EditText) getView().findViewById(R.id.passwordbox);



        binding.registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrarUsuario();

                if(compleate){
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            }
        });
    }

    //Metodos
    private void registrarUsuario(){

        dbhelper = new DataBaseHelper(getActivity());
//        if(dbhelper != null){
//            Toast.makeText(getActivity(), "OK",Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(getActivity(), "No hay contex",Toast.LENGTH_LONG).show();
//        }
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        String fullName = fullnamebox.getText().toString();
        String userName = usernamebox.getText().toString();
        String password = passwordbox.getText().toString();

        //Revisamos si los campos fueron llenados correctamente o no
        compleate = check(fullName, userName, password);
        if(compleate){
            String insertar = "INSERT INTO " + Utilidades.TABLE_USERS + " (" + Utilidades.CAMPO_TYPE + "," + Utilidades.CAMPO_FULLNAME
                    + "," + Utilidades.CAMPO_EMAIL + "," + Utilidades.CAMPO_USERNAME + "," + Utilidades.CAMPO_PASSWORD + ") " +
                    "VALUES (" + "'Regular'" + ", '" + fullName + "' , '" + emailbox.getText().toString() + "' , '"
                    + userName + "' , '" + password + "' )";

            db.execSQL(insertar);
            db.close();
        }else{
            Toast.makeText(getActivity(), "ERROR: Campos incompletos. Completar Registro",Toast.LENGTH_LONG).show();
        }

    }
    //Finción que se encarga de revisar si los campos estan vacios o no
    private boolean check(String campo1, String campo2, String campo3){
        if(TextUtils.isEmpty(campo1) || TextUtils.isEmpty(campo2) || TextUtils.isEmpty(campo3)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}