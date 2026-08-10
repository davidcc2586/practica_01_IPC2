package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Menu;
import org.example.EntidadeCafe.Producto;
import org.example.Herramientas.Fila;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestionMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuDB {

    protected Connection connection;
    protected BackendMenuGestionMenu backendMenuGestionMenu;

    public MenuDB(Connection connection, BackendMenuGestionMenu backendMenuGestionMenu){
        this.connection = connection;
        this.backendMenuGestionMenu = backendMenuGestionMenu;
    }

    public void agregarMenusBaseDatos(){
        String solicitarMenusDB = "SELECT * FROM Menu";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(solicitarMenusDB);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Menu nuevoMenu = new Menu(resultSet.getInt("id_producto"), resultSet.getString("nombreProducto"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getString("direccionImagen"));
                backendMenuGestionMenu.agregarMenu(nuevoMenu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Fila<Producto> productosDisponibles(){
        Fila<Producto> productos = new Fila<>();
        String sqlInstruccion = "SELECT * FROM Inventario";

        try{
            PreparedStatement consulta = connection.prepareStatement(sqlInstruccion);
            ResultSet resultado = consulta.executeQuery();

            while(resultado.next()){
                Producto producto = new Producto(resultado.getInt("id_insumo"), resultado.getString("nombre"), resultado.getString("unidadMedida"), resultado.getDouble("costoInsumo"), resultado.getInt("stockMinimo"), resultado.getString("direccionImagen"), resultado.getInt("cantidadStock"));
                productos.agregarDato(producto);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return productos;
    }

    public void agregarNuevoMenu(String nombre,String categoria,double precio,String direccionImagen){
        String ingresarNuevoMenu = "INSERT INTO Menu(nombreProducto,categoria,precio,direccionImagen) VALUES(?,?,?,?)";
        String solicitarUltimoMenu = "SELECT * FROM Menu WHERE id_producto = (SELECT MAX(id_producto) FROM Menu)";

        try{
            PreparedStatement ingresarMenu = connection.prepareStatement(ingresarNuevoMenu);
            ingresarMenu.setString(1,nombre);
            ingresarMenu.setString(2,categoria);
            ingresarMenu.setDouble(3,precio);
            ingresarMenu.setString(4,direccionImagen);
            ingresarMenu.executeUpdate();

            PreparedStatement ultimoMenu = connection.prepareStatement(solicitarUltimoMenu);
            ResultSet resultSet = ultimoMenu.executeQuery();

            if(resultSet.next()){
                Menu nuevoMenu = new Menu(resultSet.getInt("id_producto"), resultSet.getString("nombreProducto"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getString("direccionImagen"));
                backendMenuGestionMenu.agregarMenu(nuevoMenu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int idUltimoMenu(){
        String solicitarUltimoMenu = "SELECT id_producto FROM Menu WHERE id_producto = (SELECT MAX(id_producto) FROM Menu)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(solicitarUltimoMenu);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("id_producto");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public void agregarProductoInsumo(int idProductoInsumo,int idProducto,int cantidad){
        String agregarProductoInsumo = "INSERT INTO ProductoInsumo(id_insumo,id_producto,cantidad) VALUES(?,?,?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(agregarProductoInsumo);
            preparedStatement.setInt(1,idProductoInsumo);
            preparedStatement.setInt(2,idProducto);
            preparedStatement.setInt(3,cantidad);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarMenu(int idMenu){
        String limpiarProductoInsumo = "DELETE FROM ProductoInsumo WHERE id_producto = ?";
        String sqlEliminarMenu = "DELETE FROM Menu WHERE id_producto = ?";
        try {
            PreparedStatement limpiar = connection.prepareStatement(limpiarProductoInsumo);
            limpiar.setInt(1,idMenu);
            limpiar.execute();

            PreparedStatement eliminarMenuEspecifico = connection.prepareStatement(sqlEliminarMenu);
            eliminarMenuEspecifico.setInt(1,idMenu);
            eliminarMenuEspecifico.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}