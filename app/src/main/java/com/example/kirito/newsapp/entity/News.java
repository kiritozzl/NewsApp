package com.example.kirito.newsapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirito on 2018.04.03.
 */

public class News implements Serializable {
    private String title;
    private String imgUrl;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public News(String title, String imgUrl, String url) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public News() {

    }

}
