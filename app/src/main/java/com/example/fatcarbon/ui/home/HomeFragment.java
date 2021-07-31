package com.example.fatcarbon.ui.home;
/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

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
import java.util.Locale;

/**
 * Home view fragment, days summary
 */
public class HomeFragment extends Fragment {




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //retreive user from intent
        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");

        // set the welcoming text and date
        TextView test = root.findViewById(R.id.text_home);
        test.setText("Welcome " + user.getUsername());
        TextView date = root.findViewById(R.id.text_home_date);
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy", new Locale("fi_FI"));
        System.out.println(sdf.format(now));
        date.setText(sdf.format(now));


        TextView calInTd = root.findViewById(R.id.textViewCalEatenKcal);
        TextView calInTdPct = root.findViewById(R.id.textViewCalEatenPrcent);
        TextView calBrntTd = root.findViewById(R.id.textViewCalBurntKcal);
        TextView wghtTd = root.findViewById(R.id.textViewCurrentWeight);
        TextView wghtToTrgt = root.findViewById(R.id.textViewWeightToTarget);

        // create decimal formats for user friendly viewing
        DecimalFormat df = new DecimalFormat("#0");
        DecimalFormat dfp = new DecimalFormat("#0.0");

        // set the display value for today calorie intake
        calInTd.setText(String.valueOf(df.format(user.getDiary().getDailyCalIntake(now)) + " kCal"));
        // set the dusplay value for calorie intake precentage of daily recommended calories
        calInTdPct.setText(String.valueOf(dfp.format(user.getDiary().getDailyCalIntake(now)
                / user.calculateBaseCalories() * 100)) + "%");
        // set the display text for calories burnt in days activities
        calBrntTd.setText(String.valueOf(df.format(user.getDiary().getDailyEnergyBurnt(now, user.getCurrentWeight()))) + " kCal");
        // set the display text for current weight
        wghtTd.setText(String.valueOf(user.getCurrentWeight()) + " kg");
        // set the display text for deviation from weight target
        wghtToTrgt.setText(String.valueOf((user.getCurrentWeight()) -user.getTargetWeight()) + " kg");

        return root;
    }


}