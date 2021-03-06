package com.eliezerwohl.herokucaffeine;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final String TAG = "main";
    public static MySQLiteHelper db;
    public AppStatus appStatus;
    public String currentStatus;
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
               statusOn();
            }

        }
        else{
            statusOff();
        }
        final NotifyUser notify = new NotifyUser();
        notify.notifyUser(currentStatus, MainActivity.this);
        currentStatusButton.setText(currentStatus);
        final HerokuTimer herokuTimer = new HerokuTimer();
        currentStatusButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (currentStatus.equals("off")){
                    herokuTimer.timerStart(MainActivity.this);
                    statusOn();
                }
                else{
                    statusOff();
                    herokuTimer.timerStop(MainActivity.this);
                }
                notify.notifyUser(currentStatus, MainActivity.this);
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
//    Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
}
