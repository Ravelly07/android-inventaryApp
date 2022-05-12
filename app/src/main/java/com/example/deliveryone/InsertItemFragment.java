package com.example.deliveryone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliveryone.backend.MainController;
import com.example.deliveryone.databinding.FragmentInsertItemBinding;

public class InsertItemFragment extends Fragment {
    private FragmentInsertItemBinding binding;
    //Elementos del view
    EditText descriptionInsertBox, costoInsertBox, ivaInsertBox, codigoInsertBox;
    //VARIABLE PARA ALMACENAR LA PRIMARY KEY RECIBIDA
    Double codebar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInsertItemBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Definimos los elementos del view
        descriptionInsertBox = (EditText) getView().findViewById(R.id.descriptionInsertBox);
        costoInsertBox = (EditText) getView().findViewById(R.id.costoInsertBox);
        ivaInsertBox = (EditText)  getView().findViewById(R.id.ivaInsertBox);
        codigoInsertBox = (EditText) getView().findViewById(R.id.codigoInsertBox);

        binding.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Insertamos el registro en la DB
                MainController.insert(descriptionInsertBox,costoInsertBox,ivaInsertBox,codigoInsertBox,getActivity());
                //Automaticamente nos regresa a la vista anterior | No olvidemos enviar el tipo de usuario
                Bundle bundle = new Bundle();
                bundle.putString("userType", "Admin");
                getParentFragmentManager().setFragmentResult("key1",bundle);
                Toast.makeText(getActivity(), "BOTON REGISTRO",Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(InsertItemFragment.this)
                        .navigate(R.id.action_insertItemFragment_to_ItemsFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}