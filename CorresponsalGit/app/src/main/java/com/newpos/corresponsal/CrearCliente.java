package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CrearCliente extends AppCompatActivity {

    ImageButton atras;
    ImageButton menu;
    EditText nombre;
    EditText cedula;
    EditText saldo;
    Button confirmar;
    Button canselar;
    DbConecction dbConecction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);
        getSupportActionBar().hide();

        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        nombre = findViewById(R.id.nombreNewCliente);
        cedula = findViewById(R.id.ccNewCliente);
        saldo = findViewById(R.id.saldoNewCliente);
        confirmar = findViewById(R.id.confirmarNewCliente);
        canselar = findViewById(R.id.canselaNewCliente);
        dbConecction = new DbConecction(this);

        menu.setVisibility(View.INVISIBLE);

        menu.setVisibility(View.INVISIBLE);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(nombre.getText().length() == 0 || cedula.getText().toString().equals("") || saldo.getText().toString().equals(""))) {

                    Usuarios usuario = new Usuarios();
                    usuario.setId_usuario(cedula.getText().toString());
                    usuario.setNombre(nombre.getText().toString());
                    usuario.setSaldo(Integer.parseInt(saldo.getText().toString()));
                    usuario.setFecha(fecha());

                    Context context = view.getContext();
                    Intent intent = new Intent(context, VistaPin.class);
                    intent.putExtra("usuario", usuario );
                    context.startActivity(intent);

                }else{
                    Toast.makeText(CrearCliente.this, "Se deben llenar todos los campos.", Toast.LENGTH_LONG).show();
                }

            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, VistaCanselar.class);
                intent.putExtra("respuesta", "REGISTRO CLIENTE CANSELADO" );
                intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
                context.startActivity(intent);
            }
        });
    }

    private  void salir(){
        Intent intent = new Intent(this, AdminVistaPrincipal.class);
        startActivity(intent);
    }

    public String fecha(){
        String año = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        int newaño = Integer.parseInt(año) + 5;
        String fecha = newaño+"/"+ new SimpleDateFormat("MM/dd").format(Calendar.getInstance().getTime());
        return fecha;
    }

}