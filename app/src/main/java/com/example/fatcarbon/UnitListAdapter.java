package com.example.fatcarbon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnitListAdapter extends RecyclerView.Adapter<UnitListAdapter.MyViewHolder>{

    FoodItem item;
    Context context;
    User user;

    public UnitListAdapter(FragmentActivity ct, FoodItem itm, User usr){
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
        myViewHolder.text1.setText(item.getUnits().get(myViewHolder.getAdapterPosition())[0]);
        myViewHolder.text2.setText(item.getUnits().get(myViewHolder.getAdapterPosition())[1] + "g");
        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.getDiary().addEntry(new FoodDiaryItem(item,
                        Double.parseDouble(item.getUnits().get(myViewHolder.getAdapterPosition())[1])));


            }
        });
    }

    @Override
    public int getItemCount() {
        return item.getUnits().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.textFoodUnitItem);
            text2 = itemView.findViewById(R.id.textFoodUnitUnit);
            mainLayout = itemView.findViewById(R.id.mainUnitLayout);
        }
    }

}
