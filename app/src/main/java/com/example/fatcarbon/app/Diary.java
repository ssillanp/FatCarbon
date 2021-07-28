package com.example.fatcarbon.app;

import java.io.Serializable;
import java.util.ArrayList;

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
    Diary () {
        entries = new ArrayList();
    };
    
    //
    // Methods
    //


    //
    // Accessor methods
    //


    /**
     * Get the value of Entries
     * @return the value of Entries
     */
    public ArrayList<DiaryItem> getEntries () {
        return entries;
    }

    public ArrayList<DiaryItem> getWeightEntries () {
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : entries){
            if (item instanceof WeightDiaryItem){
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getFoodEntries () {
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : entries){
            if (item instanceof FoodDiaryItem){
                results.add(item);
            }
        }
        return results;
    }

    public ArrayList<DiaryItem> getActivityEntries () {
        ArrayList<DiaryItem> results = new ArrayList<>();
        for (DiaryItem item : entries){
            if (item instanceof ActivityDiaryItem){
                results.add(item);
            }
        }
        return results;
    }

    //
    // Other methods
    //

    /**
     * @param        entry
     */
    public void addEntry(DiaryItem entry)
    {
        entries.add(entry);
    }


}
