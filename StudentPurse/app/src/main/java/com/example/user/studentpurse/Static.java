package com.example.user.studentpurse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Services.BalanceService;
import com.example.user.studentpurse.Services.IBalanceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Static extends AppCompatActivity {
    IBalanceService balanceService;
    Spinner Moneys;
    TextView BalanceTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        balanceService = new BalanceService(Static.this);
        Moneys = (Spinner)findViewById(R.id.scl);
        BalanceTextView = (TextView)findViewById(R.id.tv22);
        List<String> storages = new ArrayList<String>();
        try {
            storages = balanceService.getAllStorage();
        } catch (IOException e) {
            Toast.makeText(Static.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        final List<String> simpleList = storages;
        ArrayAdapter<String> MoneysAdapter = new ArrayAdapter<>(Static.this,R.layout.support_simple_spinner_dropdown_item,simpleList);
        Moneys.setAdapter(MoneysAdapter);

        Moneys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Static.this, simpleList.get(position), Toast.LENGTH_SHORT).show();
                try {
                    Balance bal = balanceService.getBalance(simpleList.get(position));
                    BalanceTextView.setText(balanceService.toString(bal));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
