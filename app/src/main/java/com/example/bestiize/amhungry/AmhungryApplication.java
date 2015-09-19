package com.example.bestiize.amhungry;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.example.bestiize.amhungry.manager.NewsManager;
import com.example.bestiize.amhungry.models.News;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bestiize on 15/9/2558.
 */
public class AmhungryApplication extends Application{
    private NewsManager newsManager;
   // private httpservice



    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.bestiize.amhungry",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("keyhashamhungry:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }



        newsManager =new NewsManager(getApplicationContext());
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));
        newsManager.addNews(new News("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/09/16/5f17b2e1663845afa5602109d82b7613.jpg"));


        Log.d("NewsManager","Application inited");

    }
    public NewsManager getNewsManager() {
        return newsManager;
    }

    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }


}
