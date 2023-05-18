package dao;


import dao.records.Score;
import servicio.ServicioPartida;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class InicializarBADA {

    public static void crearBADA() {
        if (!existeBADA()) {
            crearBaseDeDatos();
            System.out.println("Base de datos creada.");
            return;
        }
        System.out.println("Ya existe la base de datos.");
    }

    public static boolean existeBADA() {
        File archivoBD = new File("tanquesbd.db");
        return archivoBD.exists();
    }

    public static void crearBaseDeDatos() {
        String url = "jdbc:sqlite:db\\tanquesbd.db";
        String sql = obtenerScriptCreacionBADA();

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            Class.forName("org.sqlite.JDBC");

            statement.executeUpdate(sql);
            System.out.println("Base de datos creada exitosamente");

        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos: " + e.getMessage());
        }
    }

    private static String obtenerScriptCreacionBADA() {
        StringBuilder script = new StringBuilder();

        crearCarpeta("db");

        try (InputStream inputStream = InicializarBADA.class.getResourceAsStream("/sql/creacionBADA.sql");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                script.append(linea).append('\n');
            }
        } catch (IOException | NullPointerException e) {
            System.err.println("Error al leer el script de creación de la base de datos: " + e.getMessage());
        }

        return script.toString();
    }

    private static void crearCarpeta(String nombreCarpeta) {
        File carpeta = new File(nombreCarpeta);

        if (carpeta.exists()) {
            System.out.println("La carpeta ya existe.");
            return;
        }

        if (carpeta.mkdir()) {
            System.out.println("Carpeta creada exitosamente.");
        } else {
            System.out.println("No se pudo crear la carpeta.");
        }
    }
}
