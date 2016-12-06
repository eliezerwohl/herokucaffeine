package com.eliezerwohl.herokucaffeine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Elie on 12/6/2016.
 */

public class AppStatus {
    public String appStatus;
    public String key = "keyName";


    public String getAppStatus(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String appStatus = preferences.getString(key, null);
        return appStatus;
    }

    public void setAppStatus(String appStatus, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        this.appStatus = appStatus;
        editor.putString(key, appStatus);
        editor.commit();

    }
}
