package com.example.fatcarbon.app;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ProtocolException;
import java.net.URL;

public class JsonReader {

    private URL url;
    private String response;

    JsonReader() {}

    JsonReader(URL urlIn){
        url = urlIn;
        response = getJSON();
    }

    public String getJSON() {
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
