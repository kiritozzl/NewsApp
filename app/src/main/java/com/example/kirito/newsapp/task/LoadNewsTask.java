package com.example.kirito.newsapp.task;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.kirito.newsapp.adapter.NewsAdapter;
import com.example.kirito.newsapp.entity.News;
import com.example.kirito.newsapp.http.Http;
import com.example.kirito.newsapp.http.JsonHelper;

import java.util.List;

/**
 * Created by kirito on 2018.04.03.
 */

public class LoadNewsTask extends AsyncTask<Integer, Void, List<News>> {
    private NewsAdapter adapter;
    private onFinishListener listener;
    private static final String TAG = "LoadNewsTask";

    public LoadNewsTask(NewsAdapter adapter) {
        super();
        this.adapter = adapter;
    }

    public LoadNewsTask(NewsAdapter adapter, onFinishListener listener) {
        super();
        this.adapter = adapter;
        this.listener = listener;
    }

    @Override
    protected List<News> doInBackground(Integer... integers) {
        try {
            Thread.currentThread();
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<News> newsList = JsonHelper.parseToList(Http.getNewsList(integers[0]));
        return newsList;
    }

    @Override
    protected void onPostExecute(List<News> newsList) {
        if (newsList != null)
            adapter.refreshNewsList(newsList);
        if (listener != null) {
            listener.afterTaskFinish();
        }

    }

    public interface onFinishListener {
        public void afterTaskFinish();
    }
}
