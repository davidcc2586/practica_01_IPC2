package org.example.InterfasGrafica.backendsMenus;

import org.example.EntidadeCafe.Menu;
import org.example.EntidadeCafe.Producto;
import org.example.EntidadeCafe.ProductoInsumo;
import org.example.Herramientas.Fila;
import org.example.Herramientas.FilaEntidadCafe;
import org.example.Herramientas.ManejoArchivosHTML;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.FichaProducto;
import org.example.InterfasGrafica.GestorMenu;
import org.example.ManejoBaseDatos.MenuDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackendMenuGestionMenu {

    protected FilaEntidadCafe menuBaseDatos;
    protected Fila<Menu> menusFila;
    protected MenuDB menuDB;
    protected Connection connection;
    protected JDesktopPane panelPrincipal;
    protected GestorMenu gestorMenu;
    private String direccionImagen;

    public BackendMenuGestionMenu(Connection connection,JDesktopPane jDesktopPane){
        this.connection = connection;
        panelPrincipal = jDesktopPane;
        menuBaseDatos = new FilaEntidadCafe();
        menusFila = new Fila<>();
        menuDB = new MenuDB(connection,this);
        gestorMenu = new GestorMenu();
        menuDB.agregarMenusBaseDatos();
    }

    public void controlMenus(){
        JInternalFrame internalFrame = new JInternalFrame("Control de Inventario",true,true,true,true);
        internalFrame.setSize(480,475);
        internalFrame.setLocation(940,535);
        internalFrame.setVisible(true);
        internalFrame.setContentPane(gestorMenu.getjScrollPane());

        panelPrincipal.add(internalFrame);

        try{
            internalFrame.setSelected(true);
        }catch(java.beans.PropertyVetoException ex){
            ex.printStackTrace();
        }
    }

    public void crearNuevoMenu(){
        Fila<ProductoInsumo> productoInsumoFila = new Fila<>();

        JInternalFrame internalFrame = new JInternalFrame("Nuevo producto",true,true,true,true);
        internalFrame.setSize(500,500);

        int x = (panelPrincipal.getWidth()-500)/2;
        int y = (panelPrincipal.getHeight()-500)/2;

        internalFrame.setLocation(x,y);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        JLabel tituloNombre = new JLabel("Nombre:");
        tituloNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nombre = new JTextField();
        nombre.setPreferredSize(new Dimension(100,30));
        nombre.setMaximumSize(new Dimension(100,30));
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tituloInsumosRequeridos = new JLabel("Insumos requeridos por el producto:");
        tituloInsumosRequeridos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel insumosRequeridosPanel = new JPanel();
        insumosRequeridosPanel.setLayout(new BoxLayout(insumosRequeridosPanel,BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(insumosRequeridosPanel);
        jScrollPane.setPreferredSize(new Dimension(480,200));
        jScrollPane.setMaximumSize(new Dimension(480,200));

        JPanel ingresarInsumos = new JPanel(new GridLayout(1,3));
        ingresarInsumos.setPreferredSize(new Dimension(480,40));
        ingresarInsumos.setMaximumSize(new Dimension(480,40));

        FilaEntidadCafe productos = new FilaEntidadCafe();
        Fila<Producto> productosDisponibles = new Fila<>();
        String[] nombreProductos = new String[menuDB.productosDisponibles().getTamañoFila()];

        int i = 0;
        Nodo<Producto> actual = menuDB.productosDisponibles().getPrimero();

        while(actual != null){
            productos.agregarDato(actual.getDato());
            productosDisponibles.agregarDato(actual.getDato());
            nombreProductos[i] = actual.getDato().getNombreInsumo();
            i++;
            actual = actual.getSiguiente();
        }

        JComboBox<String> insumosDisponibles = new JComboBox<>(nombreProductos);
        JTextField cantidadInsumos = new JTextField();
        JButton agregarInsumo = new JButton("Agregar");

        agregarInsumo.addActionListener(e -> {
            String nombreProductoAgregar = String.valueOf(insumosDisponibles.getSelectedItem());
            int indice = productos.buscarElementoIndice(nombreProductoAgregar);
            Producto producto = productosDisponibles.buscarElemento(indice);

            productoInsumoFila.agregarDato(new ProductoInsumo(producto.getIdInsumo(),Integer.parseInt(cantidadInsumos.getText())));

            productos.eliminarElemento(indice);
            productosDisponibles.eliminarElemento(indice);

            insumosDisponibles.removeAllItems();

            Nodo<Producto> actual1 = productosDisponibles.getPrimero();

            while(actual1 != null){
                insumosDisponibles.addItem(actual1.getDato().getNombreInsumo());
                actual1 = actual1.getSiguiente();
            }

            cantidadInsumos.setText("");

            insumosRequeridosPanel.add(new FichaProducto(producto).getFichaProducto());

            insumosRequeridosPanel.revalidate();
            insumosRequeridosPanel.repaint();
        });

        ingresarInsumos.add(insumosDisponibles);
        ingresarInsumos.add(cantidadInsumos);
        ingresarInsumos.add(agregarInsumo);

        JPanel especificacionesProducto = new JPanel(new GridLayout(3,4));

        especificacionesProducto.add(new JLabel("Categoria:"));
        especificacionesProducto.add(new JLabel(""));

        String[] categoriasDisponibles = {"bebida caliente","bebida fria","postre","comida"};

        JComboBox<String> categoria = new JComboBox<>(categoriasDisponibles);

        especificacionesProducto.add(categoria);
        especificacionesProducto.add(new JLabel(""));
        especificacionesProducto.add(new JLabel("Precio:"));
        especificacionesProducto.add(new JLabel(""));

        JTextField precio = new JTextField();

        especificacionesProducto.add(precio);
        especificacionesProducto.add(new JLabel(""));
        especificacionesProducto.add(new JLabel("Imagen:"));
        especificacionesProducto.add(new JLabel(""));

        JButton buscarImagen = new JButton("Agregar imagen");

        buscarImagen.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int resultado = chooser.showOpenDialog(null);

            if(resultado == JFileChooser.APPROVE_OPTION){
                File archivo = chooser.getSelectedFile();
                direccionImagen = archivo.getAbsolutePath();
            }
        });

        especificacionesProducto.add(buscarImagen);
        especificacionesProducto.add(new JLabel(""));

        JButton botonIngresarProducto = new JButton("Ingresar");
        botonIngresarProducto.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonIngresarProducto.addActionListener(e -> {
            menuDB.agregarNuevoMenu(nombre.getText(),String.valueOf(categoria.getSelectedItem()),Double.parseDouble(precio.getText()),direccionImagen);

            int idMenu = menuDB.idUltimoMenu();

            Nodo<ProductoInsumo> productoInsumoNodo = productoInsumoFila.getPrimero();

            while(productoInsumoNodo != null){
                menuDB.agregarProductoInsumo(productoInsumoNodo.getDato().getIdProductoInsumo(),idMenu,productoInsumoNodo.getDato().getCantidad());

                productoInsumoNodo = productoInsumoNodo.getSiguiente();
            }

            internalFrame.dispose();
        });

        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(tituloNombre);
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(nombre);
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(tituloInsumosRequeridos);
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(jScrollPane);
        jPanel.add(Box.createVerticalStrut(20));
        jPanel.add(ingresarInsumos);
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(especificacionesProducto);
        jPanel.add(Box.createVerticalStrut(10));
        jPanel.add(botonIngresarProducto);
        jPanel.add(Box.createVerticalStrut(10));

        internalFrame.add(jPanel);
        panelPrincipal.add(internalFrame);

        internalFrame.setVisible(true);

        try{
            internalFrame.setSelected(true);
        }catch(java.beans.PropertyVetoException ex){
            ex.printStackTrace();
        }
    }

    public void eliminarMenu(){
        JInternalFrame jInternalFrame = new JInternalFrame("Eliminar Menu", true, true,true,true);
        jInternalFrame.setSize(600,400);
        int x = (panelPrincipal.getWidth()-600) / 2;
        int y = (panelPrincipal.getHeight()-400) / 2;
        jInternalFrame.setLocation(x, y);
        jInternalFrame.setVisible(true);

        JScrollPane jScrollPane = new JScrollPane();

        JPanel jpanelMenus = new JPanel();
        jpanelMenus.setLayout(new BoxLayout(jpanelMenus, BoxLayout.Y_AXIS));

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new GridLayout(2,5));
        encabezado.add(new JLabel("Menus Cafeteria: "));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel(""));
        encabezado.add(new JLabel("ID"));
        encabezado.add(new JLabel("nombre"));
        encabezado.add(new JLabel("Categoria"));
        encabezado.add(new JLabel("Precio"));
        encabezado.add(new JLabel(""));
        encabezado.setPreferredSize(new Dimension(580,50));
        encabezado.setMaximumSize(new Dimension(580,50));
        encabezado.setMinimumSize(new Dimension(580,50));
        jpanelMenus.add(encabezado);

        Nodo<Menu> actual = menusFila.getPrimero();
        while(actual != null){
            Menu menu = actual.getDato();

            JPanel filaMenu = new JPanel();
            filaMenu.setLayout(new GridLayout(1,5));
            filaMenu.setPreferredSize(new Dimension(580,50));
            filaMenu.setMaximumSize(new Dimension(580,50));
            filaMenu.setMinimumSize(new Dimension(580,50));

            JLabel id = new JLabel(String.valueOf(menu.getIdentificador()));
            JLabel nombre = new JLabel(menu.getNombre());
            JLabel categoria = new JLabel(menu.getCategoria());
            JLabel precio = new JLabel(String.valueOf(menu.getPrecio()));
            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jpanelMenus.remove(filaMenu);
                    int identificador = menu.getIdentificador();
                    eliminarMenu(identificador);
                    gestorMenu.eliminarMenu(identificador);
                    menuDB.eliminarMenu(identificador);
                    jpanelMenus.revalidate();
                    jpanelMenus.repaint();
                }
            });

            filaMenu.add(id);
            filaMenu.add(nombre);
            filaMenu.add(categoria);
            filaMenu.add(precio);
            filaMenu.add(botonEliminar);

            jpanelMenus.add(filaMenu);

            actual = actual.getSiguiente();
        }

        jScrollPane.setViewportView(jpanelMenus);
        jInternalFrame.setContentPane(jScrollPane);
        panelPrincipal.add(jInternalFrame);
        try {
            jInternalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    //Ventana NO.4
    public void exportarMenuHTML(){
        ManejoArchivosHTML manejoArchivosHTML = new ManejoArchivosHTML();
        manejoArchivosHTML.escribirHTML(menusFila);
    }

    public void agregarMenu(Menu menu){
        menusFila.agregarDato(menu);
        gestorMenu.agregarMenu(menu);
        copiarFilas(menusFila,menuBaseDatos);
    }

    public void eliminarMenu(int identificador){
        int indiceEliminar = menuBaseDatos.buscarElementoIndice(identificador);
        menusFila.eliminarElemento(indiceEliminar);
        copiarFilas(menusFila,menuBaseDatos);
    }

    public void cambiarPrecio(int identificador,double precio){
        int indiceModificar = menuBaseDatos.buscarElementoIndice(identificador);

        if(indiceModificar != 0){
            Menu menu = menusFila.buscarElemento(indiceModificar);
            menu.setPrecio(precio);
            gestorMenu.actualizarMenu(identificador);
            copiarFilas(menusFila,menuBaseDatos);
        }
    }

    public void copiarFilas(Fila<Menu> original,FilaEntidadCafe copia){
        copia.limpiarFila();

        Nodo<Menu> actual = original.getPrimero();

        while(actual != null){
            copia.agregarDato(actual.getDato());
            actual = actual.getSiguiente();
        }
    }
}