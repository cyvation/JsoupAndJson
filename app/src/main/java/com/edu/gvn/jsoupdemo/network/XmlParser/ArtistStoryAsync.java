package com.edu.gvn.jsoupdemo.network.XmlParser;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;

import com.edu.gvn.jsoupdemo.common.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by hnc on 18/10/2016.
 */

public class ArtistStoryAsync extends AsyncTask<String, Void, String> {

    private Context mContext;
    private ArtistStoryCallback callback;

    public ArtistStoryAsync(Context context, ArtistStoryCallback callback) {
        this.mContext = context;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            Document root = Jsoup.connect(params[0]).get();

            Elements content = root.select("div.entry");
            String story = content.html();
            story = story.replaceAll("<li>", "<br>");
            story = Html.fromHtml(story).toString();
            LogUtils.e("huutho", story);

            return story;

        } catch (IOException e) {
            e.printStackTrace();
            cancel(true);
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null && !TextUtils.isEmpty(s)) {
            callback.callBack(s);
        }
        cancel(true);
    }

    public interface ArtistStoryCallback {
        public void callBack(String story);
    }
}
