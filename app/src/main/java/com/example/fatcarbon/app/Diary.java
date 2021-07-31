package com.example.fatcarbon.app;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Class Diary
 */
public class Diary implements Serializable {

    //
    // Fields
    //

    protected ArrayList<DiaryItem> entries;

    //
    // Constructors
    //
    Diary() {
        entries = new ArrayList();
    }

    ;

    //
    // Methods
    //


    //
    // Accessor methods
    //


    /**
     * Get the value of Entries
     *
     * @return the value of Entries
     */
    public ArrayList<DiaryItem> getEntries() {
        return entries;
    }

    public ArrayList<DiaryItem> getWeightEntries() {
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : this.entries) {
            if (item instanceof WeightDiaryItem) {
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getFoodEntries() {
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : this.entries) {
            if (item instanceof FoodDiaryItem) {
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getFoodEntries(Date day) {
        ArrayList<DiaryItem> results = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", new Locale("fi_FI"));
        for (DiaryItem item : this.entries) {
            if (item instanceof FoodDiaryItem) {
                if (sdf.format(item.getDate()).equals(sdf.format(day))){ //TODO fix dates
                    results.add(item);
                }
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getActivityEntries() {
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : this.entries) {
            if (item instanceof ActivityDiaryItem) {
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getActivityEntries(Date day) {
        ArrayList<DiaryItem> results = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", new Locale("fi_FI"));
        for (DiaryItem item : this.entries) {
            if (item instanceof ActivityDiaryItem) {
                if (sdf.format(item.getDate()).equals(sdf.format(day))){
                    results.add(item);
                }
            }
        }
        return results;
    }

    public double getDailyCalIntake(Date day) {
        double dci = 0;
        for (DiaryItem item:this.getFoodEntries(day)){
            dci += item.getAmount() / 100 * ((FoodItem) item.getItem()).getEnergyKcal();
        }
        return dci;
    }

    public double getDailyEnergyBurnt(Date day, double weight) {
        double deb = 0;
        for (DiaryItem item:this.getActivityEntries(day)){
            deb += ((ActivityDiaryItem) item).getCalories(weight);
        }
        return deb;
    }


    //
    // Other methods
    //

    /**
     * @param entry
     */
    public void addEntry(DiaryItem entry) {
        entries.add(entry);
    }


}
