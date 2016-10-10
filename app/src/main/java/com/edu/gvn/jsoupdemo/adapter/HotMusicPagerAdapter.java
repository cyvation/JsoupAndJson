package com.edu.gvn.jsoupdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

    public HotMusicPagerAdapter(FragmentManager fm) {
        super(fm);
        mListFragment = new ArrayList<>();
        mListFragment.add(new VietNamFragment());
        mListFragment.add(new UsUkFragment());
        mListFragment.add(new KoreaFragment());
        mListFragment.add(new VietRapFragment());

        mListTitle = new ArrayList<>();
        mListTitle.add("Việt nam");
        mListTitle.add("Âu mỹ");
        mListTitle.add("Hàn quốc");
        mListTitle.add("Rap Việt");

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
