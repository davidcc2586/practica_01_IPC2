package org.example.InterfasGrafica.ElementosVisuales;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalCentro extends JPanel{
    private JPanel gestionMesas;
    private JPanel gestionMenu;
    private JPanel gestionPersonal;
    private JPanel gestionInventario;
    private JPanel gestionReporte;
    private JPanel panelCentral;

    private GestorMesas gestorMesas;

    public VentanaPrincipalCentro(){
        gestorMesas = new GestorMesas();
        setLayout(new BorderLayout());

        if (panelCentral != null) {
            add(panelCentral, BorderLayout.CENTER);
        }
    }

}
