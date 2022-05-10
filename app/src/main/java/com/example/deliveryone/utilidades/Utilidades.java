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
            "VALUES (" + "7501861600017" +" , "+ "'ACEITE IMPERIAL SOYA 1LT'"+ " , " + "'SYS'" + " , " + "38.88" + ")" ;

    public static final String PRODUCTO2 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "7501003124135" +" , "+ "'CHICHARO HERDEZ 215G'"+ " , " + "'SYS'" + " , " + "6.68" + ")" ;

    public static final String PRODUCTO3 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "7501125103582" +" , "+ "'ELECTROLIT PIÑA 625ML'"+ " , " + "'SYS'" + " , " + "16.33" + ")" ;

    public static final String PRODUCTO4 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "7501003340139" +" , "+ "'MAYONESA McCORMICK 390G'"+ " , " + "'SYS'" + " , " + "30" + ")" ;

    public static final String PRODUCTO5 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "78909434" +" , "+ "'FERRERO ROCHER 3 PZS'"+ " , " + "'IV8'" + " , " + "19.58" + ")" ;

    public static final String PRODUCTO6 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "7501000658923" +" , "+ "'G MARIAS GAMESA 170G'"+ " , " + "'IV8'" + " , " + "7.74" + ")" ;

    public static final String PRODUCTO7 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "7501008023143" +" , "+ "'CER CORN FLAKES KELLOGGS 500 G'"+ " , " + "'IV8'" + " , " + "35.37" + ")" ;

    public static final String PRODUCTO8 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "7509546655321" +" , "+ "'FABULOSO CITRICO+BICARBONATO 1LT'"+ " , " + "'IVA'" + " , " + "15.23" + ")" ;

    public static final String PRODUCTO9 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "7501036623469" +" , "+ "'HIG REGIO RINDE+ 4/600 H'"+ " , " + "'IVA'" + " , " + "26.38" + ")" ;

    public static final String PRODUCTO10 = "INSERT INTO " + TABLE_ITEMS +
            " (" + CAMPO_BAR_CODE + "," + CAMPO_DESCRIPTION + "," + CAMPO_TAXTYPE + "," + CAMPO_COST + ") " +
            "VALUES (" + "75007614" +" , "+ "'COCA COLA 600ML'"+ " , " + "'IVA'" + " , " + "11.48" + ")" ;
}