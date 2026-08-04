package org.example.InterfasGrafica.Menus;

import javax.swing.*;

public abstract class MenuDeBarra {

    protected JMenu jMenu;
    protected JDesktopPane panelPrincipal;

    public MenuDeBarra (JDesktopPane panelPrincipal, String nombreMenu){
        this.panelPrincipal = panelPrincipal;
        jMenu = new JMenu(nombreMenu);
        crearMenu();
    }

    public abstract void crearMenu();

    public JMenu getjMenu() {
        return jMenu;
    }
}
