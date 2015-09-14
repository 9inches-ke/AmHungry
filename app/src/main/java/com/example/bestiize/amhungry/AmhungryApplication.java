package com.example.bestiize.amhungry;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Bestiize on 15/9/2558.
 */
public class AmhungryApplication extends Application{
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());


    }


}
