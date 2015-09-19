package com.example.bestiize.amhungry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.example.bestiize.amhungry.R;

/**
 * Created by Bestiize on 9/18/2015.
 */
public class SplashActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
       countDownTimer= new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

                finish();
            }
        };
        countDownTimer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
    
}
