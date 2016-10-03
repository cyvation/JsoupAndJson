package com.edu.gvn.jsoupdemo.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.BaseActivity;

import info.abdolahi.CircularMusicProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment {

    private CircularMusicProgressBar mMusicProgress;
    private ImageView mPlayState, mNext, mForward;
    private TextView mNameSong, mArtistSong;
    private ImageView mShuffle, mRepeat;
    private SeekBar mVolume;

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

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        mPlayState.setActivated(BaseActivity.mPlayService.isPlaying());
        mMusicProgress.setValue(percentMusicProgress(BaseActivity.mPlayService.currentPosition(),BaseActivity.mPlayService.maxDuration()));
        mVolume.setProgress(BaseActivity.mPlayService.getVolume());

    }

    private int percentMusicProgress(int currentPosition, int maxDuration) {
        return currentPosition / maxDuration * 100;
    }
}
