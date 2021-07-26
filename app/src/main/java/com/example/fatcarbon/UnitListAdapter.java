package com.example.fatcarbon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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

public class UnitListAdapter extends RecyclerView.Adapter<UnitListAdapter.MyViewHolder>{

    FoodItem item;
    Context context;
    User user;

    public UnitListAdapter(FragmentActivity ct, FoodItem itm){
        item = itm;
        context = ct;
        user = User.getInstance();
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
        myViewHolder.text1.setText(item.getUnits().get(myViewHolder.getAdapterPosition())[0]);
        myViewHolder.text2.setText(item.getUnits().get(myViewHolder.getAdapterPosition())[1] + "g");

        myViewHolder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double foodPortion = Double.parseDouble(item.getUnits().get(myViewHolder.getAdapterPosition())[1]);
                int foodPortionFactor;
                if (!myViewHolder.textPortions.getText().toString().equals("")){
                    foodPortionFactor = Integer.parseInt(myViewHolder.textPortions.getText().toString());
                } else {
                    foodPortionFactor = 1;
                }
                user.getDiary().addEntry(new FoodDiaryItem(item, foodPortion * foodPortionFactor));
                UserDataWriter udw = new UserDataWriter(context);
                udw.writeItem(user);
                Fragment fv = new FoodsViewFragment();
                FragmentManager manager = ((MainActivityLoggedIn) context).getSupportFragmentManager();
                FragmentTransaction tranact = manager.beginTransaction();
                tranact.replace(R.id.foodsFragmentLayout, fv);
                tranact.commit();
            }
        });
        //        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {

//
//            }
//        });
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
