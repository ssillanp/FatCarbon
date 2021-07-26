package com.example.fatcarbon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FoodUnitSelectorFragment extends Fragment {

    FoodItem item;
    User user;
    RecyclerView recyclerViewUnits;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (FoodItem) getArguments().getSerializable("item");
        }
        user = User.getInstance();
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
        UnitListAdapter adapter = new UnitListAdapter(getActivity(), item);
        recyclerViewUnits.setAdapter(adapter);
        recyclerViewUnits.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}