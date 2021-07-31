package com.example.fatcarbon.ui.foods;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fatcarbon.app.FoodItem;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.User;

/**
 * Fragment for food units selecting, base for the recyclerView for units
 */

public class FoodUnitSelectorFragment extends Fragment {

    FoodItem item;
    User user;
    RecyclerView recyclerViewUnits;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retrieve the user for intent
        user = (User) getActivity().getIntent().getSerializableExtra("user");

        // retrieve the food item from arguments
        if (getArguments() != null) {
            item = (FoodItem) getArguments().getSerializable("item");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_unit_selector, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        recyclerViewUnits = getView().findViewById(R.id.recyclerFoodUnits);
        FoodItem item = (FoodItem) getArguments().getSerializable("item");
        UnitListAdapter adapter = new UnitListAdapter(getActivity(), item, user);
        recyclerViewUnits.setAdapter(adapter);
        recyclerViewUnits.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}