package com.example.kirito.newsapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.example.kirito.newsapp.entity.FavoriteActivity;
import com.example.kirito.newsapp.entity.NewsItemListFragment;

public class MainActivity extends AppCompatActivity {
    private static final int PAGE_COUNT = 7;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private PagerAdapter adapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.viewpager);

        pager.setOffscreenPageLimit(PAGE_COUNT);
        adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favourite_menu){
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return NewsItemListFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position+1){
                case 1:
                    title = "娱乐";
                    break;
                case 2:
                    title = "军事";
                    break;
                case 3:
                    title = "体育";
                    break;
                case 4:
                    title = "经济";
                    break;
                case 5:
                    title = "历史";
                    break;
                case 6:
                    title = "汽车";
                    break;
                case 7:
                    title = "时尚";
                    break;
            }
            return title;
        }
    }
}
