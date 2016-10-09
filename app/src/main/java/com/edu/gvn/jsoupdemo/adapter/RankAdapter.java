package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.model.online.RankModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuuTho on 10/9/2016.
 */

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private Context mContext;
    private List<RankModel> mData;
    private IRecyclerRankOnClickListener onClickListener;
    private LayoutInflater inflater;

    public RankAdapter(Context context, ArrayList<RankModel> mData, IRecyclerRankOnClickListener onClickListener) {
        this.mContext = context;
        this.mData = mData;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_rank, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RankModel model = mData.get(position);

        holder.txtOrder.setText(model.order);
        holder.txtUp.setText(model.up);
        holder.txtName.setText(model.name);
        holder.txtArtist.setText(model.artis);

        try{
            int upNumber = Integer.parseInt(model.up);
            if (upNumber < 0) holder.imgUp.setImageResource(R.drawable.ic_rank_down);
            else if (upNumber > 0) holder.imgUp.setImageResource(R.drawable.ic_rank_up);
        }catch (Exception e){
            holder.imgUp.setImageResource(R.drawable.ic_rank_stand);
        }


        Picasso.with(mContext)
                .load(model.imgSrc)
                .placeholder(R.drawable.add)
                .into(holder.imgSrc);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtOrder, txtUp, txtName, txtArtist;
        private ImageView imgUp, imgSrc, imgDownload, imgAdd, imgToPlayer;

        public ViewHolder(View itemView) {
            super(itemView);
            txtOrder = (TextView) itemView.findViewById(R.id.txt_order);
            txtUp = (TextView) itemView.findViewById(R.id.txt_up);
            txtName = (TextView) itemView.findViewById(R.id.txt_song_name);
            txtArtist = (TextView) itemView.findViewById(R.id.txt_artist);

            imgUp = (ImageView) itemView.findViewById(R.id.img_up);
            imgSrc = (ImageView) itemView.findViewById(R.id.img_song);
            imgDownload = (ImageView) itemView.findViewById(R.id.btn_download);
            imgAdd = (ImageView) itemView.findViewById(R.id.btn_add);
            imgToPlayer = (ImageView) itemView.findViewById(R.id.btn_to_player);

            itemView.setOnClickListener(this);
            imgDownload.setOnClickListener(this);
            imgAdd.setOnClickListener(this);
            imgToPlayer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == itemView) onClickListener.onItemClick(v,getAdapterPosition());
            if (v == imgDownload) onClickListener.onDownloadClick();
            if (v == imgAdd) onClickListener.onAddClick();
            if (v == imgToPlayer) onClickListener.onToPlayerClick();

        }
    }

    public interface IRecyclerRankOnClickListener {
        void onItemClick(View v, int position);

        void onDownloadClick();

        void onAddClick();

        void onToPlayerClick();
    }
}
