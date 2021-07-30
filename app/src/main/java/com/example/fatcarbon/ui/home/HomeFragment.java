package com.example.fatcarbon.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.User;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
        GraphView weightGraph = root.findViewById(R.id.home_weight_graph);
        GraphView foodGraph = root.findViewById(R.id.home_food_graph);
        GraphView activityGraph = root.findViewById(R.id.home_activity_graph);
        updateGraph(user, root, foodGraph);


        return root;
    }

    public void updateGraph(User user, View root, GraphView graph) {
        Date day = Calendar.getInstance().getTime();
        day.setHours(0);
        day.setMinutes(0);
        day.setSeconds(0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -7);
        Date stDate = cal.getTime();
        DataPoint[] pointsArray = new DataPoint[7];
        DataPoint[] targetArray = new DataPoint[7];
        for (int i = 0; i < 7; i++) {
            cal.add(Calendar.DATE, 1);
            System.out.println(cal.getTime());
            pointsArray[i] = new DataPoint(cal.getTime(), user.getDiary().getDailyCalIntake(cal.getTime()));
            targetArray[i] = new DataPoint(cal.getTime(), user.calculateBaseCalories());
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(pointsArray);
        LineGraphSeries<DataPoint> seriesTarget = new LineGraphSeries<DataPoint>(targetArray);
        seriesTarget.setColor(Color.RED);
        seriesTarget.setDrawDataPoints(false);
        seriesTarget.setThickness(8);
        graph.addSeries(series);
        graph.addSeries(seriesTarget);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        graph.getViewport().setMinX(stDate.getTime());
        graph.getViewport().setMaxX(cal.getTime().getTime());
//        graph.getViewport().setMinY(((WeightDiaryItem) weights.get(0)).getWeightValue() * 0.4);
//        graph.getViewport().setMaxY(((WeightDiaryItem) weights.get(0)).getWeightValue() * 1.6);
        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setYAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);


    }
}