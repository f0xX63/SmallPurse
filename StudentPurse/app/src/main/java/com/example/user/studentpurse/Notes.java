package com.example.user.studentpurse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.studentpurse.Services.FirstOpenApp;

public class Notes extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private PrefManager prefManager;
    private SliderAdapter sliderAdapter;
    private Button next;
    private Button back;
    private int mCurrentPage;
    private EditText name;
    private EditText cash;

    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        next = (Button) findViewById(R.id.nxt);
        back = (Button) findViewById(R.id.prev);
        name = (EditText) findViewById(R.id.name);
        cash = (EditText) findViewById(R.id.count);

        mSlideViewPager = (ViewPager) findViewById(R.id.sliderViewPager);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FirstOpenApp.FillFiles(getBaseContext());
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                }
                mSlideViewPager.setCurrentItem(mCurrentPage+1);
                if(mCurrentPage==2){
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), SplashScreen.class);
                            startActivity(intent);
                        }
                    });
                }
        }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FirstOpenApp.FillFiles(getBaseContext());
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                }
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Notes.this, SplashScreen.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            mCurrentPage = position;
            if ( position == 0){
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);
                name.setVisibility(View.VISIBLE);
                cash.setVisibility(View.INVISIBLE);

                next.setText("Next");
                back.setText("");
            }else if(position == 2){
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                name.setVisibility(View.INVISIBLE);
                cash.setVisibility(View.INVISIBLE);

                next.setText("Finish");
                back.setText("Back");
            }else{
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                cash.setVisibility(View.VISIBLE);
                name.setVisibility(View.INVISIBLE);

                next.setText("Next");
                back.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
