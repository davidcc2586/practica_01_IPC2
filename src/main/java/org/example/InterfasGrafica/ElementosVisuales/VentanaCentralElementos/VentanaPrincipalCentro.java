package org.example.InterfasGrafica.ElementosVisuales.VentanaCentralElementos;

import org.example.InterfasGrafica.VentanaPrincipal;

import javax.swing.*;

public class VentanaPrincipalCentro{
    private JPanel panelCentral;
    private JScrollPane scrollMesas;
    private JScrollPane scrollMenu;
    private JScrollPane scrollPersonal;
    private JScrollPane scrollInventario;
    private JScrollPane scrollReporte;

    protected VentanaPrincipal escritorioAplicacion;
    protected GestorMesas gestorMesas;

    public VentanaPrincipalCentro(VentanaPrincipal escritorioAplicacion){
        this.escritorioAplicacion = escritorioAplicacion;
        construirElementosVentanaPrincipalCentro();
    }

    public void construirElementosVentanaPrincipalCentro(){
        gestorMesas = new GestorMesas(scrollMesas);
    }

    public JPanel getPanelCentral() {
        return panelCentral;
    }

    public GestorMesas getGestorMesas() {
        return gestorMesas;
    }
}
