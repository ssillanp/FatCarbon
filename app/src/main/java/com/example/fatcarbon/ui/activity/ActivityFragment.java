package com.example.fatcarbon.ui.activity;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.fatcarbon.R;
import com.example.fatcarbon.app.ActivityDiaryItem;
import com.example.fatcarbon.app.DiaryItem;
import com.example.fatcarbon.app.User;
import com.example.fatcarbon.app.UserDataWriter;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Fragment for activities
 */

public class ActivityFragment extends Fragment {

    RecyclerView recyclerActivity;
    Spinner activitySelector;
    User user;
    View root;
    Date acDate = null;
    Time stTime = null;
    Time enTime = null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_activity, container, false);

        //retrieve user from intent
        user = (User) getActivity().getIntent().getSerializableExtra("user");

        //initialize the activity selector drop down
        activitySelector = root.findViewById(R.id.spinnerActSelect);
        //set the drop down menu items from predefined string array (@string/mets)
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.mets));
        activitySelector.setAdapter(adapter);
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        EditText setDate = root.findViewById(R.id.editTextActivityDate);
        EditText setStartTime = root.findViewById(R.id.editTextActivityStartTime);
        EditText setEndTime = root.findViewById(R.id.editTextActivityEndTime);
        Button addActvity = root.findViewById(R.id.buttonAddActivity);
        recyclerActivity = root.findViewById(R.id.activity_recycler);

        // set listener for date editText (selector)
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == setDate) {
                    final Calendar calendar = Calendar.getInstance();
                    int mYear = calendar.get(Calendar.YEAR);
                    int mMonth = calendar.get(Calendar.MONTH);
                    int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                    //show date picker dialog
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity()
                            , new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            setDate.setText(String.format("%d.%d.%d", dayOfMonth, month + 1, year));
                            SimpleDateFormat format =
                                    new SimpleDateFormat("dd.MM.yyyy", new Locale("fi_FI"));
                            try {
                                // set tha acDate for activity date value
                                acDate = format.parse(setDate.getText().toString());
                                System.out.println(acDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });

        //set listener for time setting fields
        View.OnClickListener timeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mHour, mMinute;
                final Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR);
                mMinute = calendar.get(Calendar.MINUTE);
                final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity()
                        , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        DecimalFormat df = new DecimalFormat("00");
                        if (v == setStartTime) {
                            setStartTime.setText(String.format("%s:%s", df.format(hourOfDay), df.format(minute)));
                            stTime = new Time(hourOfDay, minute, 0);
                        } else if (v == setEndTime) {
                            setEndTime.setText(String.format("%s:%s", df.format(hourOfDay), df.format(minute)));
                            enTime = new Time(hourOfDay, minute, 0);
                        }
                    }
                }, mHour, mMinute, true);

                timePickerDialog.show();

            }
        };
        setStartTime.setOnClickListener(timeListener);
        setEndTime.setOnClickListener(timeListener);

        // set listener for activity add button
        addActvity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ensure that none of the required fields are empty
                if (acDate != null & stTime != null & enTime != null & activitySelector.getSelectedItemId() != 0) {

                    // create new diary entry activity
                    ActivityDiaryItem adi = new ActivityDiaryItem();
                    adi.setDuration(enTime.getTime() - stTime.getTime());
                    adi.setSport(activitySelector.getSelectedItem().toString()
                            , ((int) activitySelector.getSelectedItemId()) - 1);
                    adi.setDate(acDate);
                    user.getDiary().addEntry(adi);

                    // save user
                    UserDataWriter udw = new UserDataWriter(getActivity());
                    udw.writeItem(user);

                    //  update the recycler view
                    updateActivityView();

                }
            }
        });
        // update the recycler view
        updateActivityView();
    }

    // function updates the activity recycler view
    public void updateActivityView(){
        ArrayList<DiaryItem> listItems = user.getDiary().getActivityEntries();
        ActivityAdapter adapter = new ActivityAdapter(getActivity(), listItems, user);
        recyclerActivity.setAdapter(adapter);
        recyclerActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}