package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Mesa;
import org.example.InterfasGrafica.backendsMenus.BackendMenuGestorMesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MesaDB {


    private Connection connection;
    private BackendMenuGestorMesa backendMenuGestorMesa;

    public MesaDB(Connection connection, BackendMenuGestorMesa backendMenuGestorMesa){
        this.connection = connection;
        this.backendMenuGestorMesa = backendMenuGestorMesa;
    }

    public void agregarMesasBaseDatos(){
        String sqlInstruccion = "SELECT * FROM Mesa";
        try {
            PreparedStatement consulta = connection.prepareStatement(sqlInstruccion);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                Mesa mesa = new Mesa(resultado.getInt("id_mesa"), resultado.getInt("capacidad"), resultado.getString("estadoMesa"));
                backendMenuGestorMesa.agregarMesa(mesa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void agregarNuevaMesa(int capacidad){
        String insertaMesa = "INSERT INTO Mesa(capacidad) VALUES(?)";
        String solicitarUltimaMesa = "SELECT * FROM Mesa WHERE id_mesa = (SELECT MAX(id_mesa) FROM Mesa)";
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertaMesa);
            preparedStatement1.setInt(1,capacidad);
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = connection.prepareStatement(solicitarUltimaMesa);
            ResultSet resultadoUltimaMesa = preparedStatement2.executeQuery();
            resultadoUltimaMesa.next();

            Mesa nuevaMesa = new Mesa(resultadoUltimaMesa.getInt("id_mesa"), resultadoUltimaMesa.getInt("capacidad"), resultadoUltimaMesa.getString("estadoMesa"));
            backendMenuGestorMesa.agregarMesa(nuevaMesa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarMesa(int identificador){
        String eliminarMesa = "DELETE FROM Mesa WHERE id_mesa = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(eliminarMesa);
            preparedStatement.setInt(1,identificador);
            preparedStatement.execute();
        }catch (Exception e){
        }

    }
}
