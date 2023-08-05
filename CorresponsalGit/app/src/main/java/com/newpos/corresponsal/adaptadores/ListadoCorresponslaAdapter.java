package com.newpos.corresponsal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newpos.corresponsal.ConfirmarCorresponsal;
import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListadoCorresponslaAdapter extends RecyclerView.Adapter<ListadoCorresponslaAdapter.Corresponsale> {

    ArrayList<Corresponsales> listaCorresponsal;
    ArrayList<Corresponsales> listaOriginalCorr;

    public ListadoCorresponslaAdapter(ArrayList<Corresponsales> listaCorresponsales) {
        this.listaCorresponsal = listaCorresponsales;
        listaOriginalCorr = new ArrayList<>();
        listaOriginalCorr.addAll(listaCorresponsales);
    }

    @NonNull
    @Override
    public ListadoCorresponslaAdapter.Corresponsale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plantilla_lista_libros, null, false);
        return new Corresponsale(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Corresponsale holder, int position) {
        holder.nombre.setText(listaCorresponsal.get(position).getNombre());
        holder.NumCuenta.setText(listaCorresponsal.get(position).getCuenta());
        holder.cedula.setText(String.valueOf(listaCorresponsal.get(position).getNit()));
        holder.saldo.setText(String.valueOf(listaCorresponsal.get(position).getSaldo()));
        holder.estado.setText(listaCorresponsal.get(position).getEstado());
    }

    @Override
    public int getItemCount() {
        return listaCorresponsal.size();
    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            listaCorresponsal.clear();
            listaCorresponsal.addAll(listaOriginalCorr);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Corresponsales> collection = listaCorresponsal.stream().filter(i -> i.getNit().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaCorresponsal.clear();
                listaCorresponsal.addAll(collection);
            }else{
                for (Corresponsales l: listaOriginalCorr){
                    if (l.getNit().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaCorresponsal.add(l);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class Corresponsale extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView NumCuenta;
        TextView cedula;
        TextView saldo;
        TextView estado;

        public Corresponsale(@NonNull View itemView) {
            super(itemView);

            nombre  = itemView.findViewById(R.id.LCnombre);
            NumCuenta = itemView.findViewById(R.id.LCnumCuenta);
            cedula = itemView.findViewById(R.id.LCcedula);
            saldo = itemView.findViewById(R.id.LCsaldo);
            estado = itemView.findViewById(R.id.estado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ConfirmarCorresponsal.class);
                    intent.putExtra("corresponsales", listaCorresponsal.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });


        }
    }
}
