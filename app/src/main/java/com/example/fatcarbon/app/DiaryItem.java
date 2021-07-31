package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import java.io.Serializable;
import java.util.Date;

/**
 * Abstract class for all diary items
 */

public abstract class DiaryItem implements Serializable {

    private Date date;
    private Object item;
    private double amount;
    protected String unit;

    public DiaryItem() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date day) {
        date = day;
    }

    public void setDateNow(){
         date = new Date();
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object itm) {
        item = itm;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unt) {
        unit = unt;
    }
}
