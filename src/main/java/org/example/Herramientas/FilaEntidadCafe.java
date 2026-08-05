package org.example.Herramientas;

import org.example.EntidadeCafe.EntidadCafe;

public class FilaEntidadCafe extends Fila<EntidadCafe>{

    public int buscarElementoIndice(int identificador){
        int indice = 1;
        Nodo<EntidadCafe> nodoRecorrer = primero;
        while (nodoRecorrer != null){
            if (nodoRecorrer.getDato().getIdentificador() == identificador){
                return indice;
            }
            indice++;
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
        return 0;
    }

}
