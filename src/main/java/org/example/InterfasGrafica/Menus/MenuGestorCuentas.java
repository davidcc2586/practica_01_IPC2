package org.example.InterfasGrafica.Menus;

import org.example.InterfasGrafica.backendsMenus.BackendMenuGestionInventario;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestorCuentas;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestorMesa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuGestorCuentas extends MenuDeBarra{

    protected BackendMenuGestorCuentas backendMenuGestorCuentas;
    public MenuGestorCuentas(JDesktopPane panelPrincipal, Connection connection, BackendMenuGestorMesa backendMenuGestorMesa, BackendMenuGestionInventario backendMenuGestionInventario) {
        super(panelPrincipal, connection, "Gestion de Cuentas");
         backendMenuGestorCuentas = new BackendMenuGestorCuentas(connection,panelPrincipal, backendMenuGestorMesa,backendMenuGestionInventario);
    }

    @Override
    public void crearMenu() {
        JMenuItem itemCuentaAbiertas = new JMenuItem("Control de Cuentas Abiertas");
        itemCuentaAbiertas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuentasAbiertas();
            }
        });
        JMenuItem itemCuentasCerradas = new JMenuItem("Control de Cuentas Cerradas");
        itemCuentasCerradas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuentasCerradas();
            }
        });

        jMenu.add(itemCuentaAbiertas);
        jMenu.add(itemCuentasCerradas);
    }

    public void cuentasAbiertas(){
        backendMenuGestorCuentas.crearControlCuentasAbiertas();
    }

    public  void cuentasCerradas(){
        backendMenuGestorCuentas.crearControlCuentasCerradas();
    }
}
