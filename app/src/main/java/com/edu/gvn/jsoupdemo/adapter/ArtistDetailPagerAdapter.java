package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.fragment.online.artist.AlbumOfArtistFragment;
import com.edu.gvn.jsoupdemo.fragment.online.artist.SongOfArtistFragment;
import com.edu.gvn.jsoupdemo.fragment.online.artist.StoryArtistFragment;

import java.util.ArrayList;

/**
 * Created by hnc on 17/10/2016.
 */

public class ArtistDetailPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listFragment = new ArrayList<>();
    private ArrayList<String> listTitle = new ArrayList<>();

    public ArtistDetailPagerAdapter(Context context, FragmentManager fm , String urlArtist, String name, String urlImage) {
        super(fm);

        listFragment.add(SongOfArtistFragment.newInstance(urlArtist,name,urlImage));
        listFragment.add(AlbumOfArtistFragment.newInstance(urlArtist,name,urlImage));
        listFragment.add(StoryArtistFragment.newInstance(urlArtist,urlImage));

        listTitle.add(context.getString(R.string.artist_detail_fragment_title_song));
        listTitle.add(context.getString(R.string.artist_detail_fragment_title_album));
        listTitle.add(context.getString(R.string.artist_detail_fragment_title_story));
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
