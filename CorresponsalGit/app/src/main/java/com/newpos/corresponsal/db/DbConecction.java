package com.newpos.corresponsal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.SharedPreference;
import com.newpos.corresponsal.Entidades.Transacciones;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.variables.Constantes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DbConecction extends DbHelper{

    Context context;
    SharedPreference sp ;

    public DbConecction(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public String infoUsuario(String correo){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String passUsuario;
        Cursor cursorUsers ;

        cursorUsers= db.rawQuery("select * from "+ TABLE_CORRESPONSAL +" WHERE "+CORREO+" = '"+ correo+"'", null);

        if(cursorUsers.moveToFirst()) {
            passUsuario = cursorUsers.getString(7);
        }else {
            return null;
        }
        cursorUsers.close();
        return passUsuario;
    }

    public long insertarNuevoCliente(Usuarios usuario) {

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put( CC_CLIENTE, usuario.getId_usuario());
            values.put(NOMBRE, usuario.getNombre());
            values.put(NUM_TARJETA, NunTarjetaRandom(usuario.getId_usuario()));
            values.put(SALDO, usuario.getSaldo());
            values.put(PIN, usuario.getPin());
            values.put(FECHAT, usuario.getFecha());
            values.put(CVV, NunCVVRandom());

            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public String NunTarjetaRandom(String cc){
        String numero = String.valueOf(Math.round(3 + Math.random()*3));
        numero+= cc;
        int limite = 16-numero.length();
        for (int i = 0; i < limite; i++){
            numero += String.valueOf(Math.round(Math.random()* 9));
        }
        return numero;
    }


    public String NunCVVRandom(){
        String numero = "";
        for (int i = 0; i < 3; i++){
            numero += String.valueOf(Math.round(Math.random()* 9));
        }
        return numero;
    }

    public long insertarNuevoCorresponsal(Corresponsales corresponsal) {

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(NOMBRE_CORRESPONSAL, corresponsal.getNombre());
            values.put(NIT_CORRESPONSAL, corresponsal.getNit());
            values.put(CORREO, corresponsal.getCorreo());
            values.put(PASSWORD, corresponsal.getPassword());
            values.put(SALDO_CORRESPONSAL, corresponsal.getSaldo());
            values.put(CUENTA_CORRESPONSAL,corresponsal.getCuenta());
            values.put(ESTADO, "HABILITADO");

            id = db.insert(TABLE_CORRESPONSAL, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }


    public Usuarios infoUsuarioCC(String cc){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuarios usuario = new Usuarios();
        Cursor cursorUsers ;

        cursorUsers= db.rawQuery("select * from "+ TABLE_USUARIOS+" WHERE "+CC_CLIENTE+" = '"+ cc+"'", null);

        if(cursorUsers.moveToFirst()) {
            usuario.setId_usuario(cursorUsers.getString(0));
            usuario.setNombre(cursorUsers.getString(1));
            usuario.setNumTarjeta(cursorUsers.getString(2));
            usuario.setSaldo(cursorUsers.getInt(3));
            usuario.setFecha(cursorUsers.getString(4));
            usuario.setPin(cursorUsers.getInt(5));
            usuario.setCvv(cursorUsers.getString(6));

        }else {
            return null;
        }
        cursorUsers.close();
        return usuario;
    }


    public Corresponsales infoCorresponsal(String nit){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Corresponsales corresponsal = new Corresponsales();
        Cursor cursorUsers ;

        cursorUsers= db.rawQuery("select * from "+ TABLE_CORRESPONSAL +" WHERE "+NIT_CORRESPONSAL+" = '"+ nit+"'", null);

        if(cursorUsers.moveToFirst()) {
            corresponsal.setCod_corresponsal( cursorUsers.getInt(0));
            corresponsal.setNombre(cursorUsers.getString(1));
            corresponsal.setNit(cursorUsers.getString(2));
            corresponsal.setCuenta(cursorUsers.getString(3));
            corresponsal.setSaldo(cursorUsers.getInt(4));
            corresponsal.setCorreo(cursorUsers.getString(5));
            corresponsal.setEstado(cursorUsers.getString(6));
            corresponsal.setPassword(cursorUsers.getString(7));
        }else {
            return null;
        }
        cursorUsers.close();
        return corresponsal;
    }

    public Corresponsales infoCorresponsalCorreo(String correo){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Corresponsales corresponsal = new Corresponsales();
        Cursor cursorUsers ;

        cursorUsers= db.rawQuery("select * from "+ TABLE_CORRESPONSAL +" WHERE "+NOMBRE_CORRESPONSAL+" = '"+ correo+"'", null);

        if(cursorUsers.moveToFirst()) {
            corresponsal.setCod_corresponsal( cursorUsers.getInt(0));
            corresponsal.setNombre(cursorUsers.getString(1));
            corresponsal.setNit(cursorUsers.getString(2));
            corresponsal.setCuenta(cursorUsers.getString(3));
            corresponsal.setSaldo(cursorUsers.getInt(4));
            corresponsal.setCorreo(cursorUsers.getString(5));
            corresponsal.setEstado(cursorUsers.getString(6));
            corresponsal.setPassword(cursorUsers.getString(7));
        }else {
            return null;
        }
        cursorUsers.close();
        return corresponsal;
    }


    public Usuarios infoCorresponsalNumTar(String numero){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuarios usuarios = new Usuarios();
        Cursor cursorUsers ;

        cursorUsers= db.rawQuery("select * from "+ TABLE_USUARIOS +" WHERE "+NUM_TARJETA+" = '"+ numero+"'", null);

        if(cursorUsers.moveToFirst()) {
            usuarios.setId_usuario(cursorUsers.getString(0));
            usuarios.setNombre(cursorUsers.getString(1));
            usuarios.setNumTarjeta(cursorUsers.getString(2));
            usuarios.setSaldo(cursorUsers.getInt(3));
            usuarios.setFecha(cursorUsers.getString(4));
            usuarios.setPin(cursorUsers.getInt(5));
            usuarios.setCvv(cursorUsers.getString(6));
        }else {
            return null;
        }
        cursorUsers.close();
        return usuarios;
    }

    public boolean HAbilitarDeshabilitar(String nit, String estado) {

        boolean id = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            db.execSQL("UPDATE "+TABLE_CORRESPONSAL+" SET "+ESTADO+" = '"+estado+"' WHERE "+NIT_CORRESPONSAL+" = "+nit+" ;");
            id = true;
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Usuarios>  listaUsuarios(){

        ArrayList<Usuarios> lista = new ArrayList<>();
        Cursor cursorUsuarios;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Usuarios usuario ;

            cursorUsuarios = db.rawQuery("select * from "+TABLE_USUARIOS , null);

            if(cursorUsuarios.moveToFirst()){
                do {
                    usuario = new Usuarios();
                    usuario.setId_usuario(cursorUsuarios.getString(0));
                    usuario.setNombre(cursorUsuarios.getString(1));
                    usuario.setNumTarjeta(cursorUsuarios.getString(2));
                    usuario.setSaldo(cursorUsuarios.getInt(3));
                    usuario.setFecha(cursorUsuarios.getString(4));
                    usuario.setPin(cursorUsuarios.getInt(5));
                    lista.add(usuario);
                }while (cursorUsuarios.moveToNext());
            }else {
                return null;
            }
        } catch (Exception ex){
            ex.toString();
        }
        return lista;
    }



    public ArrayList<Corresponsales>  listaCorresponsal(){

        ArrayList<Corresponsales> lista = new ArrayList<>();
        Cursor cursorCorresponsal;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Corresponsales corresponsal ;

            cursorCorresponsal = db.rawQuery("select * from "+TABLE_CORRESPONSAL , null);

            if(cursorCorresponsal.moveToFirst()){
                do {
                    corresponsal = new Corresponsales();
                    corresponsal.setCod_corresponsal(cursorCorresponsal.getInt(0));
                    corresponsal.setNombre(cursorCorresponsal.getString(1));
                    corresponsal.setNit(cursorCorresponsal.getString(2));
                    corresponsal.setCuenta(cursorCorresponsal.getString(3));
                    corresponsal.setSaldo(cursorCorresponsal.getInt(4));
                    corresponsal.setCorreo(cursorCorresponsal.getString(5));
                    corresponsal.setEstado(cursorCorresponsal.getString(6));
                    lista.add(corresponsal);
                }while (cursorCorresponsal.moveToNext());
            }else {
                return null;
            }
        } catch (Exception ex){
            ex.toString();
        }
        return lista;
    }

    public boolean editarCorresponsal(Corresponsales corresponsal) {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE "+TABLE_CORRESPONSAL+
                    " SET "+NOMBRE_CORRESPONSAL+" = '"+ corresponsal.getNombre()+
                    "', "+NIT_CORRESPONSAL+" = '"+corresponsal.getNit()+
                    "', "+SALDO_CORRESPONSAL+" = '"+ corresponsal.getSaldo()+
                    "', "+PASSWORD+" = '"+ corresponsal.getPassword()+
                    "' WHERE "+COD_CORRESPONSAL+" ='"+corresponsal.getCod_corresponsal()+"' ");
            correcto = true;

        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }


    public Boolean insertarTransaccion(Transacciones transaccion) {

        sp = new SharedPreference(context);
        long id = 0;
        Boolean respuesta;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ID_CLIENTE, transaccion.getIdCliente());
            values.put(CODIGO_CORRES, infoUsuario(sp.getUsuarioActivo()));
            values.put(FECHA, fecha());
            values.put(TIPO, transaccion.getTipo());
            values.put(MONTO,transaccion.getMonto());

            id = db.insert(TABLE_TRANSACCIONES, null, values);
            if (id>0){
                respuesta = descontarMontoTarjeta(transaccion.getIdCliente(), transaccion.getMonto() );
                if (respuesta){
                    respuesta = agregarMontoACorresponsal(infoUsuario(sp.getUsuarioActivo()), transaccion.getMonto());
                    if (respuesta){
                        return true;
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }else {
                return false;
            }
        } catch (Exception ex){
            ex.toString();
            return false;
        }
    }

    public String fecha(){
        return new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    }


    public boolean descontarMontoTarjeta(String cod, String cantida) {

        boolean id = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL("UPDATE t_usuarios SET saldo = saldo-"+cantida+" WHERE cedula_cliente = '"+cod+"' ;");
            id = true;
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }


    public boolean agregarMontoACorresponsal(String cod, String cantida) {

        boolean id = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL("UPDATE t_corresponsal SET saldo_corresponsal = saldo_corresponsal + "+cantida+" WHERE nit_corresponsal = "+cod+" ;");
            id = true;
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }


    public Boolean insertarTransaccionDepocito(Transacciones transaccion) {

        sp = new SharedPreference(context);
        long id = 0;
        Boolean respuesta;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ID_CLIENTE, transaccion.getIdCliente());
            values.put(CODIGO_CORRES, infoUsuario(sp.getUsuarioActivo()));
            values.put(CC_DEPOCITOS, transaccion.getIdDepocito());
            values.put(FECHA, fecha());
            values.put(TIPO, transaccion.getTipo());
            values.put(MONTO,transaccion.getMonto());

            id = db.insert(TABLE_TRANSACCIONES, null, values);
            if (id>0){
                respuesta = agregarMontoTarjeta(transaccion.getIdCliente(), transaccion.getMonto() );
                if (respuesta){
                    respuesta = descontarMontoACorresponsal(infoUsuario(sp.getUsuarioActivo()), transaccion.getMonto());
                    if (respuesta){
                        return true;
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }else {
                return false;
            }
        } catch (Exception ex){
            ex.toString();
            return false;
        }
    }

    public boolean agregarMontoTarjeta(String cod, String cantida) {

        boolean id = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL("UPDATE t_usuarios SET saldo = saldo + "+cantida+" WHERE cedula_cliente = '"+cod+"' ;");
            id = true;
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public boolean descontarMontoACorresponsal(String cod, String cantida) {

        boolean id = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL("UPDATE t_corresponsal SET saldo_corresponsal = saldo_corresponsal - "+ cantida +" WHERE nit_corresponsal = "+cod+" ;");
            id = true;
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public Boolean insertarTransaccionTransferencia(Transacciones transaccion, Usuarios usuarioInp) {

        sp = new SharedPreference(context);
        long id = 0;
        Boolean respuesta;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ID_CLIENTE, transaccion.getIdCliente());
            values.put(CODIGO_CORRES, infoUsuario(sp.getUsuarioActivo()));
            values.put(FECHA, fecha());
            values.put(TIPO, transaccion.getTipo());
            values.put(MONTO,transaccion.getMonto());
            values.put(CC_DEPOCITOS, usuarioInp.getId_usuario());

            id = db.insert(TABLE_TRANSACCIONES, null, values);
            if (id>0){
                respuesta = descontarMontoTarjeta(transaccion.getIdCliente(), transaccion.getMonto() );
                if (respuesta){
                    respuesta = agregarMontoTarjeta(usuarioInp.getId_usuario(), transaccion.getMonto());
                    if (respuesta){
                        return true;
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }else {
                return false;
            }
        } catch (Exception ex){
            ex.toString();
            return false;
        }
    }

    public ArrayList<Transacciones>  listaTransacciones(){

        ArrayList<Transacciones> lista = new ArrayList<>();
        Cursor cursorTransaccion;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Transacciones transaccion ;

            cursorTransaccion = db.rawQuery("select * from "+TABLE_TRANSACCIONES , null);

            if(cursorTransaccion.moveToFirst()){
                do {
                    transaccion = new Transacciones();
                    transaccion.setCodigoTransaccion(cursorTransaccion.getInt(0));
                    transaccion.setIdCliente(cursorTransaccion.getString(1));
                    transaccion.setIdCorresponsal(cursorTransaccion.getInt(2));
                    transaccion.setIdDepocito(cursorTransaccion.getString(3));
                    transaccion.setFechaTransaccion(cursorTransaccion.getString(4));
                    transaccion.setTipo(cursorTransaccion.getString(5));
                    transaccion.setMonto(cursorTransaccion.getString(6));
                    lista.add(transaccion);
                }while (cursorTransaccion.moveToNext());
            }else {
                return null;
            }
        } catch (Exception ex){
            ex.toString();
        }
        return lista;
    }

public Boolean cobrosExtras(String cliente){
    Boolean respuesta;
    sp = new SharedPreference(context);
    respuesta = descontarMontoTarjeta(cliente, "1000" );
    if (respuesta){
        respuesta = agregarMontoACorresponsal(infoUsuario(sp.getUsuarioActivo()), "1000");
        if (respuesta){
            return true;
        }else {
            return false;
        }
    }else {
        return false;
    }
}


}
