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

public class VistaPin extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;

    TextView titulo;
    EditText pin;
    Button aceptar;
    Button canselar;
    Usuarios usuario;
    DbConecction dbConecction;
    int pinValidar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pin);
        getSupportActionBar().hide();

        pin = findViewById(R.id.pin);
        aceptar = findViewById(R.id.aceptar);
        canselar = findViewById(R.id.canselar);
        usuario = new Usuarios();
        dbConecction = new DbConecction(this);
        titulo = findViewById(R.id.titulo);

        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);

        menu.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);

        titulo.setText("Ingrese su pin");

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(pin.getText().toString().length() == 4){

                    pinValidar = Integer.parseInt(pin.getText().toString());
                    pin.setText("");

                    titulo.setText("Ingrese nuevamente su pin");

                    aceptar.setOnClickListener(new View.OnClickListener() {
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

                                        Toast.makeText(VistaPin.this, "Registro guardado", Toast.LENGTH_LONG).show();
                                        salir();

                                    } else {
                                        Toast.makeText(VistaPin.this, "Error al guardad el registro", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(VistaPin.this, "no coincidern los pines ", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(VistaPin.this, "Ingrese nuevamente su pin", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });
    }

    private  void salir(){
        Intent intent = new Intent(this, AdminVistaPrincipal.class);
        intent.putExtra("respuesta", "Salir de Pago con tarjeta");
        startActivity(intent);
    }

}