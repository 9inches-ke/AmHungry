package com.example.bestiize.amhungry.manager;

import android.content.Context;

import com.example.bestiize.amhungry.AmhungryApplication;
import com.example.bestiize.amhungry.models.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bestiize on 9/17/2015.
 */
public class NewsManager {
    private List<News> newsList ;
    private AmhungryApplication amhungryApplication;
    public NewsManager (Context context){
        this.newsList = new ArrayList<>();
        this.amhungryApplication = (AmhungryApplication) context;

    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
    public void addNews(News news){
        this.newsList.add(news);

    }
}
