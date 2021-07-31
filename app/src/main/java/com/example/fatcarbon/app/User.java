package com.example.fatcarbon.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class User
 */
public class User implements Serializable {

    //
    // Fields
    //

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

    //
    // Constructors
    //
    User() {
        username = "test";
        diary = new Diary();
    }

    public User(String user, PasswordHasher hasher) {
        username = user;
        passwordHasher = hasher;
        diary = new Diary();
    }

    //
    // Methods
    //

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of passwordHasher
     *
     * @param newVar the new value of passwordHasher
     */
    public void setPasswordHasher(PasswordHasher newVar) {
        passwordHasher = newVar;
    }

    /**
     * Get the value of passwordHasher
     *
     * @return the value of passwordHasher
     */
    public PasswordHasher getPasswordHasher() {
        return passwordHasher;
    }

    /**
     * Set the value of age
     *
     * @param newVar the new value of age
     */
    public void setAge(int newVar) {
        age = newVar;
    }

    /**
     * Get the value of age
     *
     * @return the value of age
     */
    public int getAge() {
        return age;
    }

    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
        WeightDiaryItem wdi = new WeightDiaryItem();
        wdi.setWeightValue(startWeight);
        wdi.setDateNow();
        this.diary.addEntry(wdi);
    }

    public double getStartWeight(){
        return this.startWeight;
    }

    public double getCurrentWeight(){
        ArrayList<DiaryItem> entries = this.getDiary().getWeightEntries();
        return ((WeightDiaryItem) entries.get(entries.size() -1)).getWeightValue();

    }


    public void setActivityLevel(actLevel level) {
        activityLevel = level;
    }

    public int getActivityLevelIndex() {
        Map<actLevel, Integer> map = new HashMap();
        int i=0;
        for (actLevel obj:actLevel.values()){
            map.put(obj, i);
            i++;
        }
        return map.get(activityLevel);
    }

    public void setSex(sexes sx) {
        sex = sx;
    }

    public double calculateBaseCalories() {
        if (sex == sexes.FEMALE) {
            return (447.593 + (9.247 * startWeight) + (3.098 * height) - (4.330 * age)) * activityLevel.coeff;
        } else {
            return (88.362 + (13.397 * startWeight) + (4.799 * height) - (5.677 * age)) * activityLevel.coeff;
        }
    }

    /**
     * Set the value of height
     *
     * @param newVar the new value of height
     */
    public void setHeight(double newVar) {
        height = newVar;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
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



    //
    // Other methods
    //

    /**
     * @param user
     * @param password
     * @return User
     */

    public User validateUser(String user, String password) {
        if (username.equals(user)) {
            if (passwordHasher.validatePassword(password)) {
                return this;
            }
        }
        return null;
    }


}
