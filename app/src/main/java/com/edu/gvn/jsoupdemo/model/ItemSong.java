package com.edu.gvn.jsoupdemo.model;

import com.edu.gvn.jsoupdemo.model.submodel.ArtistList;
import com.edu.gvn.jsoupdemo.model.submodel.SourceList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hnc on 01/09/2016.
 */
public class ItemSong {
    @SerializedName("id")
    public String id ;
    @SerializedName("name")
    public String name ;
    @SerializedName("artist")
    public String artist ;
    @SerializedName("link")
    public String link ;
    @SerializedName("artist_list")
    public List<ArtistList> artist_list ;
    @SerializedName("cover")
    public String cover ;
    @SerializedName("source")
    public List<SourceList> source_list ;
    @SerializedName("lyric")
    public String lyric ;
    @SerializedName("mv_link")
    public String mv_link;

}
