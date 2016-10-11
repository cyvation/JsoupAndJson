package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by hnc on 11/10/2016.
 */

public class ArtistItemModel  {
    public String  dataId ;
    public String dataName;
    public String hRef ;
    public String imgSrc;

    @Override
    public String toString() {
        return "ArtistItemModel{" +
                "dataId='" + dataId + '\'' +
                ", dataName='" + dataName + '\'' +
                ", hRef='" + hRef + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }
}
