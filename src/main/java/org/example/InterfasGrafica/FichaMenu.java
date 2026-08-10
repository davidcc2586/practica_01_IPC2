package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Menu;
import org.example.Herramientas.ControlImagen;

import javax.swing.*;
import java.awt.*;

public class FichaMenu {
    private JPanel fichaMenu;
    private JPanel imagenMenu;
    private JLabel nombre;
    private JLabel precio;
    private JLabel categoria;
    private JLabel idMenu;
    protected Menu menu;

    public FichaMenu(Menu menu){
        this.menu = menu;

        imagenMenu.setLayout(new FlowLayout());
        JLabel contenedorImagen = new JLabel();
        contenedorImagen.setIcon(new ControlImagen().devolverImagen(menu.getDireccionImagen(),55,55));
        imagenMenu.add(contenedorImagen);
        idMenu.setText(String.valueOf(menu.getIdMenu()));
        nombre.setText(menu.getNombre());
        categoria.setText(menu.getCategoria());
        precio.setText(String.valueOf(menu.getPrecio()));
        actualizarFicha();
    }

    public void actualizarFicha(){
        configurarFicha();
        precio.setText(String.valueOf(menu.getPrecio()));
        fichaMenu.revalidate();
        fichaMenu.repaint();
    }
    public void configurarFicha(){
        fichaMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        Dimension dimension = new Dimension(450, 80);
        fichaMenu.setPreferredSize(dimension);
        fichaMenu.setMaximumSize(dimension);
        fichaMenu.setMinimumSize(dimension);
    }

    public JPanel getFichaMenu() {
        return fichaMenu;
    }

    public Menu getMenu() {
        return menu;
    }
}
