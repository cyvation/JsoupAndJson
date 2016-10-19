package com.edu.gvn.jsoupdemo.fragment.online.top100;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top100DetailFragment extends Fragment {
    private static final String _BUNDLE_TOP = "bundle.top";

    private String urlTop;

    public static Top100DetailFragment newInstance(String topType) {
        Top100DetailFragment fragment = new Top100DetailFragment();
        Bundle args = new Bundle();
        args.putString(_BUNDLE_TOP, topType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            urlTop = getDataBundle(savedInstanceState);
        else
            urlTop = getDataBundle(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top100_detail, container, false);
        return v;
    }

    private String getDataBundle(Bundle savedInstanceState) {
        return savedInstanceState.getString(_BUNDLE_TOP);
    }

}
