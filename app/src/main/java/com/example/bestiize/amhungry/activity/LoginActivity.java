package com.example.bestiize.amhungry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bestiize.amhungry.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.w3c.dom.Text;

/**
 * Created by Bestiize on 15/9/2558.
 */
public class LoginActivity extends AppCompatActivity {
    private Button login;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView mTextDetails;
    private FacebookCallback<LoginResult>  mCallback;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.login_button_amhungry);
        mTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        mProfileTracker =new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                displayWelcomeMessage(currentProfile);
            }
        };
        mTokenTracker.startTracking();
        mProfileTracker.startTracking();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();


            }
        });

        mCallback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();

//                mTextDetails.setText("user " + accessToken.getUserId());
                Profile profile = Profile.getCurrentProfile();
                displayWelcomeMessage(profile);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

                finish();

            }

            @Override
            public void onCancel() {
                Log.d("profile_me","hhhhhh");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("profile_me","hhhhhh");

            }
        };

        callbackManager =CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button_facebook);
        loginButton.setReadPermissions("public_profile");
        loginButton.registerCallback(callbackManager,mCallback);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
        AppEventsLogger.activateApp(this);

    }
    private void displayWelcomeMessage(Profile profile){


        if(profile!=null){

//            mTextDetails.setText("Welcome" + profile.getName());
            Log.d("profile_me", "welcome");

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.startTracking();

    }
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}

