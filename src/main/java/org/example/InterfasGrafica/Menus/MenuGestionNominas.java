package org.example.InterfasGrafica.Menus;

import org.example.InterfasGrafica.backendsMenus.BackendMenuGestionNominas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuGestionNominas extends MenuDeBarra {

    protected BackendMenuGestionNominas backendMenuGestionNominas;

    public MenuGestionNominas(JDesktopPane panelPrincipal, Connection connection){
        super(panelPrincipal, connection,"Gestión de Nóminas");
        backendMenuGestionNominas = new BackendMenuGestionNominas(connection, panelPrincipal);
    }

    @Override
    public void crearMenu() {
        JMenuItem itemControl = new JMenuItem("Control de Nomina");
        itemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control();
            }
        });
        jMenu.add(itemControl);
    }

    public void control(){
        backendMenuGestionNominas.controlNomina();
    }

}
