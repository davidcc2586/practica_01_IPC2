package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Mesa;
import org.example.InterfasGrafica.ElementosVisuales.VentanaCentralElementos.GestorMesas;
import org.example.InterfasGrafica.Menus.MenuGestorMesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MesaDB {

    private MenuGestorMesa menuGestorMesa;
    private Connection connection;
    private GestorMesas gestorMesas;

    public MesaDB(MenuGestorMesa menuGestorMesa, Connection connection, GestorMesas gestorMesas){
        this.menuGestorMesa = menuGestorMesa;
        this.connection = connection;
        this.gestorMesas = gestorMesas;
    }

    public void agregarMesasBaseDatos(){
        String sqlInstruccion = "SELECT * FROM Mesa";
        try {
            PreparedStatement consulta = connection.prepareStatement(sqlInstruccion);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                Mesa mesa = new Mesa(resultado.getInt("id_mesa"), resultado.getInt("capacidad"), resultado.getString("estadoMesa"));
                gestorMesas.agregarMesa(mesa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
