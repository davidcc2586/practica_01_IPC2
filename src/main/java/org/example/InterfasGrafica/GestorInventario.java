package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Producto;
import org.example.Herramientas.Fila;
import org.example.Herramientas.Nodo;

import javax.swing.*;
import java.awt.*;

public class GestorInventario {

    protected JPanel jPanel;
    protected JScrollPane jScrollPane;
    protected Fila<FichaProducto> fichasprudcto;

    public GestorInventario(){
        jPanel = new JPanel();
        jScrollPane = new JScrollPane();
        fichasprudcto = new Fila<>();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(jPanel);
    }

    public void agregarProducto(Producto producto){
        FichaProducto fichanuevoProducto = new FichaProducto(producto);
        jPanel.add(fichanuevoProducto.getFichaProducto());
        fichasprudcto.agregarDato(fichanuevoProducto);
        jPanel.revalidate();
        jPanel.revalidate();
    }

    public void actualizarProducto(int identificador){
        Nodo<FichaProducto> actual = fichasprudcto.getPrimero();
        while (actual != null){
            Producto producto = actual.getDato().getProducto();
            if(identificador == producto.getIdentificador()){
                actual.getDato().actualizarficha();
                break;
            }
            actual = actual.getSiguiente();
        }
    }

    public void eliminarProducto(int identificador){
        Nodo<FichaProducto> actual = fichasprudcto.getPrimero();
        int indice = 1;
        while (actual != null){
            Producto producto = actual.getDato().getProducto();
            if (identificador == producto.getIdentificador()){
                jPanel.remove(actual.getDato().getFichaProducto());
                fichasprudcto.eliminarElemento(indice);
                reordenarFichas();
                return;
            }
            indice++;
            actual = actual.getSiguiente();
        }
    }

    public void reordenarFichas(){
        Nodo<FichaProducto> actual = fichasprudcto.getPrimero();
        jPanel.removeAll();
        while(actual != null){
            jPanel.add(actual.getDato().getFichaProducto());
            actual = actual.getSiguiente();
        }
        jPanel.revalidate();
        jPanel.repaint();
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}
