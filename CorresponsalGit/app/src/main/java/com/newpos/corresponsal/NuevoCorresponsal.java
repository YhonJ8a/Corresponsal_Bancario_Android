package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.variables.Constantes;

public class NuevoCorresponsal extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;
    EditText nombre;
    EditText nit;
    EditText correo;
    EditText password;
    Button confirmar;
    Button candelar;

    Corresponsales corresponsal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_corresponsal);
        getSupportActionBar().hide();

        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        nombre = findViewById(R.id.ccConsulta);
        nit = findViewById(R.id.NewCorrNit);
        correo = findViewById(R.id.NewCorrCorreo);
        password = findViewById(R.id.NEewCorrPassword);
        confirmar = findViewById(R.id.confirmarNewCorr);
        candelar = findViewById(R.id.canselaNewCorr);

        menu.setVisibility(View.INVISIBLE);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });


        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nombre.getText().length()!= 0 && nit.getText().length() != 0 && correo.getText().length() != 0 && password.getText().length() != 0){

                    corresponsal = new Corresponsales();
                    corresponsal.setNombre(nombre.getText().toString());
                    corresponsal.setNit(nit.getText().toString());
                    corresponsal.setCorreo(correo.getText().toString());
                    //corresponsal.setCuenta();
                    corresponsal.setPassword(password.getText().toString());
                    corresponsal.setSaldo(1000000);

                    Context context = view.getContext();
                    Intent intent = new Intent(context, VistaConfirmacion.class);
                    intent.putExtra("corresponsal", corresponsal);
                    context.startActivity(intent);

                }else{
                    Toast.makeText(NuevoCorresponsal.this, "Se deben llenar todos los campos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        candelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaSalir();
            }
        });

    }

    private  void salir(){
        Intent intent = new Intent(this, AdminVistaPrincipal.class);
        startActivity(intent);
    }
    public void vistaSalir(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", "NUEVO CORRESPONSAL CANSELADA!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }
}