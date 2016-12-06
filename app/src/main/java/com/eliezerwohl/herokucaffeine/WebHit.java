package com.eliezerwohl.herokucaffeine;

import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Elie on 12/2/2016.
 */

public class WebHit extends AsyncTask<ArrayList<String>, Void,  String>{

    public void connectToSite(String urlString){
        HttpURLConnection urlConnection = null;
        try{
            Log.d(TAG, "ConnectToSite: starting url" + urlString);
           URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            //maybe get rid of this next line?  is needed? yes it is
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            urlConnection.disconnect();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }

    @Override
    protected String doInBackground(ArrayList<String>... passing) {
        ArrayList<String> passed = passing[0];
        Log.d(TAG, "doInBackground: "  + passed.size() );
      for (int i = 0; i < passed.size(); i++){
          String tempString = passed.get(i);
          connectToSite(tempString);
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
