package com.edu.gvn.jsoupdemo.fragment.online.album;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.AlbumCategoryAdapter;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.common.TypeView;
import com.edu.gvn.jsoupdemo.fragment.BaseFragment;
import com.edu.gvn.jsoupdemo.model.online.CategoryAlbumModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumCategoryFragment extends BaseFragment implements AlbumCategoryAdapter.ICategoryOnItemClick {
    private Context mContext;
    private RecyclerView mListCategory;
    private AlbumCategoryAdapter mCategoryAdapter;
    private ArrayList<CategoryAlbumModel> mCategoryData;
    private GridLayoutManager mGridLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryData = new ArrayList<>();

        mCategoryAdapter = new AlbumCategoryAdapter(mContext, mCategoryData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_album, container, false);
        mListCategory = (RecyclerView) v.findViewById(R.id.rv_category_album);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        setRecyclerItemViewType();
        mListCategory.setLayoutManager(mGridLayoutManager);
        mListCategory.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setOnItemClick(AlbumCategoryFragment.this);


    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new AddData().execute();
            }
        }, 280);

    }

    private void setRecyclerItemViewType() {
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (mCategoryData.get(position).getViewType() == TypeView.TITLE) ? mGridLayoutManager.getSpanCount() : 1;
            }
        });
    }

    @Override
    public void onItemClick(View v, int position) {
        String genderUrl = mCategoryData.get(position).getUrl();

        ((HomeActivity) getActivity()).replaceFragment(GenderAlbumFragment.newInstance(genderUrl));
    }

    private class AddData extends AsyncTask<Void, Void, Void> {
        private ArrayList<CategoryAlbumModel> data;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            data = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            data.add(new CategoryAlbumModel(TypeView.TITLE, getString(R.string.viet_nam), -1, null));

            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.v_pop),
                    R.drawable.album_vn_nhac_tre,
                    Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRE));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_romance),
                    R.drawable.album_vn_nhac_tru_tinh,
                    Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRU_TINH));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_rap_hiphop),
                    R.drawable.album_vn_rap_hiphop,
                    Mp3ZingBaseUrl.ALBUMS_VN_RAP_HIPHOP));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_trinh_cong_son),
                    R.drawable.album_vn_nhac_trinh,
                    Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRINH));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_chidrent),
                    R.drawable.album_vn_nhac_thieu_nhi,
                    Mp3ZingBaseUrl.ALBUMS_VN_NHAC_THIEU_NHI));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_rock),
                    R.drawable.album_vn_rock_viet,
                    Mp3ZingBaseUrl.ALBUMS_VN_ROCK_VIET));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_dance),
                    R.drawable.album_vn_dance,
                    Mp3ZingBaseUrl.ALBUMS_VN_NHAC_DANCE_VIET));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_country),
                    R.drawable.album_vn_nhac_que_huong,
                    Mp3ZingBaseUrl.ALBUMS_VN_NHAC_QUE_HUONG));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.vn_red),
                    R.drawable.album_vn_nhac_cach_mang,
                    Mp3ZingBaseUrl.ALBUMS_VN_NHAC_CACH_MANG));

            data.add(new CategoryAlbumModel(TypeView.TITLE, getString(R.string.us_uk), -1, null));

            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_pop),
                    R.drawable.album_us_pop,
                    Mp3ZingBaseUrl.ALBUMS_US_POP));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_rok),
                    R.drawable.album_us_rock,
                    Mp3ZingBaseUrl.ALBUMS_US_ROCK));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_rap_hiphop),
                    R.drawable.album_us_rap_hiphop,
                    Mp3ZingBaseUrl.ALBUMS_US_RAP_HIPHOP));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_country),
                    R.drawable.album_us_country,
                    Mp3ZingBaseUrl.ALBUMS_US_COUNTRY));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_edm),
                    R.drawable.album_us_edm,
                    Mp3ZingBaseUrl.ALBUMS_US_ELECTRONIC_DANCE));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_r_b),
                    R.drawable.album_us_rb_soul,
                    Mp3ZingBaseUrl.ALBUMS_US_R_B_SOUL));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_trance_house_techno),
                    R.drawable.album_us_trance_house_techno,
                    Mp3ZingBaseUrl.ALBUMS_US_TRANCE_HOUSE_TECHNO));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.us_blue_jazz),
                    R.drawable.album_us_blue_jazz,
                    Mp3ZingBaseUrl.ALBUMS_US_BLUES_JAZZ));

            data.add(new CategoryAlbumModel(TypeView.TITLE, getString(R.string.asia), -1, null));

            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.k_pop), R.drawable.album_asia_korea,
                    Mp3ZingBaseUrl.ALBUMS_ASIAN_KOREA));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.jav), R.drawable.album_asia_japan,
                    Mp3ZingBaseUrl.ALBUMS_ASIAN_JAPAN));
            data.add(new CategoryAlbumModel(TypeView.CONTENT,
                    getString(R.string.china), R.drawable.album_asia_china,
                    Mp3ZingBaseUrl.ALBUMS_ASIAN_CHINA));

            mCategoryData.addAll(data);
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCategoryAdapter.notifyDataSetChanged();
        }
    }
}
