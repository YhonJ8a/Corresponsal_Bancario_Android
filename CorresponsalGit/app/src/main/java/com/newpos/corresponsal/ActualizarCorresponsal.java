package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.variables.Constantes;

public class ActualizarCorresponsal extends AppCompatActivity {

    EditText nombre;
    EditText nit;
    EditText correo;
    EditText password;
    ImageButton menu;
    ImageButton atras;
    Button confirmar;
    Button canselar;
    Corresponsales corresponsal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_corresponsal);
        getSupportActionBar().hide();

        nombre = findViewById(R.id.ACTNOmbre);
        nit = findViewById(R.id.ACTNit);
        correo = findViewById(R.id.ACTCorreo);
        password = findViewById(R.id.ACTPassword);
        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        confirmar = findViewById(R.id.confirmarAct);
        canselar = findViewById(R.id.canselaAct);
        corresponsal = new Corresponsales();

        menu.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        corresponsal = (Corresponsales) extras.getSerializable("corresponsales");

        nombre.setText(corresponsal.getNombre());
        nit.setText(corresponsal.getNit());
        correo.setText(corresponsal.getCorreo());
        password.setText(corresponsal.getPassword());

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(nombre.getText().toString().equals("") && nit.getText().toString().equals("") && correo.getText().toString().equals("") && password.getText().toString().equals(""))){

                    corresponsal.setNombre(nombre.getText().toString());
                    corresponsal.setNit(nit.getText().toString());
                    corresponsal.setCorreo(correo.getText().toString());
                    corresponsal.setPassword(password.getText().toString());
                    siguiente(corresponsal);
                }else{
                    Toast.makeText(ActualizarCorresponsal.this, "No deje campos vacios", Toast.LENGTH_LONG).show();
                }

            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalirNegativo();
            }
        });

    }

    public void SalirNegativo(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", " CORRESPONSAL HABILITADO!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);

        startActivity(intent);
    }

    public void  siguiente(Corresponsales corresponsal){
        Intent intent = new Intent(this,ConfirmarCorresponsal.class);
        intent.putExtra("corresponsales", corresponsal);
        intent.putExtra("anterior", "corresponsal");
        startActivity(intent);
    }

}