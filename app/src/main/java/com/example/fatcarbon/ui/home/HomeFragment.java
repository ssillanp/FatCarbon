package com.example.fatcarbon.ui.home;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

//    private HomeViewModel homeViewModel;

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
//        ProgressBar weightBar = root.findViewById(R.id.progressBarWeight);
//        ProgressBar foodBar = root.findViewById(R.id.progressBarFood);
//        ProgressBar activityBar = root.findViewById(R.id.progressBarActivity);
//        weightBar.setProgress(10);
//        foodBar.setProgress(50);
//        activityBar.setProgress(100);





        return root;
    }
}