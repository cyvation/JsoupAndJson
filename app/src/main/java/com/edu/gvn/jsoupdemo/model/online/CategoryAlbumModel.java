package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by hnc on 05/10/2016.
 */

public class CategoryAlbumModel {
    private int view;
    private String title;
    private int image;
    private String url ;

    public CategoryAlbumModel(int view, String title, int image , String url) {
        this.view = view;
        this.url = url;
        this.title = title;
        this.image = image;
    }

    public int getView() {
        return view;
    }


    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }
}
