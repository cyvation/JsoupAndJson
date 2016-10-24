package com.edu.gvn.jsoupdemo.fragment.online.top100;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.BaseActivity;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.AlbumDetailAdapter;
import com.edu.gvn.jsoupdemo.adapter.Top100DetailAdapter;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.fragment.PlayerFragment;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;
import com.edu.gvn.jsoupdemo.model.online.Top100;
import com.edu.gvn.jsoupdemo.network.JsonParser.Top100Async;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top100DetailFragment extends Fragment implements AlbumDetailAdapter.IRecyclerMenuItemClick {
    private static final String _BUNDLE_TOP = "bundle.top";

    private String urlTop;

    private RecyclerView mTop100List;
    private ArrayList<Top100.ItemSong> top100Data;
    private Top100DetailAdapter mTop100Adapter;

    public static Top100DetailFragment newInstance(String topType) {
        Top100DetailFragment fragment = new Top100DetailFragment();
        Bundle args = new Bundle();

        StringBuilder builder = new StringBuilder();
        builder.append(Mp3ZingBaseUrl.TOP_MUSIC);
        builder.append(topType);

        args.putString(_BUNDLE_TOP, builder.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            urlTop = getDataBundle(savedInstanceState);
        else
            urlTop = getDataBundle(getArguments());


        senRequestMp3(urlTop);
        top100Data = new ArrayList<>();
        mTop100Adapter = new Top100DetailAdapter(getActivity(), top100Data);
        mTop100Adapter.setMenuItemClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top100_detail, container, false);
        mTop100List = (RecyclerView) v.findViewById(R.id.rv_top100);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTop100List.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTop100List.setAdapter(mTop100Adapter);
        mTop100Adapter.notifyDataSetChanged();
    }

    private String getDataBundle(Bundle savedInstanceState) {
        return savedInstanceState.getString(_BUNDLE_TOP);
    }

    private void senRequestMp3(String url) {
        Top100Async top100Async = new Top100Async(getActivity(), top100Callback);
        top100Async.execute(url);
    }

    private Top100Async.ITop100AsyncCallback top100Callback = new Top100Async.ITop100AsyncCallback() {
        @Override
        public void callBack(Top100 top) {
            top100Data.addAll(top.data);
            mTop100Adapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onItemClick(View v, int position) {
        BaseActivity.mPlayService.setListAlbum(convertData());
        if (position != BaseActivity.mPlayService.getIndexSong()) {
            BaseActivity.mPlayService.playIndex(position);
        }
    }

    @Override
    public void onDownloadClick(View v, int position) {

    }

    @Override
    public void onAddClick(View v, int position) {

    }

    @Override
    public void onToPlayerClick(View v, int position) {
        ((HomeActivity) getActivity()).replaceFragmentWithToolbar(PlayerFragment.newInstance());
    }


    private ArrayList<DetailAlbumModel> convertData() {
        ArrayList<DetailAlbumModel> data = new ArrayList<>();
        for (int i = 0; i < top100Data.size(); i++) {
            String order = top100Data.get(i).order;
            String id = top100Data.get(i).id;
            String songName = top100Data.get(i).name;

            data.add(new DetailAlbumModel(order, id, songName));
        }
        return data;
    }
}
