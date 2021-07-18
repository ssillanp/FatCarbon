package com.example.fatcarbon;

import java.io.Serializable;
import java.util.*;


/**
 * Class User
 */
public class User implements Serializable {

    //
    // Fields
    //

    private String username;
    private PasswordHasher passwordHasher;
    private int age;
    private WeightDiary weightDiary;
    private FoodDiary foodDiary;
    private ActivityDiary activityDiary;
    private double height;
    private String city;
    private boolean smoking;
    
    //
    // Constructors
    //
    public User (String user, PasswordHasher hasher) {
        username = user;
        passwordHasher = hasher;
    }

    //
    // Methods
    //
    
     /**
     * Get the value of username
     * @return the value of username
     */
    public String getUsername () {
        return username;
    }

    /**
     * Set the value of passwordHasher
     * @param newVar the new value of passwordHasher
     */
    public void setPasswordHasher (PasswordHasher newVar) {
        passwordHasher = newVar;
    }

    /**
     * Get the value of passwordHasher
     * @return the value of passwordHasher
     */
    public PasswordHasher getPasswordHasher () {
        return passwordHasher;
    }

    /**
     * Set the value of age
     * @param newVar the new value of age
     */
    public void setAge (int newVar) {
        age = newVar;
    }

    /**
     * Get the value of age
     * @return the value of age
     */
    public int getAge () {
        return age;
    }

    /**
     * Set the value of weightDiary
     * @param newVar the new value of weightDiary
     */
    public void setWeightDiary (WeightDiary newVar) {
        weightDiary = newVar;
    }

    /**
     * Get the value of weightDiary
     * @return the value of weightDiary
     */
    public WeightDiary getWeightDiary () {
        return weightDiary;
    }

    /**
     * Set the value of foodDiary
     * @param newVar the new value of foodDiary
     */
    public void setFoodDiary (FoodDiary newVar) {
        foodDiary = newVar;
    }

    /**
     * Get the value of foodDiary
     * @return the value of foodDiary
     */
    public FoodDiary getFoodDiary () {
        return foodDiary;
    }

    /**
     * Set the value of activityDiary
     * @param newVar the new value of activityDiary
     */
    public void setActivityDiary (ActivityDiary newVar) {
        activityDiary = newVar;
    }

    /**
     * Get the value of activityDiary
     * @return the value of activityDiary
     */
    public ActivityDiary getActivityDiary () {
        return activityDiary;
    }

    /**
     * Set the value of height
     * @param newVar the new value of height
     */
    public void setHeight (double newVar) {
        height = newVar;
    }

    /**
     * Get the value of height
     * @return the value of height
     */
    public double getHeight () {
        return height;
    }

    /**
     * Set the value of city
     * @param newVar the new value of city
     */
    public void setCity (String newVar) {
        city = newVar;
    }

    /**
     * Get the value of city
     * @return the value of city
     */
    public String getCity () {
        return city;
    }

    /**
     * Set the value of smoking
     * @param newVar the new value of smoking
     */
    public void setSmoking (boolean newVar) {
        smoking = newVar;
    }

    /**
     * Get the value of smoking
     * @return the value of smoking
     */
    public boolean getSmoking () {
        return smoking;
    }

    //
    // Other methods
    //

    /**
     * @return       User
     * @param        user
     * @param        password
     */

    public User validateUser(String user, String password)
    {
        if (username.equals(user)){
            if (passwordHasher.validatePassword(password)){
                System.out.println("User " + username + " validated!");
                return this;
            }
        }
        return null;
    }


}
