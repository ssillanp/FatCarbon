package com.example.fatcarbon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;


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

    public void readJSON() {
        String json = getJSON();
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    int id = row.getInt("id");
                    JSONObject type = row.getJSONObject("type");
                    String typ = type.getString("code");
                    String name = row.getString("name");
                    String port = row.getString("ediblePortion");
                    String ener = row.getString("energy");

                    System.out.println(id);
                    System.out.println("TYPE: " +  typ);
                    System.out.println("NAME " + name);
                    System.out.println("PORT " + port);
                    System.out.println("EENE " + ener);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getJSON() {
        String response = null;
        try {
            URL url = new URL("https://fineli.fi/fineli/api/v1/foods?q=omena");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
