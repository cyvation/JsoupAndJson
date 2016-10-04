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
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.BaseActivity;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.DetailAlbumAdapter;
import com.edu.gvn.jsoupdemo.fragment.PlayerFragment;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.DetailAlbumAsync;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailAlbumFragment extends Fragment implements DetailAlbumAsync.DetailAlbumCallback, DetailAlbumAdapter.IReyclerViewItemOnClickListener {
    public static final String KEY_ALBUM = "key.album";
    private static final String TAG = DetailAlbumFragment.class.getSimpleName();

    private AlbumModel mAlbumModel;

    private RecyclerView mRecyclerListSong;
    private DetailAlbumAdapter mDetailAlbumAdapter;
    private ArrayList<DetailAlbumModel> mDetailAlbumData;

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
        mAlbumModel = saveInstanceState.getParcelable(KEY_ALBUM);
        Log.i(TAG, "getDataBundleInstance: " + mAlbumModel.getTitle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getDataBundleInstance(getArguments());
        } else {
            getDataBundleInstance(savedInstanceState);
        }

        mDetailAlbumData = new ArrayList<>();
        mDetailAlbumAdapter = new DetailAlbumAdapter(getActivity(), mDetailAlbumData);
        mDetailAlbumAdapter.setItemOnClickListener(this);

        DetailAlbumAsync detailAlbumAsync = new DetailAlbumAsync(this);
        detailAlbumAsync.execute(mAlbumModel.getHref());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_album, container, false);

        mRecyclerListSong = (RecyclerView) v.findViewById(R.id.fragment_detail_album_list_song_of_album);
        mImageAlbum = (ImageView) v.findViewById(R.id.fragment_detail_album_image_album);
        txtAlbumName = (TextView) v.findViewById(R.id.fragment_detail_album_name);
        txtArtistName = (TextView) v.findViewById(R.id.fragment_detail_album_artist_name);

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerListSong.setAdapter(mDetailAlbumAdapter);
        mRecyclerListSong.setLayoutManager(new LinearLayoutManager(getActivity()));

        Picasso.with(getActivity()).load(mAlbumModel.getImg_src()).into(mImageAlbum);

        Log.i(TAG, "onViewCreated: " + mAlbumModel.getTitle());

        int indexLastDash = mAlbumModel.getTitle().indexOf("-");
        String title = mAlbumModel.getTitle().substring(0, indexLastDash - 1);
        String name = mAlbumModel.getTitle().substring(indexLastDash + 2);
        txtAlbumName.setText(title);
        txtArtistName.setText(name);

    }

    @Override
    public void callBack(ArrayList<DetailAlbumModel> model) {
        mDetailAlbumData.addAll(model);
        mDetailAlbumAdapter.notifyDataSetChanged();
        mDetailAlbumData.size();
        BaseActivity.mPlayService.setListAlbum(mDetailAlbumData);
    }


    @Override
    public void onItemClick(View v, int position) {
        BaseActivity.mPlayService.playIndex(position);
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
