package com.eliezerwohl.herokucaffeine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.eliezerwohl.herokucaffeine.R.id.currentStatusButton;
import static com.eliezerwohl.herokucaffeine.R.id.stop;


public class MainActivity extends AppCompatActivity {
    final String TAG = "main";
    public static MySQLiteHelper db;
    AlarmManager  alarmManager;
    Intent intent;
    PendingIntent pendingIntent;
    AppStatus appStatus;
    public String currentStatus;

    public  void timerStart(){
        alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(this, SampleBootReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),6000,
                pendingIntent);
        Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(this));
        appStatus.setAppStatus("on", this);
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(this));
    }
    public void timerStop(){
        Log.d(TAG, "timerStop: STOP");
//        appStatus.setAppStatus("off", this);
        alarmManager.cancel(pendingIntent);
   Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(this));
        appStatus.setAppStatus("off", this);
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db = new MySQLiteHelper(this);
        setContentView(R.layout.activity_main);
        appStatus = new AppStatus();
        final Button currentStatusButton = (Button) findViewById(R.id.currentStatusButton);
//        appStatus.setAppStatus("OH MY GLOB", this);
        if (appStatus.getAppStatus(this).equals("off")){
            Log.d(TAG, "appStatus: off");
            currentStatus = "off";


        }
        else {
            Log.d(TAG, "appStatus: on ");
            currentStatus = "off";
        }
        currentStatusButton.setText(currentStatus);

        currentStatusButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (currentStatus.equals("off")){

                    currentStatus = "on";
                    timerStart();
                }
                else{

                    currentStatus = "off";
                    timerStop();
                }
                currentStatusButton.setText(currentStatus);

            }

        });
        Button activate = (Button) findViewById(R.id.activate);
        activate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                timerStart();
            }
        });
        Button next = (Button) findViewById(R.id.createNewSite);
        Button viewAll = (Button) findViewById(R.id.viewAll);
        viewAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), DisplaySites.class);
                startActivityForResult(myIntent, 0);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NewSite.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
    public void displaySites(View view){
        startActivity(new Intent(this, DisplaySites.class));
    }
//    Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
}
