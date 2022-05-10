package com.example.deliveryone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class ItemViewFragment extends Fragment {
    Integer codebar; //Esta bariable almacena el codigo de barras del producto (su primary key)

    public ItemViewFragment() {
        // Required empty public constructor
    }

    //Metodos-------
    private void SearchItem(){

    }

    //---------------


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
                codebar = result.getInt("codebar");
                Toast.makeText(getActivity(), "El BarCode del producto es : " + codebar ,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_view, container, false);
    }
}