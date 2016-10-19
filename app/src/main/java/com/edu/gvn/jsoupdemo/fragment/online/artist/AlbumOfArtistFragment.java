package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.AlbumGenderAdapter;
import com.edu.gvn.jsoupdemo.common.ILoadMoreOnListener;
import com.edu.gvn.jsoupdemo.fragment.online.album.DetailAlbumFragment;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.ArtistAlbumsAsync;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumOfArtistFragment extends Fragment {
    public static final String _BUNDLE_ARTIST = "bundle.artist";
    public static final String _BUNDLE_NAME = "bundle.name";
    public static final String _BUNDLE_IMAGE = "bundle.image";

    public static final int NUMBER_COLUMN = 2;
    private String mName, mUrlImage, mUrlAritst;

    private ImageView mCoverArtist, mAvatar;
    private TextView mArtistName, mHide;
    private ProgressBar mLoading;
    private RecyclerView mListSong;

    private ArrayList<AlbumModel> listAlbum ;
    private AlbumGenderAdapter mAlbumGenderAdapter ;
    private int indexPage = 1;


    public static AlbumOfArtistFragment newInstance(String urlArtist, String name, String urlImage) {
        Bundle args = new Bundle();
        args.putString(_BUNDLE_ARTIST, urlArtist);
        args.putString(_BUNDLE_NAME, name);
        args.putString(_BUNDLE_IMAGE, urlImage);
        AlbumOfArtistFragment fragment = new AlbumOfArtistFragment();
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
        listAlbum = new ArrayList<>();
        sendRequest(mUrlAritst,indexPage);
        mAlbumGenderAdapter  = new AlbumGenderAdapter(getActivity(),listAlbum);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album_of_artist, container, false);
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


        mListSong.setLayoutManager(new GridLayoutManager(getActivity(),NUMBER_COLUMN));

        mListSong.setAdapter(mAlbumGenderAdapter);
        mListSong.setDrawingCacheEnabled(true);
        mListSong.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mAlbumGenderAdapter.setOnLoadMoreListener(new ILoadMoreOnListener() {
            @Override
            public void onLoadListener() {
                indexPage++;
                sendRequest(mUrlAritst, indexPage);
            }
        });


        mAlbumGenderAdapter.setOnItemClickListener(new AlbumGenderAdapter.GenderAlbumOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                ((HomeActivity) getActivity()).replaceFragment(DetailAlbumFragment.newInstance(listAlbum.get(position)));
            }
        });

        if (listAlbum.size()!=0) mLoading.setVisibility(View.INVISIBLE);
    }

    private void getDataBundle(Bundle saveInstanceState) {
        mName = saveInstanceState.getString(_BUNDLE_NAME);
        mUrlImage = saveInstanceState.getString(_BUNDLE_IMAGE);
        mUrlAritst = saveInstanceState.getString(_BUNDLE_ARTIST);
    }

    private ArtistAlbumsAsync.IArtistAlbumAsyncCallback albumAsyncCallback = new ArtistAlbumsAsync.IArtistAlbumAsyncCallback() {
        @Override
        public void callBack(ArrayList<AlbumModel> albumModels) {
            if (listAlbum.size() != 0) listAlbum.remove(listAlbum.size() - 1);

            listAlbum.addAll(albumModels);
            mAlbumGenderAdapter.setNotifiDataChange();
            mLoading.setVisibility(View.GONE);
        }
    };


    private void sendRequest(String url, int indexPage) {
        ArtistAlbumsAsync artistAlbumsAsync = new ArtistAlbumsAsync(getActivity(), albumAsyncCallback);
        artistAlbumsAsync.execute(url, String.valueOf(indexPage));
    }
}
