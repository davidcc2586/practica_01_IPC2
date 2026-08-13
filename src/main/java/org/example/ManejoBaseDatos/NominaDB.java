package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Empleado;
import org.example.EntidadeCafe.Nomina;
import org.example.Herramientas.Fila;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestionNominas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class NominaDB {

    protected Connection connection;
    protected BackendMenuGestionNominas backendMenuGestionNominas;

    public NominaDB(Connection connection, BackendMenuGestionNominas backendMenuGestionNominas){
        this.connection = connection;
        this.backendMenuGestionNominas = backendMenuGestionNominas;
    }

    public void agregarNominasBaseDatos(){
        String sqlNominasDB = "SELECT * FROM Nomina";
        agregarNomina(sqlNominasDB);
    }

    public void agregarNuevaNomina(int idEmpleado, String tipo, String fecha) {
        String solicitarSalarioEmpleado = "SELECT salario FROM Empleado WHERE id_empleado = ?";
        String insertarNuevaNomina = "INSERT INTO Nomina(idEmpleado, monto, tipo, fechaPago) VALUES (?,?,?,?)";
        String solicitarUltimaNomina = "SELECT * FROM Nomina WHERE id_nomina = (SELECT MAX(id_nomina) FROM Nomina)";
        try {
            PreparedStatement solicitarEmpleadoSalario = connection.prepareStatement(solicitarSalarioEmpleado);
            solicitarEmpleadoSalario.setInt(1, idEmpleado);
            ResultSet salarioresultado = solicitarEmpleadoSalario.executeQuery();
            salarioresultado.next();
            double montoPagar = 0.0;
            if (tipo.equalsIgnoreCase("quincena")) {
                montoPagar = salarioresultado.getDouble("salario") * 0.30;
            } else {
                montoPagar = salarioresultado.getDouble("salario") * 0.70;
            }

            PreparedStatement insertaNomina = connection.prepareStatement(insertarNuevaNomina);
            insertaNomina.setInt(1,idEmpleado);
            insertaNomina.setDouble(2,montoPagar);
            insertaNomina.setString(3,tipo);
            insertaNomina.setString(4,fecha);
            insertaNomina.execute();

            agregarNomina(solicitarUltimaNomina);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pagar(int idEmpleado, String tipo){
        String solicitarSalarioEmpleado = "SELECT salario FROM Empleado WHERE id_empleado = ?";
        LocalDate fecha = LocalDate.now();
        String sqlPagar = "INSERT INTO Transacciones(tipo, motivo, monto, fecha) VALUES (?,?,?,?)";
        double montoPagar = 0;

        try{
            PreparedStatement solicitarEmpleadoSalario = connection.prepareStatement(solicitarSalarioEmpleado);
            solicitarEmpleadoSalario.setInt(1,idEmpleado);
            ResultSet salarioresultado = solicitarEmpleadoSalario.executeQuery();
            salarioresultado.next();

            if(tipo.equalsIgnoreCase("quincena")){
                montoPagar = salarioresultado.getDouble("salario") * 0.30;
            }else{
                montoPagar = salarioresultado.getDouble("salario") * 0.70;
            }

            PreparedStatement pagar = connection.prepareStatement(sqlPagar);
            pagar.setString(1,"egreso");
            pagar.setString(2,"Pago empleado con id: " + idEmpleado);
            pagar.setDouble(3,-montoPagar);
            pagar.setObject(4,fecha);
            pagar.execute();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void agregarNomina(String instruccion){
        String solicitarNombreEmpleado = "SELECT nombre FROM Empleado WHERE id_empleado = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(instruccion);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                PreparedStatement solicitarNombreEmpleadoDB = connection.prepareStatement(solicitarNombreEmpleado);
                solicitarNombreEmpleadoDB.setInt(1,resultSet.getInt("idEmpleado"));
                ResultSet resultSetNombre = solicitarNombreEmpleadoDB.executeQuery();
                resultSetNombre.next();
                String nombre = resultSetNombre.getString("nombre");
                Nomina nuevaNomina = new Nomina(resultSet.getInt("id_nomina"), resultSet.getInt("idEmpleado"), nombre, resultSet.getDate("fechaPago").toLocalDate(), resultSet.getString("tipo"), resultSet.getDouble("monto"),resultSet.getString("estado"));
                backendMenuGestionNominas.agregarNomina(nuevaNomina);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Fila<Empleado> empleadosActivos(){
        Fila<Empleado> empleadoFila = new Fila<>();
        String sqlEmpleadosActivos = "SELECT * FROM Empleado WHERE estado = 'activo'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlEmpleadosActivos);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Empleado empleado = new Empleado(resultSet.getInt("id_empleado"),resultSet.getString("DPI"),resultSet.getString("nombre"),resultSet.getString("apellido"),resultSet.getString("rol"),resultSet.getString("jornadaLaboral"),resultSet.getDouble("salario"),resultSet.getDate("fechaContratacion").toLocalDate(),resultSet.getString("estado"),resultSet.getString("direcionImagen"));
                empleadoFila.agregarDato(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return empleadoFila;
    }

    public void actualizarEstadoNomina(int idNomina){
        String sql = "UPDATE Nomina SET estado = 'pagado' WHERE id_nomina = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idNomina);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}