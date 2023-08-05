package com.newpos.corresponsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newpos.corresponsal.Entidades.OperacionesMenu;
import com.newpos.corresponsal.Entidades.SharedPreference;
import com.newpos.corresponsal.adaptadores.ListaOpcionesAdapter;
import com.newpos.corresponsal.variables.Constantes;

import java.util.ArrayList;

public class AdminVistaPrincipal extends AppCompatActivity {

    ImageButton menu;
    ImageButton atras;
    TextView nomCliente;
    SharedPreference sp;
    RecyclerView opciones;
    ListaOpcionesAdapter lista;
    View elMenu;
    Button ActCorresponsal;
    Button ActCliente;
    Button salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vista_principal);
        getSupportActionBar().hide();


        menu = findViewById(R.id.AdminOpciones);
        atras = findViewById(R.id.AdminAtras);
        atras.setVisibility(View.INVISIBLE);
        sp = new SharedPreference(this);
        opciones = findViewById(R.id.RecOpciones);
        elMenu = findViewById(R.id.menu);
        elMenu.setVisibility(View.INVISIBLE);
        ActCorresponsal = findViewById(R.id.ActCorres);
        ActCliente = findViewById(R.id.ActClientes);
        salir = findViewById(R.id.salir);

        nomCliente = findViewById(R.id.nombreAdmin);
        nomCliente.setText(sp.getUsuarioActivo());

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elMenu.setVisibility(View.VISIBLE);

                ActCorresponsal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActualizarCorr();
                    }
                });

                ActCliente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActualizarCliente();
                    }
                });

                salir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salir();
                    }
                });
            }
        });

        opciones();

    }

    // Opciones Array
    public void opciones(){

        ArrayList<OperacionesMenu> listaOp = new ArrayList<>();

        listaOp.add(new OperacionesMenu(Constantes.AGREGAR_USUARIO, Constantes.VISTA_AU, "Crear Cliente"));
        listaOp.add(new OperacionesMenu(Constantes.AGREGAR_CORRESPONSAL, Constantes.VISTA_AC, "Registrar Corresponsal"));
        listaOp.add(new OperacionesMenu(Constantes.CONSULTAR_CLIENTE, Constantes.VISTA_CCli, "Consultar Clinete"));
        listaOp.add(new OperacionesMenu(Constantes.CONSULTAR_CORRESPÓNSAL, Constantes.VISTA_Cor, "Consultar Corresponsal"));
        listaOp.add(new OperacionesMenu(Constantes.LISTA_CLIENTES, Constantes.VISTA_LCli, "Listado Clientes"));
        listaOp.add(new OperacionesMenu(Constantes.LISTA_CORRESPONSAL, Constantes.VISTA_LCor, "Listados Corresponsales"));

        lista = new ListaOpcionesAdapter(listaOp, AdminVistaPrincipal.this, "rojo");
        opciones.setAdapter(lista);
        opciones.setLayoutManager(new GridLayoutManager(AdminVistaPrincipal.this, 2));

    }


    private  void salir(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    private  void ActualizarCorr(){
        Intent intent = new Intent(this, ConsultarCorresponsal.class);
        intent.putExtra("anterior", "menu");
        startActivity(intent);
    }

    private  void ActualizarCliente(){
        Intent intent = new Intent(this, ConsultarCliente.class);
        intent.putExtra("anterior", "menu");
        startActivity(intent);
    }

}