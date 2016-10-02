package com.edu.gvn.jsoupdemo.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.edu.gvn.jsoupdemo.common.Player;

/**
 * Created by hnc on 30/09/2016.
 */

public class PlayService extends Service {
    private static final String TAG = PlayService.class.getSimpleName();
    private LocalBinder binder = new LocalBinder();
    private Player mPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: binded");
        play();
        return binder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: created");
        super.onCreate();
        mPlayer = new Player(this); 
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(startId, new Notification());
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: destroyed");
        super.onDestroy();
    }


    public class LocalBinder extends Binder {
      public PlayService getService(){
          return PlayService.this ;
      }
    }

    public void play(){
        mPlayer.setDataSource("http://mp3.zing.vn/embed/song/ZW7UFI6I");
    }
}
