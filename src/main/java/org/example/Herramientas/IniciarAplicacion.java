package org.example.Herramientas;

import org.example.InterfasGrafica.VentanaPrincipal;

import javax.swing.*;

public class IniciarAplicacion {

    public  IniciarAplicacion(){

    }

    public void iniciar(){

        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.crearElementosGraficos();
    }
}
