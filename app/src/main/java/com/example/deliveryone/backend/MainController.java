package com.example.deliveryone.backend;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

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
            Toast.makeText(act, "Acceso permitido" , Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            Toast.makeText(act, "ERROR: Las credenciales no son correctas. Rectificar",Toast.LENGTH_LONG).show();
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

    private void queryAllRegisters(){

    }
    private void QueryOneRegister(){

    }
    private void deleteRegister(){

    }
    private void updateRegister(){

    }


}
