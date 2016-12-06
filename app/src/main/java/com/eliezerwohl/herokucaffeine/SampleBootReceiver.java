package com.eliezerwohl.herokucaffeine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
            Intent i = new Intent(context, MainActivity.class);  
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            Log.d(TAG, "onReceive: booted activity");
        }
        else{
            Log.d(TAG, "onReceive: other");
        }
    }

}
