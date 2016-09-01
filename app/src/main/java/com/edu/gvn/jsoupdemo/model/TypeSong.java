package com.edu.gvn.jsoupdemo.model;

/**
 * Created by hnc on 01/09/2016.
 */
public class TypeSong {
    private String title ;
    private String url ;

    public TypeSong(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
