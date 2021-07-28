package com.example.fatcarbon.app;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DiaryItem implements Serializable {

    private Date date;
    private Object item;

    public DiaryItem() {}

    public DiaryItem(Object itm){
        date = Calendar.getInstance().getTime();
        item = itm;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
