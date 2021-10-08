package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.List;

public class CpuPage extends AppCompatActivity {
    Button btn7;
    TextView ProcessInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_page);

        btn7=(Button)findViewById(R.id.btn7);
        ProcessInfo = findViewById(R.id.ProcessInfo);

        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent( CpuPage.this, HomePage.class);
                startActivity(i);
                finish();
            }
        });

        StringBuilder mText = new StringBuilder();

        ActivityManager activityManager = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningProcesses = activityManager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo process: runningProcesses){
            mText.append(process.processName);
            mText.append("\n");

            ProcessInfo.setText(mText.toString());
        }
    }
}