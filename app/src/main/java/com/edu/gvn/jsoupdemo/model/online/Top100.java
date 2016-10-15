package com.edu.gvn.jsoupdemo.model.online;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HuuTho on 10/13/2016.
 */

public class Top100 {
@SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public List<ItemSong> data;

    @Override
    public String toString() {
        return "Top100{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public class ItemSong {
        @SerializedName("id")
        public String id;
        @SerializedName("code")
        public String code;
        @SerializedName("order")
        public String order;
        @SerializedName("name")
        public String name;
        @SerializedName("artist")
        public String artist;
        @SerializedName("thumb")
        public String thumb;
        @SerializedName("link")
        public String link;
        @SerializedName("video_link")
        public String video_link;
        @SerializedName("album_name")
        public String album_name;
        @SerializedName("album_link")
        public String album_link;
        @SerializedName("artist_list")
        List<ItemArtist> artist_list;

        @Override
        public String toString() {
            return "ItemSong{" +
                    "id='" + id + '\'' +
                    ", code='" + code + '\'' +
                    ", order='" + order + '\'' +
                    ", name='" + name + '\'' +
                    ", artist='" + artist + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", link='" + link + '\'' +
                    ", video_link='" + video_link + '\'' +
                    ", album_name='" + album_name + '\'' +
                    ", album_link='" + album_link + '\'' +
                    ", artist_list=" + artist_list +
                    '}';
        }
    }

    public class ItemArtist {
        @SerializedName("name")
        public String name;
        @SerializedName("link")
        public String link;

        @Override
        public String toString() {
            return "ItemArtist{" +
                    "name='" + name + '\'' +
                    ", link='" + link + '\'' +
                    '}';
        }
    }
}
