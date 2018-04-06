package com.example.kirito.newsapp.entity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kirito.newsapp.R;
import com.example.kirito.newsapp.adapter.NewsAdapter;
import com.example.kirito.newsapp.support.MyApplication;
import com.example.kirito.newsapp.support.Utility;
import com.example.kirito.newsapp.task.LoadNewsTask;

/**
 * Created by kirito on 2018.04.03.
 */

public class NewsItemListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
         AdapterView.OnItemClickListener {
    private ListView lv;
    private SwipeRefreshLayout refreshLayout;
    private NewsAdapter adapter;
    private Context context;
    private boolean isConnected;
    private int id;

    private static final String TAG = "NewsItemListFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        id = getArguments().getInt("id");
        isConnected = Utility.checkNetworkConnection(context);
    }

    public static NewsItemListFragment newInstance(int id){
        NewsItemListFragment fragment = new NewsItemListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout,container,false);
        lv = view.findViewById(R.id.lv);
        refreshLayout = view.findViewById(R.id.swip_refrash);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        adapter = new NewsAdapter(context,R.layout.listview_item);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        if (isConnected){
            new LoadNewsTask(adapter, new LoadNewsTask.onFinishListener() {
                @Override
                public void afterTaskFinish() {
                    refreshLayout.setRefreshing(false);
                }
            }).execute(id);
        }
        else Utility.noNetworkAlert(context);
        return view;
    }

    @Override
    public void onRefresh() {
        if (isConnected){
            new LoadNewsTask(adapter, new LoadNewsTask.onFinishListener() {
                @Override
                public void afterTaskFinish() {
                    refreshLayout.setRefreshing(false);
                }
            }).execute(id);
        }
        else Utility.noNetworkAlert(context);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        NewsDetailActivity.startActivity(context, adapter.getItem(i));
    }
}
