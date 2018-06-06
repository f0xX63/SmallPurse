package com.example.user.studentpurse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {


    private ProgressBar bar;
    private int ProgressStatus = 0;
    private TextView name;

    private Handler mHandler = new Handler();
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        name = (TextView) findViewById(R.id.name);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.getProgressDrawable().setColorFilter(R.color.white, android.graphics.PorterDuff.Mode.SRC_IN);
        final Intent i = new Intent(this,MainActivity.class);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(ProgressStatus < 100){
                        ProgressStatus++;
                        android.os.SystemClock.sleep(30);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                bar.setProgress(ProgressStatus);
                            }
                        });
                    }

                } finally {
                    startActivity(i);
                    finish();
                }
            }
        }).start();
    }
}

