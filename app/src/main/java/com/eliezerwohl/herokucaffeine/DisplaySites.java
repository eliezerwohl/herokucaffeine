package com.eliezerwohl.herokucaffeine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class DisplaySites extends MainActivity {

    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sites);
        ListView lv = (ListView) findViewById(R.id.listView);
        List testList = db.getAllSites();
        ExtraAdapter adapter = new ExtraAdapter(this, R.layout.displayrow, testList);
        lv.setAdapter(adapter);

    }
}
