package org.example.InterfasGrafica.backendsMenus;

import org.example.EntidadeCafe.Mesa;
import org.example.Herramientas.ControlImagen;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.GestorMesas;
import org.example.ManejoBaseDatos.MesaDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class BackendMenuGestorMesa {

    protected FilaEntidadCafe mesasBaseDatos = new FilaEntidadCafe();
    protected Fila<Mesa> mesas = new Fila<>();
    protected MesaDB mesaDB;
    protected GestorMesas gestorMesas;
    protected Connection connection;
    protected JDesktopPane panelPrincipal;

    public BackendMenuGestorMesa(Connection connection, JDesktopPane jDesktopPane){
        this.connection = connection;
        panelPrincipal = jDesktopPane;
        gestorMesas = new GestorMesas();
        mesaDB = new MesaDB(connection,this);
        mesaDB.agregarMesasBaseDatos();
    }

    // ventana del menú NO.1
    public void controlMesas(){
        JInternalFrame internalFrame = new JInternalFrame("Control de Mesas", true, true, true, true);
        internalFrame.setSize(920, 1000);
        internalFrame.setLocation(10,10);
        internalFrame.setVisible(true);
        internalFrame.setContentPane(gestorMesas.getScrollPane());
        panelPrincipal.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    // ventana dek nenó NO.2
    public  void agregarNuevaMesa(){
        JInternalFrame internalFrame = new JInternalFrame("Agregar Nueva mesa", true, true, true, true);
        internalFrame.setSize(500, 500);
        int x = (panelPrincipal.getWidth()-500) / 2;
        int y = (panelPrincipal.getHeight()-500) / 2;
        internalFrame.setLocation(x, y);
        internalFrame.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        JLabel jLabel = new JLabel("Capacidad de la mesa: ");
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField textoCapacidad = new JTextField();
        textoCapacidad.setPreferredSize(new Dimension(100, 30));
        textoCapacidad.setMaximumSize(new Dimension(100, 30));
        textoCapacidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel imagen = new JLabel(new ControlImagen().devolverImagen("/Imagenes/mesaLibre.png", 220,220));
        imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton agregar = new JButton("agregar");
        agregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             int capacidad = Integer.parseInt(textoCapacidad.getText());
             mesaDB.agregarNuevaMesa(capacidad);
             internalFrame.dispose();
            }
        });
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(jLabel);
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(textoCapacidad);
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(imagen);
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(agregar);
        jPanel.add(Box.createHorizontalStrut(10));
        //diseño de la logica del cosito
        internalFrame.add(jPanel);
        panelPrincipal.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    //ventana del menu NO.3
    public void eliminarMesa(){
        JInternalFrame jInternalFrame = new JInternalFrame("Eliminar mesas", true, true,true,true);
        jInternalFrame.setSize(620,400);
        int x = (panelPrincipal.getWidth()-620) / 2;
        int y = (panelPrincipal.getHeight()-400) / 2;
        jInternalFrame.setLocation(x, y);
        jInternalFrame.setVisible(true);

        JScrollPane jScrollPane = new JScrollPane();

        JPanel jpanelMesas = new JPanel();
        jpanelMesas.setLayout(new BoxLayout(jpanelMesas, BoxLayout.Y_AXIS));

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new GridLayout(2,4));
        encabezado.add(new JLabel("Mesas de la cafeteria: "));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel("ID mesa"));
        encabezado.add(new JLabel("Capacidad"));
        encabezado.add(new JLabel("Estado de la mesa"));
        encabezado.add(new JLabel(""));
        encabezado.setPreferredSize(new Dimension(580,50));
        encabezado.setMaximumSize(new Dimension(580,50));
        encabezado.setMinimumSize(new Dimension(580,50));
        jpanelMesas.add(encabezado);

        Nodo<Mesa> actual = mesas.getPrimero();
        while(actual != null){
            Mesa mesaActual = actual.getDato();

            JPanel filaMesa = new JPanel();
            filaMesa.setLayout(new GridLayout(1,4));
            filaMesa.setPreferredSize(new Dimension(580,50));
            filaMesa.setMaximumSize(new Dimension(580,50));
            filaMesa.setMinimumSize(new Dimension(580,50));

            JLabel idMesa = new JLabel(String.valueOf(mesaActual.getIdentificador()));
            JLabel capacidadMesa = new JLabel(String.valueOf(mesaActual.getCapacidad()));
            JLabel estadoMesa = new JLabel(mesaActual.getEstadoActual());

            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jpanelMesas.remove(filaMesa);
                    int identificador = mesaActual.getIdentificador();
                    eliminarMesadDB(identificador);
                    gestorMesas.eliminarMesa(identificador);
                    mesaDB.eliminarMesa(identificador);
                    jpanelMesas.revalidate();
                    jpanelMesas.repaint();
                }
            });

            filaMesa.add(idMesa);
            filaMesa.add(capacidadMesa);
            filaMesa.add(estadoMesa);
            filaMesa.add(botonEliminar);

            jpanelMesas.add(filaMesa);

            actual = actual.getSiguiente();
        }

        jScrollPane.setViewportView(jpanelMesas);
        jInternalFrame.setContentPane(jScrollPane);
        panelPrincipal.add(jInternalFrame);
        try {
            jInternalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }


    public void agregarMesa(Mesa mesa){
        mesas.agregarDato(mesa);
        gestorMesas.agregarMesa(mesa);
        copiarFilas(mesas, mesasBaseDatos);
    }

    public void eliminarMesadDB(int identificador){
        int indiceEliminar = mesasBaseDatos.buscarElementoIndice(identificador);
            mesas.eliminarElemento(indiceEliminar);
            copiarFilas(mesas, mesasBaseDatos);
    }

    public void cambiarEstadoMesa(int identificador, String estado){
        int indiceModificar = mesasBaseDatos.buscarElementoIndice(identificador);
        if (indiceModificar != 0) {
            Mesa mesaModificar = mesas.buscarElemento(indiceModificar);
            mesaModificar.cambiarEstado(estado);
            gestorMesas.actualizarMesa(identificador);
            copiarFilas(mesas, mesasBaseDatos);
        }
    }

    public void copiarFilas(Fila<Mesa> origanal, FilaEntidadCafe copiar){
        copiar.limpiarFila();
        Nodo<Mesa> actual = origanal.getPrimero();
        while (actual != null){
            copiar.agregarDato(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}
