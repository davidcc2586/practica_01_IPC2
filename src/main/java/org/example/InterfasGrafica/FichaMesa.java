package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Mesa;

import javax.swing.*;
import java.awt.*;

public class FichaMesa{

    protected Mesa mesa;
    private JLabel numeroMesa;
    private JLabel capacidad;
    private JLabel estado;
    private JPanel imagenMesa;
    private JPanel fichaMesa;

    public FichaMesa(Mesa mesa){
        this.mesa = mesa;
        numeroMesa.setText(String.valueOf(mesa.getNumeroMesa()));
        capacidad.setText(String.valueOf(mesa.getCapacidad()));
        actualizarEstado();
    }
    public  void configuracionFicha(){
        fichaMesa.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        Dimension dimension = new Dimension(275, 350);
        fichaMesa.setPreferredSize(dimension);
        fichaMesa.setMaximumSize(dimension);
        fichaMesa.setMinimumSize(dimension);
    }
    public void actualizarEstado(){
        configuracionFicha();
        imagenMesa.removeAll();
        imagenMesa.setLayout(new FlowLayout());
        JLabel contenedorImagen = new JLabel();
        contenedorImagen.setIcon(mesa.getImagenEstadoMesa());
        imagenMesa.add(contenedorImagen);
        estado.setText(mesa.getEstadoActual());
        imagenMesa.revalidate();
        imagenMesa.repaint();
        fichaMesa.revalidate();
        fichaMesa.repaint();
    }

    public JPanel getFichaMesa() {
        return fichaMesa;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
