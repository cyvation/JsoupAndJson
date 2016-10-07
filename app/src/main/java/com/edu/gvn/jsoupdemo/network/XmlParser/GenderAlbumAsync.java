package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.common.TypeView;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hnc on 21/09/2016.
 */

public class GenderAlbumAsync extends AsyncTask<String, Void, ArrayList<AlbumModel>> {

    private static final String TAG = GenderAlbumAsync.class.getSimpleName();

    public interface GenderAlbumDataCallback {
        void callBack(ArrayList<AlbumModel> data);
    }

    private GenderAlbumDataCallback getData;

    public GenderAlbumAsync(GenderAlbumDataCallback getData) {
        this.getData = getData;
    }

    @Override
    protected ArrayList<AlbumModel> doInBackground(String... params) {
        ArrayList<AlbumModel> mdata = new ArrayList<>();

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

                    AlbumModel albumModel = new AlbumModel(href, srcImage, title);
                    albumModel.setView(TypeView.CONTENT);
                    mdata.add(albumModel);
                }
            }

            // Thêm 1 phần tử với view là loadmore
            AlbumModel model = new AlbumModel("","","");
            model.setView(TypeView.TITLE);
            mdata.add(model);

            return mdata;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<AlbumModel> albumModels) {
        super.onPostExecute(albumModels);
        if (albumModels !=null&& albumModels.size()!=0){
            getData.callBack(albumModels);
        }
        this.cancel(true);
    }
}
