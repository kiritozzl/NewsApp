package com.example.kirito.newsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirito.newsapp.R;
import com.example.kirito.newsapp.entity.News;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by kirito on 2018.04.03.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private LayoutInflater inflater;
    private int resource;
    private ImageLoader loader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.no_image)
            .showImageOnFail(R.drawable.no_image)
            .showImageForEmptyUri(R.drawable.no_image)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();

    public NewsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.inflater = LayoutInflater.from(context);
        this.resource = resource;
        loader.init(ImageLoaderConfiguration.createDefault(context));
    }

    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resource = resource;
        loader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(resource,null);
            holder = new viewHolder();
            holder.newsImg = convertView.findViewById(R.id.news_image);
            holder.newsTv = convertView.findViewById(R.id.news_title);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        News news = getItem(position);
        holder.newsTv.setText(news.getTitle());
        if(news.getImgUrl() != null){
            loader.displayImage(news.getImgUrl(),holder.newsImg,options);
        }
        return convertView;
    }

    class viewHolder{
        ImageView newsImg;
        TextView newsTv;
    }

    public void refreshNewsList(List<News> newsList) {
        clear();
        addAll(newsList);
        notifyDataSetChanged();
    }
}
