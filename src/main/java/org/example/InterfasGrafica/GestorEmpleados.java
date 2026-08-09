package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Empleado;
import org.example.Herramientas.Fila;
import org.example.Herramientas.Nodo;

import javax.swing.*;

public class GestorEmpleados {

    protected JPanel jPanel;
    protected JScrollPane jScrollPane;
    protected Fila<FichaEmpleado> fichasEmpleado;

    public GestorEmpleados(){
        jPanel = new JPanel();
        jScrollPane = new JScrollPane();
        fichasEmpleado = new Fila<>();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(jPanel);
    }

    public void agregarEmpleado(Empleado empleado){
        FichaEmpleado nuevoEmpleado = new FichaEmpleado(empleado);
        jPanel.add(nuevoEmpleado.getFichaEmpleado());
        fichasEmpleado.agregarDato(nuevoEmpleado);
        jPanel.revalidate();
        jPanel.revalidate();
    }

    public void actualizarEmpleado(int identificador){
        Nodo<FichaEmpleado> actual = fichasEmpleado.getPrimero();
        while (actual != null){
            Empleado empleado = actual.getDato().getEmpleado();
            if(identificador == empleado.getIdentificador()){
                actual.getDato().actualizarficha();
               break;
            }
            actual = actual.getSiguiente();
        }
    }

    public void eliminarEmpleado(int identificador){
        Nodo<FichaEmpleado> actual = fichasEmpleado.getPrimero();
        int indice = 1;
        while (actual != null){
            Empleado empleado = actual.getDato().getEmpleado();
            if (identificador == empleado.getIdentificador()){
                jPanel.remove(actual.getDato().getFichaEmpleado());
                fichasEmpleado.eliminarElemento(indice);
                reordenarFichas();
                return;
            }
            indice++;
            actual = actual.getSiguiente();
        }
    }

    public void reordenarFichas(){
        Nodo<FichaEmpleado> actual = fichasEmpleado.getPrimero();
        jPanel.removeAll();
        while(actual != null){
           jPanel.add(actual.getDato().getFichaEmpleado());
            actual = actual.getSiguiente();
        }
        jPanel.revalidate();
        jPanel.repaint();
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}
