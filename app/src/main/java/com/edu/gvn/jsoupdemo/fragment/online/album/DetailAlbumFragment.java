package com.edu.gvn.jsoupdemo.fragment.online.album;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.BaseActivity;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.DetailAlbumAdapter;
import com.edu.gvn.jsoupdemo.fragment.BaseFragment;
import com.edu.gvn.jsoupdemo.fragment.PlayerFragment;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.DetailAlbumAsync;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailAlbumFragment extends BaseFragment implements DetailAlbumAsync.DetailAlbumCallback,
        DetailAlbumAdapter.IReyclerViewItemClickListener {

    private static final String TAG = DetailAlbumFragment.class.getSimpleName();
    public static final String KEY_ALBUM = "key.album";

    private AlbumModel mHolderDetailAlbum;
    private RecyclerView mListDetail;
    private DetailAlbumAdapter mDetailAdapter;
    private ArrayList<DetailAlbumModel> mDetailData;
    private ProgressBar mLoading;

    private ImageView mImageAlbum;
    private TextView txtAlbumName;
    private TextView txtArtistName;


    public static DetailAlbumFragment newInstance(AlbumModel albumModel) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_ALBUM, albumModel);
        DetailAlbumFragment fragment = new DetailAlbumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void getDataBundleInstance(Bundle saveInstanceState) {
        mHolderDetailAlbum = saveInstanceState.getParcelable(KEY_ALBUM);
        Log.i(TAG, "getDataBundleInstance: " + mHolderDetailAlbum.getTitle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getDataBundleInstance(getArguments());
        } else {
            getDataBundleInstance(savedInstanceState);
        }

        mDetailData = new ArrayList<>();
        mDetailAdapter = new DetailAlbumAdapter(getActivity(), mDetailData);
        mDetailAdapter.setItemClickListener(this);

        DetailAlbumAsync detailAlbumAsync = new DetailAlbumAsync(this);
        detailAlbumAsync.execute(mHolderDetailAlbum.getHref());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_album, container, false);

        mListDetail = (RecyclerView) v.findViewById(R.id.fragment_detail_album_list_song_of_album);
        mImageAlbum = (ImageView) v.findViewById(R.id.fragment_detail_album_image_album);
        txtAlbumName = (TextView) v.findViewById(R.id.fragment_detail_album_name);
        txtArtistName = (TextView) v.findViewById(R.id.fragment_detail_album_artist_name);
        mLoading = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListDetail.setAdapter(mDetailAdapter);
        mListDetail.setLayoutManager(new LinearLayoutManager(getActivity()));

        Picasso.with(getActivity()).load(mHolderDetailAlbum.getImg_src()).into(mImageAlbum);

        Log.i(TAG, "onViewCreated: " + mHolderDetailAlbum.getTitle());

        int indexLastDash = mHolderDetailAlbum.getTitle().indexOf("-");
        String title = mHolderDetailAlbum.getTitle().substring(0, indexLastDash - 1);
        String name = mHolderDetailAlbum.getTitle().substring(indexLastDash + 2);
        txtAlbumName.setText(title);
        txtArtistName.setText(name);

        if (mDetailData.size() != 0) mLoading.setVisibility(View.GONE);
    }

    @Override
    public void callBack(ArrayList<DetailAlbumModel> model) {
        mDetailData.addAll(model);
        mDetailAdapter.notifyDataSetChanged();
        mLoading.setVisibility(View.GONE);


        if (mDetailData.size() != (BaseActivity.mPlayService.getListData()).size()) {
            BaseActivity.mPlayService.setListAlbum(mDetailData);
            Log.i(TAG, "onItemClick: play set");
        }
    }


    @Override
    public void onItemClick(View v, int position) {
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
}
