package org.example.InterfasGrafica.Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestionPersonal extends MenuDeBarra {

    public  MenuGestionPersonal(JDesktopPane panelPrincipal){
        super(panelPrincipal,"Gestión de Personal");
    }

    @Override
    public void crearMenu() {

        JMenuItem itemRegistrar = new JMenuItem("Registrar");
        itemRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
        JMenuItem itemActualizar = new JMenuItem("Actualizar");
        itemActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               actualizar();
            }
        });
        JMenuItem itemListar = new JMenuItem("Listar");
        itemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             listar();
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

    public void registrar(){

    }
    public void actualizar(){

    }
    public void listar(){

    }
    public void deshabilitar(){

    }

}
