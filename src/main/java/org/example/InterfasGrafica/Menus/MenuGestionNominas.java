package org.example.InterfasGrafica.Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestionNominas extends MenuDeBarra {

    public MenuGestionNominas(JDesktopPane panelPrincipal){
        super(panelPrincipal,"Gestión de Nóminas");
    }

    @Override
    public void crearMenu() {
        JMenuItem itemProcesar = new JMenuItem("Procesar");
        itemProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesar();
            }
        });
        JMenuItem itemRegistrar = new JMenuItem("Registrar");
        itemRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
        JMenuItem itemListar = new JMenuItem("Listar");
        itemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });


        jMenu.add(itemProcesar);
        jMenu.add(itemRegistrar);
        jMenu.add(itemListar);
    }

    public void procesar(){

    }
    public void registrar(){

    }
    public void listar(){

    }

}
