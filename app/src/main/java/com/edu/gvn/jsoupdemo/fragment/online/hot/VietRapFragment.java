package com.edu.gvn.jsoupdemo.fragment.online.hot;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.adapter.HotOrAlbumMusicAdapter;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.HotMusicParserAsync;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VietRapFragment extends Fragment {

    private static final String TAG = VietNamFragment.class.getSimpleName();
    private RecyclerView mListAlbumHot;
    private HotOrAlbumMusicAdapter mAdapter;
    private HotMusicParserAsync mHotMusicAsync;
    private ArrayList<AlbumModel> mData = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHotMusicAsync = new HotMusicParserAsync(new HotMusicParserAsync.GetDataCallback() {
            @Override
            public void getData(ArrayList<AlbumModel> data) {
                mData = data;
                mAdapter.addData(data);
            }
        });
        mHotMusicAsync.execute(Mp3ZingBaseUrl.HOT_RAP_VIET);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new HotOrAlbumMusicAdapter(getActivity(), mData);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hotmusic_vietraphot, container, false);
        mListAlbumHot = (RecyclerView) v.findViewById(R.id.rv_hotmusic_vietnam);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListAlbumHot.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mListAlbumHot.setAdapter(mAdapter);

    }
}
