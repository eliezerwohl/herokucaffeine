package com.eliezerwohl.herokucaffeine;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final String TAG = "main";
    public static MySQLiteHelper db;
    public AlarmManager  alarmManager;
    public Intent intent;
    public PendingIntent pendingIntent;
    public AppStatus appStatus;
    public String currentStatus;
    public int timeDelay = 600 * 100  * 30;
    public int mId = 4242;
    Button currentStatusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MySQLiteHelper(this);
        setContentView(R.layout.activity_main);
        appStatus = new AppStatus();
        currentStatusButton = (Button) findViewById(R.id.currentStatusButton);
        Button next = (Button) findViewById(R.id.createNewSite);
        Button jolt = (Button) findViewById(R.id.jolt);
        if (appStatus.getAppStatus(this) != null){
            if (appStatus.getAppStatus(this).equals("off")){
              statusOff();
            }
            else {
               statusOff();
            }

        }
        else{
            statusOff();

        }
        notifyUser(currentStatus);
        currentStatusButton.setText(currentStatus);
        currentStatusButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (currentStatus.equals("off")){
                    timerStart();
                    statusOn();
                }
                else{
                    statusOff();
                    timerStop();
                }
                notifyUser(currentStatus);
                currentStatusButton.setText(currentStatus);
            }
        });

        jolt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebHit webhit = new WebHit();
                webhit.execute(db.getUrl());
            }
        });
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
    public void statusOn(){
        currentStatus = "on";
        currentStatusButton.setBackgroundColor(Color.GREEN);
    }
    public void statusOff(){
        currentStatus = "off";
        currentStatusButton.setBackgroundColor(Color.RED);

    }
    public  void timerStart(){
        alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(this, SampleBootReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),timeDelay,
                pendingIntent);
        Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(this));
        appStatus.setAppStatus("on", this);
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(this));
    }
    public void timerStop(){
        Log.d(TAG, "timerStop: STOP");
        if (pendingIntent !=null){
            alarmManager.cancel(pendingIntent);
        }
        Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(this));
        appStatus.setAppStatus("off", this);
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(this));
    }

    public void displaySites(View view){
        startActivity(new Intent(this, DisplaySites.class));
    }
    public void notifyUser(String string){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.coffee)
                        .setContentTitle("HEROKU CAFFEINE")
                        .setContentText("Status: " + string);
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(mId, builder.build());
    }
//    Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
}
