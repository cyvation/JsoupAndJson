package com.edu.gvn.jsoupdemo.fragment.online;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.edu.gvn.jsoupdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album, container, false);

        ((RelativeLayout)v.findViewById(R.id.vn_vpop)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_country)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_dance)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_nhacthieunhi)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_rap_hiphop)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_red)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_rock)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_romance)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.vn_trinhcongson)).setOnClickListener(this);

        ((RelativeLayout)v.findViewById(R.id.us_blue_jazz)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.us_country)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.us_edm)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.us_pop)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.us_rap_hiphop)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.us_rb_soul)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.us_rock)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.us_trance)).setOnClickListener(this);

        ((RelativeLayout)v.findViewById(R.id.asia_korea)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.asia_japan)).setOnClickListener(this);
        ((RelativeLayout)v.findViewById(R.id.asia_china)).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vn_vpop : break;
            case R.id.vn_country : break;
            case R.id.vn_dance : break;
            case R.id.vn_nhacthieunhi : break;
            case R.id.vn_rap_hiphop : break;
            case R.id.vn_red : break;
            case R.id.vn_rock : break;
            case R.id.vn_romance : break;
            case R.id.vn_trinhcongson : break;

            case R.id.us_blue_jazz : break;
            case R.id.us_country : break;
            case R.id.us_edm : break;
            case R.id.us_pop : break;
            case R.id.us_rap_hiphop : break;
            case R.id.us_rb_soul : break;
            case R.id.us_rock : break;
            case R.id.us_trance : break;

            case R.id.asia_korea : break;
            case R.id.asia_japan : break;
            case R.id.asia_china : break;
        }
    }
}
