package com.example.fatcarbon.app;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
        Date day = Calendar.getInstance().getTime();
        day.setHours(0);
        day.setMinutes(0);
        day.setSeconds(0);
        date = day;
        System.out.println(date);
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
