package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.os.AsyncTask;
import android.util.Log;

import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HuuTho on 9/18/2016.
 */
public class HotMusicParserAsync extends AsyncTask<String, Void, ArrayList<AlbumModel>> {

    private static final String TAG = HotMusicParserAsync.class.getSimpleName();

    public interface GetDataCallback {
        void getData(ArrayList<AlbumModel> data);
    }

    private GetDataCallback callback;

    public HotMusicParserAsync(GetDataCallback callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<AlbumModel> doInBackground(String... params) {
        ArrayList<AlbumModel> data = new ArrayList<>();

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

                        AlbumModel albumModel = new AlbumModel(a_href, img_src, title);
                        data.add(albumModel);

                        Log.i(TAG, "doInBackground: " + title);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<AlbumModel> albumModels) {
        super.onPostExecute(albumModels);
        if (albumModels.size() != 0) {
            callback.getData(albumModels);
            Log.i(TAG, "onPostExecute: " + albumModels.size());
        }
        this.cancel(true);
    }
}
