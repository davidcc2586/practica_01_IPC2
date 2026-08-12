package org.example.InterfasGrafica.backendsMenus;

import org.example.EntidadeCafe.*;
import org.example.EntidadeCafe.Menu;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.FichaMenu;
import org.example.InterfasGrafica.GestorCuenta;
import org.example.ManejoBaseDatos.CuentaDB;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;

public class BackendMenuGestorCuentas {

    protected Connection connection;
    protected JDesktopPane panelPrincipal;
    protected GestorCuenta gestorCuenta;
    protected CuentaDB cuentaDB;
    protected JPanel jPanelCuentasAbiertas;
    protected JPanel jPanelCuentaCerrada;

    public BackendMenuGestorCuentas (Connection connection, JDesktopPane panelPrincipal){
        this.connection = connection;
        this.panelPrincipal = panelPrincipal;
        cuentaDB = new CuentaDB(connection, this);
        gestorCuenta = new GestorCuenta(panelPrincipal, cuentaDB);
    }

    public void crearControlCuentasAbiertas(){
        JInternalFrame jInternalFrame = new JInternalFrame("Cuentas Abiertas", true, true, true, true);
        jInternalFrame.setSize(800,500);
        int x = (panelPrincipal.getWidth()-800)/2;
        int y = (panelPrincipal.getHeight()-500)/2;
        jInternalFrame.setLocation(x,y);

        JPanel panelPrincipalCuentasCerradas = new JPanel();
        panelPrincipalCuentasCerradas.setLayout(new BoxLayout(panelPrincipalCuentasCerradas,BoxLayout.Y_AXIS));

        JPanel cabecera = new JPanel(new GridLayout(1, 3));
        Dimension dimension = new Dimension(440, 30);
        cabecera.setPreferredSize(dimension);
        cabecera.setMaximumSize(dimension);
        cabecera.setMinimumSize(dimension);
        cabecera.add(crearEtiqueta(""));
        cabecera.add(crearEtiqueta("Cuentas Abiertas"));
        cabecera.add(crearEtiqueta(""));

        JButton botonNuevaCuenta = botonCrearNuevaCuenta();
        JPanel finaljpanel = crearFila(5);
        finaljpanel.add(crearEtiqueta(""));
        finaljpanel.add(crearEtiqueta(""));
        finaljpanel.add(crearEtiqueta(""));
        finaljpanel.add(crearEtiqueta(""));
        finaljpanel.add(botonNuevaCuenta);


        panelPrincipalCuentasCerradas.add(cabecera);
        panelPrincipalCuentasCerradas.add(jscrollPanelCuentaAbiertas());
        panelPrincipalCuentasCerradas.add(botonNuevaCuenta);

        jInternalFrame.setContentPane(panelPrincipalCuentasCerradas);
        panelPrincipal.add(jInternalFrame);
        jInternalFrame.setVisible(true);

        try{
            jInternalFrame.setSelected(true);
        }catch(java.beans.PropertyVetoException ex){
            ex.printStackTrace();
        }
    }

