package com.example.deliveryone.backend;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "tiendita.db";
    private static final String TABLE_USERS = "t_users";
    private static final String TABLE_ITEMS = "t_items";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "type TEXT not null," +
                "fullName TEXT not null," +
                "email TEXT," +
                "username TEXT not null," +
                "password TEXT not null)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ITEMS + "(" +
                "bar_code INTEGER PRIMARY KEY," +
                "description TEXT not null," +
                "taxType TEXT not null," +
                "cost INTEGER not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE_USERS);
        sqLiteDatabase.execSQL("drop Table if exists " + TABLE_ITEMS);
        onCreate(sqLiteDatabase);
    }

    //All registries
    public Cursor getItems(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + TABLE_ITEMS, null);
        return cursor;
    }

}
