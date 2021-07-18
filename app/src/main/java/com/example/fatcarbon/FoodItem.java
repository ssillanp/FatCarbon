package com.example.fatcarbon;

import java.util.*;


/**
 * Class FoodItem
 */
public class FoodItem {

    //
    // Fields
    //

    private String name;
    private double calories;
    private double amount;
    private String type;
    
    //
    // Constructors
    //
    public FoodItem () { };
    
    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Set the value of name
     * @param newVar the new value of name
     */
    public void setName (String newVar) {
        name = newVar;
    }

    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName () {
        return name;
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

    /**
     * Set the value of amount
     * @param newVar the new value of amount
     */
    public void setAmount (double newVar) {
        amount = newVar;
    }

    /**
     * Get the value of amount
     * @return the value of amount
     */
    public double getAmount () {
        return amount;
    }

    /**
     * Set the value of type
     * @param newVar the new value of type
     */
    public void setType (String newVar) {
        type = newVar;
    }

    /**
     * Get the value of type
     * @return the value of type
     */
    public String getType () {
        return type;
    }

    //
    // Other methods
    //

}
