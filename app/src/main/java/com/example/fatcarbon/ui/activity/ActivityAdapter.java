package com.example.fatcarbon.ui.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fatcarbon.app.ActivityDiaryItem;
import com.example.fatcarbon.app.DiaryItem;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder>{

    ArrayList<DiaryItem> itemsList;
    Context context;
    User user;

    public ActivityAdapter(FragmentActivity ct, ArrayList<DiaryItem> items, User usr){
        itemsList = items;
        context = ct;
        user = usr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        myViewHolder.text1.setText(((ActivityDiaryItem) itemsList.get(i)).getSport());
        myViewHolder.text2.setText(df.format(itemsList.get(i).getDate()));
        myViewHolder.text3.setText(String.valueOf(((ActivityDiaryItem) itemsList.get(i)).getDurationString()));
        myViewHolder.text4.setText(String.valueOf(((ActivityDiaryItem) itemsList.get(i))
                .getCalories(user.getCurrentWeight())) + " kCal");

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2, text3, text4;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.textActivityItem);
            text2 = itemView.findViewById(R.id.textViewActivityDate);
            text3 = itemView.findViewById(R.id.textViewActivityDuration);
            text4 = itemView.findViewById(R.id.textViewActivityCal);
            mainLayout = itemView.findViewById(R.id.activityMainLayout);
        }
    }

}
