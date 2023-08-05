package com.newpos.corresponsal.adaptadores;

import android.os.Build;
import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newpos.corresponsal.Entidades.Transacciones;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListadoTransaccionesAdater extends RecyclerView.Adapter<ListadoTransaccionesAdater.Transaciones> {


    ArrayList<Transacciones> listaTransacciones;
    ArrayList<Transacciones> listaOriginal;
    String textsali;

    public ListadoTransaccionesAdater(ArrayList<Transacciones> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
        listaOriginal = new ArrayList<>();
        listaOriginal = listaTransacciones;
    }

    @NonNull
    @Override
    public ListadoTransaccionesAdater.Transaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plantilla_lista_transaccional, null, false);
        return new Transaciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoTransaccionesAdater.Transaciones holder, int position) {

        textsali = "FECHA: "+ listaTransacciones.get(position).getFechaTransaccion()+"\n" +
                "TIPO DE TRANSACCION: "+ listaTransacciones.get(position).getTipo()+"\n" +
                "MONTO: "+ listaTransacciones.get(position).getMonto()+"\n" +
                "N° CC ó CUENTA: "+ listaTransacciones.get(position).getIdCliente();
        holder.textSalida.setText(textsali);
        holder.idTransaccion.setText(String.valueOf(listaTransacciones.get(position).getCodigoTransaccion()));
    }



    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            listaTransacciones.clear();
            listaTransacciones.addAll(listaOriginal);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Transacciones> collection = listaTransacciones.stream().filter(i -> i.getIdCliente().contains(txtBuscar)).collect(Collectors.toList());
                listaTransacciones.clear();
                listaTransacciones.addAll(collection);
            }else{
                for (Transacciones l: listaOriginal){
                    if (l.getIdCliente().contains(txtBuscar)){
                        listaTransacciones.add(l);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaTransacciones.size();
    }

    public class Transaciones extends RecyclerView.ViewHolder {

        TextView textSalida;
        TextView idTransaccion;

        public Transaciones(@NonNull View itemView) {
            super(itemView);

            textSalida = itemView.findViewById(R.id.salidaTextListTransacciones);
            idTransaccion = itemView.findViewById(R.id.idTransaccion);

        }
    }
}
