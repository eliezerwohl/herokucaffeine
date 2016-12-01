package com.eliezerwohl.herokucaffeine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class DisplaySites extends MainActivity {
String TAG="DisplaySites";
    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sites);
        ListView lv = (ListView) findViewById(R.id.listView);
        List testList = db.getAllSites();
        ExtraAdapter adapter = new ExtraAdapter(this, R.layout.displayrow, testList);
        lv.setAdapter(adapter);


    }
    public void enableClick(View view){
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        if (buttonText.equals("enable")){
            Log.d(TAG, "enableClick: time to disable");
            b.setText("disable");
            String tag = b.getTag().toString();
            int id = Integer.parseInt(tag);

            db.updateEnable(0, id);
        }
        else{
            Log.d(TAG, "enableClick: time to enable");
            b.setText("enable");
            db.updateEnable(1);

        }
        Log.d(TAG, "testClick: " + view.getTag());
    }
}
