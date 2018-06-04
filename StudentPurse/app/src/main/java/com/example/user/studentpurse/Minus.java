package com.example.user.studentpurse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.studentpurse.Domain.Categories;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.Services.BalanceService;
import com.example.user.studentpurse.Services.CategoriesService;
import com.example.user.studentpurse.Services.IBalanceService;
import com.example.user.studentpurse.Services.ICategoryService;
import com.example.user.studentpurse.Services.ISpendingService;
import com.example.user.studentpurse.Services.SpendingService;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Minus extends AppCompatActivity{
    Spinner Moneys;
    EditText Date;
    Spinner Category;
    Spinner Place;
    EditText input;
    ImageButton AddCategory;
    ImageButton AddSubCategory;
    Button ok;
    EditText etText;
    SharedPreferences sPref;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    final String SAVED_TEXT = "saved_text";

    final List<String> DateList = new ArrayList<String>();

    ISpendingService spendingService;
    ICategoryService categoryService;
    IBalanceService balanceService;
    String inputName;

    List<String> storages = new ArrayList<String>();
    List<String> CategoryList = new ArrayList<String>();
    List<String> categories = new ArrayList<String>();
    List<String> subCategories = new ArrayList<String>();




    SmallPurseParameters parameters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minus);

        categoryService = new CategoriesService(Minus.this);
        balanceService = new BalanceService(Minus.this);
        spendingService = new SpendingService(Minus.this);

        try {
            parameters = JSONHelper.importFromJSON(Minus.this);
            storages = balanceService.getAllStorage();
            categories = categoryService.getAllCategotiesString();
            subCategories = new ArrayList<>(Arrays.asList(parameters.categories[0].SubCategories));
        } catch (IOException e) {
            Toast.makeText(Minus.this, e.getMessage(), Toast.LENGTH_LONG);
        }

        InitializeComponents();

        final ArrayAdapter<String> StorageAdapter = getAdapterInstance(storages);
        ArrayAdapter<String> CategoryAdapter = getAdapterInstance(categories);
        ArrayAdapter<String> SubCategoryAdapter = getAdapterInstance(subCategories);

        Moneys.setAdapter(StorageAdapter);
        Category.setAdapter(CategoryAdapter);
        Place.setAdapter(SubCategoryAdapter);

        Moneys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    List<String> newSubCategory = new ArrayList<String>(Arrays.asList(categoryService.getCategoryByName(categoryService.getAllCategoties().get(position).Name).SubCategories));
                    Place.setAdapter(getAdapterInstance(newSubCategory));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog ad = getModalInstanse("Добавление новой категории", "Название категории:").create();
                ad.show();
                ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Categories categories = new Categories(parameters.categories.length, inputName, new String[]{});
                        try {
                            categoryService.addCategory(categories);
                            Category.setAdapter(getAdapterInstance(categoryService.getAllCategotiesString()));
                            Place.setAdapter(getAdapterInstance(new ArrayList<String>()));
                        } catch (IOException e) {
                            Toast.makeText(Minus.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        AddSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog ad = getModalInstanse("Добавление нового места", "Название места:").create();
                ad.show();
                ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        try {
                            SmallPurseParameters parameters = JSONHelper.importFromJSON(Minus.this);
                            List<String> newSubCategories = new ArrayList<>();
                            for (int i = 0; i < parameters.categories.length; i++) {
                                if(parameters.categories[i].Name.equals(Category.getSelectedItem())){
                                    newSubCategories = new ArrayList<>(Arrays.asList(parameters.categories[i].SubCategories));
                                    newSubCategories.add(inputName);
                                    parameters.categories[i].SubCategories = new String[newSubCategories.size()];
                                    parameters.categories[i].SubCategories = newSubCategories.toArray(parameters.categories[i].SubCategories);
                                }
                            }
                            JSONHelper.exportToJSON(Minus.this, parameters);
                            Place.setAdapter(getAdapterInstance(newSubCategories));
                        } catch (IOException e) {
                            Toast.makeText(Minus.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 0;
                try {
                    index = spendingService.getLastId();
                } catch (IOException e) {
                   Toast.makeText(Minus.this, e.getMessage(),Toast.LENGTH_LONG).show();
                }
                if (etText.getText().toString().isEmpty() || etText.getText() == null)
                {
                    Toast.makeText(Minus.this, "Не все поля заполнены",Toast.LENGTH_LONG).show();
                    return;
                }
                try{
                    Double.parseDouble(etText.getText().toString());
                }catch (Exception e){
                    Toast.makeText(Minus.this, "Не верный формат суммы",Toast.LENGTH_LONG).show();
                    return;
                }
                Spending spending = new Spending(index, Category.getSelectedItem().toString(), Place.getSelectedItem().toString(), new Date(), Moneys.getSelectedItem().toString(), Double.parseDouble(etText.getText().toString()));
                try {
                    spendingService.addSpending(spending);
                    balanceService.subBalance(spending);
                } catch (IOException e) {
                    Toast.makeText(Minus.this, e.getMessage(),Toast.LENGTH_LONG).show();
                }
                try {
                    Intent intent = new Intent(Minus.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex){
                    Toast.makeText(Minus.this, "Не все поля заполнены или неверный тип суммы списывания",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private AlertDialog.Builder getModalInstanse(String header, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(Minus.this);
        builder.setTitle(header);
        builder.setMessage(title);
        input = new EditText(Minus.this);
        builder.setView(input);
        builder.setPositiveButton("Принять", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputName = input.getText().toString();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder;
    }

    public ArrayAdapter<String> getAdapterInstance(List<String> items){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Minus.this,R.layout.support_simple_spinner_dropdown_item, items);
        return adapter;
    }

    private void InitializeComponents(){
        ok = (Button) findViewById(R.id.okm);
        etText = (EditText)findViewById(R.id.tv4);
        Moneys = (Spinner)findViewById(R.id.moneys);
        Date = (EditText)findViewById(R.id.date);
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        Date.setText(strDate);
        Date.setFocusable(false);
        Date.setClickable(false);
        Category = (Spinner) findViewById(R.id.category);
        Place = (Spinner)findViewById(R.id.place);
        AddCategory = (ImageButton)findViewById(R.id.addCategory);
        AddSubCategory = (ImageButton) findViewById(R.id.addSubCategory);
    }
}
