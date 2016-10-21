package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.network.XmlParser.ArtistStoryAsync;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryArtistFragment extends Fragment implements ArtistStoryAsync.ArtistStoryCallback {
    public static final String _BUNDLE_ARTIST = "bundle.artist";
    private String urlArtist;
    private TextView mStory;
    private String saved ="";

    public static StoryArtistFragment newInstance(String urlArtist) {

        Bundle args = new Bundle();
        args.putString(_BUNDLE_ARTIST, urlArtist);
        StoryArtistFragment fragment = new StoryArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getDataBundle(getArguments());
        } else {
            getDataBundle(savedInstanceState);
        }

        ArtistStoryAsync artistStoryAsync = new ArtistStoryAsync(getActivity(), this);
        artistStoryAsync.execute(urlArtist + "/tieu-su");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_story_artist, container, false);
        mStory = (TextView) v.findViewById(R.id.fragment_story_artist_story);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStory.setText(saved);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void callBack(String story) {
        mStory.setText(story);
        saved = story;
    }

    private void getDataBundle(Bundle savedInstanceState) {
        urlArtist = savedInstanceState.getString(_BUNDLE_ARTIST);
    }
}
