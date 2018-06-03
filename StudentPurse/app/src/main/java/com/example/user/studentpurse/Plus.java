package com.example.user.studentpurse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Plus extends AppCompatActivity {
    Spinner Moneys;
    Spinner Date;
    Spinner Category;
    Spinner Place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minus);

        Moneys = (Spinner)findViewById(R.id.moneys);
        Date = (Spinner)findViewById(R.id.date);
        Category = (Spinner) findViewById(R.id.category);
        Place = (Spinner)findViewById(R.id.place);
        final List<String> simpleList = new ArrayList<String>();
        simpleList.add("Карта");
        simpleList.add("Наличка");
        simpleList.add("Подушка");
        simpleList.add("Добавить");
        ArrayAdapter<String> MoneysAdapter = new ArrayAdapter<>(Plus.this,R.layout.support_simple_spinner_dropdown_item,simpleList);
        Moneys.setAdapter(MoneysAdapter);

        Moneys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Plus.this, simpleList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<String> DateList = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        final String sDate = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + " at " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        DateList.add(sDate);
        DateList.add("Добавить");

        ArrayAdapter<String> DateAdapter = new ArrayAdapter<>(Plus.this,R.layout.support_simple_spinner_dropdown_item,DateList);
        Date.setAdapter(DateAdapter);

        Date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Plus.this,DateList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<String> CategoryList = new ArrayList<String>();
        CategoryList.add("Еда");
        CategoryList.add("Одежда");
        CategoryList.add("Проезд");
        CategoryList.add("Добавить");

        ArrayAdapter<String> CategoryAdapter = new ArrayAdapter<>(Plus.this,R.layout.support_simple_spinner_dropdown_item,CategoryList);
        Category.setAdapter(CategoryAdapter);

        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Plus.this, CategoryList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final List<String> PlaceList = new ArrayList<String>();
        PlaceList.add("Перчини");
        PlaceList.add("Спортмастер");
        PlaceList.add("Ашан");
        PlaceList.add("Добавить");

        ArrayAdapter<String> PlaceAdapter = new ArrayAdapter<>(Plus.this,R.layout.support_simple_spinner_dropdown_item,PlaceList);
        Place.setAdapter(PlaceAdapter);

        Place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Plus.this, PlaceList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
