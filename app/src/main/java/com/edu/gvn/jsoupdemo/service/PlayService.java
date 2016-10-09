package com.edu.gvn.jsoupdemo.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
        return binder;
    }

    @Override
    public void onCreate() {
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
        super.onDestroy();
    }

    public void playIndex(int index) {
        mPlayer.playIndex(index);
    }

    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    public void next() {
        mPlayer.next();
    }

    public void forward() {
        mPlayer.forward();
    }

    public void pause() {
        mPlayer.pause();
    }

    public void start() {
        mPlayer.start();
    }


    public void setVolume(int volume) {
        mPlayer.setVolume(volume);
    }

    public void setShuffle() {
        mPlayer.setShuffle();
    }

    public void setRepeat() {
        mPlayer.setRepeat();
    }

    public void setListAlbum(ArrayList<DetailAlbumModel> mData) {
        mPlayer.setDataAlbumList(mData);
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

    public int getCurrentPosition() {
        return mPlayer.getCurrentPostion();
    }

    public int getMaxDuration() {
        return mPlayer.getDuration();
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


    public int getIndexSong() {
        return mPlayer.getIndex();
    }

    public ArrayList<DetailAlbumModel> getListData() {
        return mPlayer.getListData();
    }


    public void setOnComplete(Player.MediaPlayerOnComplete onComplete) {
        mPlayer.setOnComplete(onComplete);
    }


    public class LocalBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }
}
