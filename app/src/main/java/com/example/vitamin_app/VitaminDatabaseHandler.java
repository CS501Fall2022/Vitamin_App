package com.example.vitamin_app;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class VitaminDatabaseHandler extends SQLiteOpenHelper {
    public static final String VITAMIN_TABLE = "vitamin_table";
    public static final String VITAMIN_NAME = "vitamin_name";
    public static final String VITAMIN_TYPE = "vitamin_type";
    public static final String VITAMIN_DOSAGE = "vitamin_dosage";
    public static final String VITAMIN_DESCRIPTION = "vitamin_description";
    public static final String VITAMIN_IMAGE = "vitamin_image";
    public static final String COLUMN_ID  ="column_id";
    private static final int VERSION = 2;
    private static InputStream inputStream;
    public VitaminDatabaseHandler(@Nullable Context context){
        super(context,"vitamin.db", null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS vitamin_table");

        String query = "CREATE TABLE " + VITAMIN_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VITAMIN_NAME + " TEXT, " +
                VITAMIN_DESCRIPTION + " TEXT, " +
                VITAMIN_DOSAGE+ " INTEGER, " +
                VITAMIN_TYPE + " TEXT, " +
                VITAMIN_IMAGE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VITAMIN_TABLE);
        onCreate(db);
    }
    public void addCSV(String name, String desc, String dos, String type, String img) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VITAMIN_NAME, name);
        cv.put(VITAMIN_DESCRIPTION, desc);
        cv.put(VITAMIN_DOSAGE, dos);
        cv.put(VITAMIN_TYPE, type);
        cv.put(VITAMIN_IMAGE, img);
        db.insert(VITAMIN_TABLE, null, cv);
    }
    @SuppressLint("Range")
    public ArrayList<String []> getData(){
        ArrayList<String []> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + VITAMIN_TABLE, null);
        while(cursor.moveToNext()){
            String[] str = {cursor.getString(1),cursor.getString(4) ,cursor.getString(2),cursor.getString(3),cursor.getString(5)};
            list.add(str);
        }
        return list;
    }

}
