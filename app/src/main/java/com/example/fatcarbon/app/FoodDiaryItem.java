package com.example.fatcarbon.app;

public class FoodDiaryItem extends DiaryItem {

    private double portion;

    public FoodDiaryItem(FoodItem item, double port){
        super(item);
        portion = port;

    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }
}
