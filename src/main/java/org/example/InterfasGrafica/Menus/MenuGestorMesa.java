package org.example.InterfasGrafica.Menus;

import org.example.EntidadeCafe.Mesa;
import org.example.Exceptions.MesaInexistenteException;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.ElementosVisuales.VentanaCentralElementos.GestorMesas;
import org.example.ManejoBaseDatos.MesaDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuGestorMesa extends MenuDeBarra {

    protected FilaEntidadCafe mesasBaseDatos = new FilaEntidadCafe();
    protected Fila<Mesa> mesas = new Fila<>();
    protected GestorMesas gestorMesas;
    protected Connection connection;
    protected MesaDB mesaDB;

    public MenuGestorMesa(JDesktopPane panelPrincipal, Connection connection, GestorMesas gestorMesas) {
        super(panelPrincipal, "Gestion de Mesas");
        this.connection = connection;
        this.gestorMesas = gestorMesas;
        mesaDB = new MesaDB(this, connection, gestorMesas);
        mesaDB.agregarMesasBaseDatos();
    }

    @Override
    public void crearMenu() {
        JMenuItem itemOcuparMesa = new JMenuItem("Agregar mesa");
        itemOcuparMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarMesa();
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


    public void agregarMesa(){
        abrirVentanaInterna("pruevassss", 600, 550);
    }

    public void facturar(){

    }


    public void agregarMesa(Mesa mesa){
        mesas.agregarDato(mesa);
        copiarFilas(mesas, mesasBaseDatos);
        //con sql añadir este dato
        //gestorMesas.agregarMesa(mesa, true);
    }

    public void eliminarMesa(int identificador) throws MesaInexistenteException {
        int indiceEliminar = mesasBaseDatos.buscarElementoIndice(identificador);
        if (indiceEliminar != 0) {
            mesas.eliminarElemento(indiceEliminar);
            copiarFilas(mesas, mesasBaseDatos);
        }else {
            throw new MesaInexistenteException("La mesa no se encuentra en el sistema.");
        }

        // con sql eliminar este dadto
    }

    public void cambiarEstadoMesa(int identificador, String estado) throws MesaInexistenteException {
        int indiceModificar = mesasBaseDatos.buscarElementoIndice(identificador);
        if (indiceModificar != 0) {
            Mesa mesaModificar = mesas.buscarElemento(indiceModificar);
            mesaModificar.cambiarEstado(estado);
            copiarFilas(mesas, mesasBaseDatos);
        }else {
            throw new MesaInexistenteException("La mesa no se encuentra en el sistema.");
        }
    }

    public void copiarFilas(Fila<Mesa> origanal, FilaEntidadCafe copiar){
        copiar.limpiarFila();
        Nodo<Mesa> temporal = origanal.getPrimero();
        while (temporal != null){
            copiar.agregarDato(temporal.getDato());
            temporal = temporal.getSiguiente();
        }
    }


    private void abrirVentanaInterna(String titulo, int ancho, int alto) {
        // Parametros: Título, Resizable, Closable, Maximizable, Iconifiable
        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(ancho, alto);
        int x = (panelPrincipal.getWidth() - ancho) / 2;
        int y = (panelPrincipal.getHeight() - alto) / 2;
        internalFrame.setLocation(Math.max(0, x), Math.max(0, y));
        internalFrame.setVisible(true);
        panelPrincipal.add(internalFrame);

        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }
}
