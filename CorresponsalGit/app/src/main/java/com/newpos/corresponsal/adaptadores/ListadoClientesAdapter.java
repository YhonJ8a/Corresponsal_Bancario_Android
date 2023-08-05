package com.newpos.corresponsal.adaptadores;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newpos.corresponsal.Entidades.Corresponsales;
import com.newpos.corresponsal.Entidades.Usuarios;
import com.newpos.corresponsal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListadoClientesAdapter extends RecyclerView.Adapter<ListadoClientesAdapter.Clientes> {

    ArrayList<Usuarios> listaUsuarios;
    ArrayList<Usuarios> listaOriginal;

    public ListadoClientesAdapter(ArrayList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaUsuarios);
    }



    @NonNull
    @Override
    public Clientes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plantilla_lista_libros, null, false);
        return new Clientes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Clientes holder, int position) {
        holder.nombre.setText(listaUsuarios.get(position).getNombre());
        holder.NumCuenta.setText(listaUsuarios.get(position).getNumTarjeta());
        holder.cedula.setText(String.valueOf(listaUsuarios.get(position).getId_usuario()));
        holder.saldo.setText(String.valueOf(listaUsuarios.get(position).getSaldo()));
    }



    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            listaUsuarios.clear();
            listaUsuarios.addAll(listaOriginal);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Usuarios> collection = listaUsuarios.stream().filter(i -> i.getId_usuario().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaUsuarios.clear();
                listaUsuarios.addAll(collection);
            }else{
                for (Usuarios l: listaOriginal){
                    if(l.getId_usuario().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaUsuarios.add(l);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class Clientes extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView NumCuenta;
        TextView cedula;
        TextView saldo;
        TextView estado;

        public Clientes(@NonNull View itemView) {
            super(itemView);

            nombre  = itemView.findViewById(R.id.LCnombre);
            NumCuenta = itemView.findViewById(R.id.LCnumCuenta);
            cedula = itemView.findViewById(R.id.LCcedula);
            saldo = itemView.findViewById(R.id.LCsaldo);
            estado = itemView.findViewById(R.id.estado);
        }
    }
}
