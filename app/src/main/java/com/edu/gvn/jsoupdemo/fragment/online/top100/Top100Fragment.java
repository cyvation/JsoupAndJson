package com.edu.gvn.jsoupdemo.fragment.online.top100;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top100Fragment extends Fragment {
    private ViewPager mTopPager;
    private TabLayout mTabTop;
    private TabPagerAdapter mTabPagerAdapter;
    private Animation zoomIn, zoomOut ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabPagerAdapter = new TabPagerAdapter(getFragmentManager());
        zoomIn = AnimationUtils.loadAnimation(getActivity(),R.anim.tab_zoom_in);
        zoomOut = AnimationUtils.loadAnimation(getActivity(),R.anim.tab_zoom_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top100, container, false);
        mTopPager = (ViewPager) v.findViewById(R.id.top100_fragment_pager);
        mTabTop = (TabLayout) v.findViewById(R.id.tablayout);

        mTabTop.addOnTabSelectedListener(onTabSelectedListener);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabTop.setupWithViewPager(mTopPager);
        mTopPager.setAdapter(mTabPagerAdapter);
    }

    private TabLayout.OnTabSelectedListener  onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            TextView tabTitle = (TextView) (((LinearLayout) ((LinearLayout) mTabTop.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
            tabTitle.startAnimation(zoomIn);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            TextView tabTitle = (TextView) (((LinearLayout) ((LinearLayout) mTabTop.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
            tabTitle.startAnimation(zoomOut);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private class TabPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> listFragment = new ArrayList<>();
        private ArrayList<String> listTitle = new ArrayList<>();
        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
            listFragment.add(Top100DetailFragment.newInstance(Mp3ZingBaseUrl.ID_VN_NHAC_TRE));
            listFragment.add(Top100DetailFragment.newInstance(Mp3ZingBaseUrl.ID_US_POP));
            listFragment.add(Top100DetailFragment.newInstance(Mp3ZingBaseUrl.ID_ASIAN_HAN_QUOC));
            listFragment.add(Top100DetailFragment.newInstance(Mp3ZingBaseUrl.ID_HT_PIANO));

            listTitle.add("Việt nam");
            listTitle.add("Âu mỹ");
            listTitle.add("Hàn quốc");
            listTitle.add("Hòa tấu");
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

}
