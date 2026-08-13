package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Empleado;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestorEmpleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class EmpleadoDB {

    protected Connection connection;
    protected BackendMenuGestorEmpleado backendMenuGestorEmpleado;

    public EmpleadoDB(Connection connection, BackendMenuGestorEmpleado backendMenuGestorEmpleado){
        this.connection = connection;
        this.backendMenuGestorEmpleado = backendMenuGestorEmpleado;
    }

    public void agregarEmpleadoBaseDatos(){
        String sqlInstruccion = "SELECT * FROM Empleado";
        try {
            PreparedStatement consulta = connection.prepareStatement(sqlInstruccion);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                Empleado empleado = new Empleado(resultado.getInt("id_empleado"),resultado.getString("DPI"),resultado.getString("nombre"), resultado.getString("apellido"),resultado.getString("rol"),resultado.getString("jornadaLaboral"), resultado.getDouble("salario"), resultado.getDate("fechaContratacion").toLocalDate(), resultado.getString("estado"), resultado.getString("direcionImagen"));
                backendMenuGestorEmpleado.agregarEmpleado(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarNuevoEmpleado(String dpi, String nombre, String apellido, String rol, String jornadaLaboral, double salario, LocalDate fechaContratacion){
        String insertarEmpleado = """
    INSERT INTO Empleado(DPI,nombre,apellido,rol,jornadaLaboral,salario,fechaContratacion) 
    VAlUES(?,?,?,?,?,?,?)""";
        String solicitarUltimoEmpleado = "SELECT * FROM Empleado WHERE id_empleado = (SELECT MAX(id_empleado) from Empleado)";
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertarEmpleado);
            preparedStatement1.setString(1, dpi);
            preparedStatement1.setString(2, nombre);
            preparedStatement1.setString(3, apellido);
            preparedStatement1.setString(4, rol);
            preparedStatement1.setString(5, jornadaLaboral);
            preparedStatement1.setDouble(6, salario);
            preparedStatement1.setDate(7, java.sql.Date.valueOf(fechaContratacion));
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = connection.prepareStatement(solicitarUltimoEmpleado);
            ResultSet resultado = preparedStatement2.executeQuery();
            resultado.next();
            Empleado empleado = new Empleado(resultado.getInt("id_empleado"),resultado.getString("DPI"),resultado.getString("nombre"), resultado.getString("apellido"),resultado.getString("rol"),resultado.getString("jornadaLaboral"), resultado.getDouble("salario"), resultado.getDate("fechaContratacion").toLocalDate(), resultado.getString("estado"), resultado.getString("direcionImagen"));
            backendMenuGestorEmpleado.agregarEmpleado(empleado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(int identificador){
        String sqlEliminarEmpleado = "DELETE FROM Empleado WHERE id_empleado = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlEliminarEmpleado);
            preparedStatement.setInt(1,identificador);
            preparedStatement.execute();
        }catch (Exception e){
        }

    }

    public void actualizarEstadoEmpleado(int identificador, String estado){
        String actualizarEmpleado = "UPDATE Empleado SET estado = ? WHERE id_empleado = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(actualizarEmpleado);
            preparedStatement.setString(1,estado);
            preparedStatement.setInt(2,identificador);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verificarExisteDpi(String dpi){
        String sqlInstruccion = "SELECT * FROM Empleado";
        try {
            PreparedStatement consulta = connection.prepareStatement(sqlInstruccion);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                if(dpi.equalsIgnoreCase(resultado.getString("DPI"))){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
