package com.daniilmuraveyko.edda.utilites;

/**
 * Created by danichmur on 10.10.17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {

    public static String getJSONFromUrl(String url) {

        final String TAG = "JsonParser.java";
        InputStream is = null;
        JSONObject jObj = null;
        String json = "";
        HttpURLConnection httpClient = null;

        // make HTTP request
        try {
            httpClient = (HttpURLConnection) new URL(url).openConnection();
            httpClient.setReadTimeout(10000);
            httpClient.setConnectTimeout(15000);
            httpClient.setRequestMethod("GET");
            httpClient.connect();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            is = httpClient.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();

        } catch (Exception e) {
            Log.e(TAG, "Error converting result " + e.toString());
        }
        // return JSON String
        return json;
    }
}