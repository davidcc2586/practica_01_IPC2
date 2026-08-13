package org.example.InterfasGrafica.backendsMenus;

import org.example.EntidadeCafe.RankingMenu;
import org.example.EntidadeCafe.Transaccion;
import org.example.Herramientas.Fila;
import org.example.Herramientas.ManejoArchivosHTML;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.FichaMenu;
import org.example.InterfasGrafica.FichaTransaccion;
import org.example.ManejoBaseDatos.ReportesDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BackenndMenuReportes {

    protected Connection connection;
    protected JDesktopPane panelPrincipal;
    protected ReportesDB reportesDB;
    protected JPanel contenedorTransacciones;
    protected JTextField txtFechaInicio;
    protected JTextField txtFechaFin;
    protected JComboBox<String> comboTipo;
    protected JLabel lblTotalIngresos;
    protected JLabel lblTotalEgresos;
    protected JLabel lblFlujoNeto;

    Fila<RankingMenu> rankingMenus = new Fila<>();

    public BackenndMenuReportes(Connection connection, JDesktopPane panelPrincipal) {
        this.connection = connection;
        this.panelPrincipal = panelPrincipal;
        reportesDB = new ReportesDB(connection, this);
    }

    public void crearReporteFlujoCaja() {
        JInternalFrame jInternalFrame = new JInternalFrame("Flujo de caja", true, true, true, true);
        jInternalFrame.setSize(900, 600);
        int x = (panelPrincipal.getWidth() - 900) / 2;
        int y = (panelPrincipal.getHeight() - 600) / 2;
        jInternalFrame.setLocation(x, y);

        JPanel jPanelPrincipal = new JPanel(new BorderLayout(10, 10));

        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filtros.add(new JLabel("Desde:"));
        txtFechaInicio = new JTextField(10);
        txtFechaInicio.setText(LocalDate.now().withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        filtros.add(txtFechaInicio);
        filtros.add(new JLabel("Hasta:"));
        txtFechaFin = new JTextField(10);
        txtFechaFin.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        filtros.add(txtFechaFin);
        filtros.add(new JLabel("Tipo:"));
        String[] tipoOrden = {"Todos", "Ingreso", "Egreso"};
        comboTipo = new JComboBox<>(tipoOrden);
        filtros.add(comboTipo);
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(e -> filtrarTransacciones());
        filtros.add(btnFiltrar);
        JButton escribirHTML = botonHTML();

        filtros.add(escribirHTML);
        jPanelPrincipal.add(filtros, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        JPanel encabezado = new JPanel(new GridLayout(1, 5));
        Dimension dimension = new Dimension(850, 35);
        encabezado.setPreferredSize(dimension);
        encabezado.setMaximumSize(dimension);
        encabezado.setMinimumSize(dimension);

        encabezado.add(crearEtiqueta("ID"));
        encabezado.add(crearEtiqueta("Fecha"));
        encabezado.add(crearEtiqueta("Motivo"));
        encabezado.add(crearEtiqueta("Tipo"));
        encabezado.add(crearEtiqueta("Monto"));

        centro.add(encabezado);

        contenedorTransacciones = new JPanel();
        contenedorTransacciones.setLayout(new BoxLayout(contenedorTransacciones, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(contenedorTransacciones);
        centro.add(scrollPane);
        jPanelPrincipal.add(centro, BorderLayout.CENTER);

        JPanel panelTotales = new JPanel(new GridLayout(1, 3));
        lblTotalIngresos = new JLabel("Ingresos: Q 0.00");
        lblTotalEgresos = new JLabel("Egresos: Q 0.00");
        lblFlujoNeto = new JLabel("Flujo neto: Q 0.00");
        lblTotalIngresos.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalEgresos.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlujoNeto.setHorizontalAlignment(SwingConstants.CENTER);

        panelTotales.add(lblTotalIngresos);
        panelTotales.add(lblTotalEgresos);
        panelTotales.add(lblFlujoNeto);

        jPanelPrincipal.add(panelTotales, BorderLayout.SOUTH);

        jInternalFrame.add(jPanelPrincipal);
        panelPrincipal.add(jInternalFrame);
        jInternalFrame.setVisible(true);
        filtrarTransacciones();
    }

    public void filtrarTransacciones() {

        try {

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText(), formato);
            LocalDate fechaFin = LocalDate.parse(txtFechaFin.getText(), formato);

            if (fechaFin.isBefore(fechaInicio)) {
                JOptionPane.showMessageDialog(panelPrincipal, "La fecha final no puede ser anterior a la fecha inicial.", "Fechas incorrectas", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Fila<Transaccion> transacciones = reportesDB.obtenerTransacciones(fechaInicio, fechaFin, (String) comboTipo.getSelectedItem());
            mostrarTransacciones(transacciones);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelPrincipal, "Formato de fecha incorrecto.\nUtilize: yyyy-MM-dd\nEjemplo: 2026-08-12", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarTransacciones(Fila<Transaccion> transacciones) {
        contenedorTransacciones.removeAll();
        double ingresos = 0;
        double egresos = 0;

        Nodo<Transaccion> actual = transacciones.getPrimero();
        while (actual != null) {
            Transaccion transaccion = actual.getDato();
            FichaTransaccion ficha = new FichaTransaccion(transaccion);
            contenedorTransacciones.add(ficha.getjPanel());

            if (transaccion.getTipo().equalsIgnoreCase("ingreso")) {
                ingresos = ingresos + transaccion.getMonto();
            } else if (transaccion.getTipo().equalsIgnoreCase("egreso")) {
                egresos = egresos + transaccion.getMonto();
            }

            actual = actual.getSiguiente();
        }

        lblTotalIngresos.setText(String.format("Ingresos: Q %.2f", ingresos));
        lblTotalEgresos.setText(String.format("Egresos: Q %.2f", egresos));
        lblFlujoNeto.setText(String.format("Flujo neto: Q %.2f", ingresos - egresos));
        contenedorTransacciones.revalidate();
        contenedorTransacciones.repaint();
    }

    public JButton botonHTML(){
        JButton boton = new JButton("Exportar a HTML");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText(), formato);
                LocalDate fechaFin = LocalDate.parse(txtFechaFin.getText(), formato);
                Fila<Transaccion> transacciones = reportesDB.obtenerTransacciones(fechaInicio, fechaFin, (String) comboTipo.getSelectedItem());
                ManejoArchivosHTML manejoArchivosHTML = new ManejoArchivosHTML();
                manejoArchivosHTML.escribirHTMLTransacciones(transacciones);
            }
        });
        return boton;
    }

    public void crearRankingMenus() {
        JInternalFrame jInternalFrame = new JInternalFrame("Ranking de Menús Más Vendidos", true, true, true, true);
        jInternalFrame.setSize(900, 600);
        int x = (panelPrincipal.getWidth() - 900) / 2;
        int y = (panelPrincipal.getHeight() - 600) / 2;
        jInternalFrame.setLocation(x, y);

        JPanel jPanelPrincipal = new JPanel(new BorderLayout(10, 10));

        JPanel encabezado = new JPanel(new GridLayout(1, 5));
        encabezado.add(crearEtiqueta("Posición"));
        encabezado.add(crearEtiqueta("ID"));
        encabezado.add(crearEtiqueta("Menú"));
        encabezado.add(crearEtiqueta("Categoría"));
        encabezado.add(crearEtiqueta("Vendidos"));

        JPanel contenedorRanking = new JPanel();
        contenedorRanking.setLayout(new BoxLayout(contenedorRanking, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(contenedorRanking);

        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.add(encabezado, BorderLayout.CENTER);
        parteSuperior.add(crearBotonExportarRanking(), BorderLayout.EAST);

        jPanelPrincipal.add(parteSuperior, BorderLayout.NORTH);
        jPanelPrincipal.add(scrollPane, BorderLayout.CENTER);

        jInternalFrame.add(jPanelPrincipal);
        panelPrincipal.add(jInternalFrame);
        jInternalFrame.setVisible(true);

        rankingMenus = reportesDB.obtenerRankingMenus();
        mostrarRankingMenus(rankingMenus, contenedorRanking);
    }

    public void mostrarRankingMenus(Fila<RankingMenu> rankingMenus, JPanel contenedorRanking) {
        contenedorRanking.removeAll();

        Nodo<RankingMenu> actual = rankingMenus.getPrimero();
        int posicion = 1;

        while (actual != null) {
            RankingMenu rankingMenu = actual.getDato();

            JPanel panel = new JPanel(new GridLayout(1, 6));
            panel.setPreferredSize(new Dimension(850, 60));
            panel.setMaximumSize(new Dimension(850, 60));
            panel.setMinimumSize(new Dimension(850, 60));

            panel.add(crearEtiqueta(String.valueOf(posicion)));
            panel.add(crearEtiqueta(String.valueOf(rankingMenu.getMenu().getIdMenu())));
            panel.add(crearEtiqueta(rankingMenu.getMenu().getNombre()));
            panel.add(crearEtiqueta(rankingMenu.getMenu().getCategoria()));
            panel.add(crearEtiqueta(String.valueOf(rankingMenu.getCantidadVendida())));
            panel.add(crearEtiqueta(""));

            contenedorRanking.add(panel);

            posicion++;
            actual = actual.getSiguiente();
        }

        contenedorRanking.revalidate();
        contenedorRanking.repaint();
    }

    public JButton crearBotonExportarRanking() {
        JButton boton = new JButton("Exportar a HTML");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManejoArchivosHTML manejoArchivosHTML = new ManejoArchivosHTML();
                manejoArchivosHTML.escribirHTMLRanking(rankingMenus);
            }
        });
        return boton;
    }

    public JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        return etiqueta;
    }

}