package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.adapter.ArtistListAdapter;
import com.edu.gvn.jsoupdemo.common.ILoadMoreOnListener;
import com.edu.gvn.jsoupdemo.common.IRecyclerViewOnItemClickListener;
import com.edu.gvn.jsoupdemo.model.online.ArtistItemModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.ListArtistAsync;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListArtistFragment extends Fragment implements ListArtistAsync.IArtistCallBack, ILoadMoreOnListener {
    public static final String _BUNDLE_ARTIST = "bundle.artist";
    private final static int NUMBER_COLUMN = 2;

    private String mUrlListArtist;
    private EditText mSearch;
    private RecyclerView mArtistList ;
    private ArtistListAdapter mArtistAdapter ;
    private ArrayList<ArtistItemModel> mArtistData;

    private int indexPage = 1;
    private ProgressBar mLoading ;

    public static ListArtistFragment newInstance(String genderArtist) {
        Bundle args = new Bundle();
        args.putString(_BUNDLE_ARTIST, genderArtist);
        ListArtistFragment fragment = new ListArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) mUrlListArtist = getDataBundle(getArguments());
        else mUrlListArtist = getDataBundle(savedInstanceState);

        senRequestArtist(mUrlListArtist,String.valueOf(indexPage));
        mArtistData = new ArrayList<>();
        mArtistAdapter = new ArtistListAdapter(getActivity(),mArtistData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_artist, container, false);
        mSearch = (EditText) v.findViewById(R.id.fragment_list_artist_search_view);
        mArtistList = (RecyclerView) v.findViewById(R.id.fragment_list_artist_rv);
        mLoading = (ProgressBar) v.findViewById(R.id.loading);

        mArtistAdapter.setOnItemClickListener(onItemClickListener);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArtistList.setLayoutManager(new StaggeredGridLayoutManager(NUMBER_COLUMN,StaggeredGridLayoutManager.VERTICAL));
        mArtistList.setAdapter(mArtistAdapter);
        mArtistAdapter.notifyDataSetChanged();
        mArtistAdapter.setOnLoadMoreListener(this);


        if (mArtistData.size() ==0){
            mLoading.setVisibility(View.VISIBLE);
        }



    }

    private String getDataBundle(Bundle saveInstanceState) {
        return  saveInstanceState.getString(_BUNDLE_ARTIST);
    }

    private void senRequestArtist(String baseUrl, String indexPage){
      new ListArtistAsync(getActivity(),this).execute(baseUrl,indexPage);
    }

    @Override
    public void callBack(ArrayList<ArtistItemModel> lists) {
        if (lists.size() !=0){
            lists.remove(lists.size()-1);
            mArtistData.addAll(lists);
            mArtistAdapter.setNotifiDataChange();
            mLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadListener() {
        indexPage ++ ;
        senRequestArtist(mUrlListArtist,String.valueOf(indexPage));
    }

    IRecyclerViewOnItemClickListener onItemClickListener = new IRecyclerViewOnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            
        }
    };
}
