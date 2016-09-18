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
import com.edu.gvn.jsoupdemo.model.online.HotMusicModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HuuTho on 9/18/2016.
 */
public class HotMusicAdapter extends RecyclerView.Adapter<HotMusicAdapter.ViewHolder> {

    private ArrayList<HotMusicModel> mData;
    private Context context;
    private LayoutInflater inflater;


    public HotMusicAdapter(Context context,ArrayList<HotMusicModel> mData) {
        this.mData = mData;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_hot_music,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HotMusicModel model = mData.get(position);
        String desc = model.getDesc();
        int lastIndexDash = desc.lastIndexOf("-");
        String title = desc.substring(0, lastIndexDash - 1);
        String content = desc.substring(lastIndexDash + 1);

        holder.txtTitle.setText(title);
        holder.txtContent.setText(content);
        Picasso.with(context).load(mData.get(position).getImg_src()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        Log.i("tha thu", "getItemCount: " + mData.size());
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtTitle, txtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_album_hot);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title_album_hot);
            txtContent = (TextView) itemView.findViewById(R.id.txt_content_album_hot);
        }
    }

    public void addData(ArrayList<HotMusicModel> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();

    }

}
