package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Transaccion;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class FichaTransaccion {

    protected Transaccion transaccion;
    protected JPanel jPanel;

    public FichaTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
        crearFicha();
    }

    public void crearFicha() {

        jPanel = new JPanel(new GridLayout(1, 5));
        Dimension dimension = new Dimension(785, 30);
        jPanel.setPreferredSize(dimension);
        jPanel.setMaximumSize(dimension);
        jPanel.setMinimumSize(dimension);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        jPanel.add(crearEtiqueta(String.valueOf(transaccion.getIdReporte())));
        jPanel.add(crearEtiqueta(transaccion.getFecha().format(formato)));
        jPanel.add(crearEtiqueta(transaccion.getMotivo()));
        jPanel.add(crearEtiqueta(transaccion.getTipo()));
        jPanel.add(crearEtiqueta(String.format("Q %.2f", transaccion.getMonto())));
    }

    public JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        return etiqueta;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }
}