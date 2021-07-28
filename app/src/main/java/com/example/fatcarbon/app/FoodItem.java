package com.example.fatcarbon.app;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class FoodItem
 */
public class FoodItem implements Serializable {

    //
    // Fields
    //
    private int id;
    private String type;
    private String name;
    private int ediblePortion;
    private ArrayList<String[]> units;
    private double energy;
    private double energyKcal;
    private double fat;
    private double protein;
    private double carbohydrate;
    private double alcohol;
    private double organicAcids;
    private double sugarAlcohol;
    private double saturatedFat;
    private double fiber;
    private double sugar;
    private double salt;


    
    //
    // Constructors
    //

    public FoodItem() {
        units = new ArrayList<>();
    }

    FoodItem (int ID) {
        id=ID;
        units = new ArrayList<>();
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdiblePortion() {
        return ediblePortion;
    }

    public void setEdiblePortion(int ediblePortion) {
        this.ediblePortion = ediblePortion;
    }

    public ArrayList<String[]> getUnits() {
        return units;
    }

    public void addUnit(String[] unit) {
        this.units.add(unit);
    }


    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getEnergyKcal() {
        return energyKcal;
    }

    public void setEnergyKcal(double energyKcal) {
        this.energyKcal = energyKcal;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getOrganicAcids() {
        return organicAcids;
    }

    public void setOrganicAcids(double organicAcids) {
        this.organicAcids = organicAcids;
    }

    public double getSugarAlcohol() {
        return sugarAlcohol;
    }

    public void setSugarAlcohol(double sugarAlcohol) {
        this.sugarAlcohol = sugarAlcohol;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;

    }




}
