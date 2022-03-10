package com.example.deliveryone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ItemsFragment extends Fragment {


    String exampleList[] = {"Zargo", "Bonbon", "Coni", "Peggy", "Alkapone", "Layla"}; //ESTO SOLO PARA TENER UN EJEMPLO DE VISTA

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_items, container, false);
        ListView listView = (ListView) fragmentView.findViewById(R.id.list_item);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,exampleList);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NavHostFragment.findNavController(ItemsFragment.this)
                        .navigate(R.id.action_ItemsFragment_to_itemViewFragment);
            }
        });
        return fragmentView;
    }
}