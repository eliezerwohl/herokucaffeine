package com.eliezerwohl.herokucaffeine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Elie on 12/6/2016.
 */

public class AppStatus {
    public String appStatus;
    public String key = "keyName";
    public String editKey ="editId";

    public String getAppStatus(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String appStatus = preferences.getString(key, null);
        Log.d(TAG, "getAppStatus: got");
        return appStatus;
    }

    public void setAppStatus(String appStatus, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        this.appStatus = appStatus;
        editor.putString(key, appStatus);
        editor.commit();
        Log.d(TAG, "setAppStatus: set");
    }
    public void setEditId(String editId, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(editKey, editId);
        editor.commit();
    }
    public String getEditId(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String editId = preferences.getString(editKey, null);
        return editId;
    }
}
