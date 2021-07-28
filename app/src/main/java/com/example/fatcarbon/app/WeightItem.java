package com.example.fatcarbon.app;


import java.io.Serializable;

/**
 * Class Weighting
 */
public class WeightItem implements Serializable {

    //
    // Fields
    //


    private double weightValue;
    
    //
    // Constructors
    //

    public WeightItem() {}

    public WeightItem(double weight) {
        weightValue = weight;
    }
    
    //
    // Methods
    //


    //
    // Accessor methods
    //
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
