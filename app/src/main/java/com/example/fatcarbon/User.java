package com.example.fatcarbon;

import java.io.ObjectStreamException;
import java.io.Serializable;


/**
 * Class User
 */
public class User implements Serializable {

    //
    // Fields
    //

    private static final User user = new User();
    private String username;
    private PasswordHasher passwordHasher;
    private int age;
    private final Diary diary;
    private double height;
    private double startWeight;
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
    private User() {
        diary = new Diary();
    }

    public static User getInstance() {
        return user;
    }

    private Object readResolve()  throws ObjectStreamException {
        return user;
    }

    //
    // Methods
    //
    public void setUsername(String usernm){
        username = usernm;
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

    public void setWeight(double startWeight) {
        this.startWeight = startWeight;
        this.diary.addEntry(new DiaryItem(new WeightItem(this.startWeight)));
    }


    public void setActivityLevel(actLevel level) {
        activityLevel = level;
    }

    public void setSex(sexes sx) {
        sex = sx;
    }

    public double calculateBaseCalories() {
        if (sex == sexes.FEMALE) {
            return (447.593 + (9.247 * startWeight) + (3.098 * height) - (4.330 * age)) * activityLevel.coeff;
        } else {
            return (88.362 + (13.397 * startWeight) + (4.799 * height) - (85.677 * age)) * activityLevel.coeff;
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
