package com.eliezerwohl.herokucaffeine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewSite extends MainActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "onCreate";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_site);
        final EditText input   = (EditText)findViewById(R.id.inputSite);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSave = input.getText().toString();
                db.addSite(toSave);
                input.setText("");
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
