package com.example.fatcarbon.ui.foods;

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
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
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
        user = (User) getActivity().getIntent().getSerializableExtra("user");
        recyclerTodaysFood = getView().findViewById(R.id.recyclerTodaysFood);
        ArrayList<DiaryItem> listItems = user.getDiary().getFoodEntries();
        TodaysFoodListAdapter adapter = new TodaysFoodListAdapter(getActivity(), listItems, user);
        recyclerTodaysFood.setAdapter(adapter);
        recyclerTodaysFood.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}