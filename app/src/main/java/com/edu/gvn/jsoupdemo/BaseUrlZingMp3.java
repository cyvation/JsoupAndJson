package com.edu.gvn.jsoupdemo;

import com.edu.gvn.jsoupdemo.model.TypeSong;

import java.util.ArrayList;

/**
 * Created by hnc on 01/09/2016.
 */
public final class BaseUrlZingMp3 {
    public static final String BASE_ZING_MP3 = "http://mp3.zing.vn/";
    public static final String TOP_100 = "xhr/song?op=get-top&start=0&length=100&id=";
    public static final String BASE_IMAGE ="http://image.mp3.zdn.vn/";

    public static ArrayList<TypeSong> vietNam() {
        ArrayList<TypeSong> data = new ArrayList<>();

//        data.add(new TypeSong("Nhạc Trẻ","http://mp3.zing.vn/top100/Nhac-Tre/IWZ9Z088.html"));
//        data.add(new TypeSong("Trữ Tình","http://mp3.zing.vn/top100/Nhac-Tru-Tinh/IWZ9Z08B.html"));
//        data.add(new TypeSong("Rock Việt","http://mp3.zing.vn/top100/Rock-Viet/IWZ9Z08A.html"));
//        data.add(new TypeSong("Rap/Hip Hop Việt","http://mp3.zing.vn/top100/Rap-Viet/IWZ9Z089.html"));
//        data.add(new TypeSong("Dance Việt","http://mp3.zing.vn/top100/Nhac-Dance/IWZ9Z0CW.html"));
//        data.add(new TypeSong("Nhạc Cách Mạng","http://mp3.zing.vn/top100/Nhac-Dance/IWZ9Z0CW.html"));
//        data.add(new TypeSong("Nhạc Quê Hương","http://mp3.zing.vn/top100/Nhac-Que-Huong/IWZ9Z08D.html"));
//        data.add(new TypeSong("Nhạc Thiếu Nhi","http://mp3.zing.vn/top100/Nhac-Thieu-Nhi/IWZ9Z08F.html"));
//        data.add(new TypeSong("Nhạc Không Lời","http://mp3.zing.vn/top100/Nhac-Khong-Loi/IWZ9Z090.html"));
//        data.add(new TypeSong("Cải Lương","http://mp3.zing.vn/top100/Cai-Luong/IWZ9Z0C6.html"));
//        data.add(new TypeSong("Nhạc Trịnh","http://mp3.zing.vn/top100/Nhac-Trinh/IWZ9Z08E.html"));
//        data.add(new TypeSong("Nhạc Phim","http://mp3.zing.vn/top100/Nhac-Phim/IWZ9Z0BA.html"));


        data.add(new TypeSong("Nhạc Trẻ", "IWZ9Z088"));
        data.add(new TypeSong("Trữ Tình", "IWZ9Z08B"));
        data.add(new TypeSong("Rock Việt", "IWZ9Z08A"));
        data.add(new TypeSong("Rap/Hip Hop Việt", "IWZ9Z089"));
        data.add(new TypeSong("Dance Việt", "IWZ9Z0CW"));
        data.add(new TypeSong("Nhạc Cách Mạng", "IWZ9Z0CW"));
        data.add(new TypeSong("Nhạc Quê Hương", "IWZ9Z08D"));
        data.add(new TypeSong("Nhạc Thiếu Nhi", "IWZ9Z08F"));
        data.add(new TypeSong("Nhạc Không Lời", "IWZ9Z090"));
        data.add(new TypeSong("Cải Lương", "IWZ9Z0C6"));
        data.add(new TypeSong("Nhạc Trịnh", "IWZ9Z08E"));
        data.add(new TypeSong("Nhạc Phim", "IWZ9Z0BA"));

        return data;
    }

