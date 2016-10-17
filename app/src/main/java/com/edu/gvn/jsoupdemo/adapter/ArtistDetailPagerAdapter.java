package com.edu.gvn.jsoupdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.gvn.jsoupdemo.fragment.online.artist.ListAlbumOfArtistFragment;
import com.edu.gvn.jsoupdemo.fragment.online.artist.ListSongOfArtistFragment;
import com.edu.gvn.jsoupdemo.fragment.online.artist.StoryArtistFragment;

import java.util.ArrayList;

/**
 * Created by hnc on 17/10/2016.
 */

public class ArtistDetailPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listFragment = new ArrayList<>();
    private ArrayList<String> listTitle = new ArrayList<>();

    public ArtistDetailPagerAdapter(FragmentManager fm) {
        super(fm);

        listFragment.add(new ListSongOfArtistFragment());
        listFragment.add(new ListAlbumOfArtistFragment());
        listFragment.add(new StoryArtistFragment());

        listTitle.add("Song");
        listTitle.add("Album");
        listTitle.add("Story");
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
