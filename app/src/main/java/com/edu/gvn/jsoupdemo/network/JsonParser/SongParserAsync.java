package com.edu.gvn.jsoupdemo.network.JsonParser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.model.online.SongOnlModel;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by HuuTho on 10/2/2016.
 */

public class SongParserAsync extends AsyncTask<String, Void, SongOnlModel> {

    private Context context;
    private IDataCallBack dataCallBack;

    public SongParserAsync(Context context, IDataCallBack callBack) {
        this.context = context;
        this.dataCallBack = callBack;
    }

    @Override
    protected SongOnlModel doInBackground(String... strings) {
        HttpURLConnection httpUrlConn;
        InputStreamReader streamReader;
        try {
            String simpleUrl = strings[0];

            StringBuilder buildUrl = new StringBuilder();
            buildUrl.append(Mp3ZingBaseUrl.BASE_ZING_MP3);
            buildUrl.append(Mp3ZingBaseUrl.LINK_SONG);
            buildUrl.append(simpleUrl);

            Log.i(TAG, "doInBackground: " + buildUrl.toString());
            Document root = Jsoup.connect(buildUrl.toString()).get();
            Element e = root.getElementById("html5player");
            String songDataURL = e.attr("data-xml");

            Log.i(TAG, "doInBackground: " + songDataURL);
            URL mUrl = new URL(songDataURL);
            httpUrlConn = (HttpURLConnection) mUrl.openConnection();
            httpUrlConn.setReadTimeout(15000);

            streamReader = new InputStreamReader(httpUrlConn.getInputStream());

            return new Gson().fromJson(streamReader, SongOnlModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(SongOnlModel songOnlModel) {
        super.onPostExecute(songOnlModel);
        if (songOnlModel != null)
            dataCallBack.callBack(songOnlModel);

        this.cancel(true);
    }

    public interface IDataCallBack {
        void callBack(SongOnlModel onlModel);
    }

}
