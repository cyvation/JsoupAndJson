package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.model.online.AlbumModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hnc on 21/09/2016.
 */

public class GenderAlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = GenderAlbumAdapter.class.getSimpleName();
    public static int TYPE_VIEW = 0;
    public static int TYPE_LOAD = 1;

    private boolean isLoading;
    private OnLoadMoreListener onLoadMoreListener;
    public Context context;
    private ArrayList<AlbumModel> mData;
    private GenderAlbumOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(GenderAlbumOnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    public GenderAlbumAdapter(Context context, ArrayList<AlbumModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_VIEW) {
            return new AlbumViewHolder(inflater.inflate(R.layout.item_gender_album, parent, false));
        } else {
            return new LoadMoreViewHolder(inflater.inflate(R.layout.item_loadmore, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position >= getItemCount() - 1 && !isLoading) {
            isLoading = true;
            onLoadMoreListener.onLoadListener();
        }
        if (getItemViewType(position) == TYPE_VIEW) {
            ((AlbumViewHolder) holder).binData(context, mData.get(position));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
                Log.i(TAG, "onClick: " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getView().equals(String.valueOf(TYPE_VIEW))) {
            return TYPE_VIEW;
        } else {
            return TYPE_LOAD;
        }
    }

    private static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txtTitle, txtName;

        private AlbumViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_fragment_online_gender_album);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_fragment_online_gender_album_title);
            txtName = (TextView) itemView.findViewById(R.id.txt_fragment_online_gender_album_name_artist);

        }

        private void binData(Context context, AlbumModel albumModel) {
            Picasso.with(context).load(albumModel.getImg_src()).into(image);

            int indexLastDash = albumModel.getTitle().indexOf("-");
            String title = albumModel.getTitle().substring(0, indexLastDash);
            String name = albumModel.getTitle().substring(indexLastDash + 2);
            txtTitle.setText(title);
            txtName.setText(name);
        }
    }

    private static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        private LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadListener();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setNotifiDataChange() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface GenderAlbumOnItemClickListener {
        void onItemClick(View v, int position);
    }
}
