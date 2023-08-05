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

public class ConfirmarDepocito extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;

    TextView CcaDepocitar;
    TextView CcqDepocita;
    TextView cantidad;

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    Button confirmar;
    Button canselar;

    Usuarios usuario;
    String cobro;
    String CCqDepocita;
    DbConecction dbConecction;
    Transacciones transaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_depocito);
        getSupportActionBar().hide();

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        CcaDepocitar = findViewById(R.id.ccqDepocita);
        CcqDepocita = findViewById(R.id.ccaDepocitar);
        cantidad = findViewById(R.id.valorDepocito);

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        confirmar = findViewById(R.id.confirmarDepocito);
        canselar = findViewById(R.id.CanselarDepocito);

        dbConecction = new DbConecction(this);
        transaccion = new Transacciones();

        Bundle extras = getIntent().getExtras();
        usuario = (Usuarios) extras.getSerializable("usuario");
        CCqDepocita = extras.getString("CCqDepocita");
        cobro = extras.getString("valor");

        CcaDepocitar.setText(usuario.getId_usuario());
        CcqDepocita.setText(CCqDepocita);
        cantidad.setText(cobro);


        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaccion.setIdCliente(usuario.getId_usuario());
                transaccion.setTipo("DEPOCITO");
                transaccion.setMonto(cobro);
                transaccion.setIdDepocito(CCqDepocita);

                boolean respuesta = dbConecction.insertarTransaccionDepocito(transaccion);
                if (respuesta){
                    salir();
                }else {
                    Toast.makeText(ConfirmarDepocito.this, "Error en la operacion!", Toast.LENGTH_LONG).show();
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


}