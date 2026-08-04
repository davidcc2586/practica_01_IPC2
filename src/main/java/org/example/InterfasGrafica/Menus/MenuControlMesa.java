package org.example.InterfasGrafica.Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuControlMesa extends MenuDeBarra {


    public MenuControlMesa(JDesktopPane panelPrincipal) {
        super(panelPrincipal, "Gestion de Mesas");
    }

    @Override
    public void crearMenu() {
        JMenuItem itemOcuparMesa = new JMenuItem("Ocupar mesa");
        itemOcuparMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocuparMesa();
            }
        });
        JMenuItem itemFacturar = new JMenuItem("Facturar");
        itemFacturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facturar();
            }
        });

        jMenu.add(itemOcuparMesa);
        jMenu.add(itemFacturar);
    }

    public void ocuparMesa(){

    }

    public void facturar(){

    }
}
