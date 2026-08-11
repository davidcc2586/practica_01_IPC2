package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Nomina;
import org.example.Herramientas.Fila;
import org.example.Herramientas.Nodo;

import javax.swing.*;
import java.awt.*;

public class GestorNomina {

    protected JPanel jPanel;
    protected JScrollPane jScrollPane;

    public GestorNomina() {
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jPanel);
        JPanel encabezado = crearFila();

        encabezado.add(crearEtiqueta("ID"));
        encabezado.add(crearEtiqueta("Empleado"));
        encabezado.add(crearEtiqueta("Nombre"));
        encabezado.add(crearEtiqueta("Monto"));
        encabezado.add(crearEtiqueta("Fecha"));
        encabezado.add(crearEtiqueta("Tipo"));
        encabezado.add(crearEtiqueta("Estado"));

        jPanel.add(encabezado);
    }

    public void agregarNomina(Nomina nomina) {

        JPanel fila = crearFila();
        fila.add(crearEtiqueta(String.valueOf(nomina.getIdNomina())));
        fila.add(crearEtiqueta(String.valueOf(nomina.getIdEmpleado())));
        fila.add(crearEtiqueta(nomina.getNombreEmpleado()));
        fila.add(crearEtiqueta(String.valueOf(nomina.getMontoPagar())));
        fila.add(crearEtiqueta(String.valueOf(nomina.getFechaPago())));
        fila.add(crearEtiqueta(nomina.getTipo()));
        fila.add(crearEtiqueta(nomina.getEstado()));

        jPanel.add(fila);
        jPanel.revalidate();
        jPanel.repaint();
    }

    public void volverListarNominas(Fila<Nomina> nominasActualizadas) {

        jPanel.removeAll();
        JPanel encabezado = crearFila();
        encabezado.add(crearEtiqueta("ID"));
        encabezado.add(crearEtiqueta("Empleado"));
        encabezado.add(crearEtiqueta("Nombre"));
        encabezado.add(crearEtiqueta("Monto"));
        encabezado.add(crearEtiqueta("Fecha"));
        encabezado.add(crearEtiqueta("Tipo"));
        encabezado.add(crearEtiqueta("Estado"));

        jPanel.add(encabezado);

        Nodo<Nomina> actual = nominasActualizadas.getPrimero();

        while (actual != null) {
            Nomina nomina = actual.getDato();
            JPanel fila = crearFila();
            fila.add(crearEtiqueta(String.valueOf(nomina.getIdNomina())));
            fila.add(crearEtiqueta(String.valueOf(nomina.getIdEmpleado())));
            fila.add(crearEtiqueta(nomina.getNombreEmpleado()));
            fila.add(crearEtiqueta(String.valueOf(nomina.getMontoPagar())));
            fila.add(crearEtiqueta(String.valueOf(nomina.getFechaPago())));
            fila.add(crearEtiqueta(nomina.getTipo()));
            fila.add(crearEtiqueta(nomina.getEstado()));

            jPanel.add(fila);
            actual = actual.getSiguiente();
        }

        jPanel.revalidate();
        jPanel.repaint();
    }

    public JPanel crearFila() {

        JPanel fila = new JPanel(new GridLayout(1, 7));
        Dimension dimension = new Dimension(440, 30);
        fila.setPreferredSize(dimension);
        fila.setMaximumSize(dimension);
        fila.setMinimumSize(dimension);

        return fila;
    }

    public JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        return etiqueta;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}