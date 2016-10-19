package com.edu.gvn.jsoupdemo.fragment.online.artist;


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
import com.edu.gvn.jsoupdemo.adapter.AlbumDetailAdapter;
import com.edu.gvn.jsoupdemo.adapter.ArtistListSongAdapter;
import com.edu.gvn.jsoupdemo.common.ILoadMoreOnListener;
import com.edu.gvn.jsoupdemo.fragment.PlayerFragment;
import com.edu.gvn.jsoupdemo.model.online.ArtistSongsModel;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.ArtistSongsAsync;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongOfArtistFragment extends Fragment {

    public static final String _BUNDLE_URL = "bundle.url";
    public static final String _BUNDLE_NAME = "bundle.name";
    public static final String _BUNDLE_IMAGE = "bundle.image";
    private ImageView mCoverArtist, mAvatar;
    private TextView mArtistName, mHide;
    private ProgressBar mLoading;
    private RecyclerView mListSong;

    private String mName, mUrlImage, mUrlArtist;

    private int indexPage = 1;
    public ArrayList<ArtistSongsModel> listSong;
    public ArtistListSongAdapter mArtistListSongAdapter;


    public static SongOfArtistFragment newInstance(String urlArtist, String name, String urlImage) {
        Bundle args = new Bundle();
        args.putString(_BUNDLE_URL, urlArtist);
        args.putString(_BUNDLE_NAME, name);
        args.putString(_BUNDLE_IMAGE, urlImage);
        SongOfArtistFragment fragment = new SongOfArtistFragment();
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
        listSong = new ArrayList<>();
        sendRequest(mUrlArtist, indexPage);
        mArtistListSongAdapter = new ArtistListSongAdapter(getActivity(), listSong);
        if (listSong.size() == 0) mLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_song_of_artist, container, false);
        mCoverArtist = (ImageView) v.findViewById(R.id.fragment_detail_album_image_album);
        mAvatar = (ImageView) v.findViewById(R.id.imageView);
        mLoading = (ProgressBar) v.findViewById(R.id.loading);
        mListSong = (RecyclerView) v.findViewById(R.id.fragment_detail_album_list_song_of_album);
        mArtistName = (TextView) v.findViewById(R.id.fragment_detail_album_name);
        mHide = (TextView) v.findViewById(R.id.fragment_detail_album_artist_name);
        mHide.setVisibility(View.GONE);


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Picasso.with(getActivity()).load(mUrlImage).placeholder(R.drawable.background_nav).into(mCoverArtist);
        mArtistName.setText(mName);

        mListSong.setAdapter(mArtistListSongAdapter);
        mListSong.setLayoutManager(new LinearLayoutManager(getActivity()));
        mArtistListSongAdapter.notifyDataSetChanged();


        mArtistListSongAdapter.setOnLoadMoreListener(new ILoadMoreOnListener() {
            @Override
            public void onLoadListener() {
                indexPage++;
                sendRequest(mUrlArtist, indexPage);
            }
        });


        mArtistListSongAdapter.setOnItemClick(new AlbumDetailAdapter.IRecyclerMenuItemClick() {
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
        });
    }

    private void getDataBundle(Bundle saveInstanceState) {
        mName = saveInstanceState.getString(_BUNDLE_NAME);
        mUrlImage = saveInstanceState.getString(_BUNDLE_IMAGE);
        mUrlArtist = saveInstanceState.getString(_BUNDLE_URL);
    }

    private ArtistSongsAsync.ArtistSongsAsyncCallBack songsAsyncCallBack = new ArtistSongsAsync.ArtistSongsAsyncCallBack() {
        @Override
        public void callBack(ArrayList<ArtistSongsModel> list) {
            if (listSong.size() != 0) listSong.remove(listSong.size() - 1);

            listSong.addAll(list);
            mArtistListSongAdapter.setNotifiDataChange();
            mLoading.setVisibility(View.GONE);


            ArrayList<DetailAlbumModel> detailAlbumModels = new ArrayList<>();
            for (int i=0;i<listSong.size();i++){
                DetailAlbumModel model = new DetailAlbumModel(String.valueOf(i),listSong.get(i).getDataIdSong(),listSong.get(i).getTitle());
                detailAlbumModels.add(model);
            }

            BaseActivity.mPlayService.setListAlbum(detailAlbumModels);
        }

    };

    private void sendRequest(String url, int indexPage) {
        ArtistSongsAsync artistSongsAsync = new ArtistSongsAsync(getActivity(), songsAsyncCallBack);
        artistSongsAsync.execute(url, String.valueOf(indexPage));
    }
}
