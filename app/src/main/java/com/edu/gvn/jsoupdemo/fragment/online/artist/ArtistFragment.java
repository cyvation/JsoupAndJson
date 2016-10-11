package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.adapter.ArtistPagerAdapter;


public class ArtistFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private ViewPager mArtistPager;
    private TabLayout mTabArtist;
    private ArtistPagerAdapter mArtistAdapter;
    private Animation zoomInAnim, zoomOutAnim;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtistAdapter = new ArtistPagerAdapter(getActivity(), getChildFragmentManager());
        zoomInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_in);
        zoomOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artist, container, false);
        mArtistPager = (ViewPager) v.findViewById(R.id.fragment_artist_pager);
        mTabArtist = (TabLayout) v.findViewById(R.id.fragment_artist_tablayout);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArtistPager.setAdapter(mArtistAdapter);
        mTabArtist.setupWithViewPager(mArtistPager);
        mTabArtist.addOnTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        TextView tabTitle = (TextView) (((LinearLayout) ((LinearLayout) mTabArtist.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
        tabTitle.startAnimation(zoomInAnim);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        TextView tabTitle = (TextView) (((LinearLayout) ((LinearLayout) mTabArtist.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
        tabTitle.startAnimation(zoomOutAnim);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
