package com.example.fatcarbon.ui.foods;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fatcarbon.FoodsViewFragment;
import com.example.fatcarbon.R;
import com.example.fatcarbon.User;

public class FoodsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_foods, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        Fragment frag = new FoodsViewFragment();
        frag.setArguments(args);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.foodsFragmentLayout, frag);
        transaction.commit();
        return root;
    }


}