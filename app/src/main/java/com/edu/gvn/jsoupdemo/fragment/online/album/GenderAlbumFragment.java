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
import android.widget.ProgressBar;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.GenderAlbumAdapter;
import com.edu.gvn.jsoupdemo.fragment.BaseFragment;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.GenderAlbumAsync;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenderAlbumFragment extends BaseFragment {
    private static final String TAG = GenderAlbumFragment.class.getSimpleName();
    public static final String KEY_ALBUM = "key_album";
    private final static int NUMBER_COLUMN = 2;
    private RecyclerView mListAlbum;
    private GenderAlbumAdapter mAlbumAdapter;
    private ArrayList<AlbumModel> mGenderAlbumData;
    private ProgressBar mLoading ;

    private String urlAlbum;
    private int indexPage = 1;

    public static GenderAlbumFragment newInstance(String urlAlbum) {
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

        mGenderAlbumData = new ArrayList<>();
        sendRequestAlbum(urlAlbum, indexPage);
        mAlbumAdapter = new GenderAlbumAdapter(getActivity(), mGenderAlbumData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_onl_album_all_album_fragment, container, false);
        mListAlbum = (RecyclerView) v.findViewById(R.id.rv_fragment_onl_album_albumfragment);
        mLoading = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListAlbum.setLayoutManager(new GridLayoutManager(getActivity(),NUMBER_COLUMN));

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


        mAlbumAdapter.setOnItemClickListener(new GenderAlbumAdapter.GenderAlbumOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.i(TAG, "onItemClick: " + position + "--");

                ((HomeActivity) getActivity()).replaceFragment(DetailAlbumFragment.newInstance(mGenderAlbumData.get(position)));
            }
        });

        if (mGenderAlbumData.size()!=0) mLoading.setVisibility(View.INVISIBLE);
    }

    public void sendRequestAlbum(String url, int indexPage) {
        GenderAlbumAsync genderAlbumAsync = new GenderAlbumAsync(albumDataCallback);
        genderAlbumAsync.execute(url, String.valueOf(indexPage));
    }

    public GenderAlbumAsync.GenderAlbumDataCallback albumDataCallback = new GenderAlbumAsync.GenderAlbumDataCallback() {
        @Override
        public void callBack(ArrayList<AlbumModel> data) {
              if (mGenderAlbumData.size() != 0) mGenderAlbumData.remove(mGenderAlbumData.size() - 1);

            mGenderAlbumData.addAll(data);
            mAlbumAdapter.setNotifiDataChange();
            mLoading.setVisibility(View.GONE);

        }
    };

}
