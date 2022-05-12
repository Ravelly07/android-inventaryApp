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
    private FragmentAdminViewBinding binding;
    //Elementos del view
    EditText descriptionBoxAd, costoBoxAd, ivaBoxAd, codigoBoxAd;
    //VARIABLE PARA ALMACENAR LA PRIMARY KEY RECIBIDA
    Double codebar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminViewBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Definimos los elementos del view
        descriptionBoxAd = (EditText) getView().findViewById(R.id.descriptionBoxAd);
        costoBoxAd = (EditText) getView().findViewById(R.id.costoBoxAd);
        ivaBoxAd = (EditText)  getView().findViewById(R.id.ivaBoxAd);
        codigoBoxAd = (EditText) getView().findViewById(R.id.codigoBoxAd);
        //Obtenemos el valor del la primaryKey del item seleccionado
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Recibimos el valor del codebar (nuestro primarykey)
                codebar = result.getDouble("codebar");
                //LLenamos los campos
                MainController.FillFields(descriptionBoxAd,costoBoxAd,ivaBoxAd,codigoBoxAd,getActivity(),codebar);
            }
        });
        //Controla lo que pasa al precionar el boton update
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Actualizamos
                String primaryKey = codebar.toString();
                MainController.update(descriptionBoxAd,costoBoxAd,ivaBoxAd,codigoBoxAd,getActivity(),primaryKey);
                //Automaticamente nos regresa a la vista anterior | No olvidemos enviar el tipo de usuario
                Bundle bundle = new Bundle();
                bundle.putString("userType", "Admin");
                getParentFragmentManager().setFragmentResult("key1",bundle);
                NavHostFragment.findNavController(AdminViewFragment.this)
                        .navigate(R.id.action_adminViewFragment_to_ItemsFragment);
            }
        });
        //Controlamos lo que sucede con el botón DELETE
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Eliminamos el registro
                String primaryKey = codebar.toString();
                MainController.delete(getActivity(), primaryKey);
                //Automaticamente nos regresa a la vista anterior | No olvidemos enviar el tipo de usuario
                Bundle bundle = new Bundle();
                bundle.putString("userType", "Admin");
                getParentFragmentManager().setFragmentResult("key1",bundle);
                NavHostFragment.findNavController(AdminViewFragment.this)
                        .navigate(R.id.action_adminViewFragment_to_ItemsFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}