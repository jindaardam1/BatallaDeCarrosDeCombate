package dao;

import dao.records.Score;
import utilidades.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertsBADA {

    /**
     *
     *
     * @param nickname el nombre del jugador que se va a insertar
     */
    public static void insertarJugador(String nickname) {
        try (Connection conexion = ConexionBADA.conectar()) {
            String sql = "INSERT INTO jugador(nickname) VALUES(?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, nickname);
                pstmt.executeUpdate();
                System.out.println("Datos insertados correctamente.");
                Logs.infoLogManager("Se ha añadido el usuario " + nickname);
            } catch (SQLException e) {
                System.out.println("Error al insertarJugador datos en la tabla 'jugador'.");
                Logs.errorLogManager(e);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos.");
            Logs.errorLogManager(e);
        }
    }

    /**
     * Inserta un registro en la tabla especificada con los datos del objeto Score proporcionado.
     *
     * @param tabla La tabla en la que se desea insertar el registro.
     * @param score El objeto Score que contiene los datos del registro a insertar.
     */
    public static void insertarRegistro(String tabla, Score score) {
        try (Connection conn = ConexionBADA.conectar()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO " + tabla + " (numMapas, numTanquesMarrones, numTanquesGrises, numTanquesAmarillos, " +
                    "numTanquesMorados, numTanquesBlancos, numTanquesNegros, numTanquesRojos, " +
                    "numTanquesVerdesClaros, numTanquesVerdesOscuros, jugadorNickname) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, score.NUM_MAPAS());
                pstmt.setInt(2, score.NUM_TANQUES_MARRONES());
                pstmt.setInt(3, score.NUM_TANQUES_GRISES());
                pstmt.setInt(4, score.NUM_TANQUES_AMARILLOS());
                pstmt.setInt(5, score.NUM_TANQUES_MORADOS());
                pstmt.setInt(6, score.NUM_TANQUES_BLANCOS());
                pstmt.setInt(7, score.NUM_TANQUES_NEGROS());
                pstmt.setInt(8, score.NUM_TANQUES_ROJO());
                pstmt.setInt(9, score.NUM_TANQUES_VERDES_CLAROS());
                pstmt.setInt(10, score.NUM_TANQUES_VERDES_OSCUROS());
                pstmt.setString(11, score.JUGADOR_NICKNAME());

                pstmt.executeUpdate();

                conn.commit();
                System.out.println("Registro guardado correctamente.");
                Logs.infoLogManager("Se ha guardado el registro correctamente.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error al hacer el insert.");
                Logs.errorLogManager(e);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            Logs.errorLogManager(e);
        }
    }
}
