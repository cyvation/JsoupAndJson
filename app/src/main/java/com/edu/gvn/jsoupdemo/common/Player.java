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

    private static final int REPEAT_OFF = 0;
    private static final int REPEAT_ON = 1;
    private static final int REPEAT_ONE = 2;

    private AudioManager mAudioManager;
    private MediaPlayer mPlayer;
    private Context mContext;
    private ArrayList<DetailAlbumModel> mListSongs;
    private int indexSong;

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
        SongParserAsync songParserAsync = new SongParserAsync(mContext, new SongParserAsync.IDataCallBack() {
            public static final String TAG = "huutho";

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

                Log.i(TAG, "callBack: " + nameSong + "\n" + artistSong + "\n" + linkDownload + "\n" + linkCover);
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
        switch (mRepeat) {
            case REPEAT_OFF:

                if (isShuffle) {
                    indexSong = mRandom.nextInt(mListSongs.size() - 1);
                    //  setDataSource(mListSongs.get(indexSong).getmOrder());
                    playIndex(indexSong);
                } else {
                    if (indexSong == mListSongs.size() - 1) {
                        mp.stop();
                        break;
                    } else {
                        indexSong++;
                        //   setDataSource(mListSongs.get(indexSong).getmOrder());
                        playIndex(indexSong);
                    }
                }

                break;
            case REPEAT_ON:

                if (isShuffle) {
                    indexSong = mRandom.nextInt(mListSongs.size() - 1);
                    // setDataSource(mListSongs.get(indexSong).getmOrder());
                    playIndex(indexSong);
                } else {

                    indexSong = (indexSong == mListSongs.size() - 1) ? 0 : (indexSong++);
                    //  setDataSource(mListSongs.get(indexSong).getmOrder());
                    playIndex(indexSong);
                }

                break;
            case REPEAT_ONE:
                //   setDataSource(mListSongs.get(indexSong).getmOrder());
                playIndex(indexSong);
                break;
            default:

        }
    }

}
