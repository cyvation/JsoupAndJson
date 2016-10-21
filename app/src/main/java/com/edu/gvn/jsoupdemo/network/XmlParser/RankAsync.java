package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.content.Context;
import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.common.SlipTitleMp3;
import com.edu.gvn.jsoupdemo.model.online.RankModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuuTho on 10/9/2016.
 */

public class RankAsync extends AsyncTask<String, Void, List<RankModel>> {
    private IRankDataCallBack rankCallback;
    private Context mContext ;
    public RankAsync(Context context , IRankDataCallBack callBack) {
        this.mContext = context;
        this.rankCallback = callBack;
    }

    @Override
    protected List<RankModel> doInBackground(String... params) {
        List<RankModel> listRank = new ArrayList<>();

        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(Mp3ZingBaseUrl.BASE_ZING_MP3);
            urlBuilder.append(params[0]);
            Document root = Jsoup.connect(urlBuilder.toString()).get();
            Elements tableBody = root.select("div.table-body > ul > li");

            int countItem = tableBody.size();
            for (int i = 0; i < countItem; i++) {
                String dataId = tableBody.get(i).attr("data-id");

                String order;
                Elements eOrder = tableBody.get(i).select("div.row-display.group.po-r > span.txt-rank");
                if (eOrder!=null && eOrder.size() !=0){
                     order = eOrder.get(0).ownText();
                }else {
                    order =String.valueOf(i);
                }


                String up;
                Elements sUp = tableBody.get(i).select("div.row-display.group.po-r > span.statistics.up");
                Elements sDown = tableBody.get(i).select("div.row-display.group.po-r > span.statistics.down");
                if (sUp != null && !sUp.text().equals("")) {
                    up = sUp.text();
                } else if (sDown != null && !sDown.text().equals("")) {
                    up = sDown.text();
                } else {
                    up = "";
                }

                Elements eItemTitle = tableBody.get(i).select("div.e-item > a");
                String title = eItemTitle.attr("title");
                String name = SlipTitleMp3.name(title);
                String artist = SlipTitleMp3.artist(title);

                Elements eItemImg = tableBody.get(i).select("div.e-item > a > img");
                String imgSrc = eItemImg.attr("src");

                RankModel rankModel = new RankModel();
                rankModel.dataId = dataId;
                rankModel.name = name;
                rankModel.artis = artist;
                rankModel.order = order;
                rankModel.up = up;
                rankModel.imgSrc = imgSrc;

                listRank.add(rankModel);
            }

            return listRank;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<RankModel> rankModels) {
        super.onPostExecute(rankModels);
        if (rankModels != null && rankModels.size() != 0 && rankCallback !=null) {
            rankCallback.callBack(rankModels);
        }
    }

    public interface IRankDataCallBack {
        void callBack(List<RankModel> lists);
    }
}
