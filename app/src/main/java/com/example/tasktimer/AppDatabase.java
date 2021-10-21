package com.example.tasktimer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Basic database class for the application
 *
 * The only class that should use this is {@link AppProvider}
 */

class AppDatabase extends SQLiteOpenHelper {
    private static final String TAG = "AppDatabase";

    public static final String DATABASE_NAME = "Tasks.db";
    public static final int DATABASE_VERSION = 1;

    //Implement AppDatabase as Singleton
    private static AppDatabase instance = null;

    private AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //synchronized method to control simultaneous access
    static AppDatabase getInstance(Context context) {
        if (null == instance) {
            synchronized (AppDatabase.class) {
                if (null == instance) {
                    instance = new AppDatabase(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sSQL = "CREATE TABLE " + TasksContract.TABLE_NAME + "(" +
                TasksContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                TasksContract.Columns.NAME + " TEXT NOT NULL," +
                TasksContract.Columns.DESCRIPTION + " TEXT, " +
                TasksContract.Columns.SORT_ORDER + " INTEGER);";
        db.execSQL(sSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: starts");
        switch (oldVersion) {
            case 1:
                //upgrade logic for version 1
                break;
            default:
                throw new IllegalStateException("onUpgrade with unknown newVersion " + newVersion);
        }
        Log.d(TAG, "onUpgrade: ends");
    }
}
