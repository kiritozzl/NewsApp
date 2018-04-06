package com.example.kirito.newsapp.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kirito.newsapp.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirito on 2018.04.03.
 */

public class JsonHelper {
    private static final String TAG = "JsonHelper";

    public static List<News> parseToList(String content){
        List<News> news = new ArrayList<>();
        JSONObject json = JSON.parseObject(content);

        boolean hasNext = false;
        if (json != null){
            hasNext = json.getBoolean("hasNext");
        }
        if (hasNext){
            JSONArray data = json.getJSONArray("data");

            for (int i = 0; i < data.size(); i++) {
                News news1 = new News();
                JSONObject obj = data.getJSONObject(i);
                news1.setTitle(obj.getString("title"));
                news1.setUrl(obj.getString("url"));
                news1.setId(obj.getString("id"));
                JSONArray imgs = obj.getJSONArray("imageUrls");
                if (imgs != null){
                    String img = imgs.getString(0);
                    news1.setImgUrl(img);
                }

                news.add(news1);
            }

        }
        return news;
    }
}
