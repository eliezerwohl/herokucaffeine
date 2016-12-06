package com.eliezerwohl.herokucaffeine;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.List;

import static android.R.attr.id;
import static android.R.attr.tag;
import static com.eliezerwohl.herokucaffeine.MainActivity.db;

public class DisplaySites extends AppCompatActivity {
    private RadioButton listRadioButton = null;
    int listIndex = -1;
    public String currentId;
    public String getCurrentId(){
        return currentId;
    }
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
        ListView lv = (ListView) findViewById(R.id.listView);
        List testList = db.getAllSites();
        ExtraAdapter adapter = new ExtraAdapter(this, R.layout.displayrow, testList);
        lv.setAdapter(adapter);
//        lv.getCheckedItemPosition();

        
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
        String selected;
       if ((id == R.id.deleteButton) && (currentId != null)){
           AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
           a_builder.setMessage("Do you want delete this site?")
                   .setCancelable(false)
                   .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           Log.d(TAG, "onClick: YES");
                           MySQLiteHelper db = new MySQLiteHelper(DisplaySites.this);
                           db.delete(Integer.parseInt(currentId));
                           db.close();
                           dialog.cancel();
                       }
                   })
                   .setNegativeButton("No",new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.cancel();
                       }
                   }) ;
           AlertDialog alert = a_builder.create();
           alert.setTitle("Alert !!!");
           alert.show();
       }
        else if (id == R.id.editItem){
           Log.d(TAG, "onOptionsItemSelected: edit");
           
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
    }

}
