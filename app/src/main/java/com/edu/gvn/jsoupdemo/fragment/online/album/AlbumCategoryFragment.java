package com.edu.gvn.jsoupdemo.fragment.online.album;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.CategoryAlbumAdapter;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.model.online.CategoryAlbumModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumCategoryFragment extends Fragment implements CategoryAlbumAdapter.ICategoryItemOnClick {

    private RecyclerView mListCategory;
    private CategoryAlbumAdapter mCategoryAdapter;
    private ArrayList<CategoryAlbumModel> mCategoryData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryData = new ArrayList<>();
        mCategoryAdapter = new CategoryAlbumAdapter(getActivity(), mCategoryData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_album, container, false);
        mListCategory = (RecyclerView) v.findViewById(R.id.recycler_category);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (mCategoryData.get(position).getView() == CategoryAlbumAdapter.TYPE_TITLE) ? layoutManager.getSpanCount() : 1;
            }
        });

        mListCategory.setLayoutManager(layoutManager);
        mListCategory.setAdapter(mCategoryAdapter);

        setData(mCategoryData);
        mCategoryAdapter.notifyDataSetChanged();
        mCategoryAdapter.setOnItemClick(this);
    }

    public void setData(ArrayList<CategoryAlbumModel> mCategoryData) {
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_TITLE, "Vietnam", -1,null));

        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "V-pop", R.drawable.album_vn_nhac_tre ,Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRE));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Romance", R.drawable.album_vn_nhac_tru_tinh,Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRU_TINH));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Rap-HipHop", R.drawable.album_vn_rap_hiphop,Mp3ZingBaseUrl.ALBUMS_VN_RAP_HIPHOP));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Trinh Cong Son", R.drawable.album_vn_nhac_trinh,Mp3ZingBaseUrl.ALBUMS_VN_NHAC_TRINH));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Childrent's", R.drawable.album_vn_nhac_thieu_nhi,Mp3ZingBaseUrl.ALBUMS_VN_NHAC_THIEU_NHI));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Rock", R.drawable.album_vn_rock_viet,Mp3ZingBaseUrl.ALBUMS_VN_ROCK_VIET));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Dance", R.drawable.album_vn_dance,Mp3ZingBaseUrl.ALBUMS_VN_NHAC_DANCE_VIET));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Country", R.drawable.album_vn_nhac_que_huong,Mp3ZingBaseUrl.ALBUMS_VN_NHAC_QUE_HUONG));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Red music", R.drawable.album_vn_nhac_cach_mang,Mp3ZingBaseUrl.ALBUMS_VN_NHAC_CACH_MANG));

        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_TITLE, "Us-Uk", -1,null));

        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Pop", R.drawable.album_us_pop,Mp3ZingBaseUrl.ALBUMS_US_POP));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Rock", R.drawable.album_us_rock,Mp3ZingBaseUrl.ALBUMS_US_ROCK));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Rap-HipHop", R.drawable.album_us_rap_hiphop,Mp3ZingBaseUrl.ALBUMS_US_RAP_HIPHOP));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Country", R.drawable.album_us_country,Mp3ZingBaseUrl.ALBUMS_US_COUNTRY));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "EDM", R.drawable.album_us_edm,Mp3ZingBaseUrl.ALBUMS_US_ELECTRONIC_DANCE));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "R/B soul", R.drawable.album_us_rb_soul,Mp3ZingBaseUrl.ALBUMS_US_R_B_SOUL));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Trance/House/Techno", R.drawable.album_us_trance_house_techno,Mp3ZingBaseUrl.ALBUMS_US_TRANCE_HOUSE_TECHNO));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Blue/Jazz", R.drawable.album_us_blue_jazz,Mp3ZingBaseUrl.ALBUMS_US_BLUES_JAZZ));

        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_TITLE, "Asia", -1,null));

        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "K-pop", R.drawable.album_asia_korea,Mp3ZingBaseUrl.ALBUMS_ASIAN_KOREA));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Jav", R.drawable.album_asia_japan,Mp3ZingBaseUrl.ALBUMS_ASIAN_JAPAN));
        mCategoryData.add(new CategoryAlbumModel(CategoryAlbumAdapter.TYPE_VIEW, "Khựa", R.drawable.album_asia_china,Mp3ZingBaseUrl.ALBUMS_ASIAN_CHINA));
    }

    @Override
    public void onItemClick(View v, int position) {
        String genderUrl = mCategoryData.get(position).getUrl() ;
        ((HomeActivity)getActivity()).replaceFragment(GenderAlbumFragment.newInstance(genderUrl));
    }
}
