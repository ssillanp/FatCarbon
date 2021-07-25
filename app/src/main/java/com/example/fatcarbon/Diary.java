package com.example.fatcarbon;

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
