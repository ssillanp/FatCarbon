package com.example.fatcarbon.ui.foods;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fatcarbon.R;

/**+
 * base fragment for all the food fragments
 */

public class FoodsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_foods, container, false);
        FragmentManager manager = getActivity().getSupportFragmentManager();;
        Fragment frag = new FoodsViewFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.foodsFragmentLayout, frag);
        transaction.commit();
        return root;
    }


}