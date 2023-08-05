package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newpos.corresponsal.adaptadores.ListadoTransaccionesAdater;
import com.newpos.corresponsal.db.DbConecction;

public class CorrHistorialTransaccional extends AppCompatActivity {

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    ImageButton menu;
    ImageButton atras;
    EditText buscador;
    RecyclerView listaCorresponsal;
    DbConecction dbConecction;
    ListadoTransaccionesAdater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corr_historial_transaccional);
        getSupportActionBar().hide();

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);
        buscador = findViewById(R.id.buscadorHistCorr);
        listaCorresponsal = findViewById(R.id.listaTransacciones);
        dbConecction = new DbConecction(this);

        menu.setVisibility(View.INVISIBLE);
        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });

        if (dbConecction.listaTransacciones() != null){
            listaCorresponsal.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ListadoTransaccionesAdater(dbConecction.listaTransacciones());
            listaCorresponsal.setAdapter(adapter);

            buscador.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    adapter.filtrado(s.toString());
                    listaCorresponsal.setAdapter(adapter);
                }
            });
        }


    }

    private  void salir(){
        Intent intent = new Intent(this, CorresponsalVistaPrincipal.class);
        startActivity(intent);
    }
}