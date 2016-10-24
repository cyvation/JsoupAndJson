package com.edu.gvn.jsoupdemo.fragment.online.hot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.adapter.HotMusicPagerAdapter;
import com.edu.gvn.jsoupdemo.fragment.BaseFragment;


public class HotMusicFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private ViewPager mHotPager;
    private TabLayout mHotTablayout;
    private HotMusicPagerAdapter mHotAdapter;

    private Animation zoomInAnim, zoomOutAnim;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotAdapter = new HotMusicPagerAdapter(getActivity(),this.getChildFragmentManager());
        zoomInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_in);
        zoomOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_music, container, false);
        mHotPager = (ViewPager) view.findViewById(R.id.fragment_hot_music_pager);
        mHotTablayout = (TabLayout) view.findViewById(R.id.fragment_hot_music_tablayout);

        mHotTablayout.addOnTabSelectedListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHotTablayout.setupWithViewPager(mHotPager);
        mHotPager.setAdapter(mHotAdapter);
        setTabIcon();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        ImageView img = (ImageView) (((LinearLayout) ((LinearLayout) mHotTablayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(0));
        TextView txt = (TextView) (((LinearLayout) ((LinearLayout) mHotTablayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
        img.startAnimation(zoomInAnim);
        txt.startAnimation(zoomOutAnim);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        ImageView img = (ImageView) (((LinearLayout) ((LinearLayout) mHotTablayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(0));
        TextView txt = (TextView) (((LinearLayout) ((LinearLayout) mHotTablayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
        img.startAnimation(zoomOutAnim);
        txt.startAnimation(zoomInAnim);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setTabIcon() {
        mHotTablayout.getTabAt(0).setIcon(R.drawable.bottom_tab_vietnam);
        mHotTablayout.getTabAt(1).setIcon(R.drawable.bottom_tab_us);
        mHotTablayout.getTabAt(2).setIcon(R.drawable.bottom_tab_korea);
        mHotTablayout.getTabAt(3).setIcon(R.drawable.bottom_tab_rapper);
    }
}
