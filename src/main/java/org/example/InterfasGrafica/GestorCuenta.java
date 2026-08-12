package org.example.InterfasGrafica;

import org.example.EntidadeCafe.Cuenta;
import org.example.EntidadeCafe.Menu;

import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;
import org.example.ManejoBaseDatos.CuentaDB;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;

public class GestorCuenta {

    protected JScrollPane jScrollPaneCuentasAbiertas;
    protected JScrollPane jScrollPaneCuentasCerrada;
    protected JDesktopPane panelPrincipal;
    protected CuentaDB cuentaDB;

    public GestorCuenta(JDesktopPane panelPrincipal, CuentaDB cuentaDB){
        this.panelPrincipal = panelPrincipal;
        this.cuentaDB = cuentaDB;
        jScrollPaneCuentasAbiertas = new JScrollPane();
        jScrollPaneCuentasCerrada = new JScrollPane();
        scrollCuentasAbiertas();
        scrollCuentasCerradas();
    }

    public void scrollCuentasAbiertas(){
        JPanel jPanelCuentasAbiertas = jpanelBase(true);
        jScrollPaneCuentasAbiertas.setViewportView(jPanelCuentasAbiertas);
    }

    public void agregarCuentaAbierta(Cuenta cuenta){
        JPanel fila = crearFila();
        fila.add(crearEtiqueta(String.valueOf(cuenta.getIdCuenta())));
        fila.add(crearEtiqueta(cuenta.getNombreMesero()));
        fila.add(crearEtiqueta(String.valueOf(cuenta.getIdmesa())));
        fila.add(crearEtiqueta(String.valueOf(cuenta.getFecha())));
        fila.add(crearEtiqueta(String.valueOf(cuenta.getHoraEntrada())));
        fila.add(crearEtiqueta(cuenta.getEstado()));
        JButton botonDetallesCuenta = new JButton();
        JButton botonPagarcuenta = new JButton();

    }

