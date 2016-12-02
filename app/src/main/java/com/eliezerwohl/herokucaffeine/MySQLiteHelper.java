package com.eliezerwohl.herokucaffeine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.enabled;
import static android.content.ContentValues.TAG;
import static android.icu.text.MessagePattern.ArgType.SELECT;
import static com.eliezerwohl.herokucaffeine.MainActivity.db;

/**
 * Created by Elie on 11/30/2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper{
    private static final int dbVersion =1;
    private static final String dbName = "siteDb";


    public MySQLiteHelper(Context context){
        super(context, dbName, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create table
        String CREATE_SITE_TABLE = "CREATE TABLE site ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "site TEXT, url TEXT, enabled INTEGER DEFAULT 1)";

        // create books table
        db.execSQL(CREATE_SITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older books table if existed
//        db.execSQL("DROP TABLE IF EXISTS books");
//
//        // create fresh books table
//        this.onCreate(db);
    }

    public void addSite(String string, String url){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("enabled", 1); // get title
        values.put("site", string); // get title
        values.put("url", url);

        // 3. insert
        db.insert("site", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
       String query =  "SELECT COUNT(*) FROM SITE";
        String other = "DESCRIBE SITE";
//        Cursor other2 = db.rawQuery(other, null);
//        Log.d(TAG, "addBook: "  + other2);
        Cursor count = db.rawQuery(query, null);
        if (count.getCount() > 0 ){
            count.moveToFirst();
           int returnCount = count.getInt(0);
            Log.d(TAG, "addBook: " + returnCount);
        }
        db.close();
        Log.d(TAG, "addBook: complete");
    }

    // Getting All
    public List<Site> getAllSites() {

        Log.d(TAG, "getAllSites: start");
        List<Site> sitetList = new ArrayList<Site>();
        // Select All Query
        String selectQuery = "SELECT  * FROM SITE";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0){

        }
        else {


            // looping through all rows and adding to list
            //if db is empty an error will occur here
            if (cursor.moveToFirst()) {
                do {
                    Site site = new Site();
                    site.setId(Integer.parseInt(cursor.getString(0)));
                    site.setSite(cursor.getString(1));
                    site.setEnabled(Integer.parseInt(cursor.getString(3)));
                    site.setUrl(cursor.getString(2));
                    // Adding contact to list
                    sitetList.add(site);
                    Log.d(TAG, "getAllSites: " + site.getSite() + site.getId());
                } while (cursor.moveToNext());
            }
        }
        Log.d(TAG, "getAllSites: ends");
        return sitetList;

    }
    public ArrayList<String> getUrl(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("hello");
        urlList.add("goodbye");
//        SELECT owner FROM pet;

        String raw = "SELECT URL FROM SITE";
        Cursor cursor = db.rawQuery(raw, null);
        if (cursor.moveToFirst()) {
            do {
                urlList.add(cursor.getString(0));
                Log.d(TAG, "getAllSites: ");
            } while (cursor.moveToNext());
        }


        return urlList;
    }
    public void  updateEnable(int status, int id) {
        Log.d(TAG, "updateEnable: start");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("enabled", status);
        String other ="UPDATE SITE SET enabled = '" + status +"' where id= " + id + ";";
        db.execSQL(other);
        // updating row
//        return db.update("SITE", values, id + " = ?",
//                new String[] { String.valueOf(contact.getID()) });

    }

}
