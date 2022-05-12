package com.example.deliveryone.backend;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.deliveryone.utilidades.Utilidades;

public class MainController {

    //Metodo para validar si el usuario esta registrado
    public static boolean searchUser(EditText user, EditText password, Activity act){
        DataBaseHelper dbhelper;
        dbhelper = new DataBaseHelper(act);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //Valores
        String v_user = user.getText().toString();
        String v_password = password.getText().toString();

        //Ejecutamos la consulta
        Cursor cursor = db.rawQuery("SELECT "+ Utilidades.CAMPO_USERNAME+ " FROM " + Utilidades.TABLE_USERS +
                " WHERE " + Utilidades.CAMPO_USERNAME + " = '" + v_user +
                "' AND " + Utilidades.CAMPO_PASSWORD + " = '" + v_password + "'", null);
        cursor.moveToFirst();
        db.close();

        try{
            cursor.isNull(0);
            Toast.makeText(act, "Acceso permitido" , Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(act, "ERROR: Las credenciales no son correctas. Rectificar",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static void SearchItem(EditText descriptionBox,EditText precio1Box, EditText precio2Box, EditText codigoBox, Activity act, Double codebar){
        //Declaramos la instancia del helper
        DataBaseHelper dbhelper;
        //Conexión
        dbhelper = new DataBaseHelper(act);
        //Variable local para la operación
        double costo, precio1,precio2;
        String impuesto;
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        //Realizamos la consulta : Select * from t_item where bar_code = codebar
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_ITEMS + " WHERE " + Utilidades.CAMPO_BAR_CODE + " = " + codebar, null);
        cursor.moveToFirst();

        //Obteniendo los 4 campos del registro
        codigoBox.setText(cursor.getString(0));
        descriptionBox.setText(cursor.getString(1));
        impuesto = cursor.getString(2);
        costo = Double.parseDouble(cursor.getString(3));

        switch (impuesto){
            case "IVA":
                precio1 = costo * 1.16;
                precio1Box.setText("$"+String.format("%.2f",precio1));
                precio2 = costo * 1.10;
                precio2Box.setText("$"+String.format("%.2f",precio2));
                break;
            case "IV8":
                precio1 = costo * 1.08;
                precio1Box.setText("$"+String.format("%.2f",precio1));
                precio2 = costo * 1.05;
                precio2Box.setText("$"+String.format("%.2f",precio2));
                break;
            case "SYS":
                precio1 = costo * 1.05;
                precio1Box.setText("$"+String.format("%.2f",precio1));
                precio2 = costo * 1.02;
                precio2Box.setText("$"+String.format("%.2f",precio2));
                break;
            default:
                break;
        }

    }

    public static boolean registrarUsuario(EditText fullnamebox,EditText emailbox ,EditText usernamebox, EditText passwordbox, Activity act ){
        //Declaramos la instacia del helper
        DataBaseHelper dbhelper;
        dbhelper = new DataBaseHelper(act);
        //Abimos la db
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        //Declaración de variables locales
        boolean compleate;
        String fullName = fullnamebox.getText().toString();
        String userName = usernamebox.getText().toString();
        String password = passwordbox.getText().toString();

        //Revisamos si los campos fueron llenados correctamente o no
        compleate = check(fullName, userName, password);
        if(compleate){
            String insertar = "INSERT INTO " + Utilidades.TABLE_USERS + " (" + Utilidades.CAMPO_TYPE + "," + Utilidades.CAMPO_FULLNAME
                    + "," + Utilidades.CAMPO_EMAIL + "," + Utilidades.CAMPO_USERNAME + "," + Utilidades.CAMPO_PASSWORD + ") " +
                    "VALUES (" + "'Regular'" + ", '" + fullName + "' , '" + emailbox.getText().toString() + "' , '"
                    + userName + "' , '" + password + "' )";

            db.execSQL(insertar);
            db.close();
        }else{
            Toast.makeText(act, "ERROR: Campos incompletos. Completar Registro",Toast.LENGTH_LONG).show();
        }

        return compleate;
    }

    //Función Auxiliar a RegistrarUsuario, Verifica que todos los campos (NECESARIOS) esten llenos.
    private static boolean check(String campo1, String campo2, String campo3){
        if(TextUtils.isEmpty(campo1) || TextUtils.isEmpty(campo2) || TextUtils.isEmpty(campo3)){
            return false;
        }else{
            return true;
        }
    }

    public  static boolean isAdmin(EditText user, EditText password, Activity act){
        DataBaseHelper dbhelper;
        dbhelper = new DataBaseHelper(act);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //Valores
        String v_user = user.getText().toString();
        String v_password = password.getText().toString();

        //Ejecutamos la consulta
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_USERS +
                " WHERE " + Utilidades.CAMPO_USERNAME + " = '" + v_user +
                "' AND " + Utilidades.CAMPO_PASSWORD + " = '" + v_password + "'", null);
        cursor.moveToFirst();

        String valor1 = cursor.getString(1);

        if(valor1.equals("Admin")){
            return true;
        }else{
            return false;
        }
    }

    public static void FillFields(EditText descriptionBoxAd, EditText costoBoxAd, EditText ivaBoxAd, EditText codigoBoxAd, Activity act, Double codebar) {
        //Declaramos la instancia del helper
        DataBaseHelper dbhelper;
        //Conexión
        dbhelper = new DataBaseHelper(act);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        //Realizamos la consulta : Select * from t_item where bar_code = codebar
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_ITEMS + " WHERE " + Utilidades.CAMPO_BAR_CODE + " = " + codebar, null);
        cursor.moveToFirst();

        //Rellenamos campos
        codigoBoxAd.setText(cursor.getString(0));
        descriptionBoxAd.setText(cursor.getString(1));
        ivaBoxAd.setText(cursor.getString(2));
        costoBoxAd.setText(cursor.getString(3));

    }

    public static void update(EditText descriptionBoxAd, EditText costoBoxAd, EditText ivaBoxAd, EditText codigoBoxAd, FragmentActivity activity, String primaryKey) {
        //Declaramos la instancia del helper
        DataBaseHelper dbhelper;
        //Conexión
        dbhelper = new DataBaseHelper(activity);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //Recolectamos la información nueva
        String newDescription =descriptionBoxAd.getText().toString();
        String newCost = costoBoxAd.getText().toString();
        String newIva = ivaBoxAd.getText().toString();
        String newCode = codigoBoxAd.getText().toString();

        String UPDATE_REGISTRO = "UPDATE "+ Utilidades.TABLE_ITEMS + " SET " +
                Utilidades.CAMPO_DESCRIPTION + " = '" + newDescription + "',"+
                Utilidades.CAMPO_COST + " = '" + newCost + "'," +
                Utilidades.CAMPO_TAXTYPE + " = '" + newIva + "'," +
                Utilidades.CAMPO_BAR_CODE + " = '" + newCode + "'" +
                " WHERE " + Utilidades.CAMPO_BAR_CODE + " = '" + primaryKey + "'";
        Toast.makeText(activity,UPDATE_REGISTRO, Toast.LENGTH_SHORT ).show();
        //Realizamos el update
        try{
            db.execSQL(UPDATE_REGISTRO);
            Toast.makeText(activity, "ACTUALIZADO",Toast.LENGTH_SHORT);
            db.close();
        }catch(Exception e){
            Toast.makeText(activity, "ERROR: NO SE ACTUALIZO",Toast.LENGTH_SHORT).show();
        }

    }

    public static void delete(FragmentActivity activity, String primaryKey) {
        //Declaramos la instancia del helper
        DataBaseHelper dbhelper;
        //Conexión
        dbhelper = new DataBaseHelper(activity);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        String DELETE_REGISTRO = "DELETE FROM "+ Utilidades.TABLE_ITEMS +
               " WHERE " + Utilidades.CAMPO_BAR_CODE + " = '" + primaryKey + "'";

        Toast.makeText(activity,DELETE_REGISTRO, Toast.LENGTH_SHORT ).show();

        //Realizamos el delete
        try{
            db.execSQL(DELETE_REGISTRO);
            db.close();
        }catch(Exception e){
            Toast.makeText(activity, "ERROR: NO SE ELIMINO EL REGISTRO",Toast.LENGTH_SHORT);
        }


    }

    public static void insert(EditText descriptionInsertBox, EditText costoInsertBox, EditText ivaInsertBox, EditText codigoInsertBox, FragmentActivity activity) {
        //Declaramos la instancia del helper
        DataBaseHelper dbhelper;
        //Conexión
        dbhelper = new DataBaseHelper(activity);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //Recolectamos la información nueva
        String newDescription =descriptionInsertBox.getText().toString();
        String newCost = costoInsertBox.getText().toString();
        String newIva = ivaInsertBox.getText().toString();
        String newCode = codigoInsertBox.getText().toString();

        String INSERT_NEW_REGISTRO = "INSERT INTO " + Utilidades.TABLE_ITEMS +
                " (" + Utilidades.CAMPO_BAR_CODE + "," + Utilidades.CAMPO_DESCRIPTION + ","
                + Utilidades.CAMPO_TAXTYPE + "," + Utilidades.CAMPO_COST + ") " +
                "VALUES (" + newCode +" , '"+ newDescription +
                "' , '" + newIva + "' , " + newCost + ")" ;

        Toast.makeText(activity,INSERT_NEW_REGISTRO, Toast.LENGTH_SHORT ).show();
        //Realizamos el update
        try{
            db.execSQL(INSERT_NEW_REGISTRO);
            Toast.makeText(activity, "SE INSERTO EL REGISTRO EXITOSAMENTE",Toast.LENGTH_SHORT);
            db.close();
        }catch(Exception e){
            Toast.makeText(activity, "ERROR:NO SE INSERTO EL REGISTRO",Toast.LENGTH_SHORT).show();
        }
    }
}