    public JButton botonDetallesCuenta(Cuenta cuenta) {

        JButton boton = new JButton("Detalles");

        boton.addActionListener(e -> {

            JInternalFrame ventana =
                    new JInternalFrame(
                            "Detalles de la Cuenta #" + cuenta.getIdCuenta(),
                            true,
                            true,
                            true,
                            true
                    );

            ventana.setSize(600, 600);

            int x = (panelPrincipal.getWidth() - ventana.getWidth()) / 2;
            int y = (panelPrincipal.getHeight() - ventana.getHeight()) / 2;

            ventana.setLocation(x, y);

            JPanel panelPrincipalDetalles = new JPanel();
            panelPrincipalDetalles.setLayout(
                    new BoxLayout(panelPrincipalDetalles, BoxLayout.Y_AXIS)
            );


            // ==========================
            // DATOS DE LA CUENTA
            // ==========================

            JPanel datosCuenta = new JPanel(new GridLayout(0, 2, 10, 10));

            datosCuenta.add(crearEtiqueta("ID Cuenta:"));
            datosCuenta.add(crearEtiqueta(
                    String.valueOf(cuenta.getIdCuenta())
            ));

            datosCuenta.add(crearEtiqueta("Mesero:"));
            datosCuenta.add(crearEtiqueta(
                    cuenta.getNombreMesero()
            ));

            datosCuenta.add(crearEtiqueta("ID Mesero:"));
            datosCuenta.add(crearEtiqueta(
                    String.valueOf(cuenta.getIdMesero())
            ));

            datosCuenta.add(crearEtiqueta("Mesa:"));
            datosCuenta.add(crearEtiqueta(
                    String.valueOf(cuenta.getIdmesa())
            ));

            datosCuenta.add(crearEtiqueta("Fecha:"));
            datosCuenta.add(crearEtiqueta(
                    cuenta.getFecha().toString()
            ));

            datosCuenta.add(crearEtiqueta("Hora de entrada:"));
            datosCuenta.add(crearEtiqueta(
                    cuenta.getHoraEntrada().toString()
            ));

            datosCuenta.add(crearEtiqueta("Estado:"));
            datosCuenta.add(crearEtiqueta(
                    cuenta.getEstado()
            ));


            // ==========================
            // MENUS DE LA CUENTA
            // ==========================

            JPanel panelMenus = new JPanel();
            panelMenus.setLayout(
                    new BoxLayout(panelMenus, BoxLayout.Y_AXIS)
            );

            Nodo<Menu> actual =
                    cuentaDB.menusSolicitadosCuenta(
                            cuenta.getIdCuenta()
                    ).getPrimero();

            while (actual != null) {

                FichaMenu ficha =
                        new FichaMenu(actual.getDato());

                panelMenus.add(ficha.getFichaMenu());

                actual = actual.getSiguiente();
            }


            JScrollPane scrollMenus =
                    new JScrollPane(panelMenus);

            scrollMenus.setPreferredSize(
                    new Dimension(550, 250)
            );


            // ==========================
            // AGREGAR MÁS MENUS
            // ==========================

            FilaEntidadCafe menus = new FilaEntidadCafe();
            Fila<Menu> menusDisponibles = new Fila<>();

            String[] nombresMenus =
                    new String[cuentaDB.menusExistencia().getTamañoFila()];

            int i = 0;

            Nodo<Menu> nodoMenu =
                    cuentaDB.menusExistencia().getPrimero();

            while (nodoMenu != null) {

                Menu menu = nodoMenu.getDato();

                menus.agregarDato(menu);
                menusDisponibles.agregarDato(menu);

                nombresMenus[i] = menu.getNombre();

                i++;
                nodoMenu = nodoMenu.getSiguiente();
            }


            JComboBox<String> comboMenus =
                    new JComboBox<>(nombresMenus);

            JButton botonAgregar =
                    new JButton("Agregar");


            botonAgregar.addActionListener(_ -> {

                String nombre =
                        (String) comboMenus.getSelectedItem();

                if (nombre == null || nombre.isBlank()) {
                    return;
                }

                int indice =
                        menus.buscarElementoIndice(nombre);

                if (indice == -1) {
                    return;
                }

                Menu menu =
                        menusDisponibles.buscarElemento(indice);

                if (menu == null) {
                    return;
                }

                panelMenus.add(
                        new FichaMenu(menu).getFichaMenu()
                );

                panelMenus.revalidate();
                panelMenus.repaint();
            });


            JPanel filaAgregar =
                    new JPanel(new GridLayout(1, 3, 10, 0));

            filaAgregar.add(comboMenus);
            filaAgregar.add(botonAgregar);


            // ==========================
            // ARMAR VENTANA
            // ==========================

            panelPrincipalDetalles.add(
                    Box.createVerticalStrut(10)
            );

            panelPrincipalDetalles.add(
                    crearEtiqueta("Información de la cuenta")
            );

            panelPrincipalDetalles.add(
                    Box.createVerticalStrut(10)
            );

            panelPrincipalDetalles.add(datosCuenta);

            panelPrincipalDetalles.add(
                    Box.createVerticalStrut(20)
            );

            panelPrincipalDetalles.add(
                    crearEtiqueta("Menús solicitados:")
            );

            panelPrincipalDetalles.add(
                    Box.createVerticalStrut(10)
            );

            panelPrincipalDetalles.add(scrollMenus);

            panelPrincipalDetalles.add(
                    Box.createVerticalStrut(10)
            );

            panelPrincipalDetalles.add(filaAgregar);

            panelPrincipalDetalles.add(
                    Box.createVerticalStrut(10)
            );


            ventana.setContentPane(
                    panelPrincipalDetalles
            );

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









    public void scrollCuentasCerradas(){
        JPanel jPanelCuentasCerradas = jpanelBase(false);
        jScrollPaneCuentasCerrada.setViewportView(jPanelCuentasCerradas);
    }

    public JPanel jpanelBase(boolean abierta){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JPanel encabezado = crearFila();
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


    public JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        return etiqueta;
    }

    public JPanel crearFila() {

        JPanel fila = new JPanel(new GridLayout(1, 7));
        Dimension dimension = new Dimension(785, 30);
        fila.setPreferredSize(dimension);
        fila.setMaximumSize(dimension);
        fila.setMinimumSize(dimension);

        return fila;
    }

    public JScrollPane getjScrollPaneCuentasAbiertas() {
        return jScrollPaneCuentasAbiertas;
    }

    public JScrollPane getjScrollPaneCuentasCerrada() {
        return jScrollPaneCuentasCerrada;
    }
}
