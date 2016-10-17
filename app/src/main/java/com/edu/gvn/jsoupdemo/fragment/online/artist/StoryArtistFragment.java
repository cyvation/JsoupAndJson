package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryArtistFragment extends Fragment {


    public StoryArtistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story_artist, container, false);
    }

}
