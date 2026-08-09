package org.example.Herramientas;

import org.example.InterfasGrafica.VentanaPrincipal;
import org.example.ManejoBaseDatos.ConexionDB;

import javax.swing.*;
import java.sql.Connection;

public class IniciarAplicacion {

    private VentanaPrincipal ventanaPrincipal;
    private Connection connection;

    public  IniciarAplicacion(){

    }

    public void iniciar(){

        ConexionDB conexionDB = new ConexionDB();
        connection = conexionDB.obtenerConexion();
        ventanaPrincipal = new VentanaPrincipal(connection);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);
    }
}
