package org.example.InterfasGrafica.Menus;

import org.example.InterfasGrafica.backendsMenus.BackendMenuGestionInventario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuGestionInventario extends MenuDeBarra {

    protected BackendMenuGestionInventario backendMenuGestionInventario;
    public MenuGestionInventario(JDesktopPane panelPrincipal, Connection connection) {
        super(panelPrincipal, connection,"Gestión de Inventario");
        backendMenuGestionInventario = new BackendMenuGestionInventario(connection,panelPrincipal);
    }

    @Override
    public void crearMenu() {
        JMenuItem itemGestionarMateriaPrima = new JMenuItem("Control de Inventario");
        itemGestionarMateriaPrima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlMateriaPrima();
            }
        });
        JMenuItem itemRegistrarComprasInsumo = new JMenuItem("Registrar Ingreso de Producto");
        itemRegistrarComprasInsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarComprasInsumo();
            }
        });
        JMenuItem itemIngresarNuevoProducto = new JMenuItem("Ingresar Nuevo producto");
        itemIngresarNuevoProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoProducto();
            }
        });

        JMenuItem itemEliminarProducto = new JMenuItem("Eliminar producto");
        itemEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        jMenu.add(itemGestionarMateriaPrima);
        jMenu.add(itemRegistrarComprasInsumo);
        jMenu.add(itemIngresarNuevoProducto);
        jMenu.add(itemEliminarProducto);
    }

    public void controlMateriaPrima(){
        backendMenuGestionInventario.controlInventario();
    }

    public void registrarComprasInsumo(){
        backendMenuGestionInventario.ingresarProducto();
    }

    public void nuevoProducto(){
        backendMenuGestionInventario.ingresarNuevoProducto();
    }

    public  void eliminarProducto(){
        backendMenuGestionInventario.eliminarProducto();
    }

    public BackendMenuGestionInventario getBackendMenuGestionInventario() {
        return backendMenuGestionInventario;
    }
}
