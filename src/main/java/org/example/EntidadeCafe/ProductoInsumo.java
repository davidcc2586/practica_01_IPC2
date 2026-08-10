package org.example.EntidadeCafe;

public class ProductoInsumo {
    protected int idProductoInsumo;
    protected int idProducto;
    protected int cantidad;

    public ProductoInsumo(int idProductoInsumo, int idProducto,  int cantidad){
        this.idProductoInsumo = idProductoInsumo;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public ProductoInsumo(int idProductoInsumo,  int cantidad){
        this.idProductoInsumo = idProductoInsumo;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdProductoInsumo() {
        return idProductoInsumo;
    }
}
