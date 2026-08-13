package org.example.ManejoBaseDatos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionDB {

    private final String HOST = "localhost";
    private final String PUERTO = "3306";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PUERTO ; //   jdbc:mysql://localhost:3306
    private final String URLBASEDATOS = URL + "/JavaBeansCafe";
    private String usuario;
    private String contraseña;

    public ConexionDB(String usuario,String clave){
        this.usuario = usuario;
        this.contraseña = clave;
        try {
            Connection connection = conexion(true);
            connection.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto", "Error", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public  Connection conexion(boolean nuevaBasedatos) throws SQLException {
        if (nuevaBasedatos) {
            return DriverManager.getConnection(URL, usuario, contraseña);
        } else {
            return DriverManager.getConnection(URLBASEDATOS, usuario, contraseña);
        }
    }

    public Connection obtenerConexion(){
        try {
            ConexionDB conexionDB = new ConexionDB(usuario, contraseña);
            return conexionDB.conexion(false);
        }catch (SQLException e){
            Connection connection = conexionNuevaBaseDatos();
            ejecutarInstruccionesSQL("/BaseDatos/añadirElementosPrueba.txt", connection);
            return connection;
        }
    }

    public Connection conexionNuevaBaseDatos(){
        Connection connection;
        try {
            ConexionDB conexionDB = new ConexionDB(usuario, contraseña);
            connection = conexionDB.conexion(true);
            ejecutarInstruccionesSQL("/BaseDatos/CrearBaseDatos.txt", connection);
            connection.close();
            return conexionDB.conexion(false);
        }catch (SQLException e){
            return null;
        }
    }

    public void ejecutarInstruccionesSQL(String direccioArchivo, Connection connection){
        BufferedReader lector;
        String linea;
        String sql = "";
        try {
            InputStream inputStream = getClass().getResourceAsStream(direccioArchivo);
            if(inputStream == null){
                return;
            }
            lector = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while((linea = lector.readLine()) != null){
                sql = sql + " " + linea;
                if(sql.contains(";")){
                    ejecutarStatement(sql, connection);
                    sql = "";
                }

            }
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ejecutarStatement(String codigoSql, Connection connection){
        try {
            Connection conexion = connection;
            PreparedStatement consulta = conexion.prepareStatement(codigoSql);
            consulta.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
