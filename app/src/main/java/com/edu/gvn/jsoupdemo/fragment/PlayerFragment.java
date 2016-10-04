package com.edu.gvn.jsoupdemo.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import info.abdolahi.CircularMusicProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment implements View.OnClickListener {

    private static final int TIME_DELAY_UPDATE = 1000;
    private static final int REPEAT_OFF = 0;
    private static final int REPEAT_ON = 1;
    private static final int REPEAT_ONE = 2;

    private CircularMusicProgressBar mMusicProgress;
    private ImageView mPlayState, mNext, mForward;
    private TextView mNameSong, mArtistSong;
    private ImageView mShuffle, mRepeat;
    private SeekBar mVolume;

    private Handler mUpdateProgressBarHandler;

    public static PlayerFragment newInstance() {
        Bundle args = new Bundle();
        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);
        mMusicProgress = (CircularMusicProgressBar) v.findViewById(R.id.fragment_player_progress_bar);
        mVolume = (SeekBar) v.findViewById(R.id.fragment_player_seekbar_volume);

        mPlayState = (ImageView) v.findViewById(R.id.fragment_player_imageview_play_state);
        mNext = (ImageView) v.findViewById(R.id.fragment_play_imageview_nexts);
        mForward = (ImageView) v.findViewById(R.id.fragment_play_imageview_forward);
        mShuffle = (ImageView) v.findViewById(R.id.fragment_player_imageview_shuffle);
        mRepeat = (ImageView) v.findViewById(R.id.fragment_player_imageview_repeat);

        mNameSong = (TextView) v.findViewById(R.id.fragment_player_song_title);
        mArtistSong = (TextView) v.findViewById(R.id.fragment_player_song_artist);

        mUpdateProgressBarHandler = new Handler();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        mPlayState.setActivated(BaseActivity.mPlayService.isPlaying());
        mShuffle.setActivated(BaseActivity.mPlayService.getShuffle());
        setStateRepeat(BaseActivity.mPlayService.getRepeat());

        mNameSong.setText(BaseActivity.mPlayService.getNameSong());
        mArtistSong.setText(BaseActivity.mPlayService.getArtistSong());

        mMusicProgress.setValue(percentMusicProgress(BaseActivity.mPlayService.currentPosition(), BaseActivity.mPlayService.maxDuration()));
        mVolume.setProgress(BaseActivity.mPlayService.getVolume());

        mVolume.setOnSeekBarChangeListener(seekBarChangeListener);
        mPlayState.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mForward.setOnClickListener(this);
        mShuffle.setOnClickListener(this);
        mRepeat.setOnClickListener(this);

        Picasso.with(getActivity()).load(BaseActivity.mPlayService.getCover()).into(mMusicProgress);

        mUpdateProgressBarHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMusicProgress.setValue(percentMusicProgress(BaseActivity.mPlayService.currentPosition(), BaseActivity.mPlayService.maxDuration()));
                mUpdateProgressBarHandler.postDelayed(this, TIME_DELAY_UPDATE);
            }
        }, TIME_DELAY_UPDATE);
    }


    private void setStateRepeat(int repeat) {
        switch (repeat) {
            case REPEAT_OFF:
                mRepeat.setSelected(false);
                mRepeat.setActivated(false);
                break;
            case REPEAT_ON:
                mRepeat.setSelected(true);
                mRepeat.setActivated(false);
                break;
            case REPEAT_ONE:
                mRepeat.setSelected(true);
                mRepeat.setActivated(true);
                break;
        }
    }

    private long percentMusicProgress(int currentPosition, int maxDuration) {
        if (maxDuration == 0) maxDuration = 1;

        int percent = (int) (( (float) currentPosition / maxDuration )* 100);
        Log.i("huutho", "run: " + BaseActivity.mPlayService.currentPosition()+ " - " +  BaseActivity.mPlayService.maxDuration() + " - " + percent);

       return percent ;
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_player_imageview_play_state:
                break;
            case R.id.fragment_play_imageview_nexts:
                break;
            case R.id.fragment_play_imageview_forward:
                break;
            case R.id.fragment_player_imageview_shuffle:
                break;
            case R.id.fragment_player_imageview_repeat:
                break;
        }
    }


}
