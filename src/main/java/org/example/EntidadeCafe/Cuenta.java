package org.example.EntidadeCafe;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cuenta extends EntidadCafe{

    protected int idCuenta;
    protected int idMesero;
    protected String nombreMesero;
    protected int idmesa;
    protected LocalDate fecha;
    protected LocalTime horaEntrada;
    protected LocalTime horaSalida;
    protected String estado;
    protected Double totalPagar;

    public Cuenta(int idCuenta, int idMesero, String nombreMesero, int idmesa, LocalDate fecha, LocalTime horaEntrada, String estado) {
        super(idCuenta, null);
        this.idCuenta = idCuenta;
        this.idMesero = idMesero;
        this.nombreMesero = nombreMesero;
        this.idmesa = idmesa;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.estado = estado;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public int getIdMesero() {
        return idMesero;
    }

    public int getIdmesa() {
        return idmesa;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public String getNombreMesero() {
        return nombreMesero;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }
}
