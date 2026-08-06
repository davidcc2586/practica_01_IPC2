package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Mesa;
import org.example.InterfasGrafica.ElementosVisuales.VentanaCentralElementos.VentanaPrincipalCentro;
import org.example.InterfasGrafica.Menus.*;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    protected JDesktopPane jDesktopPane;
    protected MenuGestionPersonal menuGestionPersonal;
    protected MenuGestionNominas menuGestionNominas;
    protected MenuGestionInventario menuGestionInventario;
    protected MenuGestionMenu menuGestionMenu;
    protected MenuGestorMesa menuControlMesa;
    private VentanaPrincipalCentro ventanaPrincipalCentro;
    protected JMenuBar jMenuBar;

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
        jMenuBar = new JMenuBar();

        menuGestionPersonal = new MenuGestionPersonal(jDesktopPane);
        menuGestionNominas = new MenuGestionNominas(jDesktopPane);
        menuGestionInventario = new MenuGestionInventario(jDesktopPane);
        menuGestionMenu = new MenuGestionMenu(jDesktopPane);
        menuControlMesa = new MenuGestorMesa(jDesktopPane);

        jMenuBar.add(menuGestionPersonal.getjMenu());
        jMenuBar.add(menuGestionNominas.getjMenu());
        jMenuBar.add(menuGestionInventario.getjMenu());
        jMenuBar.add(menuGestionMenu.getjMenu());
        jMenuBar.add(menuControlMesa.getjMenu());

        this.setJMenuBar(jMenuBar);
    }

    public void crearElementosGraficos() {
        ventanaPrincipalCentro = new VentanaPrincipalCentro(this);
        jDesktopPane.add(ventanaPrincipalCentro.getPanelCentral(), BorderLayout.CENTER);
        jDesktopPane.revalidate();
        jDesktopPane.repaint();

        //
        Mesa mesa = new Mesa(20,20);
        ventanaPrincipalCentro.getGestorMesas().agregarMesa(mesa);
        ventanaPrincipalCentro.getGestorMesas().agregarMesa(mesa);
        ventanaPrincipalCentro.getGestorMesas().agregarMesa(mesa);
        ventanaPrincipalCentro.getGestorMesas().agregarMesa(mesa);
        ventanaPrincipalCentro.getGestorMesas().agregarMesa(mesa);

    }


    public MenuGestionInventario getMenuGestionInventario() {
        return menuGestionInventario;
    }

    public MenuGestionMenu getMenuGestionMenu() {
        return menuGestionMenu;
    }

    public MenuGestionPersonal getMenuGestionPersonal() {
        return menuGestionPersonal;
    }

    public MenuGestionNominas getMenuGestionNominas() {
        return menuGestionNominas;
    }

    public MenuGestorMesa getMenuControlMesa() {
        return menuControlMesa;
    }

    public VentanaPrincipalCentro getVentanaPrincipalCentro() {
        return ventanaPrincipalCentro;
    }
}
