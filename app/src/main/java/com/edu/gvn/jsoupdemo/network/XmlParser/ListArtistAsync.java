package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.content.Context;
import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.model.online.ArtistItemModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hnc on 11/10/2016.
 */

public class ListArtistAsync extends AsyncTask<String, Void, ArrayList<ArtistItemModel>> {

    private Context mContext;
    private IArtistCallBack callBack;

    public ListArtistAsync(Context context, IArtistCallBack callBack) {
        this.mContext = context;
        this.callBack = callBack;
    }


    @Override
    protected ArrayList<ArtistItemModel> doInBackground(String... params) {

        ArrayList<ArtistItemModel> data = new ArrayList<ArtistItemModel>();
        try {
            Document root = Jsoup.connect(params[0]).get();
            Elements tabPanel = root.select("div.tab-pane");
            Elements row = tabPanel.select("div.row");


            for (int i = 0; i < row.size(); i++) {
                Elements eRow = row.get(i).select("div.pone-of-five");

                for (int k = 0; k < eRow.size(); k++) {
                    Elements poneOfFive = eRow.get(k).select("div.pone-of-five");

                    String dataName = poneOfFive.attr("data-name");
                    String dataId = poneOfFive.attr("data-id");

                    Elements eHref = poneOfFive.select("div.item > a");
                    String href = eHref.attr("href");

                    Elements eImgSrc = poneOfFive.select("div.item > a > img");
                    String imgSrc = eImgSrc.attr("src");

                    ArtistItemModel artistItem = new ArtistItemModel();
                    artistItem.dataId = dataId;
                    artistItem.dataName = dataName;
                    artistItem.hRef = href;
                    artistItem.imgSrc = imgSrc;

                    data.add(artistItem);
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ArtistItemModel> artistItemModels) {
        super.onPostExecute(artistItemModels);
        if (artistItemModels != null && artistItemModels.size() != 0) {
            callBack.callBack(artistItemModels);
        }
    }

    public interface IArtistCallBack {
        public void callBack(ArrayList<ArtistItemModel> lists);
    }
}
