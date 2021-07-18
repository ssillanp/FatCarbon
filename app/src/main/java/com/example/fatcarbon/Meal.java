package com.example.fatcarbon;

import java.util.*;


/**
 * Class Meal
 */
public class Meal {

    //
    // Fields
    //
    private enum MealTypes {
        BREAKFAST, LUNCH, DINNER, SNACK
    }

    private MealTypes type;
    private Date date;
    private ArrayList<FoodItem> foodItems;
    private double total_calories;

    //
    // Constructors
    //
    public Meal() {
    }

    ;

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Set the value of name
     *
     * @param newVar the new value of name
     */
    public void setType(MealTypes newVar) {
        type = newVar;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public MealTypes getType() {
        return type;
    }

    /**
     * Set the value of date
     *
     * @param newVar the new value of date
     */
    public void setDate(Date newVar) {
        date = newVar;
    }

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the value of foodItem
     *
     * @param newVar the new value of foodItem
     */
    public void setFoodItems(ArrayList<FoodItem> newVar) {
        foodItems = newVar;
    }

    /**
     * Get the value of foodItem
     *
     * @return the value of foodItem
     */
    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    /**
     * Set the value of total_calories
     *
     * @param newVar the new value of total_calories
     */
    public void setTotal_calories(double newVar) {
        total_calories = newVar;
    }

    /**
     * Get the value of total_calories
     *
     * @return the value of total_calories
     */
    public double getTotal_calories() {
        return total_calories;
    }

    //
    // Other methods
    //

    /**
     * @param foodItem
     */
    public void addItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }


    /**
     * @return double
     */
    public double calculateCalories() {
        return 0;
    } //TODO metodi puuttuu


}
