package com.eliezerwohl.herokucaffeine;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.URL;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {
    public static MySQLiteHelper db;
    private String[] theArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MySQLiteHelper(this);
        setContentView(R.layout.activity_main);
        WebHit webhit = new WebHit();
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this);
        Log.d("Steve", "onCreate: Steve");

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
}
