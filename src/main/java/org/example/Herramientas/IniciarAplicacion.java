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
        String usuario = JOptionPane.showInputDialog(null, "Ingrese su usuario:");

        String contraseña = "";
        JPasswordField campoPassword = new JPasswordField();
        int resultado = JOptionPane.showConfirmDialog(null, campoPassword, "Ingrese su contraseña", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            contraseña = new String(campoPassword.getPassword());
        }


        ConexionDB conexionDB = new ConexionDB(usuario,contraseña);
        connection = conexionDB.obtenerConexion();
        ventanaPrincipal = new VentanaPrincipal(connection);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);
    }
}
