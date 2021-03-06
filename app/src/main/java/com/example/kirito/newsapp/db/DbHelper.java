package com.example.kirito.newsapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kirito on 2016/9/6.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "daily_news.db";
    public static final String TABLE_NAME = "daily_news_favorite";
    public static final String COLUMN_NEWS_ID = "news_id";
    public static final String COLUMN_NEWS_IMG = "news_img";
    public static final String COLUMN_NEWS_TITLE = "news_title";
    public static final String COLUMN_NEWS_URL = "news_url";
    public static final String COLUMN_ID = "id";
    public static final int VERSION = 1;

    public static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_NAME
            +"(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_NEWS_ID + " TEXT UNIQUE, "
            +COLUMN_NEWS_IMG  + " TEXT, "
                    +COLUMN_NEWS_URL  + " TEXT, "
            +COLUMN_NEWS_TITLE + " TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
