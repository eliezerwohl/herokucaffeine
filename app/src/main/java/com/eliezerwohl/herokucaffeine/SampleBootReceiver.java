package com.eliezerwohl.herokucaffeine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Elie on 12/5/2016.
 */

public class SampleBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: gett something");
        if (intent.getAction() == null){
            Log.d(TAG, "onReceive: null");
            MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
            WebHit webhit = new WebHit();
            webhit.execute(mySQLiteHelper.getUrl());
        }
        else if ((intent.getAction() != null) && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Log.d(TAG, "onReceive: boot");
        }
        else{
            Log.d(TAG, "onReceive: other");
        }
    }

}
