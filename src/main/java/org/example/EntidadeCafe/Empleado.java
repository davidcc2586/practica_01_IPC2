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
    protected String estadoTrabajo;
    protected String direccionImagen;

    public Empleado(int idEmpleado, String DPI, String nombre,String apellido, String rolEmpleado, String jornadaLaboral, double salario, LocalDate fechaContratacion, String estado, String direccionImagen) {
        super(idEmpleado, nombre);
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

    public Empleado(int idEmpleado, String DPI, String nombre,String apellido, String rolEmpleado, String jornadaLaboral, double salario, LocalDate fechaContratacion, String estado, String estadoTrabajo, String direccionImagen) {
        super(idEmpleado, nombre);
        this.idEmpleado = idEmpleado;
        this.DPI = DPI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rolEmpleado = rolEmpleado;
        this.jornadaLaboral = jornadaLaboral;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
        this.estadoTrabajo = estadoTrabajo;
        this.direccionImagen = direccionImagen;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getDPI() {
        return DPI;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRolEmpleado() {
        return rolEmpleado;
    }

    public String getJornadaLaboral() {
        return jornadaLaboral;
    }

    public double getSalario() {
        return salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public String getEstado() {
        return estado;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
