package org.example.ManejoBaseDatos;

import org.example.EntidadeCafe.Menu;
import org.example.EntidadeCafe.RankingMenu;
import org.example.EntidadeCafe.Transaccion;
import org.example.Herramientas.Fila;
import org.example.Herramientas.Nodo;
import org.example.InterfasGrafica.backendsMenus.BackenndMenuReportes;

import java.sql.*;
import java.time.LocalDate;

public class ReportesDB {

    protected Connection connection;
    protected BackenndMenuReportes backenndMenuReportes;

    public ReportesDB(Connection connection, BackenndMenuReportes backenndMenuReportes) {
        this.connection = connection;
        this.backenndMenuReportes = backenndMenuReportes;
    }

    public Fila<Transaccion> obtenerTransacciones(LocalDate fechaInicio, LocalDate fechaFin, String tipo) {
        Fila<Transaccion> transaccionFila = new Fila<>();
        String sqlSolicitarTransaccionesOrdenadas = "SELECT * FROM Transacciones WHERE fecha BETWEEN ? AND ?";

        if (!tipo.equals("Todos")) {
            sqlSolicitarTransaccionesOrdenadas = sqlSolicitarTransaccionesOrdenadas + " AND tipo = ?";
        }

        sqlSolicitarTransaccionesOrdenadas += " ORDER BY fecha DESC";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlSolicitarTransaccionesOrdenadas);
            preparedStatement.setDate(1, Date.valueOf(fechaInicio));
            preparedStatement.setDate(2, Date.valueOf(fechaFin));

            if (!tipo.equals("Todos")) {
                preparedStatement.setString(3, tipo);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Transaccion transaccion = new Transaccion(
                        resultSet.getInt("id_transaccion"),
                        resultSet.getString("tipo"),
                        resultSet.getString("motivo"),
                        resultSet.getDouble("monto"),
                        resultSet.getTimestamp("fecha").toLocalDateTime()
                );

                transaccionFila.agregarDato(transaccion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transaccionFila;
    }

    public Fila<RankingMenu> obtenerRankingMenus() {
        Fila<RankingMenu> rankingMenus = new Fila<>();

        String sqlSolicitarMenus = "SELECT * FROM Menu";
        String sqlContarVentas = "SELECT COUNT(*) FROM DetallesPedido WHERE idProducto = ?";

        try {
            PreparedStatement preparedStatementMenus = connection.prepareStatement(sqlSolicitarMenus);
            ResultSet menus = preparedStatementMenus.executeQuery();

            while (menus.next()) {
                Menu menu = new Menu(menus.getInt("id_producto"), menus.getString("nombreProducto"), menus.getString("categoria"), menus.getDouble("precio"), menus.getString("direccionImagen"));

                PreparedStatement preparedStatementVentas = connection.prepareStatement(sqlContarVentas);
                preparedStatementVentas.setInt(1, menu.getIdMenu());

                ResultSet ventas = preparedStatementVentas.executeQuery();

                if (ventas.next()) {
                    int cantidadVendida = ventas.getInt(1);
                    RankingMenu rankingMenu = new RankingMenu(menu, cantidadVendida);
                    rankingMenus.agregarDato(rankingMenu);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ordenarRankingMenus(rankingMenus);
    }

    public Fila<RankingMenu> ordenarRankingMenus(Fila<RankingMenu> rankingMenus) {
        boolean cambio;

        do {
            cambio = false;
            Nodo<RankingMenu> actual = rankingMenus.getPrimero();

            while (actual != null && actual.getSiguiente() != null) {

                Nodo<RankingMenu> siguiente = actual.getSiguiente();

                if (actual.getDato().getCantidadVendida() < siguiente.getDato().getCantidadVendida()) {
                    RankingMenu temporal = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temporal);
                    cambio = true;
                }

                actual = actual.getSiguiente();
            }

        } while (cambio);

        return rankingMenus;
    }
}