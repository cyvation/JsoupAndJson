package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.fragment.online.rank.KoreaRankFragment;
import com.edu.gvn.jsoupdemo.fragment.online.rank.UsUkRankFragment;
import com.edu.gvn.jsoupdemo.fragment.online.rank.VietnamRankFragment;

import java.util.ArrayList;

/**
 * Created by HuuTho on 10/9/2016.
 */

public class RankPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mListFragment;
    private ArrayList<String> mListTitleFragment;

    public RankPagerAdapter(Context context ,FragmentManager fm) {
        super(fm);

        mListFragment = new ArrayList<>();
        mListFragment.add(new VietnamRankFragment());
        mListFragment.add(new UsUkRankFragment());
        mListFragment.add(new KoreaRankFragment());

        mListTitleFragment= new ArrayList<>();
        mListTitleFragment.add(context.getString(R.string.rank_pager_title_vietnam));
        mListTitleFragment.add(context.getString(R.string.rank_pager_title_usuk));
        mListTitleFragment.add(context.getString(R.string.rank_pager_title_korean));

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
        return mListTitleFragment.get(position);
    }
}
