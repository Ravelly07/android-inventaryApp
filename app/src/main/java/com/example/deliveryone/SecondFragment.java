package com.example.deliveryone;

import android.os.Bundle;
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
//-----------------Sign Up View
public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    //Declaramos los elementos del view
    EditText fullnamebox, emailbox, usernamebox, passwordbox;
    //Declaramos la instacia del helper
    DataBaseHelper dbhelper;


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

                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    //Metodos
    private void registrarUsuario(){

        dbhelper = new DataBaseHelper(getActivity());
        if(dbhelper != null){
            Toast.makeText(getActivity(), "OK",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(), "No hay contex",Toast.LENGTH_LONG).show();
        }



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}