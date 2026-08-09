package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Producto;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestionInventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventarioDB {

    protected Connection connection;
    protected BackendMenuGestionInventario backendMenuGestionInventario;

    public InventarioDB(Connection connection, BackendMenuGestionInventario backendMenuGestionInventario){
        this.connection = connection;
        this.backendMenuGestionInventario = backendMenuGestionInventario;
    }

    public void agreagarProductosBaseDatos(){
        String sqlInstruccion = "SELECT * FROM Inventario";
        try {
            PreparedStatement consulta = connection.prepareStatement(sqlInstruccion);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                 Producto producto = new Producto(resultado.getInt("id_insumo"), resultado.getString("nombre"), resultado.getString("unidadMedida"), resultado.getDouble("costoInsumo"), resultado.getInt("stockMinimo"), resultado.getString("direccionImagen"), resultado.getInt("cantidadStock"));
                backendMenuGestionInventario.agregarProducto(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarNuevoproducto(String nombre, String unidadMedida, int stockMinimo, double costoInsumo, String direccionImagen){
        String insertarProducto = """
    INSERT INTO Inventario(nombre,unidadMedida,stockMinimo,costoInsumo,direccionImagen) 
    VAlUES(?,?,?,?,?)""";
        String solicitarUltimoProducto = "SELECT * FROM Inventario WHERE id_insumo = (SELECT MAX(id_insumo) from Inventario)";
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertarProducto);
            preparedStatement1.setString(1, nombre);
            preparedStatement1.setString(2, unidadMedida);
            preparedStatement1.setInt(3, stockMinimo);
            preparedStatement1.setDouble(4, costoInsumo);
            preparedStatement1.setString(5, direccionImagen);
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = connection.prepareStatement(solicitarUltimoProducto);
            ResultSet resultado = preparedStatement2.executeQuery();
            resultado.next();
            Producto producto = new Producto(resultado.getInt("id_insumo"), resultado.getString("nombre"), resultado.getString("unidadMedida"), resultado.getDouble("costoInsumo"), resultado.getInt("stockMinimo"), resultado.getString("direccionImagen"), resultado.getInt("cantidadStock"));
            backendMenuGestionInventario.agregarProducto(producto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarInsumo(int identificador){
        String eliminarInsumo = "DELETE FROM Inventario WHERE id_insumo = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(eliminarInsumo);
            preparedStatement.setInt(1,identificador);
            preparedStatement.execute();
        }catch (Exception e){
        }
    }

    public void ingresarCantidaInsumos(int identificado, int nuevaCantidad){
        String ingresoInsumo = "UPDATE Inventario SET cantidadStock = ? where id_insumo = ?";
      try {
          PreparedStatement preparedStatement = connection.prepareStatement(ingresoInsumo);
          preparedStatement.setInt(1,nuevaCantidad);
          preparedStatement.setInt(2,identificado);
          preparedStatement.execute();
      }catch (Exception e){

      }

    }
}
