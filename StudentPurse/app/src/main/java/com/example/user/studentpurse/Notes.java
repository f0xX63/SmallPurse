package com.example.user.studentpurse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.studentpurse.Services.FirstOpenApp;

public class Notes extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private PrefManager prefManager;
    private SliderAdapter sliderAdapter;
    private Button button;
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        button = (Button) findViewById(R.id.button);
        mSlideViewPager = (ViewPager) findViewById(R.id.sliderViewPager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FirstOpenApp.FillFiles(getBaseContext());
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getBaseContext(), SplashScreen.class);
                startActivity(intent);
        }
        });
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Notes.this, SplashScreen.class));
        finish();
    }
}
