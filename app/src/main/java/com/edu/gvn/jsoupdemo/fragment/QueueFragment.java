package com.edu.gvn.jsoupdemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.activity.BaseActivity;
import com.edu.gvn.jsoupdemo.activity.HomeActivity;
import com.edu.gvn.jsoupdemo.adapter.QueueAdapter;
import com.edu.gvn.jsoupdemo.model.online.DetailAlbumModel;

import java.util.ArrayList;

import static com.edu.gvn.jsoupdemo.activity.BaseActivity.mPlayService;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueueFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mListQueue;
    private ArrayList<DetailAlbumModel> mDatas;
    private QueueAdapter mQueueAdapter;

    private TextView txtToPlay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = new ArrayList<>();
        mQueueAdapter = new QueueAdapter(getActivity(), mDatas);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_queue, container, false);
        mListQueue = (ListView) v.findViewById(R.id.list_queue);
        txtToPlay = (TextView) v.findViewById(R.id.toPlay);

        txtToPlay.setOnClickListener(this);
        mListQueue.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListQueue.setAdapter(mQueueAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPlayService != null && mPlayService.getListData() != null)
            mDatas.addAll(mPlayService.getListData());
        mQueueAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        mDatas.clear();
        mQueueAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toPlay:
                ((HomeActivity) getActivity()).replaceFragmentWithToolbar(new PlayerFragment());
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BaseActivity.mPlayService.playIndex(position);
    }
}
