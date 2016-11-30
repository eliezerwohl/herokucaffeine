package com.eliezerwohl.herokucaffeine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public void addBook(String string){
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
        Cursor count = db.rawQuery(query, null);
        if (count.getCount() > 0 ){
            count.moveToFirst();
           int returnCount = count.getInt(0);
            Log.d(TAG, "addBook: " + returnCount);
        }
        db.close();
        Log.d(TAG, "addBook: complete");
    }
}
