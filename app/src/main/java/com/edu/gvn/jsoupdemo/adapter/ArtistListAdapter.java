package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.common.IRecyclerViewOnItemClickListener;
import com.edu.gvn.jsoupdemo.common.ILoadMoreOnListener;
import com.edu.gvn.jsoupdemo.common.TypeView;
import com.edu.gvn.jsoupdemo.model.online.ArtistItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HuuTho on 10/13/2016.
 */

public class ArtistListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<ArtistItemModel> mData;
    private ILoadMoreOnListener onLoadMoreListener;
    private boolean isLoad;
    private IRecyclerViewOnItemClickListener onItemClickListener;


    public ArtistListAdapter(Context context, ArrayList<ArtistItemModel> mData) {
        this.mContext = context;
        this.mData = mData;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == TypeView.TITLE) {
            View v = inflater.inflate(R.layout.item_loadmore, parent, false);
            return new LoadMoreView(v);
        } else {
            View v = inflater.inflate(R.layout.item_artist, parent, false);
            return new ArtistView(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= mData.size() - 1 && !isLoad) {
            onLoadMoreListener.onLoadListener();
            isLoad = true;
        }

        if (mData.get(position).getTypeView() == TypeView.CONTENT) {
            ((ArtistView) holder).binData(mContext, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getTypeView() == TypeView.TITLE) {
            return TypeView.TITLE;
        } else {
            return TypeView.CONTENT;
        }
    }

    private class ArtistView extends RecyclerView.ViewHolder {
        protected ImageView imageArtist;
        protected TextView nameArtist;

        public ArtistView(View itemView) {
            super(itemView);
            imageArtist = (ImageView) itemView.findViewById(R.id.backgroundArtist);
            nameArtist = (TextView) itemView.findViewById(R.id.txt_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,getAdapterPosition());
                }
            });
        }

        private void binData(Context context, ArtistItemModel artistItemModel) {
            Picasso.with(context)
                    .load(artistItemModel.getImgSrc())
                    .placeholder(R.drawable.background_nav)
                    .into(imageArtist);
            nameArtist.setText(mData.get(getAdapterPosition()).dataName);
        }
    }

    private class LoadMoreView extends RecyclerView.ViewHolder {
        protected ProgressBar loadMore;
        public LoadMoreView(View itemView) {
            super(itemView);
            loadMore = (ProgressBar) itemView.findViewById(R.id.progressbar);
        }
    }

    public void setOnLoadMoreListener(ILoadMoreOnListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setNotifiDataChange() {
        notifyDataSetChanged();
        isLoad = false;
    }

    public void setOnItemClickListener(IRecyclerViewOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
