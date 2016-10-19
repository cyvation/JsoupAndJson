package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by hnc on 19/10/2016.
 */

public class ArtistSongsModel {
    private int view ;
    private String dataIdSong;
    private String dataCode;
    private String href;
    private String title;

    public ArtistSongsModel(String dataIdSong, String dataCode, String href, String title) {
        this.dataIdSong = dataIdSong;
        this.dataCode = dataCode;
        this.href = href;
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public String getDataIdSong() {
        return dataIdSong;
    }

    public String getDataCode() {
        return dataCode;
    }

    public String getHref() {
        return href;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
