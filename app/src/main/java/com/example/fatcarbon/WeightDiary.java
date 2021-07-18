package com.example.fatcarbon;

import java.util.*;


/**
 * Class WeightDiary
 */
public class WeightDiary extends Diary {

    //
    // Fields
    //

    private double weightGoal;
    
    //
    // Constructors
    //
    public WeightDiary () { };
    
    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Set the value of weightGoal
     * @param newVar the new value of weightGoal
     */
    public void setWeightGoal (double newVar) {
        weightGoal = newVar;
    }

    /**
     * Get the value of weightGoal
     * @return the value of weightGoal
     */
    public double getWeightGoal () {
        return weightGoal;
    }

    //
    // Other methods
    //

    /**
     * @param        startDate
     * @param        endDate
     */
    public void getWeights(Date startDate, Date endDate)
    {
    }


}
