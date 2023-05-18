package dao;

import dao.records.Score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertsBADA {
    public static void insertarJugador(String nickname) {
        try (Connection conexion = ConexionBADA.conectar()) {
            String sql = "INSERT INTO jugador(nickname) VALUES(?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, nickname);
                pstmt.executeUpdate();
                System.out.println("Datos insertados correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al insertarJugador datos en la tabla 'jugador'.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos.");
            e.printStackTrace();
        }
    }

    public static void insertarRegistro(String tabla, Score score) {
        try (Connection conn = ConexionBADA.conectar()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO " + tabla + " (numMapas, numTanquesMarrones, numTanquesGrises, numTanquesAmarillos, " +
                    "numTanquesMorados, numTanquesBlancos, numTanquesNegros, numTanquesRojo, " +
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
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error al hacer el insert.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
