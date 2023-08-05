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

public class VistaConfirmaPin extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    Usuarios usuario;
    String cobro;
    int pinValidar;

    EditText pin;
    TextView TextConfirm;

    Button confirmar;
    Button canselar;

    DbConecction dbConecction;
    Transacciones transaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_confirma_pin);
        getSupportActionBar().hide();

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        pin = findViewById(R.id.Pin);
        confirmar = findViewById(R.id.confirmarPin);
        canselar = findViewById(R.id.CanselarPin);
        TextConfirm = findViewById(R.id.TextConfirm);

        dbConecction = new DbConecction(this);
        transaccion = new Transacciones();

        Bundle extras = getIntent().getExtras();
        usuario = (Usuarios) extras.getSerializable("usuario");
        cobro = extras.getString("valor");

        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        menu.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);
        TextConfirm.setText("Confirmar PIN");

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pin.getText().toString().equals("")){
                    Toast.makeText(VistaConfirmaPin.this, "Ingrese un pin!", Toast.LENGTH_LONG).show();
                }else {

                    if (extras.getString("anterior") == null){
                        if (pin.getText().toString().equals(String.valueOf(usuario.getPin()))) {

                            transaccion.setIdCliente(usuario.getId_usuario());
                            transaccion.setMonto(cobro);
                            transaccion.setTipo("PAGOS CON TARJETA");

                            Boolean id = dbConecction.insertarTransaccion(transaccion);

                            if (id){
                                salir();
                            }else{
                                Toast.makeText(VistaConfirmaPin.this, "Error en la operacion", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(VistaConfirmaPin.this, "PIN erroneo!", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        if(pin.getText().toString().length() == 4){

                            pinValidar = Integer.parseInt(pin.getText().toString());
                            pin.setText("");

                            TextConfirm.setText("Ingrese nuevamente su pin");

                            confirmar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (pin.getText().toString().length() != 0){
                                        int pinvalidar2 = Integer.parseInt(pin.getText().toString());

                                        if (pinvalidar2 == pinValidar) {

                                            Bundle extras = getIntent().getExtras();
                                            usuario = (Usuarios) extras.getSerializable("usuario");
                                            usuario.setPin(pinValidar);

                                            long id = dbConecction.insertarNuevoCliente(usuario);

                                            if (id > 0) {

                                                Toast.makeText(VistaConfirmaPin.this, "Registro guardado", Toast.LENGTH_LONG).show();
                                                salir();

                                            } else {
                                                Toast.makeText(VistaConfirmaPin.this, "Error al guardad el registro", Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(VistaConfirmaPin.this, "no coincidern los pines ", Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(VistaConfirmaPin.this, "Ingrese nuevamente su pin", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
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