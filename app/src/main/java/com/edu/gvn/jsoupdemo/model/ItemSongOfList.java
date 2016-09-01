package com.edu.gvn.jsoupdemo.model;

import com.edu.gvn.jsoupdemo.model.submodel.ArtistList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hnc on 01/09/2016.
 */
public class ItemSongOfList {
    @SerializedName("id")
    public String id ;
@SerializedName("name")
    public String name ;
    @SerializedName("artist")
    public String artist;
    @SerializedName("thumb")
    public String thumb;
    @SerializedName("link")
    public String link;
    @SerializedName("album_name")
    public String album_name ;
    @SerializedName("album_link")
    public String album_link;
    @SerializedName("artist_list")
    public List<ArtistList> artist_list ;
}
