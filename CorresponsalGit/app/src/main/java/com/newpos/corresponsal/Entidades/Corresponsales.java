package com.newpos.corresponsal.Entidades;

import java.io.Serializable;

public class Corresponsales implements Serializable {
    private int cod_corresponsal;
    private String nombre;
    private String nit;
    private String cuenta;
    private String correo;
    private String password ;
    private int saldo;
    private  String estado;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getCod_corresponsal() {
        return cod_corresponsal;
    }

    public void setCod_corresponsal(int cod_corresponsal) {
        this.cod_corresponsal = cod_corresponsal;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String  nit) {
        this.nit = nit;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
