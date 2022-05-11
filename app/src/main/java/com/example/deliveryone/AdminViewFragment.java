package com.example.deliveryone;

import android.net.wifi.aware.PublishDiscoverySession;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deliveryone.backend.MainController;
import com.example.deliveryone.databinding.FragmentAdminViewBinding;
import com.example.deliveryone.databinding.FragmentFirstBinding;

public class AdminViewFragment extends Fragment {
    //Elementos del view
    EditText descriptionBoxAd, costoBoxAd, ivaBoxAd, codigoBoxAd;
    Button updateButton, deleteButton;
    //VARIABLE PARA ALMACENAR LA PRIMARY KEY RECIBIDA
    Double codebar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_view, container, false);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Metodo para recibir el valor del codigo de barras la lalve primaria de nuestra tabla
        getParentFragmentManager().setFragmentResultListener("key",this, new FragmentResultListener(){
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Recibimos el valor del codebar (nuestro primarykey)
                codebar = result.getDouble("codebar");

                //Definimos los elementos del view
                descriptionBoxAd = (EditText) getView().findViewById(R.id.descriptionBoxAd);
                costoBoxAd = (EditText) getView().findViewById(R.id.costoBoxAd);
                ivaBoxAd = (EditText)  getView().findViewById(R.id.ivaBoxAd);
                codigoBoxAd = (EditText) getView().findViewById(R.id.codigoBoxAd);

                //LLenamos los campos
                MainController.FillFields(descriptionBoxAd,costoBoxAd,ivaBoxAd,codigoBoxAd,getActivity(),codebar);

                updateButton = getView().findViewById(R.id.updateButton);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getContext(),"BOTON UPDATE", Toast.LENGTH_SHORT ).show();
                        //Guardamos la key;
                        String primaryKey = codigoBoxAd.getText().toString();
                        MainController.update(descriptionBoxAd,costoBoxAd,ivaBoxAd,codigoBoxAd,getActivity(),primaryKey);

                        NavHostFragment.findNavController(AdminViewFragment.this)
                                .navigate(R.id.action_adminViewFragment_to_ItemsFragment);

                    }
                });

                deleteButton = getView().findViewById(R.id.deleteButton);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"BOTON DELETE", Toast.LENGTH_SHORT ).show();
                    }
                });
            }
        });
    }

    //Metodos-------
    private void  SearchItem(){

    }

    private void Update(){

    }

    private void Delete(){

    }
    //---------------

}