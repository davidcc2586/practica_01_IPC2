package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Menu;
import org.example.Herramientas.Fila;
import org.example.Herramientas.Nodo;

import javax.swing.*;

public class GestorMenu {

    protected JPanel jPanel;
    protected JScrollPane jScrollPane;
    protected Fila<FichaMenu> fichasMenu;

    public GestorMenu(){
        jPanel = new JPanel();
        jScrollPane = new JScrollPane();
        fichasMenu = new Fila<>();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(jPanel);
    }

    public void agregarMenu(Menu menu){
        FichaMenu fichaNuevoMenu = new FichaMenu(menu);
        jPanel.add(fichaNuevoMenu.getFichaMenu());
        fichasMenu.agregarDato(fichaNuevoMenu);
        jPanel.revalidate();
        jPanel.revalidate();
    }

    public void actualizarMenu(int identificador) {
        Nodo<FichaMenu> actual = fichasMenu.getPrimero();
        while (actual != null) {
            Menu nuevoMenu = actual.getDato().getMenu();
            if (identificador == nuevoMenu.getIdentificador()) {
                actual.getDato().actualizarFicha();
                break;
            }
            actual = actual.getSiguiente();
        }
    }

    public void eliminarMenu(int identificador){
        Nodo<FichaMenu> actual = fichasMenu.getPrimero();
        int indice = 1;
        while (actual != null){
            Menu menu = actual.getDato().getMenu();
            if (identificador == menu.getIdentificador()){
                jPanel.remove(actual.getDato().getFichaMenu());
                fichasMenu.eliminarElemento(indice);
                reordenarFichas();
                return;
            }
            indice++;
            actual = actual.getSiguiente();
        }

    }

    public void reordenarFichas(){
        Nodo<FichaMenu> actual = fichasMenu.getPrimero();
        jPanel.removeAll();
        while (actual != null) {
            jPanel.add(actual.getDato().getFichaMenu());
            actual = actual.getSiguiente();
        }

        jPanel.revalidate();
        jPanel.repaint();
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}
