package com.eliezerwohl.herokucaffeine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {
    public static MySQLiteHelper db;
    private String[] theArray;
    private PendingIntent pendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MySQLiteHelper(this);
        setContentView(R.layout.activity_main);
        WebHit webhit = new WebHit();
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this);
        Log.d("Steve", "onCreate: Steve");
        Intent alarmIntent = new Intent(this, SampleBootReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, SampleBootReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),600,
                pendingIntent);


        //error if db is empty

//        webhit.execute(mySQLiteHelper.getUrl());
        Button next = (Button) findViewById(R.id.createNewSite);
        Button viewAll = (Button) findViewById(R.id.viewAll);
        viewAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), DisplaySites.class);
                startActivityForResult(myIntent, 0);
//              db.getAllSites();
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
    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }
}
