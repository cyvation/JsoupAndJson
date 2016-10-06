package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by hnc on 05/10/2016.
 */

public class CategoryAlbumModel {
    private int viewType;
    private String title;
    private int image;
    private String url ;

    public CategoryAlbumModel(int view, String title, int image , String url) {
        this.viewType = view;
        this.url = url;
        this.title = title;
        this.image = image;
    }

    public int getViewType() {
        return viewType;
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
