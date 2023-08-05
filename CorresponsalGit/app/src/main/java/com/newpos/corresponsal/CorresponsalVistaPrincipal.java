package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.OperacionesMenu;
import com.newpos.corresponsal.Entidades.SharedPreference;
import com.newpos.corresponsal.adaptadores.ListaOpcionesAdapter;
import com.newpos.corresponsal.db.DbConecction;
import com.newpos.corresponsal.variables.Constantes;

import java.util.ArrayList;

public class CorresponsalVistaPrincipal extends AppCompatActivity {

    View menuInclude;
    Button salirMenu;

    ImageButton menu;
    ImageButton atras;
    TextView nomCorresponsal;
    TextView saldoCorresponsal;
    TextView cuentaCorresponsal;
    SharedPreference sp;
    DbConecction dbConecction;
    Corresponsales corresponsal;
    RecyclerView opciones;
    ListaOpcionesAdapter lista;

    Button ActCorresponsal;
    Button Crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_vista_principal);
        getSupportActionBar().hide();

        menuInclude = findViewById(R.id.menuCorr);
        ActCorresponsal =findViewById(R.id.ActDatos);
        Crear =findViewById(R.id.CreaCliente);
        salirMenu = findViewById(R.id.salirMenu);

        menu = findViewById(R.id.CorrOpciones);
        atras = findViewById(R.id.CorrAtras);
        atras.setVisibility(View.INVISIBLE);
        opciones = findViewById(R.id.ListaOpcionesCorresponsal);

        dbConecction = new DbConecction(this);

        nomCorresponsal = findViewById(R.id.plantillaNomCorresponsal);
        saldoCorresponsal = findViewById(R.id.plantillaSaldoCorresponsal);
        cuentaCorresponsal = findViewById(R.id.plantillaNCuenta);

        menuInclude.setVisibility(View.INVISIBLE);

        opciones();

        sp = new SharedPreference(this);
        corresponsal = dbConecction.infoCorresponsalCorreo(sp.getUsuarioActivo());

        nomCorresponsal.setText(corresponsal.getNombre());
        saldoCorresponsal.setText(String.valueOf(corresponsal.getSaldo()));
        cuentaCorresponsal.setText(corresponsal.getCuenta());

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuInclude.setVisibility(View.VISIBLE);

                ActCorresponsal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActualizarCorr( corresponsal);
                    }
                });

                Crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActualizarCliente();
                    }
                });

                salirMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salir();
                    }
                });
            }
        });
    }

    // Opciones Array
    public void opciones(){

        ArrayList<OperacionesMenu> listaOp = new ArrayList<>();

        listaOp.add(new OperacionesMenu(Constantes.PAGO_TARJETA, Constantes.VISTA_PCT, "Pago con Tarjeta"));
        listaOp.add(new OperacionesMenu(Constantes.RETIROS, Constantes.VISTA_R, "Retiros"));
        listaOp.add(new OperacionesMenu(Constantes.DEPOCITOS, Constantes.VISTA_D, "Depocitos"));
        listaOp.add(new OperacionesMenu(Constantes.TRANSFERENCIAS, Constantes.VISTA_T, "Transferencias"));
        listaOp.add(new OperacionesMenu(Constantes.HISTORIAL_TRANSACCIONES, Constantes.VISTA_HT, "Histaorial Transaccional"));
        listaOp.add(new OperacionesMenu(Constantes.CONSULTA_SALDO, Constantes.VISTA_CS, "Consulta de Saldo"));

        lista = new ListaOpcionesAdapter(listaOp, CorresponsalVistaPrincipal.this, "azul");
        opciones.setAdapter(lista);
        opciones.setLayoutManager(new GridLayoutManager(CorresponsalVistaPrincipal.this, 2));

    }


    private  void salir(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    private  void ActualizarCorr(Corresponsales corresponsal){
        Intent intent = new Intent(this, CorrNuevoCliente.class);
        intent.putExtra("Corresponsal", corresponsal);
        startActivity(intent);
    }

    private  void ActualizarCliente(){
        Intent intent = new Intent(this, CorrNuevoCliente.class);
        intent.putExtra("anterior", "menu");
        startActivity(intent);
    }
}