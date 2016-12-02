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
      for (int i = 0; i < passed.size(); i++){
            Log.d(TAG, "doInBackground: looping " + passed.get(i).toString());
      }
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
