package org.example.InterfasGrafica;

import org.example.InterfasGrafica.ElementosVisuales.VentanaPrincipalCentro;
import org.example.InterfasGrafica.Menus.*;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane jDesktopPane;

    public VentanaPrincipal () {
        this.setTitle("JavaBeans Café");
        setSize(1000, 750);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jDesktopPane = new JDesktopPane();
        jDesktopPane.setLayout(new BorderLayout());

        this.add(jDesktopPane, BorderLayout.CENTER);
        crearBarraMenu();
    }

    public void crearBarraMenu(){
        JMenuBar jMenuBar = new JMenuBar();

        MenuGestionPersonal menuGestionPersonal = new MenuGestionPersonal(jDesktopPane);
        MenuGestionNominas menuGestionNominas = new MenuGestionNominas(jDesktopPane);
        MenuGestionInventario menuGestionInventario = new MenuGestionInventario(jDesktopPane);
        MenuGestionMenu menuGestionMenu = new MenuGestionMenu(jDesktopPane);
        MenuControlMesa menuControlMesa = new MenuControlMesa(jDesktopPane);

        jMenuBar.add(menuGestionPersonal.getjMenu());
        jMenuBar.add(menuGestionNominas.getjMenu());
        jMenuBar.add(menuGestionInventario.getjMenu());
        jMenuBar.add(menuGestionMenu.getjMenu());
        jMenuBar.add(menuControlMesa.getjMenu());

        this.setJMenuBar(jMenuBar);
    }

    public void crearElementosGraficos() {
        VentanaPrincipalCentro ventanaPrincipalCentro = new VentanaPrincipalCentro();
        jDesktopPane.add(ventanaPrincipalCentro, BorderLayout.CENTER);
        jDesktopPane.revalidate();
        jDesktopPane.repaint();
    }
}
