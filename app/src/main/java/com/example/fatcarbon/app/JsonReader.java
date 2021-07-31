package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ProtocolException;
import java.net.URL;

/**
 * class for reading a Json response from given REST API url
 */

public class JsonReader {

    private URL url;
    private String response;

    JsonReader() {}

    JsonReader(URL urlIn){
        url = urlIn;
        response = getJSON();
    }

    public String getJSON() {
        /**
         * Method reads the response from URL
         * @return String JSON
         */
        String response = null;
        try {
            HttpsURLConnection conn;
            conn = (HttpsURLConnection) url.openConnection();
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

    public String getResponse() {
        return response;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
