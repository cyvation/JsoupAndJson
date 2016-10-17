package com.edu.gvn.jsoupdemo.common;

import android.util.Log;

/**
 * Created by hnc on 17/10/2016.
 */

public class LogUtils {
    public static void v(String tag, String msg){
            if (Config.DEBUG) {
                Log.v(tag,msg);
            }
    }

    public static void d(String tag, String msg){
        if (Config.DEBUG) {
            Log.d(tag,msg);
        }
    }

    public static void e(String tag, String msg){
        if (Config.DEBUG) {
            Log.e(tag,msg);
        }
    }

    public static void i(String tag, String msg){
        if (Config.DEBUG) {
            Log.i(tag,msg);
        }
    }

    public static void w(String tag, String msg){
        if (Config.DEBUG) {
            Log.w(tag,msg);
        }
    }
}
