package com.example.fatcarbon.ui.foods;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.FoodDiaryItem;
import com.example.fatcarbon.app.FoodItem;
import com.example.fatcarbon.app.User;
import com.example.fatcarbon.app.UserDataWriter;
import com.example.fatcarbon.ui.MainActivityLoggedIn;


/**
 * RecyclerView adapter for Food items unit (portion) selection
 */
public class UnitListAdapter extends RecyclerView.Adapter<UnitListAdapter.MyViewHolder> {

    FoodItem item;
    Context context;
    User user;

    public UnitListAdapter(FragmentActivity ct, FoodItem itm, User usr) {
        item = itm;
        context = ct;
        user = usr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_unit_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int i) {
        // Set text for Unit description
        myViewHolder.text1.setText(item.getUnits().get(myViewHolder.getAdapterPosition())[0]);
        // Set text for unit Unit
        myViewHolder.text2.setText(item.getUnits().get(myViewHolder.getAdapterPosition())[1] + "g");
        // Set click listener for the add button
        myViewHolder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the portion size in grams for the FoodItem Unit
                Double foodPortion = Double.parseDouble(item.getUnits().get(myViewHolder.getAdapterPosition())[1]);

                // the factor for Unit (foodPortion = 100g, foodPortionFactor = 2 -> total 200g)
                int foodPortionFactor;
                // get the factor from input, set to 1 in nothing is entered
                if (!myViewHolder.textPortions.getText().toString().equals("")) {
                    foodPortionFactor = Integer.parseInt(myViewHolder.textPortions.getText().toString());
                } else {
                    foodPortionFactor = 1;
                }

                // Add new diary item
                FoodDiaryItem fdi = new FoodDiaryItem();
                fdi.setItem(item);
                fdi.setDateNow();
                fdi.setAmount(foodPortion * foodPortionFactor);
                user.getDiary().addEntry(fdi);

                // save User
                UserDataWriter udw = new UserDataWriter(context);
                udw.writeItem(user);

                // to next fragment (FoodsViewFragment)
                Fragment fv = new FoodsViewFragment();
                FragmentManager manager = ((MainActivityLoggedIn) context).getSupportFragmentManager();
                FragmentTransaction tranact = manager.beginTransaction();
                tranact.replace(R.id.foodsFragmentLayout, fv);
                tranact.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.getUnits().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2;
        EditText textPortions;
        Button addBtn;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.textFoodUnitItem);
            text2 = itemView.findViewById(R.id.textFoodUnitUnit);
            textPortions = itemView.findViewById(R.id.editTextPortions);
            addBtn = itemView.findViewById(R.id.buttonAddFoodItem);
            mainLayout = itemView.findViewById(R.id.mainUnitLayout);
        }
    }

}
