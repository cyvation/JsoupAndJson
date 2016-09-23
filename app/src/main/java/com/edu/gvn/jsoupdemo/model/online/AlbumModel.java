package com.edu.gvn.jsoupdemo.model.online;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HuuTho on 9/18/2016.
 */
public class AlbumModel implements Parcelable {

    private String view = "";
    private String href;
    private String img_src;
    private String title;

    public AlbumModel(String href, String img_src, String title) {
        this.href = href;
        this.img_src = img_src;
        this.title = title;

    }

    protected AlbumModel(Parcel in) {
        view = in.readString();
        href = in.readString();
        img_src = in.readString();
        title = in.readString();
    }

    public static final Creator<AlbumModel> CREATOR = new Creator<AlbumModel>() {
        @Override
        public AlbumModel createFromParcel(Parcel in) {
            return new AlbumModel(in);
        }

        @Override
        public AlbumModel[] newArray(int size) {
            return new AlbumModel[size];
        }
    };

    public String getHref() {
        return href;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return href + "\n" + img_src + "\n" + title;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(view);
        dest.writeString(href);
        dest.writeString(img_src);
        dest.writeString(title);
    }
}
