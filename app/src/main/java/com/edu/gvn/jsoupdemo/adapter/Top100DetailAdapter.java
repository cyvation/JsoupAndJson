package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.common.Mp3ZingBaseUrl;
import com.edu.gvn.jsoupdemo.model.online.Top100;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hnc on 21/10/2016.
 */

public class Top100DetailAdapter extends RecyclerView.Adapter<Top100DetailAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Top100.ItemSong> top100Data;
    private AlbumDetailAdapter.IRecyclerMenuItemClick recyclerMenuItemClick ;

    public Top100DetailAdapter(Context context, ArrayList<Top100.ItemSong> top100Data) {
        this.context = context;
        this.top100Data = top100Data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_top100, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        txtOrder.setText(top100Data.get(position).order);
        txtSongName.setText(top100Data.get(position).name);
        txtArtistName.setText(top100Data.get(position).artist);

        StringBuilder imgBuilder = new StringBuilder();
        imgBuilder.append(Mp3ZingBaseUrl.LINK_IMAGE_BASE);
        imgBuilder.append(top100Data.get(position).thumb);

        Picasso.with(context)
                .load(imgBuilder.toString())
                .placeholder(R.drawable.background_nav)
                .into(imgSrc);
    }

    @Override
    public int getItemCount() {
        return top100Data.size();
    }

    private TextView txtOrder, txtSongName, txtArtistName;
    private ImageView imgSrc, imgDownload, imgAdd, imgToPlayer;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            txtOrder = (TextView) itemView.findViewById(R.id.txt_order);
            txtSongName = (TextView) itemView.findViewById(R.id.txt_song_name);
            txtArtistName = (TextView) itemView.findViewById(R.id.txt_artist);

            imgSrc = (ImageView) itemView.findViewById(R.id.img_song);
            imgDownload = (ImageView) itemView.findViewById(R.id.btn_download);
            imgAdd = (ImageView) itemView.findViewById(R.id.btn_add);
            imgToPlayer = (ImageView) itemView.findViewById(R.id.btn_to_player);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerMenuItemClick.onItemClick(v,getAdapterPosition());
                }
            });

            imgDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerMenuItemClick.onDownloadClick(v,getAdapterPosition());
                }
            });

            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerMenuItemClick.onAddClick(v,getAdapterPosition());
                }
            });

            imgToPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerMenuItemClick.onToPlayerClick(v,getAdapterPosition());
                }
            });
        }
    }

    public void setMenuItemClickListener(AlbumDetailAdapter.IRecyclerMenuItemClick iRecyclerMenuItemClick){
        this.recyclerMenuItemClick = iRecyclerMenuItemClick;
    }
}
