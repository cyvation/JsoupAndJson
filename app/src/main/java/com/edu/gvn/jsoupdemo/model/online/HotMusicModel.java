package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by HuuTho on 9/18/2016.
 */
public class HotMusicModel {

    private String view= "" ;

    private String a_href;
    private String img_src;
    private String desc;

    public HotMusicModel(String a_href, String img_src, String title) {
        this.a_href = a_href;
        this.img_src = img_src;
        this.desc = title;

    }

    public String getA_href() {
        return a_href;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return a_href + "\n" + img_src + "\n" + desc;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
