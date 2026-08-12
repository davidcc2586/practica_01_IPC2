package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Cuenta;
import org.example.EntidadeCafe.Empleado;
import org.example.EntidadeCafe.Menu;
import org.example.EntidadeCafe.Mesa;
import org.example.Herramientas.Fila;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestorCuentas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class CuentaDB {

    protected Connection connection;
    protected BackendMenuGestorCuentas backendMenuGestorCuentas;

    public CuentaDB(Connection connection, BackendMenuGestorCuentas backendMenuGestorCuentas) {
        this.connection = connection;
        this.backendMenuGestorCuentas = backendMenuGestorCuentas;
    }

    public Fila<Menu> menusSolicitadosCuenta(int idCuenta){

        Fila<Menu> menus = new Fila<>();
        String sqlSolicitarMenusCuenta = "SELECT idProducto FROM DetallesPedido WHERE idPedido = ?";
        String sqlSolicitarDatosMenu = "SELECT * FROM Menu WHERE id_producto = ?";
        try {
            PreparedStatement solicitarMenusCuenta = connection.prepareStatement(sqlSolicitarMenusCuenta);
            solicitarMenusCuenta.setInt(1,idCuenta);
            ResultSet resultadoMenusCuenta = solicitarMenusCuenta.executeQuery();
            while (resultadoMenusCuenta.next()){
                PreparedStatement solicitarDatosMenu = connection.prepareStatement(sqlSolicitarDatosMenu);
                solicitarDatosMenu.setInt(1,resultadoMenusCuenta.getInt("idProducto"));
                ResultSet resultSet = solicitarDatosMenu.executeQuery();
                while(resultSet.next()){
                    Menu nuevoMenu = new Menu(resultSet.getInt("id_producto"), resultSet.getString("nombreProducto"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getString("direccionImagen"));
                    menus.agregarDato(nuevoMenu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    public Fila<Menu> menusExistencia(){
        Fila<Menu> menus = new Fila<>();
        String sqlSolicitarMenusExistentes = "SELECT * FROM Menu";

        try {
            PreparedStatement solicitarMenus = connection.prepareStatement(sqlSolicitarMenusExistentes);
            ResultSet resultSet = solicitarMenus.executeQuery();
            while (resultSet.next()){
                Menu nuevoMenu = new Menu(resultSet.getInt("id_producto"), resultSet.getString("nombreProducto"), resultSet.getString("categoria"), resultSet.getDouble("precio"), resultSet.getString("direccionImagen"));
                menus.agregarDato(nuevoMenu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    public Fila<Empleado> meseroLibres(){
        Fila<Empleado> meseros = new Fila<>();
        String sqlMeserosLIbresActivos = "SELECT * from Empleado WHERE estado = 'activo' AND estadoTrabajo = 'libre' AND rol = 'mesero'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlMeserosLIbresActivos);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Empleado empleado = new Empleado(resultSet.getInt("id_empleado"), resultSet.getString("DPI"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("rol"), resultSet.getString("jornadaLaboral"), resultSet.getDouble("salario"), resultSet.getDate("fechaContratacion").toLocalDate(), resultSet.getString("estado"), resultSet.getString("estadoTrabajo"), resultSet.getString("direcionImagen"));
                meseros.agregarDato(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meseros;
    }

    public Fila<Mesa> mesasLibres(){
        Fila<Mesa> mesas = new Fila<>();
        String sqlMeserosLIbresActivos = "SELECT * from Mesa WHERE estadoMesa = 'libre'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlMeserosLIbresActivos);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Mesa mesa = new Mesa(resultSet.getInt("id_mesa"), resultSet.getInt("capacidad"), resultSet.getString("estadoMesa"));
                mesas.agregarDato(mesa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesas;
    }

    public void agregarNuevaCuenta(int idMesero, String mesa, LocalDate fecha, LocalTime horaIngresa){
        int numeroMesa = Integer.parseInt(mesa);
        String sqlIngresarNuevaCuenta = "INSERT INTO Pedido(idMesero,idMesa,fecha,horaOcupacion) VALUES(?,?,?,?)";
        String sqlSolicitarUltimacuenta = "SELECT * FROM Pedido WHERE id_pedido = (SELECT MAX(id_pedido) FROM Pedido)";
        String sqlSolicitarNombreMesero = "SELECT nombre FROM Empleado WHERE id_empleado = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlIngresarNuevaCuenta);
            preparedStatement.setInt(1,idMesero);
            preparedStatement.setInt(2, numeroMesa);
            preparedStatement.setObject(3,fecha);
            preparedStatement.setObject(4,horaIngresa);
            preparedStatement.execute();
            cambiarEstadoMesa(numeroMesa, "ocupada");
            cambiarEstadoEmpleado(idMesero, "ocupado");

            PreparedStatement solicitarUltimaCuenta = connection.prepareStatement(sqlSolicitarUltimacuenta);
            ResultSet resultadoUltimaCuenta = solicitarUltimaCuenta.executeQuery();
            resultadoUltimaCuenta.next();
            PreparedStatement solicitarNombreEmpleado = connection.prepareStatement(sqlSolicitarNombreMesero);
            solicitarNombreEmpleado.setInt(1,idMesero);
            ResultSet resultadoNombre = solicitarNombreEmpleado.executeQuery();
            resultadoNombre.next();
            Cuenta cuenta = new Cuenta(resultadoUltimaCuenta.getInt("id_pedido"), resultadoUltimaCuenta.getInt("idMesero"),resultadoNombre.getString("nombre"),resultadoUltimaCuenta.getInt("idMesa"), resultadoUltimaCuenta.getDate("fecha").toLocalDate(), resultadoUltimaCuenta.getTime("horaOcupacion").toLocalTime(), resultadoUltimaCuenta.getString("estadoCuenta"));
            backendMenuGestorCuentas.agregarCuentaAbierta(cuenta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cambiarEstadoEmpleado(int idEmpleado, String estado){
        String sqlCambiarEstado = "UPDATE Empleado SET estadoTrabajo = ? WHERE id_empleado = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCambiarEstado);
            preparedStatement.setString(1,estado);
            preparedStatement.setInt(2,idEmpleado);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarEstadoMesa(int idMesa, String estado){
        String sqlCambiarEstado = "UPDATE Mesa SET estadoMesa = ? WHERE id_mesa = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCambiarEstado);
            preparedStatement.setString(1,estado);
            preparedStatement.setInt(2,idMesa);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Fila<Cuenta> cuentasAbiertas(){
        Fila<Cuenta> cuentasAbiertas = new Fila<>();
        String sqlSolicitarNombreMesero = "SELECT nombre FROM Empleado WHERE id_empleado = ?";
        String sqlCuentasAbiertas = "SELECT * from Pedido WHERE estadoCuenta = 'abierta'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCuentasAbiertas);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                PreparedStatement solicitarNombreEmpleado = connection.prepareStatement(sqlSolicitarNombreMesero);
                solicitarNombreEmpleado.setInt(1, resultSet.getInt("idMesero"));
                ResultSet resultadoNombre = solicitarNombreEmpleado.executeQuery();
                resultadoNombre.next();
                Cuenta cuenta = new Cuenta(resultSet.getInt("id_pedido"), resultSet.getInt("idMesero"),resultadoNombre.getString("nombre"), resultSet.getInt("idMesa"), resultSet.getDate("fecha").toLocalDate(), resultSet.getTime("horaOcupacion").toLocalTime(), resultSet.getString("estadoCuenta"));
                cuentasAbiertas.agregarDato(cuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cuentasAbiertas;
    }

    public void agregarDetallesCuenta(int idCuenta, int idMenu){
        String sqlInsertarDetalles = "INSERT INTO DetallesPedido (idPedido,idProducto,precioUnitario) Values(?,?,?)";
        String sqlSolicitarPrecio = "SELECT precio FROM Menu WHERE id_producto = ?";
        try {
            PreparedStatement solicitarPrecioMenu = connection.prepareStatement(sqlSolicitarPrecio);
            solicitarPrecioMenu.setInt(1,idMenu);
            ResultSet precioMenu = solicitarPrecioMenu.executeQuery();
            precioMenu.next();
            PreparedStatement ingresarDetalleCuenta = connection.prepareStatement(sqlInsertarDetalles);
            ingresarDetalleCuenta.setInt(1,idCuenta);
            ingresarDetalleCuenta.setInt(2,idMenu);
            ingresarDetalleCuenta.setDouble(3,precioMenu.getDouble("precio"));
            ingresarDetalleCuenta.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
