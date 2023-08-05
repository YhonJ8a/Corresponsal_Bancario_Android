package com.newpos.corresponsal.Entidades;

import java.io.Serializable;

public class Transacciones implements Serializable {

    private int codigoTransaccion;
    private String idCliente;
    private int idCorresponsal;
    private String fechaTransaccion;
    private String idDepocito;
    private String tipo;
    private String monto;


    public int getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(int codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public String getIdDepocito() {
        return idDepocito;
    }

    public void setIdDepocito(String idDepocito) {
        this.idDepocito = idDepocito;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCorresponsal() {
        return idCorresponsal;
    }

    public void setIdCorresponsal(int idCorresponsal) {
        this.idCorresponsal = idCorresponsal;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
