package com.newpos.corresponsal;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.variables.Constantes;

public class ConfirmacionCorresponsal extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;

    TextView nombre;
    TextView valor;
    TextView numeroCuenta;
    TextView numeroCuotas;
    TextView tipoTarjeta;

    Button confirmar;
    Button canselar;

    int cuotas;
    String cobro;
    Usuarios usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_corresponsal);
        getSupportActionBar().hide();

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        nombre = findViewById(R.id.nombreCliente);
        valor = findViewById(R.id.valorCliente);
        numeroCuenta = findViewById(R.id.numeroTarjeta);
        numeroCuotas = findViewById(R.id.numeroCuotas);
        tipoTarjeta = findViewById(R.id.tipoTarjeta);

        confirmar = findViewById(R.id.confirmarConfirmacion);
        canselar = findViewById(R.id.CanselarConfirmacion);

        menu.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);


        Bundle extras = getIntent().getExtras();
        usuario = (Usuarios) extras.getSerializable("usuario");
        cuotas = extras.getInt("cuotas");
        cobro = extras.getString("valor");

        nombre.setText(usuario.getNombre());
        valor.setText(String.valueOf(cobro));
        numeroCuenta.setText(codific(usuario.getNumTarjeta()));
        numeroCuotas.setText("A "+cuotas+" cuotas");
        tipoTarjeta.setText(tipoTarjeta(usuario.getNumTarjeta()));

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAConfirmarPin(usuario, valor.getText().toString() );
            }
        });
        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });
    }

    public String codific(String numeroCuenta){
        return "*************"+numeroCuenta.substring(12);
    }

    public String tipoTarjeta(String numeroCuenta){
        String PrimerN = numeroCuenta.substring(0,1);
        switch(PrimerN){
            case "3":
                return "AMERICANEXPRES";
            case "4":
                return "VISA";
            case "5":
                return "MASTERCARD";
            case "6":
                return "UNIONPAY";
            default:
                return "error";
        }
    }

    public void  irAConfirmarPin(Usuarios usuario, String valor){
        Intent intent = new Intent(this,VistaConfirmaPin.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("valor", valor);

        startActivity(intent);
    }

    private  void salir(){
        Intent intent = new Intent(this, VistaCanselarCorr.class);
        intent.putExtra("respuesta", "Salir de Pago con tarjeta");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }
}