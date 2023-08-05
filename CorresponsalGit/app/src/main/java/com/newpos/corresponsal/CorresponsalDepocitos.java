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

import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class CorresponsalDepocitos extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    EditText CCaDepocitar;
    EditText CCqueDepocita;
    EditText monto;

    Button continuar;
    Button canselar;

    Usuarios usuarioASepocitar;
    DbConecction dbConecction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_depocitos);
        getSupportActionBar().hide();

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        CCaDepocitar = findViewById(R.id.CCaDepocitar);
        CCqueDepocita = findViewById(R.id.CCqDepocita);
        monto = findViewById(R.id.montoDepocito);

        dbConecction = new DbConecction(this);

        continuar = findViewById(R.id.confirmarDepocito);
        canselar = findViewById(R.id.CanselarDepocito);

        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        menu.setVisibility(View.INVISIBLE);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(CCaDepocitar.getText().toString().equals("") ||
                    CCqueDepocita.getText().toString().equals("") ||
                    monto.getText().toString().equals(""))){

                    usuarioASepocitar = dbConecction.infoUsuarioCC(CCaDepocitar.getText().toString());
                    if (usuarioASepocitar != null){

                        irAConfirmar(usuarioASepocitar, monto.getText().toString(), CCqueDepocita.getText().toString());

                    }else{
                        Toast.makeText(CorresponsalDepocitos.this, "EL CORREO DESTINATARIO NO ESTA REGISTRADO...", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CorresponsalDepocitos.this, "No deben aver campos vacios!", Toast.LENGTH_LONG).show();
                }
            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirS();
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

    }

    public void  irAConfirmar(Usuarios usuario, String valor, String CCqDepocita){
        Intent intent = new Intent(this,ConfirmarDepocito.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("CCqDepocita", CCqDepocita);
        intent.putExtra("valor", valor);

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