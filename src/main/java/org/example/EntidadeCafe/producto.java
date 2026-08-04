package org.example.EntidadeCafe;

import org.example.Exceptions.CantidadInsuficienteException;
import org.example.Herramientas.ControlImagen;

import javax.swing.*;

public class producto extends EntidadCafe {
    protected int codigoInsumo;
    protected String nombreInsumo;
    protected String unidadMedida;
    protected double costoInsumo;
    protected int cantidadMinimaStock;
    protected int cantidadProducto;
    protected ImageIcon imagenProducto;

    public producto (int codigoInsumo, String nombreInsumo, String unidadMedida, double costoInsumo, int CantidadMInimaStock, String direccionImagen){
        super(codigoInsumo);
        this.codigoInsumo = codigoInsumo;
        this.nombreInsumo = nombreInsumo;
        this.unidadMedida = unidadMedida;
        this.costoInsumo = costoInsumo;
        this.cantidadMinimaStock = CantidadMInimaStock;
        cantidadProducto = 0;

        ControlImagen controlImagen = new ControlImagen();
        imagenProducto = controlImagen.devolverImagen(direccionImagen, 100,100);
    }

    public void ingresarProducto(int cantidadNueoProducto){
        cantidadProducto = cantidadProducto + cantidadNueoProducto;
    }

    public void retirarProducto(int cantidadRetirar) throws CantidadInsuficienteException {
        if(cantidadRetirar <= cantidadProducto){
            cantidadProducto = cantidadProducto - cantidadRetirar;
        }else{
            throw new IllegalArgumentException("No hay suficiente cantidad de producto");
        }
    }

    public int getCantidadMinimaStock() {
        return cantidadMinimaStock;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public int getCodigoInsumo() {
        return codigoInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public double getCostoInsumo() {
        return costoInsumo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setCodigoInsumo(int codigoInsumo) {
        this.codigoInsumo = codigoInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public void setCostoInsumo(double costoInsumo) {
        this.costoInsumo = costoInsumo;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setCantidadMinimaStock(int cantidadMinimaStock) {
        this.cantidadMinimaStock = cantidadMinimaStock;
    }
}
