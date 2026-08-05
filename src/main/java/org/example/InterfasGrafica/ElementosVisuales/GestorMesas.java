package org.example.InterfasGrafica.ElementosVisuales;

import org.example.EntidadeCafe.Mesa;
import org.example.Exceptions.MesaInexistenteException;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;

public class GestorMesas {

    protected FilaEntidadCafe mesasBaseDatos = new FilaEntidadCafe();
    private Fila<Mesa> mesas = new Fila<>();

    public GestorMesas(){

    }

    public void agregarMesa(Mesa mesa){
        mesas.agregarDato(mesa);
        copiarFilas(mesas, mesasBaseDatos);
    }

    public void eliminarMesa(int identificador) throws MesaInexistenteException {
        int indiceEliminar = mesasBaseDatos.buscarElementoIndice(identificador);
        if (indiceEliminar != 0) {
            mesas.eliminarElemento(indiceEliminar);
            copiarFilas(mesas, mesasBaseDatos);
        }else {
            throw new MesaInexistenteException("La mesa no se encuentra en el sistema.");
        }
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
}
