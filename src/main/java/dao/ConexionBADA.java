package dao;

import utilidades.log.Logs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBADA {
    private static final String RUTA_BD = "jdbc:sqlite:db\\tanquesbd.db";

    /**
     * Establece una conexión con la base de datos.
     * @return La conexión establecida con la base de datos.
     */
    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(RUTA_BD);
            System.out.println("Conexión establecida.");
            Logs.infoLogManager("Conexión establecida.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            Logs.errorLogManager(e);
        }
        return conexion;
    }
}
