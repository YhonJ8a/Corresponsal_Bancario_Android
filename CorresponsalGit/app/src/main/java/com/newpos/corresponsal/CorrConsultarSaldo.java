package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class CorrConsultarSaldo extends AppCompatActivity {

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    String salida;

    View notifi;
    ImageButton menu;
    ImageButton atras;
    EditText NumeroCC;
    EditText salidaText;
    DbConecction dbConecction;

    Button confirmar;
    Button Aceptar;
    Button canselar;
    Button si;
    Button no;

    Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corr_consultar_saldo);
        getSupportActionBar().hide();

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);
        dbConecction = new DbConecction(this);
        NumeroCC = findViewById(R.id.ccConsultaS);
        confirmar = findViewById(R.id.confirmarConsultSaldo);
        canselar = findViewById(R.id.canselarConsultSaldo);
        usuario= new Usuarios();
        notifi = findViewById(R.id.notificacion);
        si = findViewById(R.id.si);
        no = findViewById(R.id.no);
        salidaText = findViewById(R.id.salidaTextConsultSaldo);
        Aceptar = findViewById(R.id.aceptarConsulta);

        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);
        menu.setVisibility(View.INVISIBLE);
        notifi.setVisibility(View.INVISIBLE);
        salidaText.setVisibility(View.INVISIBLE);
        Aceptar.setVisibility(View.INVISIBLE);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NumeroCC.getText().toString().equals("")){
                    Toast.makeText(CorrConsultarSaldo.this, "Ingrese su cedula!", Toast.LENGTH_LONG).show();
                }else{
                    if(dbConecction.infoUsuarioCC((NumeroCC.getText().toString())) != null){

                        usuario = dbConecction.infoUsuarioCC(NumeroCC.getText().toString());
                        notifi.setVisibility(View.VISIBLE);

                    }else{
                        Toast.makeText(CorrConsultarSaldo.this, "Cedula no registrada", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean res = dbConecction.cobrosExtras(usuario.getNombre());

                if (res){
                    salida = "Usuario: "+ usuario.getNombre()+ "\n" +
                            "N° CC: "+ usuario.getId_usuario()+ "\n" +
                            "Saldo : "+ usuario.getSaldo();
                    salidaText.setText(salida);
                    notifi.setVisibility(View.INVISIBLE);
                    NumeroCC.setVisibility(View.INVISIBLE);
                    confirmar.setVisibility(View.INVISIBLE);
                    canselar.setVisibility(View.INVISIBLE);
                    salidaText.setVisibility(View.VISIBLE);
                    Aceptar.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(CorrConsultarSaldo.this, "Error en la operacion", Toast.LENGTH_LONG).show();
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirS();
            }
        });
        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirS();
            }
        });

        Aceptar.setOnClickListener(new View.OnClickListener() {
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

    private  void salirS(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Prosceso canselado");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }

}