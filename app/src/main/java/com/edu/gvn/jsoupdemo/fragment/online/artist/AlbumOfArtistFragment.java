package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumOfArtistFragment extends Fragment {
    public static final String _BUNDLE_NAME = "bundle.name";
    public static final String _BUNDLE_IMAGE = "bundle.image";

    private String mName, mUrlImage;
    private ImageView mCoverArtist, mAvatar;
    private TextView mArtistName, mHide;
    private ProgressBar mLoading;
    private RecyclerView mListSong;


    public static AlbumOfArtistFragment newInstance(String name, String urlImage) {
        Bundle args = new Bundle();
        args.putString(_BUNDLE_NAME, name);
        args.putString(_BUNDLE_IMAGE, urlImage);
        AlbumOfArtistFragment fragment = new AlbumOfArtistFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_album_of_artist, container, false);
        mCoverArtist = (ImageView) v.findViewById(R.id.fragment_detail_album_image_album);
        mAvatar = (ImageView) v.findViewById(R.id.imageView);
        mLoading = (ProgressBar) v.findViewById(R.id.loading);
        mListSong = (RecyclerView) v.findViewById(R.id.fragment_detail_album_list_song_of_album);
        mArtistName = (TextView) v.findViewById(R.id.fragment_detail_album_name);
        mHide = (TextView) v.findViewById(R.id.fragment_detail_album_artist_name);
        mHide.setVisibility(View.GONE);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        Picasso.with(getActivity()).load(mUrlImage).placeholder(R.drawable.background_nav).into(mCoverArtist);
        mArtistName.setText(mName);
    }

    private void getDataBundle(Bundle saveInstanceState) {
        mName = saveInstanceState.getString(_BUNDLE_NAME);
        mUrlImage = saveInstanceState.getString(_BUNDLE_IMAGE);
    }

}
