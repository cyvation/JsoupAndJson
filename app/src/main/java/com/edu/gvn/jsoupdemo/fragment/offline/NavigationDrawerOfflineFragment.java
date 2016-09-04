package com.edu.gvn.jsoupdemo.fragment.offline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;

public class NavigationDrawerOfflineFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View mView = inflater.inflate(R.layout.fragment_navigation_drawer_offline, container, false);

        return mView;
    }

}
