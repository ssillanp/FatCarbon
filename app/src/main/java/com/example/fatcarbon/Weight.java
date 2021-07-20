package com.example.fatcarbon;

import java.io.Serializable;


/**
 * Class Weighting
 */
public class Weight implements Serializable {

    //
    // Fields
    //


    private double weightValue;
    
    //
    // Constructors
    //

    Weight() {}

    Weight(double weight) {
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
