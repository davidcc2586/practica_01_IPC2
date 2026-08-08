package org.example.InterfasGrafica.Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuGestionMenu extends MenuDeBarra {

    public MenuGestionMenu(JDesktopPane panelPrincipal, Connection connection) {
        super(panelPrincipal,connection,"Gestion de Menú");
    }

    @Override
    public void crearMenu() {
        JMenuItem itemCrear= new JMenuItem("Crear");
        itemCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear();
            }
        });

        jMenu.add(itemCrear);
    }

    public void crear(){

    }
}
