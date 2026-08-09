package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Empleado;
import org.example.Herramientas.ControlImagen;

import javax.swing.*;
import java.awt.*;

public class FichaEmpleado {
    private JPanel fichaEmpleado;
    private JLabel estado;
    private JLabel idEmpleado;
    private JLabel nombre;
    private JLabel rol;
    private JLabel jornadaLaboral;
    private JPanel imagenEmpleado;
    protected Empleado empleado;

    public FichaEmpleado(Empleado empleado){
        this.empleado = empleado;
        idEmpleado.setText(String.valueOf(empleado.getIdentificador()));
        nombre.setText(empleado.getNombre());
        imagenEmpleado.setLayout(new FlowLayout());
        JLabel contenedorImagen = new JLabel();
        contenedorImagen.setIcon(new ControlImagen().devolverImagen(empleado.getDireccionImagen(),125,125));
        imagenEmpleado.add(contenedorImagen);
        actualizarficha();
    }

    public void actualizarficha(){
        configuracionFicha();
        rol.setText(empleado.getRolEmpleado());
        jornadaLaboral.setText(empleado.getJornadaLaboral());
        estado.setText(empleado.getEstado());
        fichaEmpleado.revalidate();
        fichaEmpleado.revalidate();
    }

    public  void configuracionFicha(){
        fichaEmpleado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        Dimension dimension = new Dimension(450, 170);
        fichaEmpleado.setPreferredSize(dimension);
        fichaEmpleado.setMaximumSize(dimension);
        fichaEmpleado.setMinimumSize(dimension);
    }

    public JPanel getFichaEmpleado() {
        return fichaEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }
}
