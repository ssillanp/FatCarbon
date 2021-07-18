package com.example.fatcarbon;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
    public ArrayList<FoodItem> parseFineliByKeyword(String Keyword) {
        URL url = null;
        try {
            url = new URL("https://fineli.fi/fineli/api/v1/foods?q=" + Keyword);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String json = getJSON(url);
        ArrayList<FoodItem> resultList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i=0; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                FoodItem FI = new FoodItem((int) object.get("id"));
                JSONObject type = (JSONObject) object.get("type");
                JSONObject desc = (JSONObject) type.get("description");
                FI.setType(desc.get("fi").toString());
                JSONObject name = (JSONObject) object.get("name");
                FI.setName(name.get("fi").toString());
                FI.setEdiblePortion(Integer.parseInt(object.get("ediblePortion").toString()));
                JSONArray units = (JSONArray) object.get("units");
                for (int t=1; t<units.length(); t++){
                    JSONObject unit = units.getJSONObject(t);
                    JSONObject descr = (JSONObject) unit.get("description");
                    FI.addUnit(descr.get("fi") + " "
                            +unit.get("mass" ) + "g");

                }
                FI.setEnergy(Double.parseDouble(object.get("energy").toString()));
                FI.setEnergyKcal(Double.parseDouble(object.get("energyKcal").toString()));
                FI.setFat(Double.parseDouble(object.get("fat").toString()));
                FI.setProtein(Double.parseDouble(object.get("protein").toString()));
                FI.setCarbohydrate(Double.parseDouble(object.get("carbohydrate").toString()));
                FI.setAlcohol(Double.parseDouble(object.get("alcohol").toString()));
                FI.setOrganicAcids(Double.parseDouble(object.get("organicAcids").toString()));
                FI.setSugarAlcohol(Double.parseDouble(object.get("sugarAlcohol").toString()));
                FI.setSaturatedFat(Double.parseDouble(object.get("saturatedFat").toString()));
                FI.setFiber(Double.parseDouble(object.get("fiber").toString()));
                FI.setSugar(Double.parseDouble(object.get("sugar").toString()));
                FI.setSalt(Double.parseDouble(object.get("salt").toString()));
                resultList.add(FI);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public String getJSON(URL Url) {
        String response = null;
        try {
            HttpsURLConnection conn;
            conn = (HttpsURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            response = sb.toString();
            in.close();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
