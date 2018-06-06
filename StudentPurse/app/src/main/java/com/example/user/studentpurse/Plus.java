package com.example.user.studentpurse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Services.BalanceService;
import com.example.user.studentpurse.Services.IBalanceService;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plus extends AppCompatActivity {
    Spinner Moneys;
    EditText Date;
    Button ok;
    Button back;
    EditText etText;
    IBalanceService balanceService;
    List<String> storages = new ArrayList<String>();
    SmallPurseParameters parameters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        getSupportActionBar().setTitle("Plus");

        balanceService = new BalanceService(Plus.this);
        try {
            parameters = JSONHelper.importFromJSON(Plus.this);
            storages = balanceService.getAllStorage();
        } catch (IOException e) {
            Toast.makeText(Plus.this, e.getMessage(), Toast.LENGTH_LONG);
        }
        InitializeComponents();


        ArrayAdapter<String> MoneysAdapter = getAdapterInstance(storages);
        Moneys.setAdapter(MoneysAdapter);

        Moneys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = 0;
                try {
                    value = Double.parseDouble(etText.getText().toString());
                }catch (Exception e)
                {
                    Toast.makeText(Plus.this, "Поле сумма не заполнено или неверный формат суммы", Toast.LENGTH_LONG);
                    return;
                }
                if (etText.getText().toString().isEmpty() || etText.getText() == null)
                {
                    Toast.makeText(Plus.this, "Не все поля заполнены",Toast.LENGTH_LONG).show();
                    return;
                }
                Balance balance = new Balance(value, Moneys.getSelectedItem().toString());
                try {
                    balanceService.addBalance(balance);
                } catch (IOException e) {
                    Toast.makeText(Plus.this, e.getMessage(), Toast.LENGTH_LONG);
                }
                Intent intent = new Intent(Plus.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

       back.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Plus.this, MainActivity.class);
               startActivity(intent);

               finish();
           }
       });
    }


    public ArrayAdapter<String> getAdapterInstance(List<String> items){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Plus.this,R.layout.support_simple_spinner_dropdown_item, items);
        return adapter;
    }

    private void InitializeComponents(){
        back = (Button) findViewById(R.id.bc);
        ok = (Button) findViewById(R.id.okp);
        Moneys = (Spinner)findViewById(R.id.moneys);
        Date = (EditText)findViewById(R.id.date);
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = new Date();
        String strDate = sdfDate.format(now);
        Date.setText(strDate);
        Date.setFocusable(false);
        Date.setClickable(false);
        etText = (EditText) findViewById(R.id.tv2);
    }
}
