package com.example.fatcarbon;

import java.util.Date;


/**
 * Class FoodDiary
 */
public class FoodDiary extends Diary {

    //
    // Fields
    //

    //
    // Constructors
    //
    FoodDiary () {
        super();
    }
    //
    // Methods
    //


    //
    // Accessor methods
    //

    public void addItem(Meal item){
        entries.add(item);
    }


    //
    // Other methods
    //

    /**
     * @return       double
     * @param        date
     */
    public double getDailyCalories(Date date)
    {
        return 0; //TODO laskenta puuttuu
    }


    /**
     * @return       double
     * @param        startDate
     */
    public double getWeeklyAverage(Date startDate)
    {
        return 0; //TODO laskenta puuttuu
    }


}
