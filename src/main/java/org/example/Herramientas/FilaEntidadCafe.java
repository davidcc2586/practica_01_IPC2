package org.example.Herramientas;

import org.example.EntidadeCafe.EntidadCafe;

public class FilaEntidadCafe extends Fila<EntidadCafe>{

    public EntidadCafe buscarElemento(int identificador){
        Nodo<EntidadCafe> nodoRecorrer = primero;
        while (nodoRecorrer != null){
            if (nodoRecorrer.getDato().getIdentificador() == identificador){
                return nodoRecorrer.getDato();
            }
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
        return null;
    }

    public void eliminarElemento(int identificador){
        Nodo<EntidadCafe> nodoRecorrer = primero;
        while (nodoRecorrer != null){
            if (nodoRecorrer.getDato().getIdentificador() == identificador){
                if(nodoRecorrer == primero){
                    primero = nodoRecorrer.getSiguiente();
                    if (primero != null) {
                        primero.setAnterior(null);
                    } else {
                        ultimo = null;
                    }
                }else if (nodoRecorrer == ultimo) {
                    ultimo = nodoRecorrer.getAnterior();
                    ultimo.setSiguiente(null);
                }else {
                    nodoRecorrer.getSiguiente().setAnterior(nodoRecorrer.getAnterior());
                    nodoRecorrer.getAnterior().setSiguiente(nodoRecorrer.getSiguiente());
                }
               tamañoFila--;
                return;
            }
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
    }
}
