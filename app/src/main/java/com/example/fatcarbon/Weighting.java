package com.example.fatcarbon;

import java.util.*;


/**
 * Class Weighting
 */
public class Weighting {

    //
    // Fields
    //

    private Date date;
    private double weightValue;
    
    //
    // Constructors
    //
    public Weighting () { };
    
    //
    // Methods
    //


    //
    // Accessor methods
    //

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
     * Set the value of weightValue
     * @param newVar the new value of weightValue
     */
    public void setWeightValue (double newVar) {
        weightValue = newVar;
    }

    /**
     * Get the value of weightValue
     * @return the value of weightValue
     */
    public double getWeightValue () {
        return weightValue;
    }

    //
    // Other methods
    //

}
