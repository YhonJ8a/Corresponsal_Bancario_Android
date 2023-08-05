package com.newpos.corresponsal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static  final int DATBASE_VERSION= 1;
    private static  final String DATABASE_NOMBRE = "corresponsal.db" ;
    public static  final String TABLE_TRANSACCIONES = "t_transacciones" ;
    public static  final String TABLE_USUARIOS = "t_usuarios" ;
    public static  final String TABLE_CORRESPONSAL = "t_corresponsal" ;


//    columnas tabla transacciones.

    public static final String COD_TRANSACCION = "cod_transaccion";
    public static final String ID_CLIENTE = "id_cliente";
    public static final String CODIGO_CORRES = "cod_corresponsal";
    public static final String CC_DEPOCITOS = "cc_cliente_depocito";
    public static final String FECHA = "fecha";
    public static final String TIPO = "tipo";
    public static final String MONTO = "monto";


    //    columnas tabla cliente.

    public static final String CC_CLIENTE = "cedula_cliente";
    public static final String NOMBRE = "nombre";
    public static final String NUM_TARJETA = "num_tarjeta";
    public static final String SALDO = "saldo";
    public static final String PIN = "pin";
    public static final String FECHAT = "fechat";
    public static final String CVV = "cvv";

    //    columnas tabla corresponsal.

    public static final String COD_CORRESPONSAL = "cod_corresponsal";
    public static final String NOMBRE_CORRESPONSAL = "nombre_corresponsal";
    public static final String NIT_CORRESPONSAL = "nit_corresponsal";
    public static final String CUENTA_CORRESPONSAL = "cuenta_corresponsal";
    public static final String CORREO = "correo";
    public static final String ESTADO = "estado";
    public static final String PASSWORD = "password";
    public static final String SALDO_CORRESPONSAL = "saldo_corresponsal";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null,DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_TRANSACCIONES + "(" +
                ""+COD_TRANSACCION+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+ID_CLIENTE+" INTEGER NOT NULL, " +
                ""+CODIGO_CORRES+" INTEGER NOT NULL, " +
                ""+CC_DEPOCITOS+" INTEGER , " +
                ""+FECHA+" DATE NOT NULL," +
                ""+TIPO+" TEXT, " +
                ""+MONTO+" INTEGER )");


        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_USUARIOS + "(" +
                ""+CC_CLIENTE+" INTEGER PRIMARY KEY , " +
                ""+NOMBRE+" TEXT NOT NULL, " +
                ""+NUM_TARJETA+" TEXT NOT NULL," +
                ""+SALDO+" INTEGER NOT NULL," +
                ""+FECHAT+" TEXT ," +
                ""+PIN+" INTEGER ," +
                ""+CVV+" INTEGER )");


        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CORRESPONSAL + "(" +
                ""+COD_CORRESPONSAL+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+NOMBRE_CORRESPONSAL+" TEXT NOT NULL, " +
                ""+NIT_CORRESPONSAL+" TEXT NOT NULL," +
                ""+CUENTA_CORRESPONSAL+" TEXT NOT NULL," +
                ""+SALDO_CORRESPONSAL+" INTEGER NOT NULL," +
                ""+CORREO+" TEXT NOT NULL," +
                ""+ESTADO+" TEXT NOT NULL," +
                ""+PASSWORD+" TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        onCreate(sqLiteDatabase);
    }
}
