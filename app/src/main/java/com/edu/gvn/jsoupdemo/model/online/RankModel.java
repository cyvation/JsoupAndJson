package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by HuuTho on 10/9/2016.
 */

public class RankModel {
    public String dataId;
    public String name;
    public String artis;
    public String order;
    public String up;
    public String imgSrc;

    @Override
    public String toString() {
        return "RankModel{" +
                "dataId='" + dataId + '\'' +
                ", name='" + name + '\'' +
                ", artis='" + artis + '\'' +
                ", order='" + order + '\'' +
                ", up='" + up + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }
}
