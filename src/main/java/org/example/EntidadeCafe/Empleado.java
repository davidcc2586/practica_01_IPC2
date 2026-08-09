package org.example.EntidadeCafe;

import java.time.LocalDate;

public class Empleado extends EntidadCafe {

    protected int idEmpleado;
    protected String DPI;
    protected String nombre;
    protected String apellido;
    protected String rolEmpleado;
    protected String jornadaLaboral;
    protected double salario;
    protected LocalDate fechaContratacion;
    protected String estado;
    protected String direccionImagen;

    public Empleado(int idEmpleado, String DPI, String nombre,String apellido, String rolEmpleado, String jornadaLaboral, double salario, LocalDate fechaContratacion, String estado, String direccionImagen) {
        super(idEmpleado);
        this.idEmpleado = idEmpleado;
        this.DPI = DPI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rolEmpleado = rolEmpleado;
        this.jornadaLaboral = jornadaLaboral;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
        this.direccionImagen = direccionImagen;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDPI() {
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

    public void setEstado(String estado) {
        this.estado = estado;
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
