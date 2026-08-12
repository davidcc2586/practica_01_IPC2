package org.example.InterfasGrafica.backendsMenus;

import org.example.EntidadeCafe.Empleado;
import org.example.EntidadeCafe.Nomina;
import org.example.Herramientas.Fila;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.GestorNomina;
import org.example.ManejoBaseDatos.NominaDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.DateTimeException;
import java.time.LocalDate;

public class BackendMenuGestionNominas {

    protected Fila<Nomina> nominasFila;
    protected GestorNomina gestorNomina;
    protected NominaDB nominaDB;
    protected Connection connection;
    protected JDesktopPane panelPrincipal;
    private String fechaActual;

    public BackendMenuGestionNominas(Connection connection, JDesktopPane panelPrincipal){
        this.connection = connection;
        this.panelPrincipal = panelPrincipal;
        nominasFila = new Fila<>();
        gestorNomina = new GestorNomina();
        nominaDB = new NominaDB(connection, this);
        nominaDB.agregarNominasBaseDatos();
    }

    //Ventana NO.1
    public void controlNomina(){
        fechaActual = "2026-01-01";

        JInternalFrame internalFrame = new JInternalFrame("Control de Nomina", true, true, true, true);
        internalFrame.setSize(480, 475);
        internalFrame.setLocation(1430,535);
        internalFrame.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JPanel fechaPanel = new JPanel();
        fechaPanel.setLayout(new BoxLayout(fechaPanel, BoxLayout.X_AXIS));
        fechaPanel.add(new JLabel("Fecha: "));
        JLabel fecha = new JLabel(fechaActual);
        fechaPanel.add(fecha);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));

        JButton botonProcesar = new JButton("Procesar");
        botonProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int respuesta = JOptionPane.showConfirmDialog(panelPrincipal,"¿Está seguro de pagar todas las nominas pendientes?","Confirmar pago",JOptionPane.YES_NO_OPTION);

                if(respuesta == JOptionPane.YES_OPTION){

                    Nodo<Nomina> actual = nominasFila.getPrimero();

                    while(actual != null){

                        if(actual.getDato().getEstado().equalsIgnoreCase("pendiente")){
                            nominaDB.pagar(actual.getDato().getIdEmpleado(),actual.getDato().getTipo());
                            nominaDB.actualizarEstadoNomina(actual.getDato().getIdNomina());
                            actual.getDato().setEstado("pagado");
                        }

                        actual = actual.getSiguiente();
                    }
                    gestorNomina.volverListarNominas(nominasFila);
                    JOptionPane.showMessageDialog(panelPrincipal,"Todas las nominas pendientes fueron pagadas.");

                }
            }
        });

        JButton botonActualizar = new JButton("Actualizar Fecha");
        botonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame solicitarNuevaFecha = new JInternalFrame("Actualizar Fecha", true,true,true,true);
                solicitarNuevaFecha.setSize(300, 150);
                int x = (panelPrincipal.getWidth()-300) / 2;
                int y = (panelPrincipal.getHeight()-150) / 2;
                solicitarNuevaFecha.setLocation(x,y);
                solicitarNuevaFecha.setVisible(true);

                JPanel contenedorBotenes = new JPanel();
                contenedorBotenes.setLayout(new BoxLayout(contenedorBotenes, BoxLayout.Y_AXIS));

                JLabel encabezado = new JLabel("Ingrese la Fecha: ");
                encabezado.setAlignmentX(Component.CENTER_ALIGNMENT);

                JTextField nuevaFechaTexto = new JTextField();
                Dimension dimension = new Dimension(250, 45);
                nuevaFechaTexto.setPreferredSize(dimension);
                nuevaFechaTexto.setMaximumSize(dimension);
                nuevaFechaTexto.setMinimumSize(dimension);

                JButton botonListo = new JButton("Listo");
                botonListo.setAlignmentX(Component.CENTER_ALIGNMENT);

                botonListo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        fechaActual = nuevaFechaTexto.getText();
                        try {
                            LocalDate fechaIngresada = LocalDate.parse(fechaActual);
                            fecha.setText(fechaActual);
                            int dia = fechaIngresada.getDayOfMonth();
                            if(dia == 1){
                                Nodo<Empleado> actual = nominaDB.empleadosActivos().getPrimero();
                                while(actual != null){
                                    nominaDB.agregarNuevaNomina(actual.getDato().getIdEmpleado(), "quincena", fechaActual);
                                    actual = actual.getSiguiente();
                                }
                            } else if(dia == 10){
                                Nodo<Nomina> actual = nominasFila.getPrimero();
                                while(actual != null){
                                    if(actual.getDato().getEstado().equalsIgnoreCase("pendiente") && actual.getDato().getTipo().equalsIgnoreCase("quincena")){
                                        nominaDB.pagar(actual.getDato().getIdEmpleado(), actual.getDato().getTipo());
                                        nominaDB.actualizarEstadoNomina(actual.getDato().getIdNomina());
                                        actual.getDato().setEstado("pagado");
                                    }
                                    actual = actual.getSiguiente();
                                }
                                gestorNomina.volverListarNominas(nominasFila);
                            } else if(dia == 16){
                                Nodo<Empleado> actual = nominaDB.empleadosActivos().getPrimero();
                                while(actual != null){
                                    nominaDB.agregarNuevaNomina(actual.getDato().getIdEmpleado(), "fin de mes", fechaActual);
                                    actual = actual.getSiguiente();
                                }

                            } else if(dia == 25){

                                Nodo<Nomina> actual = nominasFila.getPrimero();
                                while(actual != null){
                                    if(actual.getDato().getEstado().equalsIgnoreCase("pendiente") && actual.getDato().getTipo().equalsIgnoreCase("fin de mes")){
                                        nominaDB.pagar(actual.getDato().getIdEmpleado(), actual.getDato().getTipo());
                                        nominaDB.actualizarEstadoNomina(actual.getDato().getIdNomina());
                                        actual.getDato().setEstado("pagado");
                                    }
                                    actual = actual.getSiguiente();
                                }
                                gestorNomina.volverListarNominas(nominasFila);
                            }

                            solicitarNuevaFecha.dispose();

                        } catch(DateTimeException ex) {
                            JOptionPane.showMessageDialog(solicitarNuevaFecha, "La fecha ingresada no es valida. Use el formato YYYY-MM-DD");
                        }
                    }
                });

                contenedorBotenes.add(Box.createVerticalStrut(10));
                contenedorBotenes.add(encabezado);
                contenedorBotenes.add(Box.createVerticalStrut(10));
                contenedorBotenes.add(nuevaFechaTexto);
                contenedorBotenes.add(Box.createVerticalStrut(10));
                contenedorBotenes.add(botonListo);
                contenedorBotenes.add(Box.createVerticalStrut(10));

                solicitarNuevaFecha.add(contenedorBotenes);

                try{
                    internalFrame.setSelected(true);
                }catch(java.beans.PropertyVetoException ex){
                    ex.printStackTrace();
                }
                panelPrincipal.add(solicitarNuevaFecha);
            }
        });

        botonesPanel.add(botonProcesar);
        botonesPanel.add(new JLabel(""));
        botonesPanel.add(new JLabel(""));
        botonesPanel.add(new JLabel(""));
        botonesPanel.add(botonActualizar);

        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(fechaPanel);
        jPanel.add(Box.createVerticalStrut(20));
        jPanel.add(gestorNomina.getjScrollPane());
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(botonesPanel);
        jPanel.add(Box.createVerticalStrut(10));

        internalFrame.setContentPane(jPanel);
        panelPrincipal.add(internalFrame);
    }

    public void agregarNomina(Nomina nomina){
        nominasFila.agregarDato(nomina);
        gestorNomina.agregarNomina(nomina);
    }
}