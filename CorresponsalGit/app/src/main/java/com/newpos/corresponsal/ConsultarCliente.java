package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class ConsultarCliente extends AppCompatActivity {


    ImageView tarjetaImagen;
    View tajeta;
    TextView codigo;
    TextView fecha;
    TextView nombre;
    TextView saldo;
    TextView cvv;
    String anterior;

    ImageButton menu;
    ImageButton atras;
    EditText numreroCC;
    Button confirmar;
    Button canselar;
    DbConecction dbConecction;
    Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);
        getSupportActionBar().hide();

        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        numreroCC = findViewById(R.id.ccConsulta);
        confirmar = findViewById(R.id.confirmarConsulCliente);
        canselar = findViewById(R.id.canselarNewConsulCliente);
        dbConecction = new DbConecction(this);
        usuario = new Usuarios();

        tajeta = findViewById(R.id.tarjeta);
        tarjetaImagen = findViewById(R.id.tarjetaImg);
        codigo = findViewById(R.id.codigo);
        fecha = findViewById(R.id.fecha);
        cvv = findViewById(R.id.cvv);
        nombre = findViewById(R.id.nombre);
        saldo = findViewById(R.id.saldoVal);


        menu.setVisibility(View.INVISIBLE);
        tajeta.setVisibility(View.INVISIBLE);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });

        Bundle extras = getIntent().getExtras();
        anterior =extras.getString("anterior");


        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numreroCC.getText().length()> 0){
                    usuario = dbConecction.infoUsuarioCC(numreroCC.getText().toString());
                    if(usuario == null){
                        Toast.makeText(ConsultarCliente.this, "La cedula no esta registrada", Toast.LENGTH_LONG).show();
                    }else{

                        if (anterior.equals("opciones")){

                            Glide.with(ConsultarCliente.this).load(Constantes.IMG_TARJETA).into(tarjetaImagen);
                            codigo.setText(usuario.getNumTarjeta());
                            nombre.setText(usuario.getNombre());
                            fecha.setText(usuario.getFecha());
                            cvv.setText(usuario.getCvv());
                            saldo.setText(String.valueOf(usuario.getSaldo()));

                            numreroCC.setVisibility(View.INVISIBLE);
                            tajeta.setVisibility(View.VISIBLE);
                            canselar.setVisibility(View.GONE);

                            confirmar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    salir();
                                }
                            });

                        }else{

                            actualizarUsuario(usuario);

                        }
                    }
                }else{
                    Toast.makeText(ConsultarCliente.this, "Primero ingrese una cedula", Toast.LENGTH_LONG).show();
                }
            }
        });

        canselar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaSalir();
            }
        });

    }

    private  void salir(){
        Intent intent = new Intent(this, AdminVistaPrincipal.class);
        startActivity(intent);
    }

    public void vistaSalir(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", "CONSULTA CLIENTE CANSELADA!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);
        startActivity(intent);
    }

    private  void actualizarUsuario(Usuarios usuario){
        Intent intent = new Intent(this, ActualizarCliente.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}