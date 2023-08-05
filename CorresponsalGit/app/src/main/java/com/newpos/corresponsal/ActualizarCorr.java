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
import com.newpos.corresponsal.variables.Constantes;

public class ActualizarCorr extends AppCompatActivity {

    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;


    EditText PassActual;
    EditText PassNueva;
    EditText PassCornfNueva;
    ImageButton menu;
    ImageButton atras;
    Button confirmar;
    Button canselar;
    Corresponsales corresponsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_corr);
        getSupportActionBar().hide();

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        PassActual = findViewById(R.id.PasswordActual);
        PassNueva = findViewById(R.id.PasswordNueva);
        PassCornfNueva = findViewById(R.id.ConfirmarPassword);
        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);
        confirmar = findViewById(R.id.confirmarActCorr);
        canselar = findViewById(R.id.canselaActCorr);
        corresponsal = new Corresponsales();

        menu.setVisibility(View.INVISIBLE);
        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        corresponsal = (Corresponsales) extras.getSerializable("Corresponsal");

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(PassActual.getText().toString().equals("") && PassNueva.getText().toString().equals("") && PassCornfNueva.getText().toString().equals("") )){

                    if(PassActual.getText().toString().equals(corresponsal.getPassword())){
                        if(PassNueva.getText().toString().equals(PassCornfNueva.getText().toString())){
                            corresponsal.setPassword(PassNueva.getText().toString());
                            siguiente(corresponsal);
                        }else{
                            Toast.makeText(ActualizarCorr.this, "Las coraseñas no coinciden ", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(ActualizarCorr.this, "La coraseña no es correcta", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActualizarCorr.this, "No deje campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalirNegativo();
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salir();
            }
        });
    }

    public void SalirNegativo(){
        Intent intent = new Intent(this,VistaCanselarCorr.class);
        intent.putExtra("respuesta", " CORRESPONSAL HABILITADO!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);

        startActivity(intent);
    }

    public void  siguiente(Corresponsales corresponsal){
        Intent intent = new Intent(this,CorrConfirmarCorresponsal.class);
        intent.putExtra("corresponsales", corresponsal);
        startActivity(intent);
    }

    public void Salir(){
        Intent intent = new Intent(this,CorresponsalVistaPrincipal.class);
        startActivity(intent);
    }

}