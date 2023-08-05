package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class CorrConfirmarCorresponsal extends AppCompatActivity {

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    boolean respuesta;
    String texto;
    ImageButton menu;
    ImageButton atras;
    EditText salidaDatos;
    Button Continuar;
    Button Canselar;
    Corresponsales corresponsal;
    DbConecction dbConecction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corr_confirmar_corresponsal);
        getSupportActionBar().hide();

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        menu= findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        salidaDatos = findViewById(R.id.TextSalidaCorr);

        Continuar =findViewById(R.id.ConfirmarCorr);
        Canselar= findViewById(R.id.CanselarCorr);

        corresponsal = new Corresponsales();
        dbConecction = new DbConecction(this);

        menu.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);
        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        corresponsal = (Corresponsales) extras.getSerializable("corresponsales");

        texto = "NOMBRE CORRESPONSAL : "+ corresponsal.getNombre()+"\n" +
                "Número NIT: "+ corresponsal.getNit()+"\n" +
                "Saldo del corresponsal: "+ corresponsal.getSaldo()+"\n" +
                "Correo del corresponsal: "+ corresponsal.getCorreo();

        salidaDatos.setText(texto);

        Continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = dbConecction.editarCorresponsal(corresponsal);
                if (respuesta){
                    salir();
                }else {
                    Toast.makeText(CorrConfirmarCorresponsal.this, "Error al editar", Toast.LENGTH_LONG).show();
                }
                salir();
            }
        });

        Canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            salirS();
            }
        });

    }

    private  void salir(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Proceso realizado correctamente");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_POSITIVA);
        startActivity(intent);
    }

    private  void salirS(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Proceso canselado");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }

}