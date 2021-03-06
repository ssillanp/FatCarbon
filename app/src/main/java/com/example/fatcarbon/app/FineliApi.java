package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

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

    private URL url;
    private final String keyword;


    public FineliApi(String keywrd) {
        keyword = keywrd; //Keyword to use for search
        url = null;
        try {
            //build the URL with the keyword
            url = new URL("https://fineli.fi/fineli/api/v1/foods?q=" + keyword);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<FoodItem> parseFineliData() {
        /**
         *Method parses Food items from Fineli API Json string and creates FoodItem objects and add in list.
         *@return ArrayList<FoodItem> list of items
         */

        // get the response from Json reader
        JsonReader jr = new JsonReader(url);
        String json = jr.getResponse();

        //parse the food items  TODO change to parse only selected item from food search fragment
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
                    foodItem.addUnit(new String[]{descr.get("fi").toString(),
                            (unit.get("mass").toString())});
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
