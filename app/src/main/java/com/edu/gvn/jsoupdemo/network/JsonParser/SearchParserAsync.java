package com.edu.gvn.jsoupdemo.network.JsonParser;

import android.os.AsyncTask;
import android.util.Log;

import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.model.online.SearchModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by hnc on 14/09/2016.
 */
public class SearchParserAsync extends AsyncTask<String, Void, SearchModel> {

    private static final String TAG = SearchParserAsync.class.getSimpleName();
    private SearchDataCallBack callBack;

    public interface SearchDataCallBack {
        void callData(SearchModel model);
    }

    public SearchParserAsync(SearchDataCallBack searchDataCallBack) {
        this.callBack = searchDataCallBack;
    }


    @Override
    protected SearchModel doInBackground(String... params) {
        HttpURLConnection connection = null;

        try {
            String query = params[0];
            query = URLEncoder.encode(query, "UTF-8");
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(Mp3ZingBaseUrl.LINK_SEARCH).append(query);
            URL mUrl = new URL(urlBuilder.toString());

            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(15000);

            if (connection.getResponseCode() == 200) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String reponse;
                StringBuilder reponseBuilder = new StringBuilder();
                while ((reponse = reader.readLine()) != null) {
                    reponseBuilder.append(reponse);
                }

                int firstJsonObjectIndex = reponseBuilder.indexOf("{");
                int lastJsonObjectIndex = reponseBuilder.lastIndexOf(")");

                String jsonReponseStandar = reponseBuilder.substring(firstJsonObjectIndex, lastJsonObjectIndex);

                SearchModel searchModel = new Gson().fromJson(jsonReponseStandar, SearchModel.class);
                for (int i = 0; i < searchModel.data.get(SearchModel.INDEX_SONG).song.size(); i++) {
                    String str = searchModel.data.get(SearchModel.INDEX_SONG).song.get(i).toString();
                }
                return searchModel;
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(SearchModel searchModel) {
        super.onPostExecute(searchModel);
        if (searchModel!=null){
            callBack.callData(searchModel);
        }

        this.cancel(true);
    }
}
