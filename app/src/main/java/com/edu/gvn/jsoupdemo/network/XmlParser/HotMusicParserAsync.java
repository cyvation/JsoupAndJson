package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.os.AsyncTask;
import android.util.Log;

import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.model.online.HotMusicModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HuuTho on 9/18/2016.
 */
public class HotMusicParserAsync extends AsyncTask<String, Void, ArrayList<HotMusicModel>> {

    private static final String TAG = HotMusicParserAsync.class.getSimpleName();

    public interface GetDataCallback {
        void getData(ArrayList<HotMusicModel> data);
    }

    private GetDataCallback callback;

    public HotMusicParserAsync(GetDataCallback callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<HotMusicModel> doInBackground(String... params) {
        ArrayList<HotMusicModel> data = new ArrayList<>();

        StringBuilder url = new StringBuilder();
        url.append(Mp3ZingBaseUrl.BASE_ZING_MP3).append("/").append(params[0]);


        Document document;
        try {
            document = Jsoup.connect(url.toString()).get();


            Elements selectionMT0 = document.select("div.section.mt0");
            Log.i(TAG, "Selection MT 0 : " + selectionMT0.size());

            for (int i = 0; i < selectionMT0.size(); i++) {

                Elements row = selectionMT0.get(i).select("div.row");
                Log.i(TAG, "Row: " + row.size());


                for (int j = 0; j < row.size(); j++) {

                    Elements album_item = row.get(j).select("div.album-item.des-inside.col-3.otr");


                    for (int k = 0; k < album_item.size(); k++) {

                        Element a = album_item.get(k).getElementsByTag("a").last();
                        Element img = album_item.get(k).getElementsByTag("img").last();

                        String img_src = img.attr("src");
                        String a_href = a.attr("href");
                        String title = a.attr("title");

                        HotMusicModel hotMusicModel = new HotMusicModel(a_href, img_src, title);
                        data.add(hotMusicModel);

                        Log.i(TAG, "doInBackground: " + title);
                    }
                }

            }
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<HotMusicModel> hotMusicModels) {
        super.onPostExecute(hotMusicModels);
        if (hotMusicModels.size() != 0) {
            callback.getData(hotMusicModels);
            Log.i(TAG, "onPostExecute: " + hotMusicModels.size());
        }
    }
}
