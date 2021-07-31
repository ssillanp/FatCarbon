package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Class Diary, hold all the diaryItems
 */
public class Diary implements Serializable {

    protected ArrayList<DiaryItem> entries; // list for entries

    Diary() {
        entries = new ArrayList();
    }


    public void addEntry(DiaryItem entry) {
        entries.add(entry);
    }

    public ArrayList<DiaryItem> getEntries() {
        return entries;
    }

    public ArrayList<DiaryItem> getWeightEntries() {
        /**
         * Method returns all weight entries from Diary
         * @return ArrayList<DiaryItem> weight entries
         */
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : this.entries) {
            if (item instanceof WeightDiaryItem) {
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getFoodEntries() {
        /**
         * Method returns all food entries from Diary
         * @return ArrayList<DiaryItem> food entries
         */
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : this.entries) {
            if (item instanceof FoodDiaryItem) {
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getFoodEntries(Date day) {
        /**
         * Method returns all food entries from Diary for set day
         * @param Date day
         * @return ArrayList<DiaryItem> entries
         */
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
        /**
         * Method returns all activity entries from Diary
         * @return ArrayList<DiaryItem> entries
         */
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : this.entries) {
            if (item instanceof ActivityDiaryItem) {
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getActivityEntries(Date day) {
        /**
         * Method returns all activity entries from Diary for set day
         * @param Date day
         * @return ArrayList<DiaryItem> entries
         */
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
        /**
         * Method returns the calories eaten for given day
         * @param Date day
         * @return double Calories eaten
         */
        double dci = 0;
        for (DiaryItem item:this.getFoodEntries(day)){
            dci += item.getAmount() / 100 * ((FoodItem) item.getItem()).getEnergyKcal();
        }
        return dci;
    }

    public double getDailyEnergyBurnt(Date day, double weight) {
        /**
         * Method returns the calories burnt in days activities for given day
         * @param Date day
         * @param double users weight
         * @return double calories burnt
         */
        double deb = 0;
        for (DiaryItem item:this.getActivityEntries(day)){
            deb += ((ActivityDiaryItem) item).getCalories(weight);
        }
        return deb;
    }


}
