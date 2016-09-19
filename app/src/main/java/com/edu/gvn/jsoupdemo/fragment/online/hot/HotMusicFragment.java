package com.edu.gvn.jsoupdemo.fragment.online.hot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;


public class HotMusicFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = HotMusicFragment.class.getSimpleName();
    private LinearLayout mBottomBar ;
    private RelativeLayout mTabVn, mTabUs, mTabKorea, mTabRapVn;
    private Animation mZoomInTab, mZoomOutTab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZoomInTab = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_in);
        mZoomOutTab = AnimationUtils.loadAnimation(getActivity(), R.anim.tab_zoom_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_hot_music, container, false);

        mBottomBar = (LinearLayout) mView.findViewById(R.id.bottom_bar);
        mTabVn = (RelativeLayout) mView.findViewById(R.id.rll_vn);
        mTabUs = (RelativeLayout) mView.findViewById(R.id.rll_us);
        mTabKorea = (RelativeLayout) mView.findViewById(R.id.rll_korea);
        mTabRapVn = (RelativeLayout) mView.findViewById(R.id.rll_rap_vn);

        mTabUs.setOnClickListener(this);
        mTabVn.setOnClickListener(this);
        mTabKorea.setOnClickListener(this);
        mTabRapVn.setOnClickListener(this);
        ((HomeActivity)getActivity()).replaceFragment(this,new VietNamFragment(),R.id.child_fragment);
        return mView;
    }


    @Override
    public void onClick(View v) {
        animTab(v);
        switch (v.getId()) {
            case R.id.rll_vn:
                ((HomeActivity)getActivity()).replaceFragment(this,new VietNamFragment(),R.id.child_fragment);
                break;
            case R.id.rll_us:
                ((HomeActivity)getActivity()).replaceFragment(this,new UsUkFragment(),R.id.child_fragment);
                break;
            case R.id.rll_korea:
                ((HomeActivity)getActivity()).replaceFragment(this,new KoreaFragment(),R.id.child_fragment);
                break;
            case R.id.rll_rap_vn:
                ((HomeActivity)getActivity()).replaceFragment(this,new VietRapFragment(),R.id.child_fragment);
                break;
        }
    }

    private void animTab(View view) {
        mTabVn.startAnimation(mZoomOutTab);
        mTabRapVn.startAnimation(mZoomOutTab);
        mTabKorea.startAnimation(mZoomOutTab);
        mTabUs.startAnimation(mZoomOutTab);

        mTabKorea.clearAnimation();
        mTabVn.clearAnimation();
        mTabRapVn.clearAnimation();
        mTabUs.clearAnimation();

        view.startAnimation(mZoomInTab);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}
