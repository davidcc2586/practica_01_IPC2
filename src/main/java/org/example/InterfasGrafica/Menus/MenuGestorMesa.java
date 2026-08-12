package org.example.InterfasGrafica.Menus;

import org.example.InterfasGrafica.backendsMenus.BackendMenuGestorMesa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuGestorMesa extends MenuDeBarra {

    protected BackendMenuGestorMesa backendMenuGestorMesa;

    public MenuGestorMesa(JDesktopPane jDesktopPane, Connection connection) {
        super(jDesktopPane,connection, "Gestion de Mesas");
        backendMenuGestorMesa = new BackendMenuGestorMesa(connection, jDesktopPane);
    }

    @Override
    public void crearMenu() {
        JMenuItem itemControlMesas = new JMenuItem("Control de mesas");
        itemControlMesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaGeneralMesas();
            }
        });
        JMenuItem itemOcuparMesa = new JMenuItem("Agregar mesa");
        itemOcuparMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarMesa();
            }
        });
        JMenuItem itemFacturar = new JMenuItem("Eliminar Mesa");
        itemFacturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facturar();
            }
        });
        jMenu.add(itemControlMesas);
        jMenu.add(itemOcuparMesa);
        jMenu.add(itemFacturar);
    }

    public void vistaGeneralMesas(){
        backendMenuGestorMesa.controlMesas();
    }

    public void agregarMesa(){
        backendMenuGestorMesa.agregarNuevaMesa();
    }

    public void facturar(){
        backendMenuGestorMesa.eliminarMesa();
    }

    public BackendMenuGestorMesa getBackendMenuGestorMesa() {
        return backendMenuGestorMesa;
    }
}
