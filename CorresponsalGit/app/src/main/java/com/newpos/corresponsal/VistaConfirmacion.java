package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;

public class VistaConfirmacion extends AppCompatActivity {
    TextView textSalida;
    Button confirmar;
    Button canselar;
    Corresponsales corresponsal;
    String texto;
    DbConecction dbConecction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_confirmacion);
        getSupportActionBar().hide();

        textSalida = findViewById(R.id.TextSalida);
        confirmar = findViewById(R.id.confirmarNewCorr);
        canselar = findViewById(R.id.canselaNewCorr);
        dbConecction = new DbConecction(this);

        Bundle extras = getIntent().getExtras();
        corresponsal = (Corresponsales) extras.getSerializable("corresponsal");
        corresponsal.setCuenta(dbConecction.NunTarjetaRandom(corresponsal.getNit()));

        texto = "NOMBRE CORRESPONSAL : "+ corresponsal.getNombre()+"\n" +
                "Número NIT: "+ corresponsal.getNit()+"\n" +
                "Saldo del corresponsal: "+ corresponsal.getSaldo()+"\n" +
                "Correo del corresponsal: "+ corresponsal.getCorreo();

        textSalida.setText(texto);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = dbConecction.insertarNuevoCorresponsal(corresponsal);
                if(id > 0){
                    salir();
                } else{
                    Toast.makeText(VistaConfirmacion.this, "Error al agregar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private  void salir(){
        Intent intent = new Intent(this, AdminVistaPrincipal.class);
        startActivity(intent);
    }

}