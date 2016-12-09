package com.eliezerwohl.herokucaffeine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Elie on 12/9/2016.
 */

public class HerokuTimer {
    public int timeDelay = 600 * 100  * 30;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;

    public  void timerStart(Context context){
        Log.d(TAG, "timerStart: start");
        alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, SampleBootReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),timeDelay,
                pendingIntent);
        AppStatus appStatus = new AppStatus();
        Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(context));
        appStatus.setAppStatus("on", context);
        Log.d(TAG, "setAppStatus: current condition " + appStatus.getAppStatus(context));
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(context));

    }
    public void timerStop(Context context){
        Log.d(TAG, "timerStop: STOP");
        if (pendingIntent !=null){
            alarmManager.cancel(pendingIntent);
        }
        AppStatus appStatus = new AppStatus();
        Log.d(TAG, "timerStart was: " + appStatus.getAppStatus(context));
        appStatus.setAppStatus("off", context);
        Log.d(TAG, "setAppStatus: current condition " + appStatus.getAppStatus(context));
        Log.d(TAG, "timerStart now is: " + appStatus.getAppStatus(context));
    }
}
