package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Producto;
import org.example.Herramientas.ControlImagen;

import javax.swing.*;
import java.awt.*;

public class FichaProducto {
    private JPanel imagenProducto;
    private JLabel nombre;
    private JLabel cantidadStock;
    private JLabel idProducto;
    private JPanel imagenAlerta;
    private JLabel minimoStock;
    private JPanel fichaProducto;
    protected Producto producto;

    public FichaProducto(Producto producto){
        this.producto = producto;

        imagenProducto.setLayout(new FlowLayout());
        JLabel contenedorImagen = new JLabel();
        contenedorImagen.setIcon(new ControlImagen().devolverImagen(producto.getDireccionImagen(), 50,50));
        imagenProducto.add(contenedorImagen);
        idProducto.setText(String.valueOf(producto.getIdentificador()));
        nombre.setText(producto.getNombreInsumo());
        minimoStock.setText(String.valueOf(producto.getCantidadMinimaStock()));
        actualizarficha();
    }

    public void actualizarficha(){
        configuracionFicha();
        cantidadStock.setText(String.valueOf(producto.getCantidadProducto()));
        imagenAlerta.setLayout(new FlowLayout());
        JLabel contenedorImagenAlerta = new JLabel();
        if(producto.getCantidadProducto() <= producto.getCantidadMinimaStock()){
            imagenAlerta.removeAll();
            contenedorImagenAlerta.setIcon(new ControlImagen().devolverImagen("/Imagenes/alerta.png", 27,27));
            imagenAlerta.add(contenedorImagenAlerta);
        } else{
           imagenAlerta.removeAll();
        }
        imagenAlerta.revalidate();
        imagenAlerta.repaint();
        fichaProducto.revalidate();
        fichaProducto.repaint();
    }

    public  void configuracionFicha(){
        fichaProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        Dimension dimension = new Dimension(450, 75);
        fichaProducto.setPreferredSize(dimension);
        fichaProducto.setMaximumSize(dimension);
        fichaProducto.setMinimumSize(dimension);
    }


    public JPanel getFichaProducto() {
        return fichaProducto;
    }

    public Producto getProducto() {
        return producto;
    }
}
