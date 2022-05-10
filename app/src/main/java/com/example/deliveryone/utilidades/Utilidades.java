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

    //Algunos registros por defectos para la base de datos (Inventario)
    public static final String PRODUCTO1 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "8975" +" , "+ "'Coca Cola 600ml'"+ " , " + "'IVA'" + " , " + "18" + ")" ;

    public static final String PRODUCTO2 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "2531" +" , "+ "'Galletas Maria Gamesa'"+ " , " + "'IV8'" + " , " + "10" + ")" ;

    public static final String PRODUCTO3 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "6681" +" , "+ "'Leche Lala'"+ " , " + "'SYS'" + " , " + "12" + ")" ;

    public static final String PRODUCTO4 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "2245" +" , "+ "'Vaso Plastico #8'"+ " , " + "'SYS'" + " , " + "23" + ")" ;

    public static final String PRODUCTO5 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "9348" +" , "+ "'Chiles Rajas La Morena 210gr'"+ " , " + "'SYS'" + " , " + "7.50" + ")" ;

    public static final String PRODUCTO6 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "3506" +" , "+ "'Jabon Rosa Venus Rosa'"+ " , " + "'SYS'" + " , " + "9" + ")" ;

    public static final String PRODUCTO7 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "9016" +" , "+ "'Helado Magnum Nuez'"+ " , " + "'IVA'" + " , " + "50" + ")" ;
}