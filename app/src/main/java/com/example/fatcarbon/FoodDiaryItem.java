package com.example.fatcarbon;

public class FoodDiaryItem extends DiaryItem {

    private double portion;

    FoodDiaryItem(FoodItem item, double port){
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
