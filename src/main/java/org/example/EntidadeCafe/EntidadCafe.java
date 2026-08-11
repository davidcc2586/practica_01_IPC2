package org.example.EntidadeCafe;

public class EntidadCafe {

    protected int identificador;
    protected String nombre;

    public EntidadCafe(int identificador, String nombre){
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }
}
