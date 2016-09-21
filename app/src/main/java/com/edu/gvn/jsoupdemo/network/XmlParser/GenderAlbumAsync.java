package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.adapter.GenderAlbumAdapter;
import com.edu.gvn.jsoupdemo.model.online.HotMusicModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hnc on 21/09/2016.
 */

public class GenderAlbumAsync extends AsyncTask<String, Void, ArrayList<HotMusicModel>> {

    private static final String TAG = GenderAlbumAsync.class.getSimpleName();

    public interface GenderAlbumDataCallback {
        void callBack(ArrayList<HotMusicModel> data);
    }

    private GenderAlbumDataCallback getData;

    public GenderAlbumAsync(GenderAlbumDataCallback getData) {
        this.getData = getData;
    }

    @Override
    protected ArrayList<HotMusicModel> doInBackground(String... params) {
        ArrayList<HotMusicModel> mdata = new ArrayList<>();
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(params[0]);
        urlBuilder.append("?page=");
        urlBuilder.append(params[1]);
        try {
            Document root = Jsoup.connect(urlBuilder.toString()).get();
            Elements zContent = root.select("div.tab-pane");
            Elements row = zContent.select("div.row");
            for (Element e : row) {
                Elements poneOfFour = e.select("div.pone-of-four");
                for (int i = 0; i < poneOfFour.size(); i++) {
                    Elements item = poneOfFour.select("div.item");
                    Element a = item.get(i).getElementsByTag("a").first();
                    Element img = item.get(i).getElementsByTag("img").first();
                    String href = a.attr("href");
                    String title = a.attr("title");
                    String srcImage = img.attr("src");

                    HotMusicModel hotMusicModel = new HotMusicModel(href, srcImage, title);
                    hotMusicModel.setView(String.valueOf(GenderAlbumAdapter.TYPE_VIEW));
                    mdata.add(hotMusicModel);
                }
            }

            mdata.add(new HotMusicModel("","",""));

            return mdata;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<HotMusicModel> hotMusicModels) {
        super.onPostExecute(hotMusicModels);
        if (hotMusicModels!=null&&hotMusicModels.size()!=0){
            getData.callBack(hotMusicModels);
        }
    }
}
