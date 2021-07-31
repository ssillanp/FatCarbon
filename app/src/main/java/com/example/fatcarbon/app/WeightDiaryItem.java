package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

/**
 * Class for weightings
 */
public class WeightDiaryItem extends DiaryItem {

    private double weightValue;

    public WeightDiaryItem() {
        unit = "kg";
    }

    public void setWeightValue(double newVar) {
        weightValue = newVar;
    }

    public double getWeightValue() {
        return weightValue;
    }

}
