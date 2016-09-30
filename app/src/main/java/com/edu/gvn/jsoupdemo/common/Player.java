package com.edu.gvn.jsoupdemo.common;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hnc on 30/09/2016.
 */

public class Player implements MediaPlayer.OnCompletionListener {

    public static final int REPEAT_OFF = 0;
    public static final int REPEAT_ON = 1;
    public static final int REPEAT_ONE = 2;

    private MediaPlayer mPlayer;
    private Context mContext;
    private ArrayList<DetailAlbumModel> mListSongs;
    private int indexSong;

    private boolean isShuffle;
    private int mRepeat;
    private Random mRandom;

    public Player(Context context) {
        this.mContext = context;
        mRandom = new Random();
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(this);
    }

    public void setDataSource(String dataUri) {
        try {
            if (mPlayer != null) {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.reset();
                mPlayer.setDataSource("");
                mPlayer.prepare();
                mPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void next(int currentSong) {
        currentSong++;
        String uri = mListSongs.get(currentSong).getmOrder();
        setDataSource(uri);
    }

    public void forward(int currentSong) {
        currentSong--;
        String uri = mListSongs.get(currentSong).getmOrder();
        setDataSource(uri);
    }

    public void pause() {
        if (mPlayer != null)
            mPlayer.pause();

    }

    public void start() {
        if (mPlayer != null)
            mPlayer.start();

    }

    public int getDuration() {
        if (mPlayer != null)
            return mPlayer.getDuration();

        return -1;
    }

    public int getCurrentPostion() {
        if (mPlayer != null)
            return mPlayer.getCurrentPosition();
        return -1;
    }

    public void seekTo(int msec) {
        if (mPlayer != null) {
            mPlayer.pause();
            mPlayer.seekTo(msec);
            mPlayer.start();
        }
    }

    public void setVolume(float left, float right) {
        if (mPlayer != null)
            mPlayer.setVolume(left, right);
    }

    public void setRepeat(int repeat) {
        this.mRepeat = repeat;
    }

    public void setShuffle(boolean shuffle) {
        this.isShuffle = shuffle;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        switch (mRepeat) {
            case REPEAT_OFF:

                if (isShuffle) {
                    indexSong = mRandom.nextInt(mListSongs.size() - 1);
                    setDataSource(mListSongs.get(indexSong).getmOrder());
                } else {
                    indexSong++;
                    setDataSource(mListSongs.get(indexSong).getmOrder());
                }

                break;
            case REPEAT_ON:

                if (isShuffle) {
                    indexSong = mRandom.nextInt(mListSongs.size() - 1);
                    setDataSource(mListSongs.get(indexSong).getmOrder());
                } else {

                    indexSong = (indexSong == mListSongs.size() - 1) ? 0 : (indexSong++);
                    setDataSource(mListSongs.get(indexSong).getmOrder());
                }

                break;
            case REPEAT_ONE:
                setDataSource(mListSongs.get(indexSong).getmOrder());
                break;
            default:


        }
    }
}
