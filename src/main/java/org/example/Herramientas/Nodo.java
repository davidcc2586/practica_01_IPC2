package org.example.Herramientas;

public class Nodo<T> {

    protected Nodo<T> siguiente;
    protected Nodo<T> anterior;
    protected T dato;

    public Nodo (T dato){
        this.siguiente = null;
        this.anterior = null;
        this.dato = dato;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public T getDato() {
        return dato;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }
}
