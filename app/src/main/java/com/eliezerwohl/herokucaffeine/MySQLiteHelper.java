package com.eliezerwohl.herokucaffeine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

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
        // SQL statement to create book table
        String CREATE_SITE_TABLE = "CREATE TABLE site ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "site TEXT)";

        // create books table
        db.execSQL(CREATE_SITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
    }

    public void addSite(String string){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
//        values.put("site", table.getSite()); // get title
        values.put("site", string); // get title

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

    // Getting All Contacts
    public List<Site> getAllSites() {
        Log.d(TAG, "getAllSites: start");
        List<Site> sitetList = new ArrayList<Site>();
        // Select All Query
        String selectQuery = "SELECT  * FROM SITE";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Site site = new Site();
                site.setId(Integer.parseInt(cursor.getString(0)));
                site.setSite(cursor.getString(1));
                // Adding contact to list
                sitetList.add(site);
                Log.d(TAG, "getAllSites: " + site.getSite() + site.getId());
            } while (cursor.moveToNext());
        }
        Log.d(TAG, "getAllSites: ends");
        return sitetList;

    }

}
