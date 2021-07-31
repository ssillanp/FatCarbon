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
import com.example.fatcarbon.app.DiaryItem;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.User;

import java.util.ArrayList;

/**
 * Fragment to hole the food view recyclerView
 */
public class FoodsViewFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private User user;
    RecyclerView recyclerTodaysFood;

    public FoodsViewFragment() {
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
        return inflater.inflate(R.layout.fragment_foods_view, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        // retrieve user form intent
        user = (User) getActivity().getIntent().getSerializableExtra("user");
        recyclerTodaysFood = getView().findViewById(R.id.recyclerTodaysFood);
        // get the list og food entries from user.diary
        ArrayList<DiaryItem> listItems = user.getDiary().getFoodEntries();
        TodaysFoodListAdapter adapter = new TodaysFoodListAdapter(getActivity(), listItems, user);
        recyclerTodaysFood.setAdapter(adapter);
        recyclerTodaysFood.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}