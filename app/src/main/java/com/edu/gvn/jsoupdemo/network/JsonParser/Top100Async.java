package com.edu.gvn.jsoupdemo.network.JsonParser;

import android.os.AsyncTask;
import android.util.Log;

import com.edu.gvn.jsoupdemo.model.online.Top100;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Top100Async extends AsyncTask<String, Void, Top100> {

    @Override
    protected Top100 doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(200000);
            connection.setConnectTimeout(15000);
            int requestCode = connection.getResponseCode();

            if (requestCode == 200) {
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                Top100 top100 = new Gson().fromJson(streamReader, Top100.class);
                Log.i("huutho", "doInBackground: " + top100);
                return top100;
            }else {
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
