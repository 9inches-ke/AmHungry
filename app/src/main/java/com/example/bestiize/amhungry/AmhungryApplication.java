package com.example.bestiize.amhungry;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.example.bestiize.amhungry.manager.MenuManager;
import com.example.bestiize.amhungry.manager.NewsManager;
import com.example.bestiize.amhungry.models.CategoryMenu;
import com.example.bestiize.amhungry.models.Menu;
import com.example.bestiize.amhungry.models.News;
import com.example.bestiize.amhungry.services.http.AmhungryHTTPService;
import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Bestiize on 15/9/2558.
 */
public class AmhungryApplication extends Application{
    private NewsManager newsManager;
    private MenuManager menuManager;
    private AmhungryHTTPService amhungryHTTPService;

   // private httpservice


    public AmhungryHTTPService getAmhungryHTTPService() {
        return amhungryHTTPService;
    }

    public void setAmhungryHTTPService(AmhungryHTTPService amhungryHTTPService) {
        this.amhungryHTTPService = amhungryHTTPService;
    }

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
        this.amhungryHTTPService = new AmhungryHTTPService(this) ;



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

        menuManager =new MenuManager(getApplicationContext());


        menuManager.addMenu(new Menu("Home",R.drawable.ic_home));
        menuManager.addMenu(new Menu("Profile",R.drawable.ic_configprofile));
        menuManager.addMenu(new Menu("Facebook",R.drawable.ic_facebook));
        menuManager.addMenu(new CategoryMenu("Find"));
        menuManager.addMenu(new Menu("Seafood",R.drawable.ic_seafood));
        menuManager.addMenu(new Menu("Suggestion",R.drawable.ic_lke));
        menuManager.addMenu(new Menu("Popular",R.drawable.ic_heart));
        menuManager.addMenu(new CategoryMenu("Community"));
        menuManager.addMenu(new Menu("Share",R.drawable.ic_share));
        menuManager.addMenu(new Menu("Photo",R.drawable.ic_photo));





        Log.d("NewsManager","Application inited");

    }
    public NewsManager getNewsManager() {
        return newsManager;
    }

    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public void setMenuManager(MenuManager menuManager) {
        this.menuManager = menuManager;
    }
}
