package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class User, holds all the data of a user
 */
public class User implements Serializable {


    private final String username;
    private PasswordHasher passwordHasher;
    private int age;
    private Diary diary;
    private double height;
    private double startWeight;
    private double targetWeight;
    private double daily_calorie_base;
    private actLevel activityLevel;
    private sexes sex;


    public enum sexes {MALE, FEMALE}

    public enum actLevel {
        INACTIVE(1.3),
        OCCASIONAL(1.5),
        REGULAR(1.7),
        ACTIVE(1.9),
        PRO(2.2);

        private final double coeff;

        actLevel(double v) {
            coeff = v;
        }
    }

    //    default constructor
    User() {
        diary = new Diary();
    }

    // constructor with basic parameters
    public User(String user, PasswordHasher hasher) {
        username = user;
        passwordHasher = hasher;
        diary = new Diary();
    }

    public String getUsername() {
        return username;
    }

    public void setPasswordHasher(PasswordHasher newVar) {
        passwordHasher = newVar;
    }

    public PasswordHasher getPasswordHasher() {
        return passwordHasher;
    }

    public void setAge(int newVar) {
        age = newVar;
    }

    public int getAge() {
        return age;
    }

    public void setStartWeight(double startWeight) {
        /**
         * Method sets the start weight and adds entry in diary
         */
        this.startWeight = startWeight;
        WeightDiaryItem wdi = new WeightDiaryItem();
        wdi.setWeightValue(startWeight);
        wdi.setDateNow();
        this.diary.addEntry(wdi);
    }

    public double getStartWeight() {
        return this.startWeight;
    }

    public double getCurrentWeight() {
        /**
         * Method gets the last weight value from diary and returns it
         */
        ArrayList<DiaryItem> entries = this.getDiary().getWeightEntries();
        return ((WeightDiaryItem) entries.get(entries.size() - 1)).getWeightValue();

    }

    public void setActivityLevel(actLevel level) {
        activityLevel = level;
    }

    public int getActivityLevelIndex() {
        /**
         * method returns the activity level as an index
         */
        Map<actLevel, Integer> map = new HashMap();
        int i = 0;
        for (actLevel obj : actLevel.values()) {
            map.put(obj, i);
            i++;
        }
        return map.get(activityLevel);
    }

    public void setSex(sexes sx) {
        sex = sx;
    }

    public double calculateBaseCalories() {
        /**
         * Method calculates the base calorie consumption based on user data
         * ref https://en.wikipedia.org/wiki/Harris%E2%80%93Benedict_equation
         */
        if (sex == sexes.FEMALE) {
            return (447.593 + (9.247 * startWeight) + (3.098 * height) - (4.330 * age)) * activityLevel.coeff;
        } else {
            return (88.362 + (13.397 * startWeight) + (4.799 * height) - (5.677 * age)) * activityLevel.coeff;
        }
    }

     public void setHeight(double newVar) {
        height = newVar;
    }

    public double getHeight() {
        return height;
    }

    public Diary getDiary() {
        return diary;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public User validateUser(String user, String password) {
        /**
         * Method for validating the user with username and password
         */
        if (username.equals(user)) {
            if (passwordHasher.validatePassword(password)) {
                return this;
            }
        }
        return null;
    }


}
