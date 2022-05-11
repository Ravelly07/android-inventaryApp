package com.example.deliveryone;

import android.database.Cursor;
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
import com.example.deliveryone.databinding.FragmentFirstBinding;
import com.example.deliveryone.utilidades.Utilidades;

// -------------Login View -------------
public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    //Elementos del view
    EditText usernamebox, passwordbox;
    //Declaración de la clase herlper
    //DataBaseHelper dbhelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Identificación de los elementos del viwe
        usernamebox = (EditText) getView().findViewById(R.id.usernameboxlog);
        passwordbox = (EditText) getView().findViewById(R.id.passwordboxlog);

        //NAVEGACIÓN ITEM VIEW
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean exist = false;

                //Validación de usuario
                exist = MainController.searchUser(usernamebox, passwordbox, getActivity());

                if(exist) {
                    boolean isAdmin = MainController.isAdmin(usernamebox, passwordbox, getActivity());
                    if(isAdmin){
                        //enviamos key admin
                        Bundle bundle = new Bundle();
                        bundle.putString("userType", "Admin");
                        getParentFragmentManager().setFragmentResult("key1",bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("userType", "Regular");
                        getParentFragmentManager().setFragmentResult("key1",bundle);
                    }
                    usernamebox.setText("");
                    passwordbox.setText("");
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_itemsFragment);
                }
            }
        });


        //NAVEGACIÓN REGISTER VIEW
        binding.buttonSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}