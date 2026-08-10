package org.example.InterfasGrafica.Menus;

import org.example.InterfasGrafica.backendsMenus.BackendMenuGestionMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuGestionMenu extends MenuDeBarra {

    protected BackendMenuGestionMenu backendMenuGestionMenu;
    public MenuGestionMenu(JDesktopPane panelPrincipal, Connection connection) {
        super(panelPrincipal,connection,"Gestion de Menú");
        backendMenuGestionMenu = new BackendMenuGestionMenu(connection, panelPrincipal);
    }

    @Override
    public void crearMenu() {
        JMenuItem itemControlMenu = new JMenuItem("Control de Menú");
        itemControlMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlMenu();
            }
        });
        JMenuItem itemCrear = new JMenuItem("Crear Nuevo Menú");
        itemCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear();
            }
        });
        JMenuItem itemEliminar = new JMenuItem("Eliminar Menú");
        itemEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        JMenuItem itemExportarMenu = new JMenuItem("Exportar Menú a HTML");
        itemExportarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarMenu();
            }
        });


        jMenu.add(itemControlMenu);
        jMenu.add(itemCrear);
        jMenu.add(itemEliminar);
    }

    public void controlMenu(){
        backendMenuGestionMenu.controlMenus();
    }
    public void crear(){
        backendMenuGestionMenu.crearNuevoMenu();
    }
    public void eliminar(){
        backendMenuGestionMenu.eliminarMenu();
    }
    public void exportarMenu(){
        backendMenuGestionMenu.exportarMenuHTML();
    }
}
