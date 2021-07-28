package com.example.fatcarbon.ui.foods;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fatcarbon.*;
import com.example.fatcarbon.app.FoodItem;
import com.example.fatcarbon.app.User;
import com.example.fatcarbon.ui.MainActivityLoggedIn;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyViewHolder>{

    ArrayList<FoodItem> itemsList;
    Context context;
    User user;

    public FoodListAdapter(FragmentActivity ct, ArrayList<FoodItem> items, User usr){
        itemsList = items;
        context = ct;
        user = usr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_list_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.text1.setText(itemsList.get(i).getName());
//        myViewHolder.text1.setText(itemsList.get(i).getUnits().toString());
        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("item",  itemsList.get(i));
                args.putSerializable("user", user);
                Fragment frag = new FoodUnitSelectorFragment();
                frag.setArguments(args);
                FragmentTransaction transaction = ((MainActivityLoggedIn) context).getSupportFragmentManager().
                        beginTransaction();
                transaction.replace(R.id.foodsFragmentLayout, frag);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.textFoodListItem);
//            text2 = itemView.findViewById(R.id.textFoodListUnit);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
