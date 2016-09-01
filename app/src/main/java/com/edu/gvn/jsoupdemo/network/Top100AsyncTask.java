package com.edu.gvn.jsoupdemo.network;

import android.content.Context;
import android.os.AsyncTask;

import com.edu.gvn.jsoupdemo.BaseUrlZingMp3;
import com.edu.gvn.jsoupdemo.model.TypeSong;

import java.util.ArrayList;

/**
 * Created by hnc on 01/09/2016.
 */
public class Top100AsyncTask extends AsyncTask<String, Void, Void> {
    private static final String TAG = Top100AsyncTask.class.getSimpleName();
    private Context context;

    public Top100AsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {

            int i = 0;

            String typeTop = params[0];
            ArrayList<TypeSong> arrSubTypeSong = new ArrayList<>();
            arrSubTypeSong.clear();
            switch (typeTop) {
                case "Việt Nam":
                    arrSubTypeSong.addAll(BaseUrlZingMp3.vietNam());
                    break;
                case "Âu Mỹ":
                    arrSubTypeSong.addAll(BaseUrlZingMp3.auMy());
                    break;
                case "Châu Á":
                    arrSubTypeSong.addAll(BaseUrlZingMp3.chauA());
                    break;
                case "Hòa Tấu":
                    arrSubTypeSong.addAll(BaseUrlZingMp3.hoaTau());
                    break;
            }





        } finally {

        }


        return null;
    }
}
