package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.common.TypeView;
import com.edu.gvn.jsoupdemo.model.online.CategoryAlbumModel;

import java.util.ArrayList;

/**
 * Created by hnc on 05/10/2016.
 */

public class CategoryAlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<CategoryAlbumModel> mData;
    private ICategoryOnItemClick itemOnClick;

    public CategoryAlbumAdapter(Context context, ArrayList<CategoryAlbumModel> mData) {
        this.mContext = context;
        this.mData = mData;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return
                (viewType == TypeView.CONTENT) ?

                        (new CategoryAlbumAdapter.ItemCategory(inflater.inflate(R.layout.item_category_content, parent, false)))
                        :
                        (new CategoryAlbumAdapter.TitleCategory(inflater.inflate(R.layout.item_category_title, parent, false)));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TypeView.CONTENT:
                ((ItemCategory) holder).imgItemCategory.setImageResource(mData.get(position).getImage());
                ((ItemCategory) holder).txtSubTitleCategory.setText(mData.get(position).getTitle());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemOnClick.onItemClick(holder.itemView, holder.getAdapterPosition());
                    }
                });
                break;

            case TypeView.TITLE:
                ((TitleCategory) holder).txtCategoryTitle.setText(mData.get(position).getTitle());
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private static class ItemCategory extends RecyclerView.ViewHolder {
        private ImageView imgItemCategory;
        private TextView txtSubTitleCategory;

        private ItemCategory(View itemView) {
            super(itemView);
            imgItemCategory = (ImageView) itemView.findViewById(R.id.img_category_content);
            txtSubTitleCategory = (TextView) itemView.findViewById(R.id.txt_category_content_sub_title);
        }

    }

    private static class TitleCategory extends RecyclerView.ViewHolder {
        private TextView txtCategoryTitle;

        private TitleCategory(View itemView) {
            super(itemView);
            txtCategoryTitle = (TextView) itemView.findViewById(R.id.txt_category_title);
        }
    }

    public void setOnItemClick(ICategoryOnItemClick onItemClick) {
        this.itemOnClick = onItemClick;
    }

    public interface ICategoryOnItemClick {
        void onItemClick(View v, int position);
    }
}
