package org.example.EntidadeCafe;

import org.example.Exceptions.CantidadInsuficienteException;
import org.example.Herramientas.ControlImagen;

import javax.swing.*;

public class Producto extends EntidadCafe {
    protected int idInsumo;
    protected String nombreInsumo;
    protected String unidadMedida;
    protected double costoInsumo;
    protected int cantidadMinimaStock;
    protected int cantidadProducto;
    protected String direccionImagen;

    public Producto(int idInsumo, String nombreInsumo, String unidadMedida, double costoInsumo, int CantidadMInimaStock, String direccionImagen, int cantidadProducto){
        super(idInsumo);
        this.idInsumo = idInsumo;
        this.nombreInsumo = nombreInsumo;
        this.unidadMedida = unidadMedida;
        this.costoInsumo = costoInsumo;
        this.cantidadMinimaStock = CantidadMInimaStock;
        this.cantidadProducto = cantidadProducto;
        this.direccionImagen = direccionImagen;
    }

    public void ingresarProducto(int cantidadNueoProducto){
        cantidadProducto = cantidadProducto + cantidadNueoProducto;
    }

    public void retirarProducto(int cantidadRetirar) throws CantidadInsuficienteException {
        if(cantidadRetirar <= cantidadProducto){
            cantidadProducto = cantidadProducto - cantidadRetirar;
        }else{
            throw new CantidadInsuficienteException("No hay suficiente cantidad de producto");
        }
    }

    public int getCantidadMinimaStock() {
        return cantidadMinimaStock;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public double getCostoInsumo() {
        return costoInsumo;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public String getUnidadMedida() {
        return unidadMedida;
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
