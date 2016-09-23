package com.edu.gvn.jsoupdemo.fragment.online.album;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumCategoryFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_album, container, false);

        v.findViewById(R.id.vn_vpop).setOnClickListener(this);
        v.findViewById(R.id.vn_country).setOnClickListener(this);
        v.findViewById(R.id.vn_dance).setOnClickListener(this);
        v.findViewById(R.id.vn_nhacthieunhi).setOnClickListener(this);
        v.findViewById(R.id.vn_rap_hiphop).setOnClickListener(this);
        v.findViewById(R.id.vn_red).setOnClickListener(this);
        v.findViewById(R.id.vn_rock).setOnClickListener(this);
        v.findViewById(R.id.vn_romance).setOnClickListener(this);
        v.findViewById(R.id.vn_trinhcongson).setOnClickListener(this);

        v.findViewById(R.id.us_blue_jazz).setOnClickListener(this);
        v.findViewById(R.id.us_country).setOnClickListener(this);
        v.findViewById(R.id.us_edm).setOnClickListener(this);
        v.findViewById(R.id.us_pop).setOnClickListener(this);
        v.findViewById(R.id.us_rap_hiphop).setOnClickListener(this);
        v.findViewById(R.id.us_rb_soul).setOnClickListener(this);
        v.findViewById(R.id.us_rock).setOnClickListener(this);
        v.findViewById(R.id.us_trance).setOnClickListener(this);

        v.findViewById(R.id.asia_korea).setOnClickListener(this);
        v.findViewById(R.id.asia_japan).setOnClickListener(this);
        v.findViewById(R.id.asia_china).setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.vn_vpop:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRE));
                break;
            case R.id.vn_country:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_NHAC_QUE_HUONG));
                break;
            case R.id.vn_dance:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_NHAC_DANCE_VIET));
                break;
            case R.id.vn_nhacthieunhi:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_NHAC_THIEU_NHI));
                break;
            case R.id.vn_rap_hiphop:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_RAP_HIPHOP));
                break;
            case R.id.vn_red:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_NHAC_CACH_MANG));
                break;
            case R.id.vn_rock:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_ROCK_VIET));
                break;
            case R.id.vn_romance:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRU_TINH));
                break;
            case R.id.vn_trinhcongson:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRINH));
                break;
            case R.id.us_blue_jazz:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_BLUES_JAZZ));
                break;
            case R.id.us_country:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_COUNTRY));
                break;
            case R.id.us_edm:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_ELECTRONIC_DANCE));
                break;
            case R.id.us_pop:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_POP));
                break;
            case R.id.us_rap_hiphop:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_RAP_HIPHOP));
                break;
            case R.id.us_rb_soul:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_R_B_SOUL));
                break;
            case R.id.us_rock:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_ROCK));
                break;
            case R.id.us_trance:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_US_TRANCE_HOUSE_TECHNO));
                break;
            case R.id.asia_korea:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_ASIAN_KOREA));
                break;
            case R.id.asia_japan:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_ASIAN_JAPAN));
                break;
            case R.id.asia_china:
                ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(Mp3ZingBaseUrl.ALBUMS_ASIAN_CHINA));
                break;
        }
    }



}
