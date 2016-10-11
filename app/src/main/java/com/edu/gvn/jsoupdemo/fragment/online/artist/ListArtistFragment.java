package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListArtistFragment extends Fragment {
    public static final String _BUNDLE_ARTIST = "bundle.artist";

    private String urlGender;
    private RecyclerView mListArstist ;

    public static ListArtistFragment newInstance(String genderArtist) {
        Bundle args = new Bundle();
        args.putString(_BUNDLE_ARTIST, genderArtist);
        ListArtistFragment fragment = new ListArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) urlGender = getDataBundle(getArguments());
        else urlGender = getDataBundle(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_artist, container, false);
        mListArstist = (RecyclerView) v.findViewById(R.id.fragment_list_artist_rv);
        return v;
    }

    private String getDataBundle(Bundle saveInstanceState) {
        String url = saveInstanceState.getString(_BUNDLE_ARTIST);
        return url;
    }

}
