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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.ArtistDetailPagerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistDetailFragment extends Fragment {
    public static final String _BUNDLE_NAME = "bundle.name";
    public static final String _BUNDLE_IMAGE = "bundle.image";
    public static final String _BUNDLE_URL = "bundle.url" ;

    private ImageView mCover;
    private CircleImageView mAvatar;
    private TextView mArtist;
    private ViewPager mArtistPager;
    private TabLayout mTabArtist;

    private String mName, mImage, urlArtist;

    private ArtistDetailPagerAdapter detailPagerAdapter;
    private Animation zoomInAnim, zoomOutAnim;

    public static ArtistDetailFragment newInstance(String urlArtist ,String name, String urlImage) {
        Bundle args = new Bundle();
        args.putString(_BUNDLE_NAME, name);
        args.putString(_BUNDLE_IMAGE, urlImage);
        args.putString(_BUNDLE_URL, urlArtist);
        ArtistDetailFragment fragment = new ArtistDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getDataBundle(getArguments());
        } else {
            getDataBundle(savedInstanceState);
        }

        detailPagerAdapter = new ArtistDetailPagerAdapter(getChildFragmentManager(),urlArtist, mName, mImage);
        zoomInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_in);
        zoomOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artist_detail, container, false);
        //    mCover = (ImageView) v.findViewById(R.id.fragment_artist_detail_image_cover);
        //  mAvatar = (CircleImageView) v.findViewById(R.id.frament_artist_detail_avatar);
        //   mArtist = (TextView) v.findViewById(R.id.fragment_artist_detail_artist_name);

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

    private void getDataBundle(Bundle savedInstanceState) {
        mName = savedInstanceState.getString(_BUNDLE_NAME);
        mImage = savedInstanceState.getString(_BUNDLE_IMAGE);
        urlArtist = savedInstanceState.getString(_BUNDLE_URL);
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            TextView txt = (TextView) (((LinearLayout) ((LinearLayout) mTabArtist.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
            txt.startAnimation(zoomInAnim);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            TextView txt = (TextView) (((LinearLayout) ((LinearLayout) mTabArtist.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
            txt.startAnimation(zoomOutAnim);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
