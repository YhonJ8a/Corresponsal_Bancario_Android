package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class ConsultarCorresponsal extends AppCompatActivity {

    EditText  nit;
    ImageButton menu;
    ImageButton atras;
    Button confirmar;
    Button canselar;
    DbConecction dbConecction;
    Corresponsales corresponsal;
    String anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_corresponsal);
        getSupportActionBar().hide();

        nit = findViewById(R.id.nit);
        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        confirmar = findViewById(R.id.confirmarConsulCorresponsal);
        canselar = findViewById(R.id.canselarNewConsulCorresponsal);
        dbConecction = new DbConecction(this);
        corresponsal = new Corresponsales();
        anterior = null;


        menu.setVisibility(View.INVISIBLE);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salir();
            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaSalir();
            }
        });


        Bundle extras = getIntent().getExtras();
        anterior = extras.getString("anterior");
        if (anterior.equals("menuCorresponsal")){
            Actualizar((Corresponsales) extras.getSerializable("Corresponsal"));
        }

        confirmar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(nit.getText().length() > 1) {
                     corresponsal = dbConecction.infoCorresponsal(nit.getText().toString());
                     if (corresponsal != null && anterior.equals("null")){
                         siguiente(corresponsal);
                     }else if(corresponsal != null && anterior.equals("menu")){
                         Actualizar(corresponsal);
                     }else{
                         Toast.makeText(ConsultarCorresponsal.this, "El NIT no existe", Toast.LENGTH_LONG).show();
                     }
                 }else{
                     Toast.makeText(ConsultarCorresponsal.this, "Ingrese un NIT valido!", Toast.LENGTH_LONG).show();
                 }
             }
        });
    }

    public void  siguiente(Corresponsales corresponsal){
        Intent intent = new Intent(this,ConfirmarCorresponsal.class);
        intent.putExtra("corresponsales", corresponsal);
        startActivity(intent);
    }

    public void  Actualizar(Corresponsales corresponsal){
        Intent intent = new Intent(this,ActualizarCorresponsal.class);
        intent.putExtra("corresponsales", corresponsal);
        startActivity(intent);
    }

    public void  Salir(){
        Intent intent = new Intent(this,AdminVistaPrincipal.class);
        startActivity(intent);
    }

    public void vistaSalir(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", "CONSULTA CORRESPONSAL CANSELADA!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }


}