package com.newpos.corresponsal.Entidades;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public SharedPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("bd_shared", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUsuarioActivo(String usuarioActivo){
        editor.putString("correoUsuarioAct", usuarioActivo);
        editor.apply();
    }

    public String getUsuarioActivo(){
        return sharedPreferences.getString("correoUsuarioAct", "no encontrado");
    }
}

