package com.example.deliveryone.backend;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.deliveryone.utilidades.Utilidades;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "tiendita.db";
//    private static final String TABLE_USERS = "t_users";
//    private static final String TABLE_ITEMS = "t_items";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_ITEMS);
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_USERS);

        //Insertamos el usuario administrador
        sqLiteDatabase.execSQL(Utilidades.ADMIN_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        sqLiteDatabase.execSQL("drop Table if exists " + Utilidades.TABLE_USERS);
        sqLiteDatabase.execSQL("drop Table if exists " + Utilidades.TABLE_ITEMS);
        onCreate(sqLiteDatabase);
    }

    //All registries
    public Cursor getItems(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + Utilidades.TABLE_ITEMS, null);
        return cursor;
    }

}
