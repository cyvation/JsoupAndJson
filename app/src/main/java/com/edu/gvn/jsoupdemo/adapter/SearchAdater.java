package com.edu.gvn.jsoupdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.model.online.SearchModel;

import java.util.ArrayList;

/**
 * Created by hnc on 14/09/2016.
 */
public class SearchAdater extends ArrayAdapter<SearchModel.Song> {

    private LayoutInflater inflater;
    private ArrayList<SearchModel.Song> mData;

    public SearchAdater(Context context, int resource, ArrayList<SearchModel.Song> data) {
        super(context, resource, data);
        this.mData = data;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_search,parent,false);
        }
        TextView txtSongName = (TextView) convertView.findViewById(R.id.txt_song_name);
        TextView txtSongArtist = (TextView) convertView.findViewById(R.id.txt_artist);

        String nameSong = mData.get(position).mName;
        String nameArtist = mData.get(position).mArtist;

        txtSongName.setText(nameSong);
        txtSongArtist.setText(nameArtist);
        return convertView;
    }
}
