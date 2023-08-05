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
import com.newpos.corresponsal.Entidades.Usuarios;

public class ActualizarCliente extends AppCompatActivity {

    EditText nombre;
    EditText cedula;
    EditText pin;
    EditText pin2;
    ImageButton menu;
    ImageButton atras;
    Button confirmar;
    Button canselar;
    Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cliente);
        getSupportActionBar().hide();

        nombre = findViewById(R.id.ActNombre);
        cedula = findViewById(R.id.ActuCC);
        pin = findViewById(R.id.ActuPin);
        pin2 = findViewById(R.id.PinNuevo);
        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        confirmar = findViewById(R.id.ActConfirmar);
        canselar = findViewById(R.id.ActuCanselar);
        usuario = new Usuarios();

        Bundle extras = getIntent().getExtras();
        usuario = (Usuarios) extras.getSerializable("usuario");

        menu.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);

        nombre.setText(usuario.getNombre());
        cedula.setText(usuario.getId_usuario());

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(nombre.getText().toString().equals("") || cedula.getText().toString().equals("") || pin.getText().toString().equals("") || pin2.getText().toString().equals("") )){
                    if (pin.getText().toString().equals(pin2.getText().toString())){
                        usuario.setNombre(nombre.getText().toString());
                        usuario.setId_usuario(cedula.getText().toString());
                        usuario.setPin(Integer.parseInt(pin.getText().toString()));

                        siguiente(usuario);
                    }else{
                        Toast.makeText(ActualizarCliente.this, "Los nuevos pines no coinsiden", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActualizarCliente.this, "Se deben llenar todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void  siguiente(Usuarios usuario){
        Intent intent = new Intent(this,ConfirmarCorresponsal.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("anterior", "usuario");
        startActivity(intent);
    }

}