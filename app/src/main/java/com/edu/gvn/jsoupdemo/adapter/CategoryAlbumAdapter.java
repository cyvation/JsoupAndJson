package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.model.online.CategoryAlbumModel;

import java.util.ArrayList;

/**
 * Created by hnc on 05/10/2016.
 */

public class CategoryAlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_VIEW = 0;
    public static final int TYPE_TITLE = 1;

    private Context context;
    private ArrayList<CategoryAlbumModel> mData;
    public ICategoryItemOnClick itemOnClick ;

    public CategoryAlbumAdapter(Context context, ArrayList<CategoryAlbumModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setOnItemClick(ICategoryItemOnClick onItemClick){
        this.itemOnClick = onItemClick;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_VIEW) {
            return new CategoryAlbumAdapter.ItemCategory(inflater.inflate(R.layout.item_category_content, parent, false));
        } else {
            return new CategoryAlbumAdapter.TitleCategory(inflater.inflate(R.layout.item_category_title, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_VIEW) {
            ((ItemCategory) holder).image.setImageResource(mData.get(position).getImage());
            ((ItemCategory) holder).txtSubTitle.setText(mData.get(position).getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClick.onItemClick(holder.itemView,holder.getAdapterPosition());
                }
            });

        } else {
            ((TitleCategory) holder).txtTitle.setText(mData.get(position).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getView();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private static class ItemCategory extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView txtSubTitle;

        private ItemCategory(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_category_content);
            txtSubTitle = (TextView) itemView.findViewById(R.id.txt_category_content_sub_title);
        }

    }

    private static class TitleCategory extends RecyclerView.ViewHolder {
        public TextView txtTitle;

        private TitleCategory(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_category_title);
        }
    }

    public interface ICategoryItemOnClick{
        void onItemClick(View v, int position);
    }
}
