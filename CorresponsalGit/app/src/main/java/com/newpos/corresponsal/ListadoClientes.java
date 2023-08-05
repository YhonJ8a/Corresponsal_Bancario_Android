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
import android.widget.ImageView;
import android.widget.TextView;

import com.newpos.corresponsal.adaptadores.ListadoClientesAdapter;
import com.newpos.corresponsal.db.DbConecction;

public class ListadoClientes extends AppCompatActivity {


    ImageButton menu;
    ImageButton atras;
    EditText buscador;
    RecyclerView listalibros;
    DbConecction dbConecction;
    ListadoClientesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_clientes);
        getSupportActionBar().hide();

        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        buscador = findViewById(R.id.buscador);
        listalibros = findViewById(R.id.ListaClientes);
        dbConecction = new DbConecction(this);

        menu.setVisibility(View.INVISIBLE);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });



        if (dbConecction.listaUsuarios()!= null){
            listalibros.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ListadoClientesAdapter(dbConecction.listaUsuarios());
            listalibros.setAdapter(adapter);


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
                    listalibros.setAdapter(adapter);
                }
            });
        }


    }

    private  void salir(){
        Intent intent = new Intent(this, AdminVistaPrincipal.class);
        startActivity(intent);
    }

}