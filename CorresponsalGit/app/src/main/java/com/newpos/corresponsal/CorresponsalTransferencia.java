package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class CorresponsalTransferencia extends AppCompatActivity {

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

    Usuarios usuarioQTransfiere;
    Usuarios usuariATransferir;
    DbConecction dbConecction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_transferencia);
        getSupportActionBar().hide();


        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        CCaDepocitar = findViewById(R.id.CCaTrasferir);
        CCqueDepocita = findViewById(R.id.CCqTransferir);
        monto = findViewById(R.id.montoTranferencia);

        dbConecction = new DbConecction(this);

        continuar = findViewById(R.id.confirmarTransferencia);
        canselar = findViewById(R.id.CanselarTransferencia);
        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        usuarioQTransfiere = new Usuarios();
        usuariATransferir = new Usuarios();

        menu.setVisibility(View.INVISIBLE);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(CCaDepocitar.getText().toString().equals("") ||
                        CCqueDepocita.getText().toString().equals("") ||
                        monto.getText().toString().equals(""))){

                    usuarioQTransfiere = dbConecction.infoUsuarioCC(CCqueDepocita.getText().toString());
                    if (usuarioQTransfiere != null){
                        usuariATransferir = dbConecction.infoUsuarioCC(CCaDepocitar.getText().toString());
                        if (usuariATransferir != null){

                            irAConfirmar(usuarioQTransfiere, monto.getText().toString(),usuariATransferir);

                        }else{
                            Toast.makeText(CorresponsalTransferencia.this, "EL CORREO DESTINATARIO NO ESTA REGISTRADO...", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(CorresponsalTransferencia.this, "EL CORREO DESTINATARIO NO ESTA REGISTRADO...", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CorresponsalTransferencia.this, "No deben aver campos vacios!", Toast.LENGTH_LONG).show();
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

    public void  irAConfirmar(Usuarios usuarioOup, String valor, Usuarios usuarioInp){
        Intent intent = new Intent(this,CorrTransferenciaPin.class);
        intent.putExtra("usuarioOup", usuarioOup);
        intent.putExtra("valor", valor);
        intent.putExtra("usuarioInp", usuarioInp);
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
