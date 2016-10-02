package com.edu.gvn.jsoupdemo.model.online;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HuuTho on 10/2/2016.
 */

public class SongOnlModel {
    @SerializedName("msg")
    public String msg ;
    @SerializedName("data")
    public List<SongData> data ;


    public class SongData {
        @SerializedName("id")
        public String id ;
        @SerializedName("name")
        public String name ;
        @SerializedName("artist")
        public String artist ;
        @SerializedName("link")
        public String link ;
        @SerializedName("cover")
        public String cover ;
        @SerializedName("lyric")
        public String lyric ;
        @SerializedName("source_list")
        public List<String> source_list ;


        @Override
        public String toString() {
            return id + "\n" + name + "\n" + artist +"\n" + link+"\n" + cover ;
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
