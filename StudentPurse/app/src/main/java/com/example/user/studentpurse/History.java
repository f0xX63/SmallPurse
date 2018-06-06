package com.example.user.studentpurse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class History extends AppCompatActivity {

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");


        back = (Button) findViewById(R.id.but3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
