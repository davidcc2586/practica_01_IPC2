package org.example.Herramientas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ControlImagen {

    public ControlImagen(){

    }

    public ImageIcon devolverImagen(String direccionImagen, int largo, int alto){
        Image imagenEscalada;
        ImageIcon imagenOriginal = new ImageIcon(Objects.requireNonNull(getClass().getResource(direccionImagen)));
        Image imagen = imagenOriginal.getImage();
        imagenEscalada = imagen.getScaledInstance(largo, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
}

