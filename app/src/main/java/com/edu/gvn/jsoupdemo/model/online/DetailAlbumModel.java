package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by hnc on 23/09/2016.
 */

public class DetailAlbumModel {
    private boolean nowPlay ;
    private String mOrder;
    private String mIDSong;
    private String mSongName;

    public DetailAlbumModel(String mOrder, String mIDSong, String mSongName) {
        this.mOrder = mOrder;
        this.mSongName = mSongName;
        this.mIDSong = mIDSong;
    }

    public boolean isNowPlay() {
        return nowPlay;
    }

    public void setNowPlay(boolean nowPlay) {
        this.nowPlay = nowPlay;
    }

    public String getmSongName() {
        return mSongName;
    }

    public String getmOrder() {
        return mOrder;
    }

    public String getIDSong() {
        return mIDSong;
    }

    @Override
    public String toString() {
        return "DetailAlbumModel{" +
                "mOrder='" + mOrder + '\'' +
                ", mIDSong='" + mIDSong + '\'' +
                ", mSongName='" + mSongName + '\'' +
                '}';
    }
}

