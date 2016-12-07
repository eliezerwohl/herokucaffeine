package com.eliezerwohl.herokucaffeine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import static com.eliezerwohl.herokucaffeine.R.id.jolt;
import static com.eliezerwohl.herokucaffeine.R.id.viewAll;

public class MainActivity extends AppCompatActivity {
    final String TAG = "main";
    public static MySQLiteHelper db;
    AlarmManager alarmManager;
    Intent intent;
    PendingIntent pendingIntent;
    AppStatus appStatus;
    public String currentStatus;

    public void timerStart() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(this, SampleBootReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 6000,
                pendingIntent);
        Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(this));
        appStatus.setAppStatus("on", this);
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(this));
    }

    public void timerStop() {
        Log.d(TAG, "timerStop: STOP");
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }
        Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(this));
        appStatus.setAppStatus("off", this);
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        db = new MySQLiteHelper(this);


        appStatus = new AppStatus();
        final Button currentStatusButton = (Button) findViewById(R.id.currentStatusButton);
        Button next = (Button) findViewById(R.id.createNewSite);
        Button jolt = (Button) findViewById(R.id.jolt);
        Button viewAll = (Button) findViewById(R.id.viewAll);
        if (appStatus.getAppStatus(this) != null) {
            if (appStatus.getAppStatus(this).equals("off")) {
                Log.d(TAG, "appStatus: off");
                currentStatus = "off";
            } else {
                Log.d(TAG, "appStatus: on ");
                currentStatus = "on";
            }
        } else {
            currentStatus = "off";
        }

        Boolean bool = db.checkDataBase();
        Log.v("chicka", "wow" + bool);
        if (bool == true) {
            Log.d(TAG, "onCreate: true");

            currentStatusButton.setText(currentStatus);

            currentStatusButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (currentStatus.equals("off")) {
                        currentStatus = "on";
                        timerStart();
                    } else {
                        currentStatus = "off";
                        timerStop();
                    }
                    currentStatusButton.setText(currentStatus);
                }
            });


            jolt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    WebHit webhit = new WebHit();
                    webhit.execute(db.getUrl());
                }
            });

            viewAll.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Log.d(TAG, "onClick: still hitting");
                    Intent myIntent = new Intent(view.getContext(), DisplaySites.class);
                    startActivityForResult(myIntent, 0);
                }
            });

        }
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NewSite.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    public void displaySites(View view) {
        startActivity(new Intent(this, DisplaySites.class));
    }

//    Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
}
