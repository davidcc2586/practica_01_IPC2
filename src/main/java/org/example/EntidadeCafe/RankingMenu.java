package org.example.EntidadeCafe;

public class RankingMenu {

    protected Menu menu;
    protected int cantidadVendida;

    public RankingMenu(Menu menu, int cantidadVendida) {
        this.menu = menu;
        this.cantidadVendida = cantidadVendida;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }
}