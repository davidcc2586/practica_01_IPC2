package org.example.EntidadeCafe;

import java.time.LocalDate;

public class Empleado extends EntidadCafe {

    protected int DPI;
    protected String nombre;
    protected String rolEmpleado;
    protected String jornadaLaboral;
    protected double salario;
    protected LocalDate fechaContratacion;

    public Empleado(int DPI, String nombreCompleto, String rolEmpleado, String jornadaLaboral, double salario, LocalDate fechaContratacion) {
        super(DPI);
        this.DPI = DPI;
        this.nombre = nombreCompleto;
        this.rolEmpleado = rolEmpleado;
        this.jornadaLaboral = jornadaLaboral;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDPI() {
        return DPI;
    }

    public double getSalario() {
        return salario;
    }

    public String getRolEmpleado() {
        return rolEmpleado;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public String getJornadaLaboral() {
        return jornadaLaboral;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDPI(int DPI) {
        this.DPI = DPI;
    }

    public void setJornadaLaboral(String jornadaLaboral) {
        this.jornadaLaboral = jornadaLaboral;
    }

    public void setRolEmpleado(String rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

}