    public JButton botonCrearNuevaCuenta(){
        JButton boton = new JButton("Nueva Cuenta");
        boton.addActionListener( e -> {
         JInternalFrame solicitarDattosNuevaCuenta = new JInternalFrame("Datos de la Nueva Cuenta",true,true,true,true);
            solicitarDattosNuevaCuenta.setSize(400,120);
            int x = (panelPrincipal.getWidth()-400)/2;
            int y = (panelPrincipal.getHeight()-90)/2;
            solicitarDattosNuevaCuenta.setLocation(x,y);

            JPanel jPanel = new JPanel();
            jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

            JPanel encabezado = crearFila(1);
            encabezado.add(crearEtiqueta("Datos de la nueva cuenta "));
            JPanel nombreDatos = crearFila(3);
            nombreDatos.add(crearEtiqueta("Mesero"));
            nombreDatos.add(crearEtiqueta("Mesa"));
            nombreDatos.add(crearEtiqueta(""));

            JPanel datosNuevaCuenta = crearFila(3);
            FilaEntidadCafe empleadosFilaUniversal = new FilaEntidadCafe();
            Fila<Empleado> empleadosFila = new Fila<>();
            String[] nombresMeseros = new String[cuentaDB.meseroLibres().getTamañoFila()];
            int i = 0;
            Nodo<Empleado> actualNodoEmpleado = cuentaDB.meseroLibres().getPrimero();
            while(actualNodoEmpleado != null){
                empleadosFilaUniversal.agregarDato(actualNodoEmpleado.getDato());
                empleadosFila.agregarDato(actualNodoEmpleado.getDato());
                nombresMeseros[i] = actualNodoEmpleado.getDato().getNombre();
                i++;
                actualNodoEmpleado = actualNodoEmpleado.getSiguiente();
            }


            FilaEntidadCafe mesasFilaUniversal  = new FilaEntidadCafe();
            Fila<Mesa>  nesasFila = new Fila<>();
            String[] numeroMesas = new String[cuentaDB.mesasLibres().getTamañoFila()];
            int j = 0;
            Nodo<Mesa> actualNodoMesa = cuentaDB.mesasLibres().getPrimero();
            while(actualNodoMesa != null){
                mesasFilaUniversal.agregarDato(actualNodoMesa.getDato());
                nesasFila.agregarDato(actualNodoMesa.getDato());
                numeroMesas[j] = String.valueOf(actualNodoMesa.getDato().getIdentificador());
                j++;
                actualNodoMesa = actualNodoMesa.getSiguiente();
            }


            JComboBox empleadoDisponibles = new JComboBox<>(nombresMeseros);
            JComboBox mesasDisponibles = new JComboBox<>(numeroMesas);
            JButton ingresarNuevaCuenta = new JButton("Ingresar Cuenta");
            ingresarNuevaCuenta.addActionListener(_-> {
                if(empleadoDisponibles.getSelectedItem() != null && mesasDisponibles.getSelectedItem() != null){
                    LocalDate fecha = LocalDate.now();
                    LocalTime hora = LocalTime.now();
                    int indiceEmpleado = empleadosFilaUniversal.buscarElementoIndice(String.valueOf(empleadoDisponibles.getSelectedItem()));
                    Empleado meseroAtiende = empleadosFila.buscarElemento(indiceEmpleado);
                    cuentaDB.agregarNuevaCuenta(meseroAtiende.getIdEmpleado(), String.valueOf(mesasDisponibles.getSelectedItem()),fecha, hora);
                }
                solicitarDattosNuevaCuenta.dispose();
            });

            datosNuevaCuenta.add(empleadoDisponibles);
            datosNuevaCuenta.add(mesasDisponibles);
            datosNuevaCuenta.add(ingresarNuevaCuenta);

            jPanel.add(Box.createHorizontalStrut(10));
            jPanel.add(encabezado);
            jPanel.add(Box.createHorizontalStrut(10));
            jPanel.add(nombreDatos);
            jPanel.add(Box.createHorizontalStrut(10));
            jPanel.add(datosNuevaCuenta);
            jPanel.add(Box.createHorizontalStrut(20));

            solicitarDattosNuevaCuenta.setContentPane(jPanel);
            panelPrincipal.add(solicitarDattosNuevaCuenta);
            solicitarDattosNuevaCuenta.setVisible(true);

            try{
                solicitarDattosNuevaCuenta.setSelected(true);
            }catch(java.beans.PropertyVetoException ex){
                ex.printStackTrace();
            }
        });

        return boton;
    }

