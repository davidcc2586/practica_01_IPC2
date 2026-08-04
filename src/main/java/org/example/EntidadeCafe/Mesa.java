package org.example.EntidadeCafe;

import org.example.Herramientas.ControlImagen;

import javax.swing.*;

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
        int largo = 100;
        int alto = 100;
        ImageIcon representacionEstado = null;
        switch (estado){
            case 1:
                representacionEstado = controlImagen.devolverImagen("/Imagenes/OcupadaConMesero.png",alto,largo);
                break;
            case 2:
                representacionEstado = controlImagen.devolverImagen("/Imagenes/OcupadaConMesero.png",alto,largo);
                break;
            case 3:
                representacionEstado = controlImagen.devolverImagen("/Imagenes/OcupadaConMesero.png",alto,largo);
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
        capacidad = capacidad;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
}
