package com.example.user.studentpurse;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Notes extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private SliderAdapter sliderAdapter;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        button = (Button) findViewById(R.id.button);
        mSlideViewPager = (ViewPager) findViewById(R.id.sliderViewPager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SplashScreen.class);
                startActivity(intent);
                }
        });
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
    }
}
