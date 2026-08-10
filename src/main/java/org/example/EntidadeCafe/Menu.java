package org.example.EntidadeCafe;

public class Menu extends EntidadCafe{

    protected int idMenu;
    protected String nombre;
    protected String categoria;
    protected double precio;
    protected String direccionImagen;

    public Menu(int idMenu, String nombre,String categoria,double precio, String direccionImagen){
        super(idMenu, nombre);
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.direccionImagen = direccionImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
