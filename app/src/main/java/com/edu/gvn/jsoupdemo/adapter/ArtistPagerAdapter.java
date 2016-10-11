package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.fragment.online.artist.ListArtistFragment;

import java.util.ArrayList;

/**
 * Created by hnc on 11/10/2016.
 */

public class ArtistPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mListFragment;
    private ArrayList<String> mListTitle;

    public ArtistPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mListFragment = new ArrayList<>();
        mListTitle = new ArrayList<>();

        mListFragment.add(ListArtistFragment.newInstance(Mp3ZingBaseUrl.ARTISTS_VN));
        mListFragment.add(ListArtistFragment.newInstance(Mp3ZingBaseUrl.ARTISTS_US));
        mListFragment.add(ListArtistFragment.newInstance(Mp3ZingBaseUrl.ARTISTS_KOREA));
        mListFragment.add(ListArtistFragment.newInstance(Mp3ZingBaseUrl.ARTISTS_JAPAN));
        mListFragment.add(ListArtistFragment.newInstance(Mp3ZingBaseUrl.ARTISTS_CHINA));
        mListFragment.add(ListArtistFragment.newInstance(Mp3ZingBaseUrl.ARTISTS_CONCERT));


        mListTitle.add(context.getResources().getString(R.string.artist_vietnam));
        mListTitle.add(context.getResources().getString(R.string.artist_us));
        mListTitle.add(context.getResources().getString(R.string.artist_korea));
        mListTitle.add(context.getResources().getString(R.string.artist_jav));
        mListTitle.add(context.getResources().getString(R.string.artist_china));
        mListTitle.add(context.getResources().getString(R.string.artist_concert));

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
