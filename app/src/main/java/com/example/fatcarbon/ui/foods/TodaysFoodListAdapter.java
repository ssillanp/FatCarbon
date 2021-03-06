package com.example.fatcarbon.ui.foods;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fatcarbon.*;
import com.example.fatcarbon.app.DiaryItem;
import com.example.fatcarbon.app.FoodDiaryItem;
import com.example.fatcarbon.app.FoodItem;
import com.example.fatcarbon.app.User;

import java.util.ArrayList;

/**
 * RecyclerView adapter for the consumed food item list
 */

public class TodaysFoodListAdapter extends RecyclerView.Adapter<TodaysFoodListAdapter.MyViewHolder>{

    ArrayList<DiaryItem> itemsList;
    Context context;
    User user;

    public TodaysFoodListAdapter(FragmentActivity ct, ArrayList<DiaryItem> items, User usr){
        itemsList = items;
        context = ct;
        user = usr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.todays_food_list_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        // Set the item description text
        myViewHolder.text1.setText(((FoodItem) itemsList.get(i).getItem()).getName());

        // Set the text for anoumt comsumed (in grams)
        myViewHolder.text2.setText(String.valueOf(((FoodDiaryItem) itemsList.get(i)).getAmount()) + " g");

        // set th text value for calories consumed
        double kCal = (((FoodItem) itemsList.get(i).getItem()).getEnergyKcal()) / 100 * (((FoodDiaryItem) itemsList.get(i)).getAmount());
        myViewHolder.text3.setText(String.valueOf((int) kCal) + " kCal");

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2, text3;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.textTodayFoodListItem);
            text2 = itemView.findViewById(R.id.textVievItemPortion);
            text3 = itemView.findViewById(R.id.textViewPortionCal);
            mainLayout = itemView.findViewById(R.id.mainTodayLayout);
        }
    }

}