    public static ArrayList<TypeSong> auMy() {
        ArrayList<TypeSong> data = new ArrayList<>();

//        data.add(new TypeSong("Pop","http://mp3.zing.vn/top100/Pop/IWZ9Z097.html"));
//        data.add(new TypeSong("Rock","http://mp3.zing.vn/top100/Rock/IWZ9Z099.html"));
//        data.add(new TypeSong("Rap/ Hip Hop","http://mp3.zing.vn/top100/Rap-Hip-Hop/IWZ9Z09B.html"));
//        data.add(new TypeSong("Country","http://mp3.zing.vn/top100/Country/IWZ9Z096.html"));
//        data.add(new TypeSong("Electronic/ Dance","http://mp3.zing.vn/top100/Electronic-Dance/IWZ9Z09A.html"));
//        data.add(new TypeSong("R&B/ Soul","http://mp3.zing.vn/top100/R-B-Soul/IWZ9Z09D.html"));
//        data.add(new TypeSong("Nhạc Phim","http://mp3.zing.vn/top100/Nhac-Phim/IWZ9Z0EC.html"));
//        data.add(new TypeSong("Christian & Gospel","http://mp3.zing.vn/top100/Christian-Gospel/IWZ9Z0DE.html"));
//        data.add(new TypeSong("Indie","http://mp3.zing.vn/top100/Indie/IWZ9Z0CA.html"));
//        data.add(new TypeSong("Trance - House - Techno","http://mp3.zing.vn/top100/Trance-House-Techno/IWZ9Z0C7.html"));
//        data.add(new TypeSong("Blues - Jazz","http://mp3.zing.vn/top100/Blues-Jazz/IWZ9Z09C.html"));
//        data.add(new TypeSong("Folk","http://mp3.zing.vn/top100/Folk/IWZ9Z09E.html"));
//        data.add(new TypeSong("Audiphile","http://mp3.zing.vn/top100/Audiophile/IWZ9Z0EO.html"));


        data.add(new TypeSong("Pop", "IWZ9Z097"));
        data.add(new TypeSong("Rock", "IWZ9Z099"));
        data.add(new TypeSong("Rap/ Hip Hop", "IWZ9Z09B"));
        data.add(new TypeSong("Country", "IWZ9Z096"));
        data.add(new TypeSong("Electronic/ Dance", "IWZ9Z09A"));
        data.add(new TypeSong("R&B/ Soul", "IWZ9Z09D"));
        data.add(new TypeSong("Nhạc Phim", "IWZ9Z0EC"));
        data.add(new TypeSong("Christian & Gospel", "IWZ9Z0DE"));
        data.add(new TypeSong("Indie", "IWZ9Z0CA"));
        data.add(new TypeSong("Trance - House - Techno", "IWZ9Z0C7"));
        data.add(new TypeSong("Blues - Jazz", "IWZ9Z09C"));
        data.add(new TypeSong("Folk", "IWZ9Z09E"));
        data.add(new TypeSong("Audiphile", "IWZ9Z0EO"));

        return data;
    }

    public static ArrayList<TypeSong> chauA() {
        ArrayList<TypeSong> data = new ArrayList<>();

//        data.add(new TypeSong("Hàn Quốc","http://mp3.zing.vn/top100/Han-Quoc/IWZ9Z08W.html"));
//        data.add(new TypeSong("Nhật Bản","http://mp3.zing.vn/top100/Nhat-Ban/IWZ9Z08Z.html"));
//        data.add(new TypeSong("Hoa Ngữ","http://mp3.zing.vn/top100/Hoa-Ngu/IWZ9Z08U.html"));

        data.add(new TypeSong("Hàn Quốc", "IWZ9Z08W"));
        data.add(new TypeSong("Nhật Bản", "IWZ9Z08Z"));
        data.add(new TypeSong("Hoa Ngữ", "IWZ9Z08U"));

        return data;
    }

    public static ArrayList<TypeSong> hoaTau() {
        ArrayList<TypeSong> data = new ArrayList<>();

//        data.add(new TypeSong("Classical","http://mp3.zing.vn/top100/Classical/IWZ9Z0BI.html"));
//        data.add(new TypeSong("Piano","http://mp3.zing.vn/top100/Piano/IWZ9Z0B0.html"));
//        data.add(new TypeSong("Guitar","http://mp3.zing.vn/top100/Guitar/IWZ9Z0A9.html"));
//        data.add(new TypeSong("Violin","http://mp3.zing.vn/top100/Violin/IWZ9Z0BU.html"));
//        data.add(new TypeSong("Cello","http://mp3.zing.vn/top100/Cello/IWZ9Z0AD.html"));
//        data.add(new TypeSong("Saxophone","http://mp3.zing.vn/top100/Saxophone/IWZ9Z0B7.html"));
//        data.add(new TypeSong("New Age/World Music","http://mp3.zing.vn/top100/New-Age-World-Music/IWZ9Z0BO.html"));
//        data.add(new TypeSong("Nhạc Cụ Dân Tộc","http://mp3.zing.vn/top100/Nhac-Cu-Khac/IWZ9Z0E8.html"));


        data.add(new TypeSong("Classical", "IWZ9Z0BI"));
        data.add(new TypeSong("Piano", "IWZ9Z0B0"));
        data.add(new TypeSong("Guitar", "IWZ9Z0A9"));
        data.add(new TypeSong("Violin", "IWZ9Z0BU"));
        data.add(new TypeSong("Cello", "IWZ9Z0AD"));
        data.add(new TypeSong("Saxophone", "IWZ9Z0B7"));
        data.add(new TypeSong("New Age/World Music", "IWZ9Z0BO"));
        data.add(new TypeSong("Nhạc Cụ Dân Tộc", "IWZ9Z0E8"));

        return data;
    }

}
