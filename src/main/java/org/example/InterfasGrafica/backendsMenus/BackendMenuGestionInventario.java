package org.example.InterfasGrafica.backendsMenus;

import org.example.EntidadeCafe.Producto;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.GestorInventario;
import org.example.ManejoBaseDatos.InventarioDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

public class BackendMenuGestionInventario {

    protected FilaEntidadCafe productosBaseDatos;
    protected Fila<Producto> productosFila;
    protected InventarioDB inventarioDB;
    protected Connection connection;
    protected JDesktopPane panelPrincipal;
    protected GestorInventario gestorInventario;
    protected String direccionImagen;

    public BackendMenuGestionInventario(Connection connection, JDesktopPane jDesktopPane){
        this.connection = connection;
        panelPrincipal = jDesktopPane;
        productosBaseDatos = new FilaEntidadCafe();
        productosFila = new Fila<>();
        inventarioDB = new InventarioDB(connection, this);
        gestorInventario = new GestorInventario();
        inventarioDB.agreagarProductosBaseDatos();
    }

    //ventana NO.1
    public void controlInventario(){
        JInternalFrame internalFrame = new JInternalFrame("Control de Inventario", true, true, true, true);
        internalFrame.setSize(480, 475);
        internalFrame.setLocation(1430,10);
        internalFrame.setVisible(true);
        internalFrame.setContentPane(gestorInventario.getjScrollPane());

        panelPrincipal.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    //ventana NO.2
    public void ingresarProducto(){
        JInternalFrame jInternalFrame = new JInternalFrame("Registrar Ingreso de Producto", true, true,true,true);
        jInternalFrame.setSize(620,400);
        int x = (panelPrincipal.getWidth()-620) / 2;
        int y = (panelPrincipal.getHeight()-400) / 2;
        jInternalFrame.setLocation(x, y);
        jInternalFrame.setVisible(true);

        JScrollPane jScrollPane = new JScrollPane();

        JPanel jpanelInsumos = new JPanel();
        jpanelInsumos.setLayout(new BoxLayout(jpanelInsumos, BoxLayout.Y_AXIS));

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new GridLayout(2,5));
        encabezado.add(new JLabel("Ingreso de insumos: "));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel("ID"));
        encabezado.add(new JLabel("nombre"));
        encabezado.add(new JLabel("Cantidad en Stock"));
        encabezado.add(new JLabel("Añadir Cantidad"));
        encabezado.add(new JLabel(""));
        encabezado.setPreferredSize(new Dimension(580,50));
        encabezado.setMaximumSize(new Dimension(580,50));
        encabezado.setMinimumSize(new Dimension(580,50));
        jpanelInsumos.add(encabezado);

