package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MemoryPage extends AppCompatActivity {
    Button btn6;
    TextView Memorytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_page);

        btn6=(Button)findViewById(R.id.btn6);
        Memorytxt = findViewById(R.id.Memorytxt);
        Memorytxt.setText(getMemoryInfo());

        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent( MemoryPage.this, HomePage.class);
                startActivity(i);
                finish();
            }
        });
    }

    private String getMemoryInfo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);

        Runtime runtime = Runtime.getRuntime();

        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        String finalAvailableMem = "";
        String finalTotalMem = "";
        String finalRTmax = "";
        String finalRTtotal = "";
        String finalRTfree = "";

        long availaMem = memoryInfo.availMem;
        long totalMem = memoryInfo.totalMem;
        long RTmaxMem = runtime.maxMemory();
        long RTtotalMem = runtime.totalMemory();
        long RTfreeMem = runtime.freeMemory();

        double mb1 = availaMem / 1048576.0;

        double gb2 = totalMem / 1073741824.0;

        double mb3 = RTmaxMem / 1048576.0;

        double mb4 = RTtotalMem / 1048576.0;

        double mb5 = RTfreeMem / 1048576.0;

        finalAvailableMem = twoDecimalForm.format(mb1).concat("MB");

        finalTotalMem = twoDecimalForm.format(gb2).concat("GB");

        finalRTmax = twoDecimalForm.format(mb3).concat("MB");

        finalRTtotal = twoDecimalForm.format(mb4).concat("MB");

        finalRTfree = twoDecimalForm.format(mb4).concat("MB");

        String strMemInfo =
                "Available Memory = " + finalAvailableMem + "\n"
                + "Total Memory = " + finalTotalMem + "\n"
                + "Runtime Max Memory = " + finalRTmax + "\n"
                + "Runtime Total Memory = " + finalRTtotal + "\n"
                + "Runtime Free Memory = " + finalRTfree + "\n";

        return strMemInfo;
    }
}