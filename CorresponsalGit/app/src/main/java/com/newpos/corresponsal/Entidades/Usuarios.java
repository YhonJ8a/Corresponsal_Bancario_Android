package com.newpos.corresponsal.Entidades;

import java.io.Serializable;
import java.util.Date;

public class Usuarios implements Serializable {

    private String id_usuario;
    private String nombre;
    private String fecha;
    private int saldo;
    private int pin;
    private String numTarjeta ;
    private String cvv;

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String  id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }
}
