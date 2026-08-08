package org.example.InterfasGrafica.Menus;

import javax.swing.*;
import java.sql.Connection;

public abstract class MenuDeBarra {

    protected JMenu jMenu;
    protected JDesktopPane panelPrincipal;
    protected Connection connection;

    public MenuDeBarra (JDesktopPane panelPrincipal, Connection connection, String nombreMenu){
        this.panelPrincipal = panelPrincipal;
        this.connection = connection;
        jMenu = new JMenu(nombreMenu);
        crearMenu();
    }

    public abstract void crearMenu();

    public JMenu getjMenu() {
        return jMenu;
    }
}
