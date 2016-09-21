package com.edu.gvn.jsoupdemo.fragment.online.album;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.adapter.GenderAlbumAdapter;
import com.edu.gvn.jsoupdemo.model.online.HotMusicModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.GenderAlbumAsync;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenderAlbumFragment extends Fragment {

    public static final String KEY_ALBUM = "key_album";
    private static final String TAG = GenderAlbumFragment.class.getSimpleName();

    private RecyclerView mListAlbum;
    private GenderAlbumAdapter mAlbumAdapter;
    private ArrayList<HotMusicModel> mData;

    private String urlAlbum;
    private int indexPage = 1;

    public static GenderAlbumFragment newInstance(String urlAlbum) {
        Log.i(TAG, "newInstance: " + urlAlbum);
        GenderAlbumFragment fragment = new GenderAlbumFragment();
        Bundle args = new Bundle();
        args.putString(KEY_ALBUM, urlAlbum);
        fragment.setArguments(args);
        return fragment;
    }

    private String getBundleInstance(Bundle bundle) {
        return bundle.getString(KEY_ALBUM);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            urlAlbum = getBundleInstance(savedInstanceState);
        } else {
            urlAlbum = getBundleInstance(getArguments());
        }

        Log.i(TAG, "onCreate: " + urlAlbum);
        mData = new ArrayList<>();
        sendRequestAlbum(urlAlbum, indexPage);
        mAlbumAdapter = new GenderAlbumAdapter(getActivity(), mData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_onl_album_all_album_fragment, container, false);
        mListAlbum = (RecyclerView) v.findViewById(R.id.rv_fragment_onl_album_albumfragment);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int numberColumn = 2;
        mListAlbum.setLayoutManager(new GridLayoutManager(getActivity(),numberColumn));
        mListAlbum.setAdapter(mAlbumAdapter);
        mListAlbum.setDrawingCacheEnabled(true);
        mListAlbum.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mAlbumAdapter.setOnLoadMoreListener(new GenderAlbumAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadListener() {
                indexPage++;
                sendRequestAlbum(urlAlbum, indexPage);
            }
        });
    }

    public void sendRequestAlbum(String url, int indexPage) {
        GenderAlbumAsync genderAlbumAsync = new GenderAlbumAsync(albumDataCallback);
        genderAlbumAsync.execute(url, String.valueOf(indexPage));
    }

    public GenderAlbumAsync.GenderAlbumDataCallback albumDataCallback = new GenderAlbumAsync.GenderAlbumDataCallback() {
        @Override
        public void callBack(ArrayList<HotMusicModel> data) {
            if (mData.size() != 0) mData.remove(mData.size() - 1);

            mData.addAll(data);
            mAlbumAdapter.setNotifiDataChange();

        }
    };

}
