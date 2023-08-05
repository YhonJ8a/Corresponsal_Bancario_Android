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
import com.newpos.corresponsal.variables.Constantes;

public class CorrRetiros extends AppCompatActivity {

    View notificacion;
    Button si;
    Button no;

    TextView nomCorresponsal ;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal ;
    ImageButton atras;
    ImageButton menu;

    EditText cedula;
    EditText monto;

    Button confirmar;
    Button canselar;

    DbConecction dbConecction;
    Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corr_retiros);
        getSupportActionBar().hide();

        notificacion = findViewById(R.id.notifi);
        si = findViewById(R.id.si);
        no = findViewById(R.id.no);

        atras = findViewById(R.id.CorrAtras);
        menu = findViewById(R.id.CorrOpciones);

        cedula = findViewById(R.id.ccRetiros);
        monto = findViewById(R.id.montoRetiro);

        confirmar = findViewById(R.id.Retirosconfirmar);
        canselar = findViewById(R.id.RetirosCanselar);

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        nomCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);

        dbConecction = new DbConecction(this);
        usuario = new Usuarios();

        notificacion.setVisibility(View.INVISIBLE);
        menu.setVisibility(View.INVISIBLE);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cedula.getText().toString().isEmpty() || monto.getText().toString().equals("")){
                    Toast.makeText(CorrRetiros.this, "No deben aven aver campos vacios!", Toast.LENGTH_LONG).show();
                }else{

                    usuario = dbConecction.infoUsuarioCC(cedula.getText().toString());
                    if (usuario != null){
                        if (usuario.getSaldo() >= (Integer.parseInt(monto.getText().toString()))+2000){
                            notificacion.setVisibility(View.VISIBLE);
                            si.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int valor = Integer.parseInt(monto.getText().toString()) + 2000 ;
                                    irAConfirmar(usuario, String.valueOf(valor));
                                }
                            });
                            no.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    salir();
                                }
                            });
                        }else{
                            Toast.makeText(CorrRetiros.this, "NO cuenta con el saldo suficiente para realizal el retiro!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(CorrRetiros.this, "La cedula no esta registrada!", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }

    public void  irAConfirmar(Usuarios usuario, String valor){
        Intent intent = new Intent(this,ConfirmacionRetiro.class);
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

    private  void atras(){
        Intent intent = new Intent(this, CorresponsalVistaPrincipal.class);
        startActivity(intent);
    }
}