package com.example.fatcarbon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Diary
 */
abstract public class Diary implements Serializable {

    //
    // Fields
    //

    private ArrayList<Object> entries;
    
    //
    // Constructors
    //
    public Diary () { };
    
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
    public ArrayList<Object> getEntries () {
        return entries;
    }

    //
    // Other methods
    //

    /**
     * @param        entry
     */
    public void addEntry(Object entry)
    {
        entries.add(entry);
    }


}
