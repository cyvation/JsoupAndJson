package com.edu.gvn.jsoupdemo.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.edu.gvn.jsoupdemo.common.Player;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;

import java.util.ArrayList;

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

    public void setListAlbum(ArrayList<DetailAlbumModel> mData) {
        mPlayer.setDataAlbumList(mData);
    }

    public void playIndex(int index) {
        mPlayer.playIndex(index);
    }

    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    public int currentPosition() {
        return mPlayer.getCurrentPostion();
    }

    public int maxDuration() {
        return mPlayer.getDuration();
    }

    public int getVolume() {
        return mPlayer.getVolumeLevel();
    }

    public boolean getShuffle() {
        return mPlayer.getShuffle();
    }

    public int getRepeat() {
        return mPlayer.getRepeat();
    }

    public String getNameSong() {
        return mPlayer.getNameSong();
    }

    public String getArtistSong() {
        return mPlayer.getArtistSong();
    }

    public String getCover() {
        return mPlayer.getLinkCover();
    }

    public String getDownload() {
        return mPlayer.getLinkDownload();
    }

    public class LocalBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }
}
