package org.example.EntidadeCafe;

import java.time.LocalDateTime;

public class Transaccion {

    protected int idReporte;
    protected String tipo;
    protected String motivo;
    protected double monto;
    protected LocalDateTime fecha;

    public Transaccion(int idReporte, String tipo, String motivo, double monto, LocalDateTime fecha) {
        this.idReporte = idReporte;
        this.tipo = tipo;
        this.motivo = motivo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}