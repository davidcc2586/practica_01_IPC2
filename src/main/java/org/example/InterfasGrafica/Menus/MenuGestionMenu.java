package org.example.InterfasGrafica.Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestionMenu extends MenuDeBarra {

    public MenuGestionMenu(JDesktopPane panelPrincipal) {
        super(panelPrincipal, "Gestion de Menú");
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
