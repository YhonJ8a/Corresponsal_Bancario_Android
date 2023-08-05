package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class VistaCanselarCorr extends AppCompatActivity {

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    ImageView imagen;
    ImageButton menu;
    ImageButton atras;
    TextView salida;
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_canselar_corr);
        getSupportActionBar().hide();

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);
        salida = findViewById(R.id.salidaTextoCorr);
        salir = findViewById(R.id.SsalirCor);
        imagen = findViewById(R.id.imagenVistacanselarCorr);

        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        salida.setText(extras.getString("respuesta"));
        Glide.with(VistaCanselarCorr.this).load(extras.getString("imagen")).error(R.drawable.ic_error).into(imagen);

        menu.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });
    }

    private  void salir(){
        Intent intent = new Intent(this, CorresponsalVistaPrincipal.class);
        startActivity(intent);
    }
}