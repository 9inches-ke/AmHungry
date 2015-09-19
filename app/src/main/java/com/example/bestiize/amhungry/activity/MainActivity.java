package com.example.bestiize.amhungry.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.bestiize.amhungry.AmhungryApplication;
import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.models.News;
import com.facebook.Profile;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private AmhungryApplication app;
    private ListView news_listView;
    private NewsListAdapter newsListAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile= Profile.getCurrentProfile();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        app = (AmhungryApplication) getApplicationContext();
//        app.getNewsManager().getNewsList();
        mToolbar.setTitle("AmHungry");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DrawerLayout drawer = (DrawerLayout) inflater.inflate(R.layout.decor, null); // "null" is important.

        // HACK: "steal" the first child of decor view
        ViewGroup decor = (ViewGroup) getWindow().getDecorView();
        View child = decor.getChildAt(0);
        decor.removeView(child);
        FrameLayout container = (FrameLayout) drawer.findViewById(R.id.container); // This is the container we defined just now.
        container.addView(child);

        // Make the drawer replace the first child
        decor.addView(drawer);
        news_listView = (ListView)findViewById(R.id.list_news);
        newsListAdapter  = new NewsListAdapter(getApplicationContext());
        news_listView.setDivider(null);
        news_listView.setAdapter(newsListAdapter);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,mToolbar,R.string.drawer_open,R.string.drawer_close){
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();

            }
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();

            }
            public void onDrawerSlide(View drawerView,float slideOffset){
                super.onDrawerSlide(drawerView,slideOffset);
                mToolbar.setAlpha(1-slideOffset/2);
            }

        };
        drawer.setDrawerListener(actionBarDrawerToggle);
        drawer.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });

        Uri pictureUri= profile.getProfilePictureUri(200,200);

       // Log.d("uri_profile",pictureUri.toString());
       // pictureUri.toString();
        //((AmhungryApplication) getApplicationContext()).getNewsManager().addNews(new News(pictureUri.toString()));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
