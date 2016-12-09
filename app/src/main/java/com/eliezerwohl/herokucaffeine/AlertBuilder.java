package com.eliezerwohl.herokucaffeine;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static com.eliezerwohl.herokucaffeine.MainActivity.db;

/**
 * Created by Elie on 12/9/2016.
 */

public class AlertBuilder extends DisplaySites{
    private int currentId;
    public void alert(final Context context, String Id){
        final int currentId = Integer.parseInt(Id);
        AlertDialog.Builder a_builder = new AlertDialog.Builder(context);
        a_builder.setMessage("Do you want delete this site?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: YES");
                        db = new MySQLiteHelper(context);
                        db.delete(currentId);
                        loadPage();
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
        alert.show();
    }
}
