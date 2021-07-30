package com.example.fatcarbon.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.User;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

//    private HomeViewModel homeViewModel;


    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");
        TextView test = root.findViewById(R.id.text_home);
        test.setText("Welcome " + user.getUsername());
        TextView date = root.findViewById(R.id.text_home_date);
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.M.yyyy");
        date.setText(format.format(now));
        TextView calInTd = root.findViewById(R.id.textViewCalEatenKcal);
        TextView calInTdPct = root.findViewById(R.id.textViewCalEatenPrcent);
        TextView calBrntTd = root.findViewById(R.id.textViewCalBurntKcal);
        TextView wghtTd = root.findViewById(R.id.textViewCurrentWeight);
        TextView wghtToTrgt = root.findViewById(R.id.textViewWeightToTarget);
        DecimalFormat df = new DecimalFormat("#0");
        DecimalFormat dfp = new DecimalFormat("#0.0");
        calInTd.setText(String.valueOf(df.format(user.getDiary().getDailyCalIntake()) + " kCal"));
        calInTdPct.setText(String.valueOf(dfp.format(user.getDiary().getDailyCalIntake()
                / user.calculateBaseCalories() * 100)) + "%");
        calBrntTd.setText(String.valueOf(df.format(user.getDiary().getDailyEnergyBurnt(user.getCurrentWeight()))) + " kCal");
        wghtTd.setText(String.valueOf(user.getCurrentWeight()) + " kg");
        wghtToTrgt.setText(String.valueOf(user.getCurrentWeight() - user.getStartWeight() * 0.9) + " kg");

        return root;
    }


}