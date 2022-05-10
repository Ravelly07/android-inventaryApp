package com.example.deliveryone.utilidades;

public class Utilidades {
    public static final String TABLE_USERS = "t_users";
    public static final String TABLE_ITEMS = "t_items";
    //Campos para User
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_TYPE = "type";
    public static final String CAMPO_FULLNAME = "fullName";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_USERNAME = "username";
    public static final String CAMPO_PASSWORD = "password";
    //Campos para tabla Items
    public static final String CAMPO_BAR_CODE = "bar_code";
    public static final String CAMPO_DESCRIPTION = "description";
    public static final String CAMPO_TAXTYPE = "taxType";
    public static final String CAMPO_COST = "cost";

    public static final String CREAR_TABLA_USERS = "CREATE TABLE " + TABLE_USERS + "(" + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_TYPE + " TEXT not null, " + CAMPO_FULLNAME + " TEXT not null, " + CAMPO_EMAIL + " TEXT, " + CAMPO_USERNAME + " TEXT not null, " + CAMPO_PASSWORD + " TEXT not null)";
    public static final String CREAR_TABLA_ITEMS = "CREATE TABLE " + TABLE_ITEMS + "(" + CAMPO_BAR_CODE + " INTEGER PRIMARY KEY, " + CAMPO_DESCRIPTION + " TEXT not null, " + CAMPO_TAXTYPE + " TEXT not null, " + CAMPO_COST + " INTEGER not null)";

    //Usuario ADMIN por defecto
    public  static  final String ADMIN_USER = "INSERT INTO " + TABLE_USERS + " (" + CAMPO_TYPE + "," + CAMPO_FULLNAME
            + "," + CAMPO_EMAIL + "," + CAMPO_USERNAME + "," + CAMPO_PASSWORD + ") " +
            "VALUES (" + "'Admin'" + ", '" + "Administrador" + "' , '" + "" + "' , '"
            + "Admin" + "' , '" + "Admin123456" + "' )";
}