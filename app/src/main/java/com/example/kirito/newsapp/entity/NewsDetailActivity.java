package com.example.kirito.newsapp.entity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.kirito.newsapp.R;
import com.example.kirito.newsapp.db.DbFavNews;
import com.example.kirito.newsapp.support.Utility;

/**
 * Created by kirito on 2018.04.03.
 */

public class NewsDetailActivity extends AppCompatActivity {
    private WebView webView;
    private News news;
    private boolean isFavorite;
    private static final String TAG = "NewsDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        webView = findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);// 设置支持缩放
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        // 设置可自由缩放网
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient());

        news = (News) getIntent().getSerializableExtra("news");
        Log.e(TAG, "onCreate: ---"+ news.getUrl());
        webView.loadUrl(news.getUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favourite_menu){
            DbFavNews dbFavNews = DbFavNews.getInstance(NewsDetailActivity.this);
            isFavorite = dbFavNews.isFavorite(news);
            if (isFavorite){
                dbFavNews.deleteFavorite(news);
                Toast.makeText(NewsDetailActivity.this,"该新闻已从收藏夹中移除",Toast.LENGTH_LONG).show();
            }else {
                dbFavNews.saveFavorite(news);
                Toast.makeText(NewsDetailActivity.this,"该新闻已消息收藏",Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }

    public static void startActivity(Context context, News news) {
        if (Utility.checkNetworkConnection(context)) {
            Intent i = new Intent(context, NewsDetailActivity.class);
            i.putExtra("news", news);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Utility.noNetworkAlert(context);
        }
    }
}
