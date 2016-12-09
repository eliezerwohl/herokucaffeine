package com.eliezerwohl.herokucaffeine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import static com.eliezerwohl.herokucaffeine.MainActivity.db;

public class DisplaySites extends AppCompatActivity {
    private RadioButton listRadioButton = null;
    int listIndex = -1;
    public String currentId;
    DisplayFunctions mDisplayFunctions;

    @Override
    public void supportInvalidateOptionsMenu() {
        super.supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    String TAG="DisplaySites";
    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sites);
        mDisplayFunctions = new DisplayFunctions(this);
        mDisplayFunctions.loadPage();
    }
    public void onClickRadioButton(View v){
        Log.d(TAG, "onClickRadioButton: button click");
        View vMain = ((View) v.getParent());
        int newIndex = ((ViewGroup) vMain.getParent()).indexOfChild(vMain);
        if (listIndex == newIndex) return;
        if (listRadioButton != null) {
            listRadioButton.setChecked(false);
        }
        currentId = v.getTag().toString();
        Log.d(TAG, "onClickRadioButton: the id is" + currentId);
        listRadioButton = (RadioButton) v;
        listIndex = newIndex;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
       if ((id == R.id.deleteButton) && (currentId != null)){
           mDisplayFunctions.alert(this, currentId);
       }
        else if ((id == R.id.editItem) && (currentId != null)) {
           Log.d(TAG, "onOptionsItemSelected: edit");
           AppStatus appStatus = new AppStatus();
           appStatus.setEditId(currentId, this);
           Intent myIntent = new Intent(this, EditSite.class);
           startActivityForResult(myIntent, 0);
       }
        return true;
    }
    public void enableClick(View view){
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        String tag = b.getTag().toString();
        int id = Integer.parseInt(tag);
        if (buttonText.equals("enable")){
            Log.d(TAG, "enableClick: time to disable");
            b.setText("disable");
            Log.d(TAG, "enableClick: " + id);
            db.updateEnable(0, id);
        }
        else{
            Log.d(TAG, "enableClick: time to enable");
            b.setText("enable");
            db.updateEnable(1, id);
            Log.d(TAG, "enableClick: " + id);
        }
        Log.d(TAG, "testClick: " + view.getTag());
        mDisplayFunctions.loadPage();
    }
}
