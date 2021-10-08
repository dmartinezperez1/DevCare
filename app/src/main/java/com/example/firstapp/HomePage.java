package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent( HomePage.this, BatteryPage.class);
                startActivity(i);
                finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent( HomePage.this, MemoryPage.class);
                startActivity(i);
                finish();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent( HomePage.this, CpuPage.class);
                startActivity(i);
                finish();
            }
        });
    }
}