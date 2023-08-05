package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class VistaCanselar extends AppCompatActivity {

    ImageView imagen;
    ImageButton menu;
    ImageButton atras;
    TextView salida;
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_canselar);
        getSupportActionBar().hide();

        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        salida = findViewById(R.id.salidaTextoCS);
        salir = findViewById(R.id.SsalirCS);
        imagen = findViewById(R.id.imagenVIstacanselarCS);

        Bundle extras = getIntent().getExtras();
        salida.setText(extras.getString("respuesta"));
        Glide.with(VistaCanselar.this).load(extras.getString("imagen")).error(R.drawable.ic_error).into(imagen);

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
        Intent intent = new Intent(this, AdminVistaPrincipal.class);
        startActivity(intent);
    }
}