package org.example.EntidadeCafe;

import org.example.Herramientas.ControlImagen;

import javax.swing.*;
import java.awt.*;

public class Mesa extends EntidadCafe {

    protected int numeroMesa;
    protected int capacidad;
    protected String estadoActual;
    protected ImageIcon imagenEstadoMesa;

    public Mesa(int numeroMesa, int capacidad){
        super(numeroMesa);
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        cambiarEstado(1);
    }

    public void cambiarEstado(int estado){ // 1 = libre, 2 = ocupado y probablemente 3 = ocupada y  con mesero

        ControlImagen controlImagen = new ControlImagen();
        int largo = 220;
        int alto = 220;
        ImageIcon representacionEstado = null;
        switch (estado){
            case 1:
                estadoActual = "Libre";
                representacionEstado = controlImagen.devolverImagen("/Imagenes/OcupadaConMesero.png",largo,alto);
                break;
            case 2:
                estadoActual = "Ocupada";
                representacionEstado = controlImagen.devolverImagen("/Imagenes/OcupadaConMesero.png",largo,alto);
                break;
            default:

        }
        imagenEstadoMesa = representacionEstado;
    }
    public int getCapacidad() {
        return capacidad;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public ImageIcon getImagenEstadoMesa() {
        return imagenEstadoMesa;
    }

    public void setImagenEstadoMesa(ImageIcon imagenEstadoMesa) {
        this.imagenEstadoMesa = imagenEstadoMesa;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
}
