package com.example.fatcarbon;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


/**
 * Class ActivityItem
 */
public class ActivityItem implements Serializable {

    //
    // Fields
    //

    private String sport;
    private Date date;
    private Time duration;
    private double calories;
    
    //
    // Constructors
    //
    ActivityItem() { };
    
    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Set the value of sport
     * @param newVar the new value of sport
     */
    public void setSport (String newVar) {
        sport = newVar;
    }

    /**
     * Get the value of sport
     * @return the value of sport
     */
    public String getSport () {
        return sport;
    }

    /**
     * Set the value of date
     * @param newVar the new value of date
     */
    public void setDate (Date newVar) {
        date = newVar;
    }

    /**
     * Get the value of date
     * @return the value of date
     */
    public Date getDate () {
        return date;
    }

    /**
     * Set the value of duration
     * @param newVar the new value of duration
     */
    public void setDuration (Time newVar) {
        duration = newVar;
    }

    /**
     * Get the value of duration
     * @return the value of duration
     */
    public Time getDuration () {
        return duration;
    }

    /**
     * Set the value of calories
     * @param newVar the new value of calories
     */
    public void setCalories (double newVar) {
        calories = newVar;
    }

    /**
     * Get the value of calories
     * @return the value of calories
     */
    public double getCalories () {
        return calories;
    }

    //
    // Other methods
    //

}
