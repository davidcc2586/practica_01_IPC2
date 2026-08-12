package org.example.InterfasGrafica;

import org.example.InterfasGrafica.Menus.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class VentanaPrincipal extends JFrame {

    protected JDesktopPane jDesktopPane;
    protected MenuGestionPersonal menuGestionPersonal;
    protected MenuGestionNominas menuGestionNominas;
    protected MenuGestionInventario menuGestionInventario;
    protected MenuGestionMenu menuGestionMenu;
    protected MenuGestorMesa menuControlMesa;
    protected MenuGestorCuentas menuGestorCuentas;
    protected JMenuBar jMenuBar;
    private Connection connection;

    public VentanaPrincipal (Connection connection) {
        this.connection = connection;
        configuracionVentanaPrincipal();
        crearBarraMenu();
    }

    public void configuracionVentanaPrincipal(){
        this.setTitle("JavaBeans Café");
        this.setSize(1000, 750);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jDesktopPane = new JDesktopPane();
        this.add(jDesktopPane, BorderLayout.CENTER);
    }

    public void crearBarraMenu(){
        jMenuBar = new JMenuBar();
        menuControlMesa = new MenuGestorMesa(jDesktopPane, connection);
        menuGestionPersonal = new MenuGestionPersonal(jDesktopPane, connection);
        menuGestionNominas = new MenuGestionNominas(jDesktopPane, connection);
        menuGestionInventario = new MenuGestionInventario(jDesktopPane, connection);
        menuGestionMenu = new MenuGestionMenu(jDesktopPane, connection);
        menuGestorCuentas = new MenuGestorCuentas(jDesktopPane, connection);

        jMenuBar.add(menuGestionPersonal.getjMenu());
        jMenuBar.add(menuGestionNominas.getjMenu());
        jMenuBar.add(menuGestionInventario.getjMenu());
        jMenuBar.add(menuGestionMenu.getjMenu());
        jMenuBar.add(menuControlMesa.getjMenu());
        jMenuBar.add(menuGestorCuentas.getjMenu());
        this.setJMenuBar(jMenuBar);
    }

}
