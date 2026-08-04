package org.example.Herramientas;

public class Fila<T>{

    protected Nodo<T> primero;
    protected Nodo<T> ultimo;
    protected int tamañoFila;

    public Fila(){
        primero = null;
        ultimo = null;
        tamañoFila = 0;
    }

    public void agregarDato(T dato){
        Nodo<T> nuevoDato = new Nodo<>(dato);
        if (primero == null){
            primero = nuevoDato;
        } else {
            nuevoDato.setAnterior(ultimo);
            ultimo.setSiguiente(nuevoDato);
        }
        ultimo = nuevoDato;
        tamañoFila++;
    }

    public T sacarDato(){
        if (primero == null){
            return null;
        }
        Nodo<T> sacar = primero;
        primero = primero.getSiguiente();
        if (primero == null){
            ultimo = null;
        } else {
            primero.setAnterior(null);
        }
        tamañoFila--;
        return sacar.getDato();
    }

    /*
    public void eliminarCantidadDatos(int cantidad){
        for (int i = 0; i < cantidad && primero != null ; i++) {
            primero = primero.getSiguiente();
            if (primero == null){
                ultimo = null;
            } else {
                primero.setAnterior(null);
            }
            tamañoFila--;
        }
    }
     */

    public boolean ocupada(){
        return primero != null;
    }

    public int getTamañoFila() {
        return tamañoFila;
    }
}