    Fila<Cuenta> cuentasAbiertas;
    public JScrollPane jscrollPanelCuentaAbiertas(){
        cuentasAbiertas = new Fila<>();
        JScrollPane jScrollPane = new JScrollPane();
        jPanelCuentasAbiertas = jpanelBase(true);

        copiarFilas(cuentaDB.cuentasAbiertas(), cuentasAbiertas);
        Nodo<Cuenta> actual = cuentasAbiertas.getPrimero();
        while (actual != null){
            Cuenta cuenta = actual.getDato();
            agregarCuentaAbierta(cuenta);
            actual = actual.getSiguiente();
        }

        jScrollPane.setViewportView(jPanelCuentasAbiertas);
        return jScrollPane;
        //gestorCuenta.getjScrollPaneCuentasAbiertas();
    }
    public void agregarCuentaAbierta(Cuenta cuenta){
        JPanel fila = crearFila(7);
        fila.add(crearEtiqueta(String.valueOf(cuenta.getIdCuenta())));
        fila.add(crearEtiqueta(cuenta.getNombreMesero()));
        fila.add(crearEtiqueta(String.valueOf(cuenta.getIdmesa())));
        fila.add(crearEtiqueta(String.valueOf(cuenta.getFecha())));
        fila.add(crearEtiqueta(String.valueOf(cuenta.getHoraEntrada())));
        fila.add(crearEtiqueta(cuenta.getEstado()));
        JButton botonDetallesCuenta = botonDetallesCuenta(cuenta);
        JButton botonPagarCuenta = botonPagarCuenta();
        fila.add(botonDetallesCuenta);
        fila.add(botonPagarCuenta);
        jPanelCuentasAbiertas.add(fila);
    }

    public JButton botonDetallesCuenta(Cuenta cuenta){
        JButton boton = new JButton("Detalles");
        boton.addActionListener(e -> {

            JInternalFrame ventana = new JInternalFrame("Detalles de la Cuenta", true, true, true, true);
            ventana.setSize(720, 425);
            int x = (panelPrincipal.getWidth() - 720) / 2;
            int y = (panelPrincipal.getHeight() - 425) / 2;
            ventana.setLocation(x, y);

            JPanel panelPrincipalDetalles = new JPanel();
            panelPrincipalDetalles.setLayout(new BoxLayout(panelPrincipalDetalles, BoxLayout.Y_AXIS));


            JPanel datosCuentaTitulo = crearFila(7);
            datosCuentaTitulo.add(crearEtiqueta("ID Cuenta:"));
            datosCuentaTitulo.add(crearEtiqueta("Mesero:"));
            datosCuentaTitulo.add(crearEtiqueta("ID Mesero:"));
            datosCuentaTitulo.add(crearEtiqueta("Mesa:"));
            datosCuentaTitulo.add(crearEtiqueta("Fecha:"));
            datosCuentaTitulo.add(crearEtiqueta("Hora de entrada:"));
            datosCuentaTitulo.add(crearEtiqueta("Estado:"));
            JPanel datosCuenta = crearFila(7);
            datosCuenta.add(crearEtiqueta(String.valueOf(cuenta.getIdCuenta())));
            datosCuenta.add(crearEtiqueta(cuenta.getNombreMesero()));
            datosCuenta.add(crearEtiqueta(String.valueOf(cuenta.getIdMesero())));
            datosCuenta.add(crearEtiqueta(String.valueOf(cuenta.getIdmesa())));
            datosCuenta.add(crearEtiqueta(cuenta.getFecha().toString()));
            datosCuenta.add(crearEtiqueta(cuenta.getHoraEntrada().toString()));
            datosCuenta.add(crearEtiqueta(cuenta.getEstado()));


            JPanel panelMenus = new JPanel();
            panelMenus.setLayout(new BoxLayout(panelMenus, BoxLayout.Y_AXIS));

            Nodo<Menu> actual = cuentaDB.menusSolicitadosCuenta(cuenta.getIdCuenta()).getPrimero();
            while (actual != null) {
                FichaMenu ficha = new FichaMenu(actual.getDato());
                panelMenus.add(ficha.getFichaMenu());
                actual = actual.getSiguiente();
            }

            JScrollPane scrollMenus = new JScrollPane(panelMenus);
            scrollMenus.setPreferredSize(new Dimension(550, 200));

            FilaEntidadCafe menus = new FilaEntidadCafe();
            Fila<Menu> menusDisponibles = new Fila<>();
            String[] nombresMenus = new String[cuentaDB.menusExistencia().getTamañoFila()];
            int i = 0;
            Nodo<Menu> nodoMenu = cuentaDB.menusExistencia().getPrimero();
            while (nodoMenu != null) {

                Menu menu = nodoMenu.getDato();
                menus.agregarDato(menu);
                menusDisponibles.agregarDato(menu);
                nombresMenus[i] = menu.getNombre();
                i++;
                nodoMenu = nodoMenu.getSiguiente();
            }
            JComboBox<String> comboMenus = new JComboBox<>(nombresMenus);
            JButton botonAgregar = new JButton("Agregar");
            botonAgregar.addActionListener(_ -> {

                String nombre = (String) comboMenus.getSelectedItem();
                if (nombre == null || nombre.isBlank()) {
                    return;
                }

                int indice = menus.buscarElementoIndice(nombre);
                Menu menu = menusDisponibles.buscarElemento(indice);
                if (menu == null) {
                    return;
                }
                cuentaDB.agregarDetallesCuenta(cuenta.getIdCuenta(),menu.getIdMenu());
                panelMenus.add(new FichaMenu(menu).getFichaMenu());
                panelMenus.revalidate();
                panelMenus.repaint();
            });


            JPanel filaAgregar = new JPanel(new GridLayout(1, 3, 10, 0));

            filaAgregar.add(comboMenus);
            filaAgregar.add(botonAgregar);


            panelPrincipalDetalles.add(Box.createVerticalStrut(10));
            panelPrincipalDetalles.add(crearEtiqueta("Información de la cuenta"));
            panelPrincipalDetalles.add(Box.createVerticalStrut(10));
            panelPrincipalDetalles.add(datosCuentaTitulo);
            panelPrincipalDetalles.add(Box.createVerticalStrut(10));
            panelPrincipalDetalles.add(datosCuenta);
            panelPrincipalDetalles.add(Box.createVerticalStrut(20));
            panelPrincipalDetalles.add(crearEtiqueta("Menús solicitados:"));
            panelPrincipalDetalles.add(Box.createVerticalStrut(10));
            panelPrincipalDetalles.add(scrollMenus);
            panelPrincipalDetalles.add(Box.createVerticalStrut(10));
            panelPrincipalDetalles.add(filaAgregar);
            panelPrincipalDetalles.add(Box.createVerticalStrut(10));
            ventana.setContentPane(panelPrincipalDetalles);

            panelPrincipal.add(ventana);
            ventana.setVisible(true);

            try {
                ventana.setSelected(true);
            } catch (PropertyVetoException ex) {
                ex.printStackTrace();
            }
        });

        return boton;
    }

