package com.eliezerwohl.herokucaffeine;

import android.os.AsyncTask;
import android.util.Log;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Elie on 12/2/2016.
 */

public class WebHit extends AsyncTask<ArrayList<String>, Void,  String>{


    @Override
    protected String doInBackground(ArrayList<String>... passing) {
        ArrayList<String> passed = passing[0];
        Log.d(TAG, "doInBackground: "  + passed.size() );
//      for (int i = 0; i < arrays.length; i++){
//            Log.d(TAG, "doInBackground: looping " + i);
//      }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
