package com.example.user.studentpurse;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Plus extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {
    Spinner Moneys;
    Spinner Date;
    Button ok;
    Button dm;
    EditText etText;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    final String SAVED_TEXT = "saved_text";
    SharedPreferences sPref;
    final List<String> DateList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        dm = (Button) findViewById(R.id.dm2);
        ok = (Button) findViewById(R.id.okp);
        Moneys = (Spinner)findViewById(R.id.moneys);
        Date = (Spinner)findViewById(R.id.date);
        etText = (EditText) findViewById(R.id.tv2);

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



        Calendar c = Calendar.getInstance();
        final String sDate = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + " at " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        DateList.add(sDate);

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

        dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Plus.this, Plus.this, year,month,day);
                datePickerDialog.show();
            }
        });

       ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                saveText();
                startActivity(intent);
            }
        });
    }

    private void saveText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.commit();
        Toast.makeText(Plus.this, "Text saved", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Plus.this,Plus.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;
        final String sDate =yearFinal + "-" + monthFinal + "-" + dayFinal + "at" + hourFinal + ":" + minuteFinal;
        DateList.add(sDate);
    }
}
