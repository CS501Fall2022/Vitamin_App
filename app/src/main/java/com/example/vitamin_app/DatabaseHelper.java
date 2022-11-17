package com.example.vitamin_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String vitamin_table = "VITAMIN_TABLE";
    public static final String vitamin_name = "VITAMIN_NAME";
    public static final String vitamin_type = "VITAMIN_TYPE";

    public static final String vitamin_description = "VITAMIN_DESCRIPTION";
    public static final String column_id ="COLUMN_ID";

    public DatabaseHelper(@Nullable Context context){
        super(context,"vitamin.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //needs vitamin name, description, img?,
        String createTableStatement = "CREATE TABLE " + vitamin_table + " ("+column_id +" INTEGER PRIMARY KEY AUTOINCREMENT,"+vitamin_name + "TEXT,"+ vitamin_description+"TEXT"+vitamin_type+"TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("VITAMIN_NAME", "Vitamin 1");
        cv.put("VITAMIN_TYPE", "Energy");
        cv.put("VITAMIN_DESCRIPTION", "Energy Saving Vitamin");

        db.insert(vitamin_table, null, cv);
        return true;
    }

}
