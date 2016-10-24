package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.fragment.online.hot.KoreaFragment;
import com.edu.gvn.jsoupdemo.fragment.online.hot.UsUkFragment;
import com.edu.gvn.jsoupdemo.fragment.online.hot.VietNamFragment;
import com.edu.gvn.jsoupdemo.fragment.online.hot.VietRapFragment;

import java.util.ArrayList;

/**
 * Created by hnc on 10/10/2016.
 */

public class HotMusicPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mListFragment;
    private ArrayList<String> mListTitle;

    public HotMusicPagerAdapter(Context context , FragmentManager fm) {
        super(fm);
        mListFragment = new ArrayList<>();
        mListFragment.add(new VietNamFragment());
        mListFragment.add(new UsUkFragment());
        mListFragment.add(new KoreaFragment());
        mListFragment.add(new VietRapFragment());

        mListTitle = new ArrayList<>();
        mListTitle.add(context.getString(R.string.hot_music_pager_adapter_title_vietnam));
        mListTitle.add(context.getString(R.string.hot_music_pager_adapter_title_usuk));
        mListTitle.add(context.getString(R.string.hot_music_pager_adapter_title_korean));
        mListTitle.add(context.getString(R.string.hot_music_pager_adapter_title_rap_vietnam));

    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }
}
