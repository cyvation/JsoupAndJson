package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.content.Context;
import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.common.TypeView;
import com.edu.gvn.jsoupdemo.model.online.ArtistSongsModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hnc on 19/10/2016.
 */

public class ArtistSongsAsync extends AsyncTask<String, Void, ArrayList<ArtistSongsModel>> {
    private Context mContext;
    private ArtistSongsAsyncCallBack callBack;

    public ArtistSongsAsync(Context context, ArtistSongsAsyncCallBack callBack) {
        this.mContext = context;
        this.callBack = callBack;
    }

    @Override
    protected ArrayList<ArtistSongsModel> doInBackground(String... params) {

        ArrayList<ArtistSongsModel> listSong = new ArrayList<>();
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(params[0]);
            urlBuilder.append("/bai-hat");
            urlBuilder.append("?&page=");
            urlBuilder.append(params[1]);

            Document root = Jsoup.connect(urlBuilder.toString()).get();
            Elements listItem = root.select("div.list-item.full-width > ul > li");
            int numberItem = listItem.size();
            for (int i = 0; i < numberItem; i++) {
                Element eItem = listItem.get(i);
                String dataId = eItem.attr("data-id");
                String dataCode = eItem.attr("data-code");

                String href = eItem.select("a").attr("href");
                String title = eItem.select("a").attr("title");

                ArtistSongsModel artistSongsModel = new ArtistSongsModel(dataId, dataCode, href, title);
                artistSongsModel.setView(TypeView.CONTENT);
                listSong.add(artistSongsModel);
            }
            ArtistSongsModel artistSongsModel = new ArtistSongsModel("", "", "", "");
            artistSongsModel.setView(TypeView.TITLE);
            listSong.add(artistSongsModel);

            return listSong;

        } catch (IOException e) {
            e.printStackTrace();
            cancel(true);
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ArtistSongsModel> list) {
        super.onPostExecute(list);
        if (list != null && list.size() != 0) {
            callBack.callBack(list);
        }
        cancel(true);
    }

    public interface ArtistSongsAsyncCallBack {
        public void callBack(ArrayList<ArtistSongsModel> list);
    }
}
