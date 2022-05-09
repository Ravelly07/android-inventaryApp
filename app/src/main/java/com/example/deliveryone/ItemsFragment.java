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
import androidx.appcompat.widget.SearchView;


public class ItemsFragment extends Fragment {


    String exampleList[] = {"Zargo", "Bonbon", "Coni", "Peggy", "Alkapone", "Layla","Napoleaon",
            "Lilit","Coqueta","Ron","Draco","Chispita","Bala","Canela"}; //ESTO SOLO PARA TENER UN EJEMPLO DE VISTA
    ArrayAdapter<String> arrayAdapter;

    //Metodos-------
    private void createListItems(){


    }
    //---------------

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
        SearchView searchView = (SearchView) fragmentView.findViewById(R.id.search_bar);

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,exampleList);
        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NavHostFragment.findNavController(ItemsFragment.this)
                        .navigate(R.id.action_ItemsFragment_to_itemViewFragment);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ItemsFragment.this.arrayAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ItemsFragment.this.arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

        return fragmentView;
    }
}