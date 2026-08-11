package org.example.EntidadeCafe;

import java.time.LocalDate;

public class Nomina extends EntidadCafe{

    protected int idNomina;
    protected int idEmpleado;
    protected String nombreEmpleado;
    protected LocalDate fechaPago;
    protected String tipo;
    protected Double montoPagar;
    protected String estado;

    public Nomina(int idNomina, int idEmpleado, String nombreEmpleado, LocalDate fechaPago, String tipo, Double montoPagar, String estado) {
        super(idNomina, null);
        this.idNomina = idNomina;
        this.nombreEmpleado = nombreEmpleado;
        this.idEmpleado = idEmpleado;
        this.fechaPago = fechaPago;
        this.tipo = tipo;
        this.montoPagar = montoPagar;
        this.estado = estado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getMontoPagar() {
        return montoPagar;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdNomina() {
        return idNomina;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
