package com.edu.gvn.jsoupdemo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RemoteViews;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.common.Constants;
import com.edu.gvn.jsoupdemo.common.Player;
import com.edu.gvn.jsoupdemo.fragment.PlayerFragment;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;

import java.util.ArrayList;

/**
 * Created by hnc on 30/09/2016.
 */

public class PlayService extends Service {
    private static final String TAG = PlayService.class.getSimpleName();
    private Notification notification;
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
        createNotification();
        if (intent != null) {
            actionNotify(intent);
        }
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

    private void createNotification() {

        // remote view bind custom layout into notification
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.notifi_status);

        //show image albums;
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);

        Intent mainIntent = new Intent(this, PlayerFragment.class);
        mainIntent.setAction(Constants.ACTION.MAIN_ACTION);

        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent mainPendingIntent = PendingIntent
                .getService(this, Constants.SERVICE_REQUEST_CODE, mainIntent, Constants.SERVICE_FLAG);

        Intent prevIntent = new Intent(this, PlayService.class);
        prevIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent prevPendingIntent = PendingIntent
                .getService(this, Constants.SERVICE_REQUEST_CODE, prevIntent, Constants.SERVICE_FLAG);

        Intent playPauseIntent = new Intent(this, PlayService.class);
        playPauseIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent playPausePendingIntent = PendingIntent
                .getService(this, Constants.SERVICE_REQUEST_CODE, playPauseIntent, Constants.SERVICE_FLAG);

        Intent nextIntent = new Intent(this, PlayService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent nextPendingIntent = PendingIntent
                .getService(this, Constants.SERVICE_REQUEST_CODE, nextIntent, Constants.SERVICE_FLAG);

        views.setOnClickPendingIntent(R.id.status_bar_previous, prevPendingIntent);
        views.setOnClickPendingIntent(R.id.status_bar_play, playPausePendingIntent);
        views.setOnClickPendingIntent(R.id.status_bar_next, nextPendingIntent);
        views.setOnClickPendingIntent(R.id.status_bar_album_art, mainPendingIntent);


        views.setImageViewResource(R.id.status_bar_album_art, R.drawable.background_nav);

        views.setTextViewText(R.id.status_bar_track_name, "Play >>>");
        views.setTextViewText(R.id.status_bar_artist_name, "Wating ....");

        notification = new Notification.Builder(this).build();
        notification.contentView = views;
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.icon = R.mipmap.ic_launcher;
        notification.contentIntent = mainPendingIntent;

        startForeground(Constants.SERVICE_ID, notification);

    }

    private void actionNotify(Intent intent) {

        if (intent.getAction().equals(Constants.ACTION.MAIN_ACTION)) {
        } else if (intent.getAction().equals(Constants.ACTION.INIT_ACTION)) {

        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {

            if (getListData().size() != 0) {
                if (isPlaying()) {
                    pause();
                } else {
                    start();
                }
            }

        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            if (getListData().size() != 0)
                forward();
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            if (getListData().size() != 0)
                next();
        }
    }
}
