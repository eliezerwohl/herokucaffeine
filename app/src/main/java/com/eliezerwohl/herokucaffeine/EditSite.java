package com.eliezerwohl.herokucaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.id.edit;
import static com.eliezerwohl.herokucaffeine.MainActivity.db;
import static com.eliezerwohl.herokucaffeine.R.id.viewAll;

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
        final EditText editSite = (EditText) findViewById(R.id.editSite);
       final  EditText editUrl = (EditText) findViewById(R.id.editUrl);
        editSite.setText(tempSite);
        editUrl.setText(tempUrl);
        Button saveEdit = (Button) findViewById(R.id.saveEdit) ;
        saveEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

               if(!editUrl.getText().toString().equals(tempUrl)){
                   db.updateUrl(editUrl.getText().toString(), tempId, "url" );
               }
                if (!editSite.getText().toString().equals(tempSite)) {
                    db.updateUrl(editSite.getText().toString(), tempId, "site" );
                }
                Intent myIntent = new Intent(EditSite.this, DisplaySites.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
