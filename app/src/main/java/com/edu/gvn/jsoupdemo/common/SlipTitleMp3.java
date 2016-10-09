package com.edu.gvn.jsoupdemo.common;

/**
 * Created by HuuTho on 10/9/2016.
 */

public class SlipTitleMp3 {
    public static String name(String title) {
        int indexDash = title.lastIndexOf("-");
        return title.substring(7, indexDash - 1);
    }

    public static String artist(String title) {
        int indexDash = title.lastIndexOf("-");
        return title.substring(indexDash + 1);
    }
}
