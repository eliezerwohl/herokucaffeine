package com.eliezerwohl.herokucaffeine;

import android.app.AlarmManager;
import android.os.SystemClock;

/**
 * Created by Elie on 12/5/2016.
 */

public class HerokuTimer {
    AlarmManager mAlarmManager;
    public void setNewAlarm(){
       mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);

    }
    public void alarmIntent(){

    }

}
