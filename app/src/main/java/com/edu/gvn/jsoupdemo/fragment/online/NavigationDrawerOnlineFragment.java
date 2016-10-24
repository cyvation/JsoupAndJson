package com.edu.gvn.jsoupdemo.fragment.online;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.fragment.BaseFragment;

public class NavigationDrawerOnlineFragment extends BaseFragment implements View.OnClickListener {
    public interface GetNavItemClickListener {
        void onItemClick(View v);
    }

    private GetNavItemClickListener listener;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_navigation_drawer_online, container, false);
        inflateView.findViewById(R.id.option_voice_serch).setOnClickListener(this);
        inflateView.findViewById(R.id.option_search).setOnClickListener(this);
        inflateView.findViewById(R.id.option_hot_music).setOnClickListener(this);
        inflateView.findViewById(R.id.option_rank).setOnClickListener(this);
        inflateView.findViewById(R.id.option_artists).setOnClickListener(this);
        inflateView.findViewById(R.id.option_albums).setOnClickListener(this);
        inflateView.findViewById(R.id.option_top_ten).setOnClickListener(this);
        inflateView.findViewById(R.id.option_lyric_screen).setOnClickListener(this);
        inflateView.findViewById(R.id.option_suggested_apps).setOnClickListener(this);
        inflateView.findViewById(R.id.option_settings).setOnClickListener(this);
        inflateView.findViewById(R.id.option_exit).setOnClickListener(this);

        return inflateView;
    }


    public void setUpNavDrawer(DrawerLayout drawerLayout, Toolbar toolbar, GetNavItemClickListener listener) {
        this.mDrawerLayout = drawerLayout;
        this.listener = listener;
        mToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.nav_open, R.string.nav_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
              //  mDrawerLayout.setScrimColor(Color.TRANSPARENT);
              //  float moveFactor = drawerView.getWidth() * slideOffset;
              //  getActivity().findViewById(R.id.rl_parent).setTranslationX(moveFactor);
            }
        };

        drawerLayout.addDrawerListener(mToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mToggle.syncState();
            }
        });
    }

    public void closeNavDrawer() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }


    @Override
    public void onClick(View v) {
        listener.onItemClick(v);
    }


}
