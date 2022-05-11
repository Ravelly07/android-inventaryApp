package com.example.deliveryone;

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
import com.example.deliveryone.backend.MainController;
import com.example.deliveryone.databinding.FragmentSecondBinding;
import com.example.deliveryone.utilidades.Utilidades;

//-----------------Sign Up View
public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    //Declaramos los elementos del view
    EditText fullnamebox, emailbox, usernamebox, passwordbox;
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

        //Elementos del view
        fullnamebox = (EditText) getView().findViewById(R.id.fullnamebox);
        emailbox = (EditText) getView().findViewById(R.id.emailbox);
        usernamebox = (EditText) getView().findViewById(R.id.usernamebox);
        passwordbox = (EditText) getView().findViewById(R.id.passwordbox);

        binding.registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compleate=  MainController.registrarUsuario(fullnamebox, emailbox,usernamebox,passwordbox, getActivity());

                if(compleate){
                    fullnamebox.setText("");
                    emailbox.setText("");
                    usernamebox.setText("");
                    passwordbox.setText("");

                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}