        Nodo<Producto> actual = productosFila.getPrimero();
        while(actual != null){
            Producto producto = actual.getDato();

            JPanel filaProducto = new JPanel();
            filaProducto.setLayout(new GridLayout(1,5));
            filaProducto.setPreferredSize(new Dimension(580,50));
            filaProducto.setMaximumSize(new Dimension(580,50));
            filaProducto.setMinimumSize(new Dimension(580,50));

            JLabel id = new JLabel(String.valueOf(producto.getIdentificador()));
            JLabel nombre = new JLabel(producto.getNombreInsumo());
            JLabel cantidadStock = new JLabel(String.valueOf(producto.getCantidadProducto()));

            JTextField jTextField = new JTextField();

            JButton botonEliminar = new JButton("Añadir");
            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int identificador = producto.getIdentificador();
                    gestorInventario.actualizarProducto(identificador,Integer.parseInt(jTextField.getText()));
                    inventarioDB.ingresarCantidaInsumos(identificador,producto.getCantidadProducto(),Integer.parseInt(jTextField.getText()));

                    cantidadStock.setText(String.valueOf(producto.getCantidadProducto()));
                    jTextField.setText("");
                    jpanelInsumos.revalidate();
                    jpanelInsumos.repaint();
                }
            });

            filaProducto.add(id);
            filaProducto.add(nombre);
            filaProducto.add(cantidadStock);
            filaProducto.add(jTextField);
            filaProducto.add(botonEliminar);

            jpanelInsumos.add(filaProducto);

            actual = actual.getSiguiente();
        }

        jScrollPane.setViewportView(jpanelInsumos);
        jInternalFrame.setContentPane(jScrollPane);
        panelPrincipal.add(jInternalFrame);
        try {
            jInternalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    //ventana NO.3
    public void ingresarNuevoProducto(){
        JInternalFrame internalFrame = new JInternalFrame("Agregar Nuevo Insumo", true, true, true, true);
        internalFrame.setSize(600, 200);
        int x = (panelPrincipal.getWidth()-600) / 2;
        int y = (panelPrincipal.getHeight()-200) / 2;
        internalFrame.setLocation(x, y);
        internalFrame.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        JLabel tituloDatos = new JLabel("Datos del Nuevo Insumo:");
        tituloDatos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jPanelDatos = new JPanel(new GridLayout(2,5));
        jPanelDatos.add(new JLabel("Nombre:"));
        jPanelDatos.add(new JLabel("Unida de Medida:"));
        jPanelDatos.add(new JLabel("Stock Minimo:"));
        jPanelDatos.add(new JLabel("Costo insumo: "));
        jPanelDatos.add(new JLabel(""));
        JTextField nombre = new JTextField();
        JTextField unidadMedida = new JTextField();
        JTextField stockMinimo = new JTextField();
        JTextField costoInsumo = new JTextField();
        JButton botonAgregarImagen =new JButton("Agregar Imagen");
        botonAgregarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int resultado = chooser.showOpenDialog(null);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivo = chooser.getSelectedFile();
                    direccionImagen= archivo.getAbsolutePath();
                }
            }
        });

        jPanelDatos.add(nombre);
        jPanelDatos.add(unidadMedida);
        jPanelDatos.add(stockMinimo);
        jPanelDatos.add(costoInsumo);
        jPanelDatos.add(botonAgregarImagen);

        JButton jButton = new JButton("Agregar");
        jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreTexto = nombre.getText();
                String unidadMedidaTexto = unidadMedida.getText();
                int stockMinimoValor = Integer.parseInt(stockMinimo.getText());
                double costoInsumoValor = Double.parseDouble(costoInsumo.getText());

                inventarioDB.agregarNuevoproducto(nombreTexto,unidadMedidaTexto,stockMinimoValor,costoInsumoValor, direccionImagen);
                internalFrame.dispose();
            }
        });
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(tituloDatos);
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(jPanelDatos);
        jPanel.add(Box.createHorizontalStrut(20));
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
    //ventana NO.4
    public void eliminarProducto(){
        JInternalFrame jInternalFrame = new JInternalFrame("Eliminar Insumos", true, true,true,true);
        jInternalFrame.setSize(620,400);
        int x = (panelPrincipal.getWidth()-620) / 2;
        int y = (panelPrincipal.getHeight()-400) / 2;
        jInternalFrame.setLocation(x, y);
        jInternalFrame.setVisible(true);

        JScrollPane jScrollPane = new JScrollPane();

        JPanel jpanelInsumos = new JPanel();
        jpanelInsumos.setLayout(new BoxLayout(jpanelInsumos, BoxLayout.Y_AXIS));

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new GridLayout(2,4));
        encabezado.add(new JLabel("Insumos Cafeteria: "));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel("ID"));
        encabezado.add(new JLabel("nombre"));
        encabezado.add(new JLabel("Cantidad en Stock"));
        encabezado.add(new JLabel(""));
        encabezado.setPreferredSize(new Dimension(580,50));
        encabezado.setMaximumSize(new Dimension(580,50));
        encabezado.setMinimumSize(new Dimension(580,50));
        jpanelInsumos.add(encabezado);

        Nodo<Producto> actual = productosFila.getPrimero();
        while(actual != null){
            Producto producto = actual.getDato();

            JPanel filaProducto = new JPanel();
            filaProducto.setLayout(new GridLayout(1,4));
            filaProducto.setPreferredSize(new Dimension(580,50));
            filaProducto.setMaximumSize(new Dimension(580,50));
            filaProducto.setMinimumSize(new Dimension(580,50));

            JLabel id = new JLabel(String.valueOf(producto.getIdentificador()));
            JLabel nombre = new JLabel(producto.getNombreInsumo());
            JLabel cantidadStock = new JLabel(String.valueOf(producto.getCantidadProducto()));

            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jpanelInsumos.remove(filaProducto);
                    int identificador = producto.getIdentificador();
                    eliminarProducto(identificador);
                    gestorInventario.eliminarProducto(identificador);
                    inventarioDB.eliminarInsumo(identificador);
                    jpanelInsumos.revalidate();
                    jpanelInsumos.repaint();
                }
            });

            filaProducto.add(id);
            filaProducto.add(nombre);
            filaProducto.add(cantidadStock);
            filaProducto.add(botonEliminar);

            jpanelInsumos.add(filaProducto);

            actual = actual.getSiguiente();
        }

        jScrollPane.setViewportView(jpanelInsumos);
        jInternalFrame.setContentPane(jScrollPane);
        panelPrincipal.add(jInternalFrame);
        try {
            jInternalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public void agregarProducto(Producto producto){
        productosFila.agregarDato(producto);
        gestorInventario.agregarProducto(producto);
        copiarFilas(productosFila, productosBaseDatos);
    }

    public void eliminarProducto(int identificador){
        int indiceEliminar = productosBaseDatos.buscarElementoIndice(identificador);
        productosFila.eliminarElemento(indiceEliminar);
        copiarFilas(productosFila, productosBaseDatos);
    }

    public void copiarFilas(Fila<Producto> original, FilaEntidadCafe copia){
        copia.limpiarFila();
        Nodo<Producto> actual = original.getPrimero();
        while (actual != null){
            copia.agregarDato(actual.getDato());
            actual = actual.getSiguiente();
        }
    }


    public GestorInventario getGestorInventario() {
        return gestorInventario;
    }
}
