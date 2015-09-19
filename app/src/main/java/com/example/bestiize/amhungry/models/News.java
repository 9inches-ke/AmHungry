package com.example.bestiize.amhungry.models;

/**
 * Created by Bestiize on 9/17/2015.
 */
public class News {
    private String newsURL;

    public News(){


    }
    public News(String newsURL){
        this.newsURL=newsURL;

    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }
}
