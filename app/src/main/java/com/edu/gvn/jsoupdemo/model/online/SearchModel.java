package com.edu.gvn.jsoupdemo.model.online;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hnc on 14/09/2016.
 */
public class SearchModel {

    public static final int INDEX_ARTIST = 0;
    public static final int INDEX_ALBUM = 1;
    public static final int INDEX_VIDEO = 2;
    public static final int INDEX_SONG = 3;

    @SerializedName("result")
    public String result;

    @SerializedName("data")
    public List<MyParentObject> data;


    public class MyParentObject {
        @SerializedName("artist")
        public List<Artist> artist;
        @SerializedName("album")
        public List<Album> album;
        @SerializedName("video")
        public List<Video> video;
        @SerializedName("song")
        public List<Song> song;
    }


    public class Artist {

        @SerializedName("artist")
        public String mArtist;
        @SerializedName("thumb")
        public String mThumb;
        @SerializedName("name")
        public String mName;
        @SerializedName("id")
        public String mId;

        @Override
        public String toString() {
            return "----" + mArtist + "\n" + mThumb + "\n" + mName + "\n" + mId;
        }
    }

    public class Album {

        @SerializedName("artist")
        public String mArtist;
        @SerializedName("thumb")
        public String mThumb;
        @SerializedName("name")
        public String mName;
        @SerializedName("id")
        public String mId;

        @Override
        public String toString() {
            return "----" + mArtist + "\n" + mThumb + "\n" + mName + "\n" + mId;
        }
    }

    public class Video {

        @SerializedName("artist")
        public String mArtist;
        @SerializedName("thumb")
        public String mThumb;
        @SerializedName("name")
        public String mName;
        @SerializedName("id")
        public String mId;

        @Override
        public String toString() {
            return "----" + mArtist + "\n" + mThumb + "\n" + mName + "\n" + mId;
        }
    }

    public class Song {

        @SerializedName("artist")
        public String mArtist;
        @SerializedName("thumb")
        public String mThumb;
        @SerializedName("name")
        public String mName;
        @SerializedName("id")
        public String mId;

        @Override
        public String toString() {
            return "------------------------------------------------\n" + mArtist + "\n" + mThumb + "\n" + mName + "\n" + mId +"\n---------------------------------------------------------------------";
        }
    }


}
