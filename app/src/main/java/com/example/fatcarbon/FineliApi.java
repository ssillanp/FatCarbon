package com.example.fatcarbon;


import android.util.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Class FineliApi
 */
public class FineliApi {

    //
    // Fields
    //

    private URL keywordSearchUrl;


    //
    // Constructors
    //
    public FineliApi() {
    }

    ;

    //
    // Methods
    //


    //
    // Accessor methods
    //

//    /**
//     * Set the value of url
//     * @param newVar the new value of url
//     */
//    public void setUrl (URL newVar) {
//        url = newVar;
//    }
//
//    /**
//     * Get the value of url
//     * @return the value of url
//     */
//    public URL getUrl () {
//        return url;
//    }
//
//    //
    // Other methods
    //

    /**
     * @param foodItemName
     */
    public void getItemData(String foodItemName) {
    }


    /**
     *
     */
    public void getAvailableItems() {
    }

    /*
    Method parses Food items from Fineli API Json string and creates FoodItem objects and add in list.
    Returns List of items.
     */
    public ArrayList<FoodItem> parseFineliData(String Keyword) {
        URL url = null;
        try {
            url = new URL("https://fineli.fi/fineli/api/v1/foods?q=" + Keyword);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JsonReader jr = new JsonReader(url);
        String json = jr.getResponse();

        ArrayList<FoodItem> resultList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                FoodItem foodItem = new FoodItem();

                foodItem.setId((int) object.get("id"));

                JSONObject type = (JSONObject) object.get("type");
                JSONObject desc = (JSONObject) type.get("description");
                foodItem.setType(desc.get("fi").toString());

                JSONObject name = (JSONObject) object.get("name");
                foodItem.setName(name.get("fi").toString());

                foodItem.setEdiblePortion(Integer.parseInt(object.get("ediblePortion").toString()));

                JSONArray units = (JSONArray) object.get("units");
                for (int t = 1; t < units.length(); t++) {
                    JSONObject unit = units.getJSONObject(t);
                    JSONObject descr = (JSONObject) unit.get("description");
                    foodItem.addUnit(new Pair<>(descr.get("fi").toString(),
                            Double.parseDouble(unit.get("mass").toString())));
                }

                foodItem.setEnergy(Double.parseDouble(object.get("energy").toString()));
                foodItem.setEnergyKcal(Double.parseDouble(object.get("energyKcal").toString()));
                foodItem.setFat(Double.parseDouble(object.get("fat").toString()));
                foodItem.setProtein(Double.parseDouble(object.get("protein").toString()));
                foodItem.setCarbohydrate(Double.parseDouble(object.get("carbohydrate").toString()));
                foodItem.setAlcohol(Double.parseDouble(object.get("alcohol").toString()));
                foodItem.setOrganicAcids(Double.parseDouble(object.get("organicAcids").toString()));
                foodItem.setSugarAlcohol(Double.parseDouble(object.get("sugarAlcohol").toString()));
                foodItem.setSaturatedFat(Double.parseDouble(object.get("saturatedFat").toString()));
                foodItem.setFiber(Double.parseDouble(object.get("fiber").toString()));
                foodItem.setSugar(Double.parseDouble(object.get("sugar").toString()));
                foodItem.setSalt(Double.parseDouble(object.get("salt").toString()));

                resultList.add(foodItem);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }


}
