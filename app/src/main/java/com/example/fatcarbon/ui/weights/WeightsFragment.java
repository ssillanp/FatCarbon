package com.example.fatcarbon.ui.weights;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/


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
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.DiaryItem;
import com.example.fatcarbon.app.User;
import com.example.fatcarbon.app.UserDataWriter;
import com.example.fatcarbon.app.WeightDiaryItem;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * Fragment for weight graph and adding weightings
 */

public class WeightsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_weights, container, false);
        // retreive user from intent
        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");
        Button weightButton = root.findViewById(R.id.buttonAddWeighting);
        EditText weightValue = root.findViewById(R.id.editTextWeightVal);
        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Add a new weighting
            public void onClick(View v) {
                WeightDiaryItem wdi = new WeightDiaryItem();
                wdi.setWeightValue(Double.parseDouble(weightValue.getText().toString()));
                wdi.setDateNow();
                // add the value in diary
                user.getDiary().addEntry(wdi);

                // save user data in storage
                UserDataWriter udw = new UserDataWriter(getActivity());
                udw.writeItem(user);
                updateGraph(user, root);

            }
        });
        //update the weight graph
        updateGraph(user, root);
        return root;
    }

    // Update the weight graph
    public void updateGraph(User user, View root) {

        GraphView weightGraph = root.findViewById(R.id.weight_graph);
        weightGraph.removeAllSeries();
        weightGraph.getLegendRenderer().setVisible(false);
        // get all weight entries from user
        ArrayList<DiaryItem> weights = user.getDiary().getWeightEntries();

        // datapoint arrays for graph
        DataPoint[] pointsArray = new DataPoint[weights.size()]; //actual weight graph
        DataPoint[] targetArray = new DataPoint[weights.size()]; // target weight limit line

        for (int i = 0; i < weights.size(); i++) {
            pointsArray[i] = new DataPoint(weights.get(i).getDate(),
                    ((WeightDiaryItem) weights.get(i)).getWeightValue());
            targetArray[i] = new DataPoint(weights.get(i).getDate(),
                    user.getTargetWeight());
        }
        // create data series for the graph
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(pointsArray);
        LineGraphSeries<DataPoint> seriesTarget = new LineGraphSeries<DataPoint>(targetArray);
        // set series attributes (looks)
        series.setTitle("weights");
        series.setColor(Color.BLUE);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);
        seriesTarget.setColor(Color.RED);
        seriesTarget.setDrawDataPoints(false);
        seriesTarget.setThickness(8);

        // add the series in the graph
        weightGraph.addSeries(series);
        weightGraph.addSeries(seriesTarget);

        // set date label formatter
        weightGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        weightGraph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        // set manual x and y bounds to have nice steps
        weightGraph.getViewport().setMinX(weights.get(0).getDate().getTime() - 43200000); //43200000 -> 1/2 day in ms.
        weightGraph.getViewport().setMaxX(weights.get(weights.size() - 1).getDate().getTime() + 43200000);
        weightGraph.getViewport().setMinY(((WeightDiaryItem) weights.get(0)).getWeightValue() * 0.4);
        weightGraph.getViewport().setMaxY(((WeightDiaryItem) weights.get(0)).getWeightValue() * 1.6);
        weightGraph.getViewport().setXAxisBoundsManual(true);
        weightGraph.getViewport().setYAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        weightGraph.getGridLabelRenderer().setHumanRounding(false);

    }

}

