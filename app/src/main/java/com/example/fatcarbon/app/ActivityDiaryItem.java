package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

/**
 * Class ActivityItem, hold data for a single activity
 */
public class ActivityDiaryItem extends DiaryItem{


    private String sport;
    private Long duration;
    private double met;


    public ActivityDiaryItem() {
    }

    public void setSport(String newVar, int pos) {
        sport = newVar;
        met = calculateMetValue(pos);
    }


    public String getSport() {
        return sport;
    }

    public void setDuration(Long newVar) {
        duration = newVar;
    }


    public Long getDuration() {
        return duration;
    }

    public String getDurationString(){
        /**
         * Method returns a string representation of the activity duration
         */
        double durInh =  duration.doubleValue() / 3600000; //duration in h
        return String.valueOf((int) durInh)+ "h " + (int)((durInh - (int) durInh) * 60) +"min" ;
    }


    public double getCalories(double weight) {
        return met * weight * duration / 3600000;
    }


    private int calculateMetValue(int pos) {
        /**
         * Helper method to calculate the met value
         * mets[] -> array represents mets values for each item in @string/mets
         */
        int[] mets = {2, 3, 5, 6, 6, 6, 7, 9, 9, 9, 10, 12, 14, 15, 16, 17};
        return mets[pos];

    }

}
