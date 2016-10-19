package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.common.ILoadMoreOnListener;
import com.edu.gvn.jsoupdemo.common.TypeView;
import com.edu.gvn.jsoupdemo.model.online.ArtistSongsModel;

import java.util.ArrayList;

/**
 * Created by hnc on 19/10/2016.
 */

public class ArtistListSongAdapter extends RecyclerView.Adapter {
    public Context context;
    private ArrayList<ArtistSongsModel> artistSongsModels;

    private boolean isLoading;
    private ILoadMoreOnListener onLoadMoreListener;
    public AlbumDetailAdapter.IRecyclerMenuItemClick itemClickListener;

    public ArtistListSongAdapter(Context context, ArrayList<ArtistSongsModel> artistSongsModels) {
        this.context = context;
        this.artistSongsModels = artistSongsModels;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TypeView.CONTENT) {
            return new ArtistListSongAdapter.ArtistSongHolder(inflater.inflate(R.layout.item_artist_list_song, parent, false));
        } else {
            return new ArtistListSongAdapter.LoadMoreViewHolder(inflater.inflate(R.layout.item_loadmore, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,  int position) {
        if (position >= getItemCount() - 1 && !isLoading) {
            isLoading = true;
            onLoadMoreListener.onLoadListener();
        }
        if (getItemViewType(position) == TypeView.CONTENT) {
            ((ArtistListSongAdapter.ArtistSongHolder) holder).binData(artistSongsModels.get(position).getTitle());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistSongsModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (artistSongsModels.get(position).getView() == TypeView.CONTENT) {
            return TypeView.CONTENT;
        } else {
            return TypeView.TITLE;
        }
    }

    private  class ArtistSongHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        private ImageView download, add, toPlayer;
        private ArtistSongHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.fragment_detail_album_song_name);

            download = (ImageView) itemView.findViewById(R.id.btn_download);
            toPlayer = (ImageView) itemView.findViewById(R.id.btn_to_player);
            add = (ImageView) itemView.findViewById(R.id.btn_add);

            iconOfItemViewClick();
        }

        private void binData(String title) {
            txtTitle.setText(title);
        }

        private void iconOfItemViewClick() {

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onDownloadClick(v, getAdapterPosition());
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onAddClick(v, getAdapterPosition());
                }
            });

            toPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onToPlayerClick(v, getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }

    private static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        private LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnLoadMoreListener(ILoadMoreOnListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setNotifiDataChange() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public void setOnItemClick(AlbumDetailAdapter.IRecyclerMenuItemClick itemClick) {
        this.itemClickListener = itemClick;
    }

}
