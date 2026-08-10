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

    public T buscarElemento(int indice){
        Nodo<T> nodoRecorrer = primero;
        int indiceActual = 1;
        while (nodoRecorrer != null){
            if (indice == indiceActual){
                return nodoRecorrer.getDato();
            }
            indiceActual++;
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
        return null;
    }

    public void eliminarElemento(int indice){
        Nodo<T> nodoRecorrer = primero;
        int indiceActual = 1;
        while (nodoRecorrer != null){
            if (indiceActual == indice){
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
            indiceActual++;
            nodoRecorrer = nodoRecorrer.getSiguiente();
        }
    }

    public void limpiarFila(){
        primero = null;
        ultimo = null;
        tamañoFila = 0;
    }

    public boolean ocupada(){
        return primero != null;
    }

    public int getTamañoFila() {
        return tamañoFila;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    public Nodo<T> getUltimo() {
        return ultimo;
    }
}
