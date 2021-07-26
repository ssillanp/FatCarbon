package com.example.fatcarbon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TodaysFoodListAdapter extends RecyclerView.Adapter<TodaysFoodListAdapter.MyViewHolder>{

    ArrayList<DiaryItem> itemsList;
    Context context;
    User user;

    public TodaysFoodListAdapter(FragmentActivity ct, ArrayList<DiaryItem> items){
        itemsList = items;
        context = ct;
        user = User.getInstance();
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
        myViewHolder.text1.setText(((FoodItem) itemsList.get(i).getItem()).getName());
        myViewHolder.text2.setText(String.valueOf(((FoodDiaryItem) itemsList.get(i)).getPortion()) + " g");
        double kCal = (((FoodItem) itemsList.get(i).getItem()).getEnergyKcal()) / 100 * (((FoodDiaryItem) itemsList.get(i)).getPortion());
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
