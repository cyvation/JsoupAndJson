package com.edu.gvn.jsoupdemo.fragment.online.rank;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.BaseActivity;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.RankAdapter;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.fragment.BaseFragment;
import com.edu.gvn.jsoupdemo.fragment.PlayerFragment;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;
import com.edu.gvn.jsoupdemo.model.online.RankModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.RankAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VietnamRankFragment extends BaseFragment implements View.OnClickListener, RankAsync.IRankDataCallBack {
    private Context mContext;
    private ImageView mPlayAll;
    private RecyclerView mRankList;
    private RankAdapter mRankAdapter;
    private ProgressBar mLoading;

    private ArrayList<RankModel> mRankData;
    private ArrayList<DetailAlbumModel> mPlayData = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

        RankAsync rankAsync = new RankAsync(context, this);
        rankAsync.execute(Mp3ZingBaseUrl.BXH_VN);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRankData = new ArrayList<>();
        mRankAdapter = new RankAdapter(mContext, mRankData, rankOnClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rank_general, container, false);
        ((TextView) v.findViewById(R.id.fragment_rank_general_name)).setText("Bảng xếp hạng bài hát Việt Nam");
        mPlayAll = (ImageView) v.findViewById(R.id.img_play_all);
        mRankList = (RecyclerView) v.findViewById(R.id.fragment_rank_general_rank);
        mLoading = (ProgressBar) v.findViewById(R.id.loading);
        mPlayAll.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRankList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRankList.setAdapter(mRankAdapter);

        if (mRankData.size() == 0) {
            mLoading.setVisibility(View.VISIBLE);
        } else {
            mLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mRankData != null && mRankData.size() != 0) {
            mPlayData.addAll(convertData(mRankData));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_all:
                BaseActivity.mPlayService.setListAlbum(mPlayData);
                BaseActivity.mPlayService.playIndex(0);
                break;
        }
    }

    private RankAdapter.IRecyclerRankOnClickListener rankOnClickListener = new RankAdapter.IRecyclerRankOnClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if (BaseActivity.mPlayService.getListData().size() != mRankData.size()) {
                mPlayData.addAll(convertData(mRankData));
                BaseActivity.mPlayService.setListAlbum(mPlayData);
            }
            BaseActivity.mPlayService.playIndex(position);
        }

        @Override
        public void onDownloadClick() {

        }

        @Override
        public void onAddClick() {

        }

        @Override
        public void onToPlayerClick() {
            ((HomeActivity) getActivity()).replaceFragmentWithToolbar(PlayerFragment.newInstance());
        }
    };

    @Override
    public void callBack(List<RankModel> lists) {
        mRankData.addAll(lists);
        mRankAdapter.notifyDataSetChanged();

        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
    }

    private ArrayList<DetailAlbumModel> convertData(ArrayList<RankModel> mOldData) {
        ArrayList<DetailAlbumModel> mData = new ArrayList<>();
        for (RankModel model : mOldData) {
            mData.add(new DetailAlbumModel(model.order, model.dataId, model.name));
        }
        return mData;
    }
}
