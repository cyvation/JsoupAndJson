package com.edu.gvn.jsoupdemo.fragment.online.artist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.ArtistListAdapter;
import com.edu.gvn.jsoupdemo.common.ILoadMoreOnListener;
import com.edu.gvn.jsoupdemo.common.IRecyclerViewOnItemClickListener;
import com.edu.gvn.jsoupdemo.common.LogUtils;
import com.edu.gvn.jsoupdemo.model.online.ArtistItemModel;
import com.edu.gvn.jsoupdemo.network.XmlParser.ListArtistAsync;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListArtistFragment extends Fragment implements ListArtistAsync.IArtistCallBack, ILoadMoreOnListener {
    public static final String _BUNDLE_ARTIST = "bundle.artist";
    private final static int NUMBER_COLUMN = 2;

    public static final String ARTIST_DEFAULT = "default";
    public static final String ARTIST_SEARCH = "search";

    private String mUrlListArtist;
    private EditText mSearch;
    private RecyclerView mArtistList;
    private ArtistListAdapter mArtistAdapter;
    private ArrayList<ArtistItemModel> mArtistDefaultData;
    private ArrayList<ArtistItemModel> mArtistSearchData;
    private ArrayList<ArtistItemModel> mArtistBaseData = new ArrayList<>();

    private int indexDefaultPage = 1;
    private ProgressBar mLoading;

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

        senRequestArtist(ARTIST_DEFAULT, mUrlListArtist, String.valueOf(indexDefaultPage));

        mArtistAdapter = new ArtistListAdapter(getActivity(), mArtistBaseData);
        mArtistDefaultData = new ArrayList<>();
        mArtistSearchData = new ArrayList<>();
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
    public void onStart() {
        super.onStart();
        mSearch.addTextChangedListener(textWatcher);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArtistList.setLayoutManager(new StaggeredGridLayoutManager(NUMBER_COLUMN, StaggeredGridLayoutManager.VERTICAL));
        mArtistList.setAdapter(mArtistAdapter);
        mArtistAdapter.notifyDataSetChanged();
        mArtistAdapter.setOnLoadMoreListener(this);

        if (mArtistDefaultData.size() == 0) {
            mLoading.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity) getActivity()).showToolbar();

    }

    private String getDataBundle(Bundle saveInstanceState) {
        return saveInstanceState.getString(_BUNDLE_ARTIST);
    }

    private void senRequestArtist(String typeRequest, String baseUrl, String indexPage) {
        new ListArtistAsync(getActivity(), this).execute(typeRequest, baseUrl, indexPage);
    }

    @Override
    public void callBack(String type, ArrayList<ArtistItemModel> lists) {


        LogUtils.e("huutho", lists.toString());

        if (type.equals(ARTIST_DEFAULT) && lists.size() != 0) {
            lists.remove(lists.size() - 1);
            mArtistDefaultData.addAll(lists);
            mLoading.setVisibility(View.GONE);

            mArtistBaseData.clear();
            mArtistBaseData.addAll(mArtistDefaultData);
            mArtistAdapter.setNotifiDataChange();
        } else if (lists.size() != 0) {
            lists.remove(lists.size() - 1);
            mArtistSearchData.addAll(lists);
            mLoading.setVisibility(View.GONE);

            mArtistBaseData.clear();
            mArtistBaseData.addAll(mArtistSearchData);
            mArtistAdapter.setNotifiDataChange();
        }
    }

    @Override
    public void onLoadListener() {

        String search = mSearch.getText().toString();
        if (TextUtils.isEmpty(search) && search.equals("")) {
            indexDefaultPage++;
            senRequestArtist(ARTIST_DEFAULT, mUrlListArtist, String.valueOf(indexDefaultPage));
        }

    }


    private TextWatcher textWatcher = new TextWatcher() {
        private Timer timer = new Timer();
        private long DELAY = 750;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(final CharSequence s, final int start, int before, int count) {

            if (start > 0) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        senRequestArtist(s.toString(), mUrlListArtist, String.valueOf(1));
                    }
                }, DELAY);
            } else {
                timer.cancel();
                mArtistBaseData.clear();
                mArtistBaseData.addAll(mArtistDefaultData);
                mArtistAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private IRecyclerViewOnItemClickListener onItemClickListener = new IRecyclerViewOnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            ((HomeActivity) getActivity()).replaceFragment(
                    ArtistDetailFragment.newInstance(
                            mArtistBaseData.get(position).gethRef(),
                            mArtistBaseData.get(position).getDataName(),
                            mArtistBaseData.get(position).getImgSrc()));
        }
    };
}
