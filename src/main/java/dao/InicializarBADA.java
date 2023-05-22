package dao;


import utilidades.log.Logs;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class InicializarBADA {

    /**
     * Crea la base de datos "tanquesbd.db" si no existe.
     * Si la base de datos ya existe, se muestra un mensaje indicando esto.
     */
    public static void crearBADA() {
        if (!existeBADA()) {
            crearBaseDeDatos();
            System.out.println("Base de datos creada.");
            Logs.infoLogManager("Base de datos creada.");
            return;
        }
        System.out.println("Ya existe la base de datos.");
        Logs.debugLogManager("No se ha creado la base de datos porque ya existe.");
    }

    /**
     * Verifica si la base de datos "tanquesbd.db" existe.
     *
     * @return true si la base de datos existe, false en caso contrario.
     */
    private static boolean existeBADA() {
        File archivoBD = new File("db/tanquesbd.db");
        return archivoBD.exists();
    }


    /**
     * Crea una base de datos utilizando el controlador JDBC de SQLite y ejecuta el script de creación de base de datos.
     */
    private static void crearBaseDeDatos() {
        String url = "jdbc:sqlite:db\\tanquesbd.db";
        String sql = obtenerScriptCreacionBADA();

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            Class.forName("org.sqlite.JDBC");

            statement.executeUpdate(sql);
            System.out.println("Base de datos creada exitosamente");

        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
            Logs.errorLogManager("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos: " + e.getMessage());
            Logs.errorLogManager("Error al crear la base de datos: " + e.getMessage());
        }
    }

    /**
     * Este método obtiene el script de creación de la base de datos (BADA) como una cadena de caracteres.
     *
     * @return El script de creación de la base de datos en forma de cadena.
     */
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
            Logs.errorLogManager(e);
        }

        return script.toString();
    }

    /**
     * Crea una nueva carpeta en el sistema de archivos con el nombre especificado.
     *
     * @param nombreCarpeta El nombre de la carpeta a crear.
     */
    private static void crearCarpeta(String nombreCarpeta) {
        File carpeta = new File(nombreCarpeta);

        if (carpeta.exists()) {
            System.out.println("La carpeta ya existe.");
            Logs.debugLogManager("No se ha creado la carpeta porque ya existe.");
            return;
        }

        if (carpeta.mkdir()) {
            System.out.println("Carpeta creada exitosamente.");
            Logs.infoLogManager("Carpeta logs creada.");
        } else {
            System.out.println("No se pudo crear la carpeta.");
            Logs.errorLogManager("No se ha podido crear la carpeta de logs.");
        }
    }
}
