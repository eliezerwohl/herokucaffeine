package com.eliezerwohl.herokucaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static MySQLiteHelper db = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MySQLiteHelper(this);

        setContentView(R.layout.activity_main);
        Button next = (Button) findViewById(R.id.createNewSite);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NewSite.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
