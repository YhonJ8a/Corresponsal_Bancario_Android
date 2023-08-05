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
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

public class ConfirmarCorresponsal extends AppCompatActivity {

    String texto;
    ImageButton menu;
    ImageButton atras;
    EditText salidaDatos;
    Button habilitar;
    Button Desabilitar;
    Corresponsales corresponsal;
    Usuarios usuario;
    DbConecction dbConecction;
    String anterior;
    boolean respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_corresponsal);
        getSupportActionBar().hide();

        salidaDatos = findViewById(R.id.TextSalidaConfir);
        habilitar = findViewById(R.id.habilitar);
        Desabilitar = findViewById(R.id.deshabilitar);
        menu= findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        corresponsal = new Corresponsales();
        usuario = new Usuarios();
        dbConecction = new DbConecction(this);

        Desabilitar.setText("Canselar");
        habilitar.setText("Confirmar");
        menu.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        corresponsal = (Corresponsales) extras.getSerializable("corresponsales");
        anterior = extras.getString("anterior");
        usuario = (Usuarios) extras.getSerializable("usuario");


        if(corresponsal != null) {

            if (anterior == "corresponsal") {

                texto = "NOMBRE CORRESPONSAL : "+ corresponsal.getNombre()+"\n" +
                        "Número NIT: "+ corresponsal.getNit()+"\n" +
                        "Saldo del corresponsal: "+ corresponsal.getSaldo()+"\n" +
                        "Correo del corresponsal: "+ corresponsal.getCorreo();

                salidaDatos.setText(texto);

                habilitar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        respuesta = dbConecction.editarCorresponsal(corresponsal);
                        if (respuesta){
                            SalirPositivoActu();
                        }else {
                            Toast.makeText(ConfirmarCorresponsal.this, "Error al editar", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                Desabilitar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        vistaSalir();
                    }
                });

            }else if (anterior.equals("usuario")) {

                texto = "NOMBRE CLIENTE: "+ usuario.getNombre()+"\n" +
                        "Número de Cedula: "+ usuario.getId_usuario()+"\n" +
                        "Saldo del Clientes: "+ usuario.getSaldo();

                salidaDatos.setText(texto);

                habilitar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        respuesta = dbConecction.editarCorresponsal(corresponsal);
                        if (respuesta){
                            SalirPositivoActu();
                        }else {
                            Toast.makeText(ConfirmarCorresponsal.this, "Error al editar", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                Desabilitar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vistaSalir();
                    }
                });
            }else{

                texto = "NOMBRE CORRESPONSAL : "+ corresponsal.getNombre()+"\n" +
                        "Número NIT: "+ corresponsal.getNit()+"\n" +
                        "Saldo del corresponsal: "+ corresponsal.getSaldo()+"\n" +
                        "Correo del corresponsal: "+ corresponsal.getCorreo();

                salidaDatos.setText(texto);

                Desabilitar.setText("Deshabilitar");
                habilitar.setText("Habilitar");
                Desabilitar.setVisibility(View.INVISIBLE);
                habilitar.setVisibility(View.INVISIBLE);
                menu.setVisibility(View.INVISIBLE);

                if (corresponsal.getEstado().equals("HABILITADO")) {

                    habilitar.setVisibility(View.GONE);
                    Desabilitar.setVisibility(View.VISIBLE);
                    Desabilitar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean resp = dbConecction.HAbilitarDeshabilitar(corresponsal.getNit(),"DESHABILITADO");
                            if (resp){
                                SalirNegativo();
                            }else{
                                Toast.makeText(ConfirmarCorresponsal.this, "Error al deshabilitar", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{

                    Desabilitar.setVisibility(View.GONE);
                    habilitar.setVisibility(View.VISIBLE);
                    habilitar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean resp =dbConecction.HAbilitarDeshabilitar(corresponsal.getNit(),"HABILITADO");
                            if (resp){
                                SalirPositivo();
                            }else{
                                Toast.makeText(ConfirmarCorresponsal.this, "Error al habilitar", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        }

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vistaSalir();
            }
        });

    }

    public void vistaSalir(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", "CONSULTA CORRESPONSAL CANSELADA!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);

        startActivity(intent);
    }

    public void SalirPositivo(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", " CORRESPONSAL HABILITADO!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_POSITIVA);

        startActivity(intent);
    }

    public void SalirNegativo(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", " CORRESPONSAL DESHABILITADO!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_NEGATIVA);

        startActivity(intent);
    }

    public void SalirPositivoActu(){
        Intent intent = new Intent(this,VistaCanselar.class);
        intent.putExtra("respuesta", " CORRESPONSAL EDITADO CORRECTAMENTE!");
        intent.putExtra("imagen", Constantes.IMAGEN_CANSELAR_POSITIVA);

        startActivity(intent);
    }
}