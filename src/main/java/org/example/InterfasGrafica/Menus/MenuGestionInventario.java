package org.example.InterfasGrafica.Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestionInventario extends MenuDeBarra {


    public MenuGestionInventario(JDesktopPane panelPrincipal) {
        super(panelPrincipal, "Gestión de Inventario");
    }

    @Override
    public void crearMenu() {
        JMenuItem itemGestionarMateriaPrima = new JMenuItem("Gestionar la matería prima");
        itemGestionarMateriaPrima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionarMateriaPrima();
            }
        });
        JMenuItem itemRegistrarComprasInsumo = new JMenuItem("Registrar Compras de Insumo");
        itemRegistrarComprasInsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarComprasInsumo();
            }
        });

        jMenu.add(itemGestionarMateriaPrima);
        jMenu.add(itemRegistrarComprasInsumo);
    }

    public void gestionarMateriaPrima(){

    }

    public void registrarComprasInsumo(){

    }

}
