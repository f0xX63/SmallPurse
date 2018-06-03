package com.example.user.studentpurse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Static extends AppCompatActivity {

    Spinner Moneys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);

        Moneys = (Spinner)findViewById(R.id.scl);
        final List<String> simpleList = new ArrayList<String>();
        simpleList.add("Карта");
        simpleList.add("Наличка");
        simpleList.add("Подушка");
        simpleList.add("Добавить");
        ArrayAdapter<String> MoneysAdapter = new ArrayAdapter<>(Static.this,R.layout.support_simple_spinner_dropdown_item,simpleList);
        Moneys.setAdapter(MoneysAdapter);

        Moneys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Static.this, simpleList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
