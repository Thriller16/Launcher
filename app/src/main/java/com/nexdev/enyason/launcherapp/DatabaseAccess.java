package com.nexdev.enyason.launcherapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.nexdev.enyason.launcherapp.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

//The database class
public class DatabaseAccess {
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    //First constructor
    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //Second constructor
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }


    public boolean storemic(int micint) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("microphone", micint);
            database.update("Settings", cv, null, null);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int getmic() {
        Cursor cursor = database.rawQuery("SELECT microphone FROM Settings", null);
        cursor.moveToFirst();
        int fontIndex = cursor.getInt(cursor.getColumnIndex("microphone"));
        cursor.moveToNext();
        cursor.close();
        return fontIndex;
    }

    public boolean storenight(int nightint) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("nightmode", nightint);
            database.update("Settings", cv, null, null);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getnight() {
        Cursor cursor = database.rawQuery("SELECT nightmode FROM Settings", null);
        cursor.moveToFirst();
        int sizeIndex = cursor.getInt(cursor.getColumnIndex("nightmode"));
        cursor.moveToNext();
        cursor.close();
        return sizeIndex;
    }

    public boolean storeother(int other) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("other", other);
            database.update("Settings", cv, null, null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAppTheme() {
        Cursor cursor = database.rawQuery("SELECT other FROM Settings", null);
        cursor.moveToFirst();
        int sizeIndex = cursor.getInt(cursor.getColumnIndex("Theme"));
        cursor.moveToNext();
        cursor.close();
        return sizeIndex;
    }
}
