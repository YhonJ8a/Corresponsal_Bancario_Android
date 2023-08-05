package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Transacciones;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class ConfirmacionRetiro extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;

    TextView nombre;
    TextView cuenta;
    TextView valor;

    Button confirmar;
    Button canselar;

    Usuarios usuario;
    String cobro;
    int retiro;
    DbConecction dbConecction;
    Transacciones transaccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_retiro);
        getSupportActionBar().hide();

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        nombre = findViewById(R.id.nombreClienteRetiro);
        cuenta = findViewById(R.id.valorCliente);
        valor = findViewById(R.id.valorRetiro);

        confirmar = findViewById(R.id.confirmarConfirmacion);
        canselar = findViewById(R.id.CanselarConfirmacion);

        dbConecction = new DbConecction(this);
        transaccion = new Transacciones();


        menu.setVisibility(View.INVISIBLE);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

        Bundle extras = getIntent().getExtras();
        usuario = (Usuarios) extras.getSerializable("usuario");
        cobro = extras.getString("valor");

        retiro = Integer.parseInt(cobro)-2000;

        nombre.setText(usuario.getNombre());
        cuenta.setText(usuario.getNumTarjeta());
        valor.setText(String.valueOf(retiro));

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transaccion.setIdCliente(usuario.getId_usuario());
                transaccion.setMonto(cobro);
                transaccion.setTipo("RETIROS");

                Boolean id = dbConecction.insertarTransaccion(transaccion);

                if (id){
                    salir();
                }else{
                    Toast.makeText(ConfirmacionRetiro.this, "Error en la operacion", Toast.LENGTH_LONG).show();
                }
            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirS();
            }
        });
    }

    private  void salir(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Prosceso realizado correctamente");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_POSITIVA);
        startActivity(intent);
    }

    private  void salirS(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Prosceso canselado");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }

    private  void atras(){
        Intent intent = new Intent(this, CorresponsalVistaPrincipal.class);
        startActivity(intent);
    }
}