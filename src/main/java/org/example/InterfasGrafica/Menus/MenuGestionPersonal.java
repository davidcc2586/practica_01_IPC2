package org.example.InterfasGrafica.Menus;

import org.example.InterfasGrafica.backendsMenus.BackendMenuGestorEmpleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuGestionPersonal extends MenuDeBarra {

    public BackendMenuGestorEmpleado backendMenuGestorEmpleado;

    public  MenuGestionPersonal(JDesktopPane panelPrincipal, Connection connection){
        super(panelPrincipal,connection,"Gestión de Personal");
        backendMenuGestorEmpleado= new BackendMenuGestorEmpleado(connection,panelPrincipal);
    }

    @Override
    public void crearMenu() {

        JMenuItem itemRegistrar = new JMenuItem("Control Personal");
        itemRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPersonal();
            }
        });
        JMenuItem itemActualizar = new JMenuItem("Ingresar Empleado");
        itemActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ingresarNuevoEmpleado();
            }
        });
        JMenuItem itemListar = new JMenuItem("Eliminar/Desabilitar");
        itemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDesabilitar();
            }
        });
        JMenuItem itemDeshabilitar = new JMenuItem("Deshabilitar");
        itemDeshabilitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshabilitar();
            }
        });

        jMenu.add(itemRegistrar);
        jMenu.add(itemActualizar);
        jMenu.add(itemListar);
        jMenu.add(itemDeshabilitar);
    }

    public void controlPersonal(){
        backendMenuGestorEmpleado.controlEmpleados();
    }
    public void ingresarNuevoEmpleado(){
        backendMenuGestorEmpleado.agregarNuevoEmpleado();
    }
    public void eliminarDesabilitar(){
        backendMenuGestorEmpleado.eliminarDeshabilitarEmpleado();
    }
    public void deshabilitar(){

    }

}
