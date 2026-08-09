package org.example.Herramientas;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class ControlImagen {

    public ControlImagen() {

    }

    public ImageIcon devolverImagen(String direccionImagen, int largo, int alto) {

        ImageIcon imagenOriginal = null;
        File archivo = new File(direccionImagen);
        if (archivo.exists()) {
            imagenOriginal = new ImageIcon(direccionImagen);
        } else {
            URL recurso = getClass().getResource(direccionImagen);
            if (recurso != null) {
                imagenOriginal = new ImageIcon(recurso);
            }
        }
        Image imagen = imagenOriginal.getImage();
        Image imagenEscalada = imagen.getScaledInstance(largo, alto, Image.SCALE_SMOOTH);

        return new ImageIcon(imagenEscalada);
    }
}