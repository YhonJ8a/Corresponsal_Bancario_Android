package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.SharedPreference;
import com.newpos.corresponsal.db.DbConecction;

public class login extends AppCompatActivity {


    EditText correo;
    EditText password;
    Button IniciarSesion;
    SharedPreference sp;
    Corresponsales corresponsal;
    DbConecction dbConecction;
    boolean visible;


    //credenciales admin
    String correoAdmin = "admin@wposs.com";
    String passwordAdmin = "Admin123*";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        correo = findViewById(R.id.loginCorreo);
        password = findViewById(R.id.loginPassword);
        IniciarSesion = findViewById(R.id.loginBtnIniciaS);
        dbConecction = new DbConecction(this);
        corresponsal = new Corresponsales();
        sp = new SharedPreference(this);



        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correoStr = correo.getText().toString();
                String passwordStr = password.getText().toString();

                if (!(correoStr.equals(""))){
                    if (!(passwordStr.equals(""))){
                        if (correoStr.equals(correoAdmin) && passwordStr.equals(passwordAdmin)){
                            sp.setUsuarioActivo(correoAdmin);
                            irAVistaAdmin();
                        }else{
                            if (dbConecction.infoUsuario(correoStr) != null){
                                corresponsal.setPassword(dbConecction.infoUsuario(correoStr));
                                if(corresponsal.getPassword().equals(passwordStr)){
                                    sp.setUsuarioActivo(correoStr);
                                    irAVistaCorresponsal();
                                }else {
                                    Toast.makeText(login.this, "LA CONTRASEÑA ES INCORRECTA...", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(login.this, "EL CORREO NO ESTA REGISTRADO...", Toast.LENGTH_LONG).show();
                            }
                        }
                    }else {
                        Toast.makeText(login.this, "EL CAMPO CONTRASEÑA NO DEBE ESTAR VACIO!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(login.this, "EL CAMPO CORREO NO DEBE ESTAR VACIO!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    public void  irAVistaAdmin(){
        Intent intent = new Intent(this,AdminVistaPrincipal.class);
        startActivity(intent);
    }

    public void  irAVistaCorresponsal(){
        Intent intent = new Intent(this,CorresponsalVistaPrincipal.class);
        startActivity(intent);
    }
}