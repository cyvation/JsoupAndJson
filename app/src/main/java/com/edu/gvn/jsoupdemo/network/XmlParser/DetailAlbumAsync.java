package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hnc on 23/09/2016.
 */

public class DetailAlbumAsync extends AsyncTask<String, Void, ArrayList<DetailAlbumModel>> {
    private static final String TAG = DetailAlbumAsync.class.getSimpleName();

    public interface DetailAlbumCallback {
        void callBack(ArrayList<DetailAlbumModel> model);
    }

    private DetailAlbumCallback detailAlbumCallback;


    public DetailAlbumAsync(DetailAlbumCallback detailAlbumCallback) {
        this.detailAlbumCallback = detailAlbumCallback;
    }

    @Override
    protected ArrayList<DetailAlbumModel> doInBackground(String... params) {

        ArrayList<DetailAlbumModel> mData = new ArrayList<>();
        String mUrl = params[0];
        try {
            Document rootDoc = Jsoup.connect(mUrl).get();
            Elements li = rootDoc.select("div.box-scroll > ul.playlist > li");

            for (Element itemSong : li) {
                String idSong = itemSong.attr("data-id");
                String order = itemSong.attr("data-order");

                Element subLi = itemSong.select("div.item-song  > h3 > a").first();
                String title = subLi.attr("title");
                int indexLastDash = title.indexOf("-");
                String nameSong = title.substring(0, indexLastDash - 1);

                mData.add(new DetailAlbumModel(order, idSong, nameSong));
            }
            return mData;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<DetailAlbumModel> detailAlbumModels) {
        super.onPostExecute(detailAlbumModels);
        if (detailAlbumModels != null && detailAlbumModels.size() != 0) {
            detailAlbumCallback.callBack(detailAlbumModels);
        }
    }
}
