package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BatteryPage extends AppCompatActivity {
    //Declare Variables
    Button btn5;
    BroadcastReceiver batteryBroadcast;
    IntentFilter intentFilter;
    TextView batteryLevel,chargeStatus,batteryHealth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_page);

        //assign values with text using ID
        batteryLevel = findViewById(R.id.batteryLevel);
        batteryHealth = findViewById(R.id.batteryHealth);
        chargeStatus = findViewById(R.id.chargeStatus);
        btn5=(Button)findViewById(R.id.btn5);

        intentFilterAndBroadcast();
        //Button method to go back to home page
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent( BatteryPage.this, HomePage.class);
                startActivity(i);
                finish();
            }
        });
    }
    //Broadcast to battery manager to get info
                private void intentFilterAndBroadcast() {
                    intentFilter = new IntentFilter();
                    intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

                    batteryBroadcast = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.ACTION_BATTERY_CHANGED))
                {
                        batteryLevel.setText(String.valueOf(intent.getIntExtra("level",0))+ "%");

                        //initialize methods for health and charge status
                        setHealth(intent);
                        setChargingStatus(intent);
                }
            }
        };
    }
    //Method for charge status and switch cases
    private void setChargingStatus(Intent intent) {
        int statusTemp = intent.getIntExtra("status", -1);

        switch(statusTemp)
        {
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                chargeStatus.setText("unknown");
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                chargeStatus.setText("Charging");
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                chargeStatus.setText("Discharging");
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                chargeStatus.setText("Full");
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                chargeStatus.setText("Not Charging");
                break;
            default:
                chargeStatus.setText("None");
        }
    }
    //Method for batter health and switch statments for difffernet values
    private void setHealth(Intent intent) {

        int val = intent.getIntExtra("health", 0);

        switch (val) {
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                batteryHealth.setText("Unkown");
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                batteryHealth.setText("Good");
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                batteryHealth.setText("Overheating");
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                batteryHealth.setText("Dead");
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                batteryHealth.setText("Over Voltage");
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                batteryHealth.setText("Cold");
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                batteryHealth.setText("Unspecified Failure");
                break;
        }
    }
    //Method to start broadcast and register to values
    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(batteryBroadcast,intentFilter);
    }
    //Method to stop broadcast and unregister values
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryBroadcast);
    }
}