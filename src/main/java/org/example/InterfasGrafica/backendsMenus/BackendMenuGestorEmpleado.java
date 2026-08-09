package org.example.InterfasGrafica.backendsMenus;

import org.example.EntidadeCafe.Empleado;
import org.example.Herramientas.ControlImagen;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.GestorEmpleados;
import org.example.ManejoBaseDatos.EmpleadoDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDate;

public class BackendMenuGestorEmpleado {

    protected FilaEntidadCafe empleadosBaseDatos;
    protected Fila<Empleado> empleadoFila;
    protected EmpleadoDB empleadoDB;
    protected Connection connection;
    protected JDesktopPane panelPrincipal;
    protected GestorEmpleados gestorEmpleados;

    public BackendMenuGestorEmpleado(Connection connection, JDesktopPane jDesktopPane){
        this.connection = connection;
        panelPrincipal = jDesktopPane;
        empleadosBaseDatos = new FilaEntidadCafe();
        empleadoFila = new Fila<>();
        empleadoDB = new EmpleadoDB(connection, this);
        gestorEmpleados = new GestorEmpleados();
        empleadoDB.agregarEmpleadoBaseDatos();
    }

    //ventana NO.1
    public void controlEmpleados(){
        JInternalFrame internalFrame = new JInternalFrame("Control de Mesas", true, true, true, true);
        internalFrame.setSize(480, 475);
        internalFrame.setLocation(940,10);
        internalFrame.setVisible(true);
        internalFrame.setContentPane(gestorEmpleados.getjScrollPane());

        panelPrincipal.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    //ventana NO.2
    public void agregarNuevoEmpleado(){
        JInternalFrame internalFrame = new JInternalFrame("Agregar Nuevo Empleado", true, true, true, true);
        internalFrame.setSize(1000, 500);
        int x = (panelPrincipal.getWidth()-1000) / 2;
        int y = (panelPrincipal.getHeight()-500) / 2;
        internalFrame.setLocation(x, y);
        internalFrame.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        JLabel tituloDatos = new JLabel("Datos del Nuevo Empleado:");
        tituloDatos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jPanelDatos = new JPanel(new GridLayout(2,7));
        jPanelDatos.add(new JLabel("DPI:"));
        jPanelDatos.add(new JLabel("Nombre:"));
        jPanelDatos.add(new JLabel("Apellido:"));
        jPanelDatos.add(new JLabel("Rol:"));
        jPanelDatos.add(new JLabel("Jornada: "));
        jPanelDatos.add(new JLabel("Salario:"));
        jPanelDatos.add(new JLabel("Fecha Contratado:"));
        JTextField dpi = new JTextField();
        JTextField nombre = new JTextField();
        JTextField apellido = new JTextField();
        String[] roles = {"mesero", "cocina", "barista", "administrador"};
        JComboBox rol = new JComboBox(roles);
        String[] jornadas = {"matutina", "vespertina", "nocturna"};
        JComboBox jornadaLaboral = new JComboBox(jornadas);
        JTextField salario = new JTextField();
        JTextField fechaContratacion = new JTextField();
        jPanelDatos.add(dpi);
        jPanelDatos.add(nombre);
        jPanelDatos.add(apellido);
        jPanelDatos.add(rol);
        jPanelDatos.add(jornadaLaboral);
        jPanelDatos.add(salario);
        jPanelDatos.add(fechaContratacion);

        JLabel imagen = new JLabel(new ControlImagen().devolverImagen("/Imagenes/empleado.png", 220,220));
        imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton jButton = new JButton("Agregar");
        jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dpiTexto = dpi.getText();
                String nombreTexto = nombre.getText();
                String apellidoTexto = apellido.getText();
                String rolTexto = (String) rol.getSelectedItem();
                String jornadaTexto = (String) jornadaLaboral.getSelectedItem();
                double salarioValor = Double.parseDouble(salario.getText());
                LocalDate fecha = LocalDate.parse(fechaContratacion.getText());
                empleadoDB.agregarNuevoEmpleado(dpiTexto, nombreTexto, apellidoTexto, rolTexto, jornadaTexto, salarioValor, fecha);
                internalFrame.dispose();
            }
        });
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(tituloDatos);
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(jPanelDatos);
        jPanel.add(Box.createHorizontalStrut(20));
        jPanel.add(imagen);
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(jButton);
        jPanel.add(Box.createHorizontalStrut(10));

        internalFrame.add(jPanel);
        panelPrincipal.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    //ventana NO.3
    public void eliminarDeshabilitarEmpleado(){
        JInternalFrame jInternalFrame = new JInternalFrame("Eliminar y Desabilitar Empleados", true, true,true,true);
        jInternalFrame.setSize(1300,350);
        int x = (panelPrincipal.getWidth()-1300) / 2;
        int y = (panelPrincipal.getHeight()-400) / 2;
        jInternalFrame.setLocation(x, y);
        jInternalFrame.setVisible(true);

        JScrollPane jScrollPane = new JScrollPane();

        JPanel jpanelEmpleados = new JPanel();
        jpanelEmpleados.setLayout(new BoxLayout(jpanelEmpleados, BoxLayout.Y_AXIS));

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new GridLayout(2,7));
        encabezado.add(new JLabel("Empleados de la cafeteria: "));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel("ID Empleado"));
        encabezado.add(new JLabel("Nombre"));
        encabezado.add(new JLabel("Rol"));
        encabezado.add(new JLabel("Jornada Laboral"));
        encabezado.add(new JLabel("Estado"));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.setPreferredSize(new Dimension(1260,50));
        encabezado.setMaximumSize(new Dimension(1260,50));
        encabezado.setMinimumSize(new Dimension(1260,50));
        jpanelEmpleados.add(encabezado);

        Nodo<Empleado> actual = empleadoFila.getPrimero();
        while(actual != null) {
            Empleado empleado = actual.getDato();

            JPanel filaEmpleado = new JPanel();
            filaEmpleado.setLayout(new GridLayout(1,7));
            filaEmpleado.setPreferredSize(new Dimension(1260,50));
            filaEmpleado.setMaximumSize(new Dimension(1260,50));
            filaEmpleado.setMinimumSize(new Dimension(1260,50));

            JLabel idPersonal = new JLabel(String.valueOf(empleado.getIdentificador()));
            JLabel nombre = new JLabel(empleado.getNombre());
            JLabel rol = new JLabel(empleado.getRolEmpleado());
            JLabel jornada = new JLabel(empleado.getJornadaLaboral());
            JLabel estado = new JLabel(empleado.getEstado());

            int identificador = empleado.getIdentificador();
            JButton botonHabilitarDeshabilitar= new JButton();
            botonHabilitarDeshabilitar.setText("Deshabilitar/Habilitar");
            botonHabilitarDeshabilitar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(empleado.getEstado().equalsIgnoreCase("activo")){
                        cambiarEstadoEmpleado(identificador, "inactivo");
                        empleadoDB.actualizarEstadoEmpleado(identificador,"inactivo");
                    }else {
                        cambiarEstadoEmpleado(identificador, "activo");
                        empleadoDB.actualizarEstadoEmpleado(identificador,"activo");
                    }
                }
            });

            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jpanelEmpleados.remove(filaEmpleado);
                    eliminarEmpleadoDB(identificador);
                    gestorEmpleados.eliminarEmpleado(identificador);
                    empleadoDB.eliminarEmpleado(identificador);
                    jpanelEmpleados.revalidate();
                    jpanelEmpleados.repaint();
                }
            });

            filaEmpleado.add(idPersonal);
            filaEmpleado.add(nombre);
            filaEmpleado.add(rol);
            filaEmpleado.add(jornada);
            filaEmpleado.add(estado);
            filaEmpleado.add(botonHabilitarDeshabilitar);
            filaEmpleado.add(botonEliminar);

            jpanelEmpleados.add(filaEmpleado);

            actual = actual.getSiguiente();
        }

        jScrollPane.setViewportView(jpanelEmpleados);
        jInternalFrame.setContentPane(jScrollPane);
        panelPrincipal.add(jInternalFrame);
        try {
            jInternalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public void agregarEmpleado(Empleado empleado){
        empleadoFila.agregarDato(empleado);
        gestorEmpleados.agregarEmpleado(empleado);
        copiarFilas(empleadoFila, empleadosBaseDatos);
    }

    public void eliminarEmpleadoDB(int identificador){
        int indiceEliminar = empleadosBaseDatos.buscarElementoIndice(identificador);
        empleadoFila.eliminarElemento(indiceEliminar);
        copiarFilas(empleadoFila, empleadosBaseDatos);
    }

    public void cambiarEstadoEmpleado(int identificador, String estado){
        int indiceModificar = empleadosBaseDatos.buscarElementoIndice(identificador);
        if(indiceModificar != 0){
            Empleado empleadoModificar = empleadoFila.buscarElemento(indiceModificar);
            empleadoModificar.setEstado(estado);
            gestorEmpleados.actualizarEmpleado(identificador);
            copiarFilas(empleadoFila, empleadosBaseDatos);
        }
    }

    public void copiarFilas(Fila<Empleado> original, FilaEntidadCafe copia){
        copia.limpiarFila();
        Nodo<Empleado> actual = original.getPrimero();
        while (actual != null){
            copia.agregarDato(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}
