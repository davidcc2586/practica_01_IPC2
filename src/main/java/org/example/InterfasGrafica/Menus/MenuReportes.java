package org.example.InterfasGrafica.Menus;

import org.example.InterfasGrafica.backendsMenus.BackenndMenuReportes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuReportes extends MenuDeBarra{

    protected BackenndMenuReportes backenndMenuReportes;

    public MenuReportes(JDesktopPane panelPrincipal, Connection connection) {
        super(panelPrincipal, connection, "Reportes");
        backenndMenuReportes = new BackenndMenuReportes(connection, panelPrincipal);
    }

    @Override
    public void crearMenu() {
        JMenuItem itemFlujoCaja = new JMenuItem("Flujo de caja");
        itemFlujoCaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteFlujoCaja();
            }
        });
        JMenuItem itemProductoMasVentdido = new JMenuItem("Producto más vendido");
        itemProductoMasVentdido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteProductoMasVentdido();
            }
        });
        JMenuItem itemInsumosBajoStock = new JMenuItem("Insumos con bajo stock");
        itemInsumosBajoStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteInsumosBajoStock();
            }
        });

        jMenu.add(itemFlujoCaja);
        jMenu.add(itemProductoMasVentdido);
        jMenu.add(itemInsumosBajoStock);

    }

    public void reporteFlujoCaja(){
        backenndMenuReportes.crearReporteFlujoCaja();
    }
    public void reporteProductoMasVentdido(){
        backenndMenuReportes.crearRankingMenus();
    }

    public void reporteInsumosBajoStock(){

    }
}
