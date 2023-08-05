package com.newpos.corresponsal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newpos.corresponsal.CorresponsalVistaPrincipal;
import com.newpos.corresponsal.Entidades.OperacionesMenu;
import com.newpos.corresponsal.R;

import java.util.ArrayList;

public class ListaOpcionesAdapter extends RecyclerView.Adapter<ListaOpcionesAdapter.Opciones> {



    ArrayList<OperacionesMenu> listaOperaciones;
    Context context;
    String anterior;

    public ListaOpcionesAdapter(ArrayList<OperacionesMenu> listaOperaciones, Context context,String anterior) {
        this.listaOperaciones = listaOperaciones;
        this.context = context;
        this.anterior = anterior;
    }

    @NonNull
    @Override
    public ListaOpcionesAdapter.Opciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plantlla_opciones, null, false);
        return new Opciones(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListaOpcionesAdapter.Opciones holder, int position) {
        holder.opcion.setText(listaOperaciones.get(position).getOpcion());
        holder.nombreClass = listaOperaciones.get(position).getClasse();
        Glide.with(context).load(listaOperaciones.get(position).getImagen()).error(R.drawable.ic_baseline_android_24).into(holder.icono);
        if (anterior.equals("azul")){
            holder.colorOp.setBackgroundColor(Color.rgb(12,171,247 ));
        }else{
            holder.colorOp.setBackgroundColor(Color.rgb(255,100,100 ));
        }

    }

    @Override
    public int getItemCount() {
        return listaOperaciones.size();
    }

    public class Opciones extends RecyclerView.ViewHolder {

        TextView opcion;
        ImageView icono;
        Object nombreClass;
        LinearLayout colorOp;

        public Opciones(@NonNull View itemView) {
            super(itemView);
            opcion = itemView.findViewById(R.id.opcion);
            icono = itemView.findViewById(R.id.icono);
            colorOp = itemView.findViewById(R.id.backgOpcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, (Class<?>) nombreClass);
                    intent.putExtra("anterior", "opciones");
                    context.startActivity(intent);
                }
            });
        }
    }
}
