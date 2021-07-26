package com.example.fatcarbon.ui.foods;

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
        User user = User.getInstance();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment frag = new FoodsViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.foodsFragmentLayout, frag);
        transaction.commit();
        return root;
    }


}