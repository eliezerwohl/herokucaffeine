package com.eliezerwohl.herokucaffeine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import static com.eliezerwohl.herokucaffeine.MainActivity.db;

public class EditSite extends AppCompatActivity {
    private String TAG = "Editsite";
    private String tempUrl;
    private String tempSite;
    private int tempId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_site);
        AppStatus appStatus = new AppStatus();
        String tempVar = appStatus.getEditId(this);
        Log.d(TAG, "onCreate: " + tempVar);
        Site tempSiteObject = db.editSite(Integer.parseInt(tempVar));
        tempUrl = tempSiteObject.getUrl();
        tempSite = tempSiteObject.getSite();
        tempId = tempSiteObject.getId();
        EditText editSite = (EditText) findViewById(R.id.editSite);
        EditText editUrl = (EditText) findViewById(R.id.editUrl);
        editSite.setText(tempSite);
        editUrl.setText(tempUrl);
    }
}
