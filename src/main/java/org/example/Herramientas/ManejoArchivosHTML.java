package org.example.Herramientas;

import org.example.EntidadeCafe.Menu;
import org.example.EntidadeCafe.RankingMenu;
import org.example.EntidadeCafe.Transaccion;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ManejoArchivosHTML {

    public ManejoArchivosHTML(){

    }

    public void escribirHTML(Fila<Menu> menusDisponibles) {
        File archivo = new File(solicitarDireccionGuardar());

        try (FileWriter fileWriter = new FileWriter(archivo, StandardCharsets.UTF_8);
             PrintWriter writer = new PrintWriter(fileWriter)) {

            writer.print("<!DOCTYPE html>\n");
            writer.print("<html lang=\"es\">\n");

            writer.print("<head>\n");
            writer.print("<meta charset=\"UTF-8\">\n");
            writer.print("<title>JavaBeans Café</title>\n");
            writer.print("<style>\n");
            writer.print("body {\n");
            writer.print("font-family: Arial, sans-serif;\n");
            writer.print("background-color: #f5eee6;\n");
            writer.print("margin: 0;\n");
            writer.print("padding: 40px;\n");
            writer.print("}\n");
            writer.print("h1 {\n");
            writer.print("text-align: center;\n");
            writer.print("color: #5c3b28;\n");
            writer.print("}\n");
            writer.print(".producto {\n");
            writer.print("width: 300px;\n");
            writer.print("margin: 30px auto;\n");
            writer.print("background-color: white;\n");
            writer.print("border-radius: 15px;\n");
            writer.print("overflow: hidden;\n");
            writer.print("box-shadow: 0 5px 15px rgba(0,0,0,0.15);\n");
            writer.print("}\n");
            writer.print(".producto img {\n");
            writer.print("width: 100%;\n");
            writer.print("height: 220px;\n");
            writer.print("object-fit: cover;\n");
            writer.print("}\n");
            writer.print(".informacion {\n");
            writer.print("padding: 20px;\n");
            writer.print("}\n");
            writer.print(".nombre {\n");
            writer.print("color: #5c3b28;\n");
            writer.print("font-size: 24px;\n");
            writer.print("margin-bottom: 8px;\n");
            writer.print("}\n");
            writer.print(".categoria {\n");
            writer.print("color: #888;\n");
            writer.print("font-size: 14px;\n");
            writer.print("}\n");
            writer.print(".precio {\n");
            writer.print("color: #b56b32;\n");
            writer.print("font-size: 22px;\n");
            writer.print("font-weight: bold;\n");
            writer.print("margin-top: 15px;\n");
            writer.print("}\n");
            writer.print("</style>\n");
            writer.print("</head>\n");
            writer.print("<body>\n");
            writer.print("<h1>JavaBeans Café</h1>\n");

            Nodo<Menu> actual = menusDisponibles.getPrimero();
            while (actual != null) {
                Menu menuActual = actual.getDato();
                writer.print("<div class=\"producto\">\n");
                writer.printf("<img src=\"%s\" alt=\"%s\">%n", menuActual.getDireccionImagen(), menuActual.getNombre());
                writer.print("<div class=\"informacion\">\n");
                writer.print("<div class=\"nombre\">\n");
                writer.printf("%s%n", menuActual.getNombre());
                writer.print("</div>\n");
                writer.print("<div class=\"categoria\">\n");
                writer.printf("%s%n", menuActual.getCategoria());
                writer.print("</div>\n");
                writer.print("<div class=\"precio\">\n");
                writer.printf("Q%.2f%n", menuActual.getPrecio());
                writer.print("</div>\n");
                writer.print("</div>\n");
                writer.print("</div>\n");
                actual = actual.getSiguiente();
            }
            writer.print("</body>\n");
            writer.print("</html>\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void escribirHTMLTransacciones(Fila<Transaccion> transacciones) {

        String direccion = solicitarDireccionGuardar();

        if (direccion != null) {

            File archivo = new File(direccion);

            try (FileWriter fileWriter = new FileWriter(archivo, StandardCharsets.UTF_8);
                 PrintWriter writer = new PrintWriter(fileWriter)) {

                writer.print("<!DOCTYPE html>\n");
                writer.print("<html lang=\"es\">\n");
                writer.print("<head>\n");
                writer.print("<meta charset=\"UTF-8\">\n");
                writer.print("<title>Reporte de Transacciones</title>\n");
                writer.print("<style>\n");
                writer.print("body { font-family: Arial, sans-serif; margin: 40px; }\n");
                writer.print("h1 { text-align: center; }\n");
                writer.print("table { width: 100%; border-collapse: collapse; }\n");
                writer.print("th, td { border: 1px solid #999; padding: 10px; text-align: center; }\n");
                writer.print("th { background-color: #5c3b28; color: white; }\n");
                writer.print("</style>\n");
                writer.print("</head>\n");
                writer.print("<body>\n");

                writer.print("<h1>Reporte de Transacciones</h1>\n");

                writer.print("<table>\n");
                writer.print("<tr>\n");
                writer.print("<th>ID</th>\n");
                writer.print("<th>Fecha</th>\n");
                writer.print("<th>Motivo</th>\n");
                writer.print("<th>Tipo</th>\n");
                writer.print("<th>Monto</th>\n");
                writer.print("</tr>\n");

                Nodo<Transaccion> actual = transacciones.getPrimero();

                while (actual != null) {

                    Transaccion transaccion = actual.getDato();

                    writer.print("<tr>\n");
                    writer.printf("<td>%d</td>%n", transaccion.getIdReporte());
                    writer.printf("<td>%s</td>%n", transaccion.getFecha());
                    writer.printf("<td>%s</td>%n", transaccion.getMotivo());
                    writer.printf("<td>%s</td>%n", transaccion.getTipo());
                    writer.printf("<td>Q%.2f</td>%n", transaccion.getMonto());
                    writer.print("</tr>\n");

                    actual = actual.getSiguiente();
                }

                writer.print("</table>\n");
                writer.print("</body>\n");
                writer.print("</html>\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void escribirHTMLRanking(Fila<RankingMenu> rankingMenus) {
        String direccion = solicitarDireccionGuardar();

        if (direccion != null) {
            File archivo = new File(direccion);

            try (FileWriter fileWriter = new FileWriter(archivo, StandardCharsets.UTF_8);
                 PrintWriter writer = new PrintWriter(fileWriter)) {

                writer.print("<!DOCTYPE html>\n");
                writer.print("<html lang=\"es\">\n");
                writer.print("<head>\n");
                writer.print("<meta charset=\"UTF-8\">\n");
                writer.print("<title>Ranking de Menús</title>\n");
                writer.print("<style>\n");
                writer.print("body { font-family: Arial, sans-serif; background-color: #f5eee6; margin: 40px; }\n");
                writer.print("h1 { text-align: center; color: #5c3b28; }\n");
                writer.print("table { width: 100%; border-collapse: collapse; background-color: white; }\n");
                writer.print("th { background-color: #5c3b28; color: white; padding: 12px; }\n");
                writer.print("td { border: 1px solid #999; padding: 10px; text-align: center; }\n");
                writer.print("</style>\n");
                writer.print("</head>\n");
                writer.print("<body>\n");

                writer.print("<h1>Ranking de Menús Más Vendidos</h1>\n");

                writer.print("<table>\n");
                writer.print("<tr>\n");
                writer.print("<th>Posición</th>\n");
                writer.print("<th>Menú</th>\n");
                writer.print("<th>Categoría</th>\n");
                writer.print("<th>Precio</th>\n");
                writer.print("<th>Vendidos</th>\n");
                writer.print("</tr>\n");

                Nodo<RankingMenu> actual = rankingMenus.getPrimero();
                int posicion = 1;

                while (actual != null) {
                    RankingMenu rankingMenu = actual.getDato();
                    Menu menu = rankingMenu.getMenu();

                    writer.print("<tr>\n");
                    writer.printf("<td>%d</td>%n", posicion);
                    writer.printf("<td>%s</td>%n", menu.getNombre());
                    writer.printf("<td>%s</td>%n", menu.getCategoria());
                    writer.printf("<td>Q%.2f</td>%n", menu.getPrecio());
                    writer.printf("<td>%d</td>%n", rankingMenu.getCantidadVendida());
                    writer.print("</tr>\n");

                    posicion++;
                    actual = actual.getSiguiente();
                }

                writer.print("</table>\n");
                writer.print("</body>\n");
                writer.print("</html>\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String solicitarDireccionGuardar(){
        JFileChooser chooser = new JFileChooser();
        int decision = chooser.showSaveDialog(null);
        if (decision == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            if (!archivo.getName().endsWith(".html")) {
                archivo = new File(archivo.getAbsolutePath() + ".html");
            }
            return archivo.getAbsolutePath();
        }

        return null;
    }

}
