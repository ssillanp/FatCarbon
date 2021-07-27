package com.example.fatcarbon.ui.activity;

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
import com.example.fatcarbon.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        user = (User) getActivity().getIntent().getSerializableExtra("user");
        activitySelector = root.findViewById(R.id.spinnerActSelect);
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
        recyclerActivity = getView().findViewById(R.id.activity_recycler);
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == setDate) {
                    final Calendar calendar = Calendar.getInstance();
                    int mYear = calendar.get(Calendar.YEAR);
                    int mMonth = calendar.get(Calendar.MONTH);
                    int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                    //show dialog
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity()
                            , new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            setDate.setText(dayOfMonth + "." + (month + 1) + "." + year);
                            acDate = new Date(year-1900, month, dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
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
                        if (v == setStartTime) {
                            setStartTime.setText(hourOfDay + ":" + minute);
                            stTime = new Time(hourOfDay, minute, 0);
                        } else if (v == setEndTime) {
                            setEndTime.setText(hourOfDay + ":" + minute);
                            enTime = new Time(hourOfDay, minute, 0);
                        }
                    }
                }, mHour, mMinute, true);

                timePickerDialog.show();

            }
        };
        setStartTime.setOnClickListener(timeListener);
        setEndTime.setOnClickListener(timeListener);
        addActvity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (acDate != null & stTime != null & enTime != null & activitySelector.getSelectedItemId() != 0) {
                    ActivityItem activityItem = new ActivityItem();
                    activityItem.setDuration(enTime.getTime() - stTime.getTime());
                    activityItem.setSport(activitySelector.getSelectedItem().toString()
                            , ((int) activitySelector.getSelectedItemId()) - 1);
                    DiaryItem diaryItem = new DiaryItem();
                    diaryItem.setDate(acDate);
                    diaryItem.setItem(activityItem);
                    user.getDiary().addEntry(diaryItem);
                    UserDataWriter udw = new UserDataWriter(getActivity());
                    udw.writeItem(user);
                    updateActivityView();

                }
            }
        });
        updateActivityView();
    }

    public void updateActivityView(){
        ArrayList<DiaryItem> listItems = user.getDiary().getActivityEntries();
        ActivityAdapter adapter = new ActivityAdapter(getActivity(), listItems, user);
        recyclerActivity.setAdapter(adapter);
        recyclerActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}