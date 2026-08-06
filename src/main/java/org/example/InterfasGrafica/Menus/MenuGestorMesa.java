package org.example.InterfasGrafica.Menus;

import org.example.EntidadeCafe.Mesa;
import org.example.Exceptions.MesaInexistenteException;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.ElementosVisuales.VentanaCentralElementos.GestorMesas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestorMesa extends MenuDeBarra {

    protected FilaEntidadCafe mesasBaseDatos = new FilaEntidadCafe();
    protected Fila<Mesa> mesas = new Fila<>();
    protected GestorMesas gestorMesas;

    public MenuGestorMesa(JDesktopPane panelPrincipal) {
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

    public void cambiarEstadoMesa(int identificador, int estado) throws MesaInexistenteException {
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

    public Fila<Mesa> getMesas() {
        return mesas;
    }

    public GestorMesas getGestorMesas() {
        return gestorMesas;
    }

    public void setGestorMesas(GestorMesas gestorMesas) {
        this.gestorMesas = gestorMesas;
    }
}
