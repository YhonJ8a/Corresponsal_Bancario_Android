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

import com.newpos.corresponsal.Entidades.Transacciones;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class CorrTransferenciaPin extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    Usuarios usuarioInp;
    Usuarios usuarioOup;
    String cobro;

    EditText pin;

    Button confirmar;
    Button canselar;

    DbConecction dbConecction;
    Transacciones transaccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corr_transferencia_pin);
        getSupportActionBar().hide();

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        pin = findViewById(R.id.PinTransferencia);
        confirmar = findViewById(R.id.confirmarPinT);
        canselar = findViewById(R.id.CanselarPinT);

        dbConecction = new DbConecction(this);
        transaccion = new Transacciones();

        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        usuarioInp = (Usuarios) extras.getSerializable("usuarioInp");
        usuarioOup = (Usuarios) extras.getSerializable("usuarioOup");
        cobro = extras.getString("valor");

        menu.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);

        confirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (pin.getText().toString().equals("")){
                    Toast.makeText(CorrTransferenciaPin.this, "Ingrese un pin!", Toast.LENGTH_LONG).show();
                }else {
                    if (pin.getText().toString().equals(String.valueOf(usuarioOup.getPin()))) {

                        transaccion.setIdCliente(usuarioOup.getId_usuario());
                        transaccion.setMonto(cobro);
                        transaccion.setTipo("TRANSFRERENCIA");

                        Boolean id = dbConecction.insertarTransaccionTransferencia(transaccion, usuarioInp);

                        if (id){
                            salir();
                        }else{
                            Toast.makeText(CorrTransferenciaPin.this, "Error en la operacion", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(CorrTransferenciaPin.this, "PIN erroneo!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirS();
            }
        });
    }

    private  void salir(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Prosceso realizado correctamente");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_POSITIVA);
        startActivity(intent);
    }

    private  void salirS(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Prosceso canselado");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }
}
