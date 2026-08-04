package org.example.Herramientas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ControlImagen {

    public ControlImagen(){

    }

    public ImageIcon devolverImagen(String direccionImagen, int alto, int largo){

        Image imagenEscalada;
        ImageIcon imagenFinalElemento;

        ImageIcon imagenOriginal = new ImageIcon(Objects.requireNonNull(getClass().getResource(direccionImagen)));
        Image imagen = imagenOriginal.getImage();
        imagenEscalada = imagen.getScaledInstance(largo, alto, Image.SCALE_SMOOTH);
        imagenFinalElemento = new ImageIcon(imagenEscalada);

        //dentro del label con jLabel.seticon(devolverImagen("imagen de ejemplo))
        return imagenFinalElemento;
    }
}

