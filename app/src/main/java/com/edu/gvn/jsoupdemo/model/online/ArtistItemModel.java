package com.edu.gvn.jsoupdemo.model.online;

/**
 * Created by hnc on 11/10/2016.
 */

public class ArtistItemModel {
    private int typeView;
    public String dataId;
    public String dataName;
    public String hRef;
    public String imgSrc;

    public int getTypeView() {
        return typeView;
    }

    public void setTypeView(int typeView) {
        this.typeView = typeView;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String gethRef() {
        return hRef;
    }

    public void sethRef(String hRef) {
        this.hRef = hRef;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

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