    public JButton botonPagarCuenta(){
        JButton jButton = new JButton("Cerrar");

        return jButton;
    }

    public void copiarFilas(Fila<Cuenta> filaDB, Fila<Cuenta> filaSistema){
        Nodo<Cuenta> actual = filaDB.getPrimero();
        while (actual != null){
            filaSistema.agregarDato(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public void crearControlCuentasCerradas(){

    }


    public JPanel jpanelBase(boolean abierta){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JPanel encabezado = crearFila(7);
        encabezado.add(crearEtiqueta("ID cuenta"));
        encabezado.add(crearEtiqueta("Nombre Mesero"));
        encabezado.add(crearEtiqueta("NO. Mesa"));
        encabezado.add(crearEtiqueta("Fecha"));
        encabezado.add(crearEtiqueta("Hora Ingreso"));
        if (abierta){
            encabezado.add(crearEtiqueta("Estado"));
            encabezado.add(crearEtiqueta(""));
            encabezado.add(crearEtiqueta(""));
        } else {
            encabezado.add(crearEtiqueta("Hora Salida"));
            encabezado.add(crearEtiqueta("Estado"));
            encabezado.add(crearEtiqueta(""));
        }

        jPanel.add(encabezado);
        return jPanel;
    }

    public JPanel crearFila(int columnas) {

        JPanel fila = new JPanel(new GridLayout(1, columnas));
        Dimension dimension = new Dimension(785, 30);
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

}
