package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.network.XmlParser.ArtistStoryAsync;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryArtistFragment extends Fragment implements ArtistStoryAsync.ArtistStoryCallback {
    public static final String _BUNDLE_ARTIST = "bundle.artist";
    public static final String _BUNDLE_IMAGE = "bundle.image";
    private String urlArtist,urlImage;
    private TextView mStory;
    private ImageView mImage;
    private String saved ="";

    public static StoryArtistFragment newInstance(String urlArtist,String urlImage) {

        Bundle args = new Bundle();
        args.putString(_BUNDLE_ARTIST, urlArtist);
        args.putString(_BUNDLE_IMAGE,urlImage);
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
        mImage = (ImageView) v.findViewById(R.id.img_bg);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStory.setText(saved);
        Picasso.with(getActivity()).load(urlImage).placeholder(R.drawable.background_nav).into(mImage);
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
        urlImage = savedInstanceState.getString(_BUNDLE_IMAGE);
    }
}
