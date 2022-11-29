package com.example.vitamin_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String VITAMIN_TABLE = "vitamin_table";
    public static final String VITAMIN_NAME = "vitamin_name";
    public static final String VITAMIN_TYPE = "vitamin_type";
    public static final String VITAMIN_DOSAGE = "vitamin_dosage";
    public static final String VITAMIN_DESCRIPTION = "vitamin_description";
    public static final String COLUMN_ID  ="column_id";
    private static final int VERSION = 2;
    private static InputStream inputStream;
    public DatabaseHelper(@Nullable Context context){
        super(context,"vitamin.db", null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + VITAMIN_TABLE);
        String query = "CREATE TABLE " + VITAMIN_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VITAMIN_NAME + " TEXT, " +
                VITAMIN_DESCRIPTION + " TEXT, " +
                VITAMIN_DOSAGE+ " INTEGER, " +
                VITAMIN_TYPE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VITAMIN_TABLE);
        onCreate(db);
    }
    public void addCSV(String name, String desc, String dos, String type) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VITAMIN_NAME, name);
        cv.put(VITAMIN_DESCRIPTION, desc);
        cv.put(VITAMIN_DOSAGE, dos);
        cv.put(VITAMIN_TYPE, type);
        db.insert(VITAMIN_TABLE, null, cv);
    }

}
