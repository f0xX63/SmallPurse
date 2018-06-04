package com.example.user.studentpurse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.studentpurse.Domain.Categories;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Minus extends AppCompatActivity {
    Spinner Moneys;
    Spinner Date;
    Spinner Category;
    Spinner Place;
    EditText input;
    ImageButton AddMatch;
    ImageButton AddCategory;
    ImageButton AddSubCategory;

    SmallPurseParameters parameters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minus);
        try {
            parameters = JSONHelper.importFromJSON(Minus.this);
        } catch (IOException e) {
            Toast.makeText(Minus.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        Moneys = (Spinner)findViewById(R.id.moneys);
        Date = (Spinner)findViewById(R.id.date);
        Category = (Spinner) findViewById(R.id.category);
        Place = (Spinner)findViewById(R.id.place);
        AddCategory = (ImageButton)findViewById(R.id.addCategory);
        AddSubCategory = (ImageButton) findViewById(R.id.addSubCategory);

        AlertDialog.Builder builder = new AlertDialog.Builder(Minus.this);
        builder.setTitle("Добавление");
        builder.setMessage("Введите название");
        input = new EditText(Minus.this);
        builder.setView(input);
        builder.setPositiveButton("Принять", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Minus.this, input.getText().toString(), Toast.LENGTH_LONG);
            }
        });
        builder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = builder.create();

        AddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
            }
        });

        AddSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
            }
        });

        final List<String> simpleList = new ArrayList<String>();
        simpleList.add("Карта");
        simpleList.add("Наличка");
        simpleList.add("Подушка");
        simpleList.add("Добавить");
        ArrayAdapter<String> MoneysAdapter = new ArrayAdapter<>(Minus.this,R.layout.support_simple_spinner_dropdown_item,simpleList);
        Moneys.setAdapter(MoneysAdapter);

        Moneys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Minus.this, simpleList.get(position), Toast.LENGTH_SHORT).show();
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

        ArrayAdapter<String> DateAdapter = new ArrayAdapter<>(Minus.this,R.layout.support_simple_spinner_dropdown_item,DateList);
        Date.setAdapter(DateAdapter);

        Date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Minus.this,DateList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> categories = new ArrayList<String>();
        if(parameters!=null) {
            for (Categories category : parameters.categories) {
                categories.add(category.Name);
            }
        }
        final List<String> CategoryList = categories;

        ArrayAdapter<String> CategoryAdapter = new ArrayAdapter<>(Minus.this,R.layout.support_simple_spinner_dropdown_item,CategoryList);
        Category.setAdapter(CategoryAdapter);
        final List<String> subCategories = new ArrayList<String>();
        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Minus.this, CategoryList.get(position), Toast.LENGTH_SHORT).show();
                for (Categories category: parameters.categories) {
                    if(category.Name == CategoryList.get(position)){
                        subCategories.clear();
                        for (String subCategory: category.SubCategories) {
                            subCategories.add(subCategory);
                        }
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final List<String> PlaceList = subCategories;

        ArrayAdapter<String> PlaceAdapter = new ArrayAdapter<>(Minus.this,R.layout.support_simple_spinner_dropdown_item,PlaceList);
        Place.setAdapter(PlaceAdapter);

        Place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Minus.this, PlaceList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
