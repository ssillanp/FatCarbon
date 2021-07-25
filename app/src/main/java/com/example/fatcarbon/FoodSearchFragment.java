package com.example.fatcarbon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FoodSearchFragment extends Fragment {

    RecyclerView recyclerViewFoodSearch;

     public FoodSearchFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_food_search, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState){
        recyclerViewFoodSearch = getView().findViewById(R.id.recyclerFoodSearchResults);
        ArrayList<FoodItem> listItems = (ArrayList<FoodItem>) getArguments().getSerializable("list");
        MyAdapter adapter = new MyAdapter(getActivity(), listItems);
        recyclerViewFoodSearch.setAdapter(adapter);
        recyclerViewFoodSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}