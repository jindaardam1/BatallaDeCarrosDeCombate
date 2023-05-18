package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBADA {
    private static final String RUTA_BD = "jdbc:sqlite:db\\tanquesbd.db";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(RUTA_BD);
            System.out.println("Conexión establecida.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }
}
