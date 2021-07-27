package com.example.fatcarbon.ui.weights;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.fatcarbon.*;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class WeightsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_weights, container, false);
        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");
        Button weightButton = root.findViewById(R.id.buttonAddWeighting);
        EditText weightValue = root.findViewById(R.id.editTextWeightVal);
        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.getDiary().addEntry(new DiaryItem(new WeightItem(
                        Double.parseDouble(weightValue.getText().toString()))));
                UserDataWriter udw = new UserDataWriter(getActivity());
                udw.writeItem(user);
                updateGraph(user, root);

            }
        });
        updateGraph(user, root);
        return root;
    }

    public void updateGraph(User user, View root) {

        GraphView weightGraph = root.findViewById(R.id.weight_graph);
        weightGraph.removeAllSeries();
        weightGraph.getLegendRenderer().setVisible(false);
        ArrayList<DiaryItem> weights = user.getDiary().getWeightEntries();
        DataPoint[] pointsArray = new DataPoint[weights.size()];
        for (int i = 0; i < weights.size(); i++) {
            pointsArray[i] = new DataPoint(weights.get(i).getDate(),
                    ((WeightItem) weights.get(i).getItem()).getWeightValue());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(pointsArray);
        series.setTitle("weights");
        series.setColor(Color.BLUE);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);
        weightGraph.addSeries(series);
        // set date label formatter
        weightGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        weightGraph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

// set manual x bounds to have nice steps
        weightGraph.getViewport().setMinX(weights.get(0).getDate().getTime() - (86400000 / 2));
        weightGraph.getViewport().setMaxX(weights.get(weights.size() - 1).getDate().getTime() + (86400000 / 2));
        weightGraph.getViewport().setMinY(((WeightItem) weights.get(0).getItem()).getWeightValue() * 0.4);
        weightGraph.getViewport().setMaxY(((WeightItem) weights.get(0).getItem()).getWeightValue() * 1.6);
        weightGraph.getViewport().setXAxisBoundsManual(true);
        weightGraph.getViewport().setYAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        weightGraph.getGridLabelRenderer().setHumanRounding(false);

    }

}

