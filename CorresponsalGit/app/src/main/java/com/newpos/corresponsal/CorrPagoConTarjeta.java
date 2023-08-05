package com.newpos.corresponsal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.db.DbConecction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.time.Month;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CorrPagoConTarjeta extends AppCompatActivity {

    TextView nombreCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;

    Spinner opcionesSp;
    EditText numeroT;
    EditText cvv;
    EditText mm;
    EditText dd;
    EditText nombreCliente;
    EditText valor;

    ImageButton menu;
    ImageButton atras;

    Button Confirmar;
    Button Canselar;

    String numero;
    String cvvTxt;
    String mmTxt;
    String ddTxt;
    String nombre;
    String valorTxt;

    DbConecction dbConecction ;
    Usuarios usuario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corr_pago_con_tarjeta);
        getSupportActionBar().hide();

        nombreCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        numeroT = findViewById(R.id.CorPagoNumt);
        cvv = findViewById(R.id.CVV);
        mm = findViewById(R.id.mm);
        dd = findViewById(R.id.dd);
        nombreCliente = findViewById(R.id.CorPagoNombre);
        valor = findViewById(R.id.CorPagoValor);

        Confirmar = findViewById(R.id.CorPagoconfirmar);
        Canselar = findViewById(R.id.CorPagoCanselar);

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);

        nombreCorresponsal.setVisibility(View.INVISIBLE);
        saldoCorresponsal.setVisibility(View.INVISIBLE);
        cuentaCorresponsal.setVisibility(View.INVISIBLE);


        dbConecction = new DbConecction(this);
        usuario = new Usuarios();


        opcionesSp = findViewById(R.id.Spiner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opcionesSp.setAdapter(adapter);


        menu.setVisibility(View.INVISIBLE);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });

        Confirmar.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                numero = numeroT.getText().toString();
                cvvTxt = cvv.getText().toString();
                mmTxt = mm.getText().toString();
                ddTxt = dd.getText().toString();
                nombre = nombreCliente.getText().toString().toLowerCase(Locale.ROOT);
                valorTxt = valor.getText().toString();

                int cuotas ;

                String seleccion = opcionesSp.getSelectedItem().toString();
                switch(seleccion){
                    case "1":
                        cuotas = 1;
                        break;
                    case "2":
                        cuotas = 2;
                        break;
                    case "3":
                        cuotas = 3;
                        break;
                    case "4":
                        cuotas = 4;
                        break;
                    case "5":
                        cuotas = 5;
                        break;
                    case "6":
                        cuotas = 6;
                        break;
                    case "7":
                        cuotas = 7;
                        break;
                    case "8":
                        cuotas = 8;
                        break;
                    case "9":
                        cuotas = 9;
                        break;
                    case "10":
                        cuotas = 10;
                        break;
                    case "11":
                        cuotas = 11;
                        break;
                    case "12":
                        cuotas = 12;
                        break;
                    default:
                        cuotas = 1;
                }

                if (!(numero.equals("") ||cvvTxt.equals("") || mmTxt.equals("") || ddTxt.equals("") || nombre.equals("") || valorTxt.equals(""))){
                    usuario = dbConecction.infoCorresponsalNumTar(numero);

                    if (usuario != null){

                        try {
                            if(usuario.getCvv().equals(cvvTxt) && usuario.getNombre().equals(nombre) && datosFecha(usuario.getFecha(), ddTxt, mmTxt)) {
                                int valor = Integer.parseInt(valorTxt);
                                if (valor >= 10000 && valor <= 1000000){
                                    if (usuario.getSaldo()>= valor){
                                        irAConfirmar(usuario, cuotas, valorTxt);
                                    }else{
                                        Toast.makeText(CorrPagoConTarjeta.this, "no cuenta con el suficiente saldo para realizar el pago!", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(CorrPagoConTarjeta.this, "MINIMO: 10.000 , MAXIMO: 1'000.000 ", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(CorrPagoConTarjeta.this, "Revise sus datos!", Toast.LENGTH_LONG).show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(CorrPagoConTarjeta.this, "Usuario no registrado", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(CorrPagoConTarjeta.this, "No deben aver campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean datosFecha(String fe, @NonNull String dd, String mm) throws ParseException {

        Date fecha = new SimpleDateFormat("yyyy/MM/dd").parse(fe);
        String dia = new SimpleDateFormat("dd").format(fecha);
        String mes = new SimpleDateFormat("MM").format(fecha);


        System.out.println("fecha: "+ fecha+ " dia : "+dia +" mes "+mes);
        if (dd.equals(dia) && mm.equals(mes)){
            return true;
        }else return false;
    }


    public void  irAConfirmar(Usuarios usuario, int cuota, String valor){
        Intent intent = new Intent(this,ConfirmacionCorresponsal.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("cuotas", cuota);
        intent.putExtra("valor", valor);

        startActivity(intent);
    }

    public void  salir(){
        Intent intent = new Intent(this,CorresponsalVistaPrincipal.class);
        startActivity(intent);
    }

}