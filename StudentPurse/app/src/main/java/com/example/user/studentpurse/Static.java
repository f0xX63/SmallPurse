package com.example.user.studentpurse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Services.BalanceService;
import com.example.user.studentpurse.Services.IBalanceService;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Static extends AppCompatActivity {
    IBalanceService balanceService;
    Spinner Moneys;
    TextView BalanceTextView;
    Button Okbtn;
    ImageButton AddStorage;
    List<String> storages = new ArrayList<String>();
    EditText input;
    String inputName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        InitializeComponents();
        try {
            storages = balanceService.getAllStorage();
            storages.add("Общие");
        } catch (IOException e) {
            Toast.makeText(Static.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        ArrayAdapter<String> MoneysAdapter = getAdapterInstance(storages);
        Moneys.setAdapter(MoneysAdapter);

        Moneys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Balance balance = new Balance(0, "Ошибка");
                if(Moneys.getSelectedItem().equals("Общие"))
                {
                    try {
                        balance = balanceService.getCommonBalance();
                    } catch (IOException e) {
                        Toast.makeText(Static.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    try {
                        balance = balanceService.getBalance(Moneys.getSelectedItem().toString());
                    } catch (IOException e) {
                        Toast.makeText(Static.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                BalanceTextView.setText(balanceService.toString(balance));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AddStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog ad = getModalInstanse("Добавление места хранения", "Название места:").create();
                ad.show();
                ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        try {
                            Balance balance = new Balance(0, inputName);
                            SmallPurseParameters parameters = JSONHelper.importFromJSON(Static.this);
                            List<Balance> balances = new ArrayList<>(Arrays.asList(parameters.balances));
                            balances.add(balance);
                            parameters.balances = new Balance[balances.size()];
                            parameters.balances = balances.toArray(parameters.balances);
                            JSONHelper.exportToJSON(Static.this, parameters);
                            List<String> newBalances = balanceService.getAllStorage();
                            newBalances.add("Общие");
                            Moneys.setAdapter(getAdapterInstance(newBalances));
                        } catch (IOException e) {
                            Toast.makeText(Static.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        Okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Static.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private AlertDialog.Builder getModalInstanse(String header, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(Static.this);
        builder.setTitle(header);
        builder.setMessage(title);
        input = new EditText(Static.this);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Static.this,R.layout.support_simple_spinner_dropdown_item, items);
        return adapter;
    }


    private void InitializeComponents(){
        balanceService = new BalanceService(Static.this);
        Moneys = (Spinner)findViewById(R.id.scl);
        BalanceTextView = (TextView)findViewById(R.id.tv22);
        Okbtn = (Button)findViewById(R.id.okp);
        AddStorage = (ImageButton)findViewById(R.id.addStorage);
    }
}
