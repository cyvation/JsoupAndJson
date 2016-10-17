package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.ArtistDetailPagerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistDetailFragment extends Fragment {

    private ImageView mCover;
    private CircleImageView mAvatar;
    private TextView mArtist;
    private ViewPager mArtistPager;
    private TabLayout mTabArtist;

    private ArtistDetailPagerAdapter detailPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailPagerAdapter = new ArtistDetailPagerAdapter(getChildFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artist_detail, container, false);
        mCover = (ImageView) v.findViewById(R.id.fragment_artist_detail_image_cover);
        mAvatar = (CircleImageView) v.findViewById(R.id.frament_artist_detail_avatar);
        mArtist = (TextView) v.findViewById(R.id.fragment_artist_detail_artist_name);

        mArtistPager = (ViewPager) v.findViewById(R.id.fragment_artist_detail_viewpager);
        mTabArtist = (TabLayout) v.findViewById(R.id.fragment_detail_artist_tablayout);

        mTabArtist.setupWithViewPager(mArtistPager);
        mTabArtist.addOnTabSelectedListener(tabSelectedListener);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArtistPager.setAdapter(detailPagerAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity) getActivity()).hideToolbar();

    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
