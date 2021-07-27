package com.example.fatcarbon;

import java.io.Serializable;


/**
 * Class ActivityItem
 */
public class ActivityItem implements Serializable {

    //
    // Fields
    //

    private String sport;
    private Long duration;
    private double met;


    //
    // Constructors
    //
    public ActivityItem() {
    }

    ;

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Set the value of sport
     *
     * @param newVar the new value of sport
     */
    public void setSport(String newVar, int pos) {
        sport = newVar;
        met = calculateMetValue(pos);
    }

    /**
     * Get the value of sport
     *
     * @return the value of sport
     */
    public String getSport() {
        return sport;
    }

//    /**
//     * Set the value of date
//     *
//     * @param newVar the new value of date
//     */
//    public void setDate(Date newVar) {
//        date = newVar;
//    }
//
//    /**
//     * Get the value of date
//     *
//     * @return the value of date
//     */
//    public Date getDate() {
//        return date;
//    }

    /**
     * Set the value of duration
     *
     * @param newVar the new value of duration
     */
    public void setDuration(Long newVar) {
        duration = newVar;
    }

    /**
     * Get the value of duration
     *
     * @return the value of duration
     */
    public Long getDuration() {
        return duration;
    }

    public String getDurationString(){
        double durInh =  duration.doubleValue() / 3600000; //duration in h
        return String.valueOf((int) durInh)+ "h " + (int)((durInh - (int) durInh) * 60) +"min" ;
    }


    /**
     * Get the value of calories
     *
     * @return the value of calories
     */
    public double getCalories(double weight) {
        return met * weight * duration / 3600000;
    }



    //
    // Other methods
    //
    private int calculateMetValue(int pos) {
        int[] mets = {2, 3, 5, 6, 6, 6, 7, 9, 9, 9, 10, 12, 14, 15, 16, 17};
        return mets[pos];

    }

}
