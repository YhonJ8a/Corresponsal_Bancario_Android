package com.newpos.corresponsal.Entidades;

import android.graphics.drawable.Icon;

public class OperacionesMenu {



    private String imagen;
    private String opcion;
    private  Object classe;

    public OperacionesMenu(String imagen, Object classe, String opcion) {
        this.imagen = imagen;
        this.classe = classe;
        this.opcion = opcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Object getClasse() {
        return classe;
    }

    public void setClasse(Object classe) {
        this.classe = classe;
    }
}
