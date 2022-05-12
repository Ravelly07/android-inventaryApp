package com.example.deliveryone;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.deliveryone.backend.DataBaseHelper;
import com.example.deliveryone.backend.MainController;
import com.example.deliveryone.databinding.FragmentItemViewBinding;
import com.example.deliveryone.utilidades.Utilidades;


public class ItemViewFragment extends Fragment {
    private FragmentItemViewBinding binding;
    Double codebar; //Esta Variable almacena el codigo de barras del producto (su primary key)
    //Declaramos los elementos del view
    EditText descriptionBox, precio1Box, precio2Box,codigoBox;


    public ItemViewFragment() {
        // Required empty public constructor
    }



    public static ItemViewFragment newInstance(String param1, String param2) {
        ItemViewFragment fragment = new ItemViewFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItemViewBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Metodo para recibir el valor del codigo de barras la lalve primaria de nuestra tabla
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Recibimos el valor del codebar (nuestro primarykey)
                codebar = result.getDouble("codebar");

                //Reconocemos los elementos del view
                descriptionBox = (EditText) getView().findViewById(R.id.descriptionBox);
                precio1Box = (EditText) getView().findViewById(R.id.precio1Box);
                precio2Box = (EditText) getView().findViewById(R.id.precio2Box);
                codigoBox = (EditText) getView().findViewById(R.id.codigoBox);
                //Metodo utilizado para llenar los campos con información de la DB
                MainController.SearchItem(descriptionBox, precio1Box, precio2Box, codigoBox, getActivity(), codebar);
            }
        });

        binding.goBackReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Automaticamente nos regresa a la vista anterior | No olvidemos enviar el tipo de usuario
                Bundle bundle = new Bundle();
                bundle.putString("userType", "Regular");
                getParentFragmentManager().setFragmentResult("key1",bundle);
                NavHostFragment.findNavController(ItemViewFragment.this)
                        .navigate(R.id.action_itemViewFragment_to_ItemsFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}