package org.example.InterfasGrafica.ElementosVisuales.VentanaCentralElementos;

import org.example.EntidadeCafe.EntidadCafe;
import org.example.EntidadeCafe.Mesa;
import org.example.Herramientas.Fila;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.ElementosVisuales.FichaMesa;
import org.example.InterfasGrafica.Menus.MenuGestorMesa;

import javax.swing.*;
import java.awt.*;

public class GestorMesas {


    protected JPanel contenedorMesas;
    protected Fila<FichaMesa> fichaMesaFila;
    public GestorMesas(JScrollPane panelMesas){
        fichaMesaFila = new Fila<>();
        contenedorMesas = new JPanel();

        contenedorMesas.setLayout(new GridBagLayout());
        panelMesas.setViewportView(contenedorMesas);
    }

    public void agregarMesa(Mesa mesa){

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        int cantidad = contenedorMesas.getComponentCount();

        gridBagConstraints.gridx = cantidad % 3;
        gridBagConstraints.gridy = cantidad / 3;

        FichaMesa nuevaMesaGrafica = new FichaMesa(mesa);
        contenedorMesas.add(nuevaMesaGrafica.getFichaMesa(), gridBagConstraints);
        fichaMesaFila.agregarDato(nuevaMesaGrafica);
        contenedorMesas.revalidate();
        contenedorMesas.repaint();
    }

    public void dibujarFicha(FichaMesa fichaMesa){

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        int cantidad = contenedorMesas.getComponentCount();

        gridBagConstraints.gridx = cantidad % 3;
        gridBagConstraints.gridy = cantidad / 3;

        contenedorMesas.add(fichaMesa.getFichaMesa(), gridBagConstraints);
    }

    public void actualizarMesa(int identificador){
        Nodo<FichaMesa> actual = fichaMesaFila.getPrimero();
        while (actual != null){
            Mesa mesa = actual.getDato().getMesa();
            if(identificador == mesa.getIdentificador()){
                actual.getDato().actualizarEstado();
                break;
            }
            actual = actual.getSiguiente();
        }
    }

    public void eliminarMesa(int identificador){
        Nodo<FichaMesa> actual = fichaMesaFila.getPrimero();
        int indice = 1;
        while (actual != null){
            Mesa mesa = actual.getDato().getMesa();
            if(identificador == mesa.getIdentificador()){
                contenedorMesas.remove(actual.getDato().getFichaMesa());
                fichaMesaFila.eliminarElemento(indice);
                actualizarFichas();
                return;
            }
            indice++;
            actual = actual.getSiguiente();
        }
    }
    public void actualizarFichas(){
        Nodo<FichaMesa> actual = fichaMesaFila.getPrimero();
        contenedorMesas.removeAll();
        while (actual != null){
            dibujarFicha(actual.getDato());
            actual = actual.getSiguiente();
        }
        contenedorMesas.revalidate();
        contenedorMesas.repaint();
    }
}
