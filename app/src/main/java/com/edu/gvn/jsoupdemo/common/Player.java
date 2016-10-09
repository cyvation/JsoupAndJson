package com.edu.gvn.jsoupdemo.common;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;
import com.edu.gvn.jsoupdemo.model.online.SongOnlModel;
import com.edu.gvn.jsoupdemo.network.JsonParser.SongParserAsync;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hnc on 30/09/2016.
 */

public class Player implements MediaPlayer.OnCompletionListener {

    public final String TAG = Player.class.getSimpleName();
    private static final int REPEAT_OFF = 0;
    private static final int REPEAT_ON = 1;
    private static final int REPEAT_ONE = 2;

    private AudioManager mAudioManager;
    private MediaPlayer mPlayer;
    private Context mContext;
    private ArrayList<DetailAlbumModel> mListSongs = new ArrayList<>();
    private int indexSong = -1;

    private boolean isShuffle;
    private int mRepeat;
    private Random mRandom;

    private String nameSong;
    private String artistSong;
    private String linkCover;
    private String linkDownload;

    public Player(Context context) {
        this.mContext = context;
        mRandom = new Random();
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnCompletionListener(this);

        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    }

    public void setDataSource(String dataUri) {
        try {
            if (mPlayer != null) {
                mPlayer.reset();
                mPlayer.setDataSource(dataUri);
                mPlayer.prepare();
                mPlayer.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDataAlbumList(ArrayList<DetailAlbumModel> mData) {
        if (mListSongs != null) mListSongs.clear();
        this.mListSongs = mData;
    }

    public void playIndex(int index) {
        Log.i(TAG, "playIndex: " + mListSongs.get(index).toString());
        System.gc();
        indexSong = index;
        nameSong = "Loading ...";
        artistSong = "...";

        SongParserAsync songParserAsync = new SongParserAsync(mContext, new SongParserAsync.IDataCallBack() {
            @Override
            public void callBack(SongOnlModel onlModel) {
                String url = onlModel.data.get(0).source_list.get(1);
                int indexSlash = url.indexOf("/");
                String urlData = url.substring(indexSlash);
                String dataStream = "http://org2.s1.mp3.zdn.vn/" + urlData;
                setDataSource(dataStream);


                StringBuilder coverBuilder = new StringBuilder();
                coverBuilder.append("http://image.mp3.zdn.vn");
                coverBuilder.append(onlModel.data.get(0).cover);
                linkCover = coverBuilder.toString();

                nameSong = onlModel.data.get(0).name;
                artistSong = onlModel.data.get(0).artist;
                linkDownload = dataStream;

                if (onComplete != null) {
                    onComplete.notifiDataSetChange(nameSong, artistSong, linkCover);
                }

            }
        });
        songParserAsync.execute(mListSongs.get(index).getIDSong());
    }

    public boolean isPlaying() {
        if (mPlayer != null) {
            return mPlayer.isPlaying();
        }
        return false;
    }

    public void next() {
        Log.i(TAG, "next: next");

        if (isShuffle) {
            indexSong = mRandom.nextInt(mListSongs.size() - 1);
        } else {
            if (indexSong == mListSongs.size() - 1) {
                indexSong = 0;
            } else {
                indexSong++;
            }
        }

        playIndex(indexSong);
    }

    public void forward() {
        Log.i(TAG, "forward: forward");

        if (isShuffle) {
            indexSong = mRandom.nextInt(mListSongs.size() - 1);
        } else {
            if (indexSong == 0)
                indexSong = mListSongs.size() - 1;
            else
                indexSong--;
        }

        playIndex(indexSong);
    }

    public void pause() {
        if (mPlayer != null)
            mPlayer.pause();

    }

    public void start() {
        if (mPlayer != null)
            mPlayer.start();
    }

    public void seekTo(int msec) {
        if (mPlayer != null) {
            mPlayer.pause();
            mPlayer.seekTo(msec);
            mPlayer.start();
        }
    }

    public void setVolume(int volume) {
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }

    public void setRepeat() {

        if (mRepeat == 0) {
            mRepeat++;
        } else if (mRepeat == 1) {
            mRepeat++;
        } else if (mRepeat == 2) {
            mRepeat = 0;
        }
    }

    public void setShuffle() {
        isShuffle = !isShuffle;
    }

    public boolean getShuffle() {
        return isShuffle;
    }

    public int getRepeat() {
        return mRepeat;
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

    public int getVolumeLevel() {
        return mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public String getNameSong() {
        return nameSong;
    }

    public String getArtistSong() {
        return artistSong;
    }

    public String getLinkCover() {
        return linkCover;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public int getIndex() {
        return indexSong;
    }

    public ArrayList<DetailAlbumModel> getListData() {
        return mListSongs;
    }

    /**
     * - REPEAT OFF :
     * + nếu trộn : thì random bài hát
     * + nếu k trộn : tăng index bài hát, nếu bài cuối rồi thì stop
     * - REPEAT ON :
     * + neew trộn : random bài hát
     * + nếu k trộn : tăng index bài hát, nếu bài cuối rồi thì trở về bài 1
     * - REEPEAT ONE : ..
     *
     * @param mp : my media player
     */

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i(TAG, "onCompletion: complete");
        switch (mRepeat) {
            case REPEAT_OFF:

                if (isShuffle) {
                    indexSong = mRandom.nextInt(mListSongs.size() - 1);
                    playIndex(indexSong);
                } else {
                    if (indexSong == mListSongs.size() - 1) {
                        mp.stop();
                        break;
                    } else {
                        indexSong++;
                        playIndex(indexSong);
                    }
                }
                break;
            case REPEAT_ON:

                if (isShuffle) {
                    indexSong = mRandom.nextInt(mListSongs.size() - 1);
                } else {
                    indexSong = (indexSong == mListSongs.size() - 1) ? 0 : (indexSong++);
                }
                playIndex(indexSong);
                break;
            case REPEAT_ONE:
                playIndex(indexSong);
                break;
            default:

        }
        if (onComplete != null)
            onComplete.onComplete();
    }

    private MediaPlayerOnComplete onComplete;

    public void setOnComplete(MediaPlayerOnComplete onComplete) {
        this.onComplete = onComplete;
    }

    public interface MediaPlayerOnComplete {
        void onComplete();

        void notifiDataSetChange(String name, String artist, String image);
    }


}
