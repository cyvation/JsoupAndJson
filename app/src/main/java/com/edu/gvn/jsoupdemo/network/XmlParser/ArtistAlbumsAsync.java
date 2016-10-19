package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.content.Context;
import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.common.LogUtils;
import com.edu.gvn.jsoupdemo.common.TypeView;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hnc on 19/10/2016.
 */

public class ArtistAlbumsAsync extends AsyncTask<String, Void, ArrayList<AlbumModel>> {
    private Context mContext;
    private IArtistAlbumAsyncCallback callback;

    public ArtistAlbumsAsync(Context context, IArtistAlbumAsyncCallback callback) {
        this.mContext = context;
        this.callback = callback;
    }


    @Override
    protected ArrayList<AlbumModel> doInBackground(String... params) {

        try {
            ArrayList<AlbumModel> listAlbum = new ArrayList<>();

            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(params[0]);
            urlBuilder.append("/album");
            urlBuilder.append("?&page=");
            urlBuilder.append(params[1]);

            LogUtils.e("huutho",urlBuilder.toString());

            Document root = Jsoup.connect(urlBuilder.toString()).get();

            Elements eItem = root.select("div.album-item.otr.des-inside.col-3");
            int itemCount = eItem.size();
            LogUtils.e("huutho", "size : " + itemCount);
            for (int i = 0; i < itemCount; i++) {

                Element e = eItem.get(i).select("a").first();

                String href = e.attr("href");
                String title = e.attr("title");
                String imgSrc = e.select("img").attr("src");

                AlbumModel albumModel = new AlbumModel(href, imgSrc, title);
                albumModel.setView(TypeView.CONTENT);
                listAlbum.add(albumModel);
            }
            AlbumModel albumModel = new AlbumModel("", "", "");
            albumModel.setView(TypeView.TITLE);
            listAlbum.add(albumModel);

            return listAlbum;

        } catch (IOException e) {
            e.printStackTrace();
            cancel(true);
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<AlbumModel> albumModels) {
        super.onPostExecute(albumModels);
        if (albumModels != null && albumModels.size() != 0) {
            callback.callBack(albumModels);
        }
        cancel(true);

    }

    public interface IArtistAlbumAsyncCallback {
        public void callBack(ArrayList<AlbumModel> albumModels);
    }
}
