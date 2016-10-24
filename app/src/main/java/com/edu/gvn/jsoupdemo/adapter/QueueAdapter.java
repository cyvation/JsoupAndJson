package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;

import java.util.ArrayList;

/**
 * Created by HuuTho on 10/23/2016.
 */

public class QueueAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DetailAlbumModel> datas;
    private LayoutInflater inflater;
    private int indexSong = - 1;

    public QueueAdapter(Context context, ArrayList<DetailAlbumModel> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_queue, parent, false);
        }
        final TextView txtOrder = ((TextView) (convertView.findViewById(R.id.txt_order_queue)));
        final TextView txtNameSong = ((TextView) (convertView.findViewById(R.id.txt_name_song_queue)));
        txtOrder.setText(datas.get(position).getmOrder());
        txtNameSong.setText(datas.get(position).getmSongName());

        if (datas.get(position).isNowPlay()) {
            txtOrder.setTextColor(Color.GREEN);
            txtNameSong.setTextColor(Color.GREEN);
        }


        return convertView;
    }

}
