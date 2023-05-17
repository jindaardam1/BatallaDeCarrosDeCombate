package dao;

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

    public static void insertarScore(int numMapas, int numTanquesMarrones, int numTanquesGrises, int numTanquesAmarillos,
                                     int numTanquesMorados, int numTanquesBlancos, int numTanquesNegros,
                                     int numTanquesRojo, int numTanquesVerdesClaros, int numTanquesVerdesOscuros,
                                     String jugadorNickname) {

        try (Connection conn = ConexionBADA.conectar()) {

            conn.setAutoCommit(false);

            String sql = "INSERT INTO score (numMapas, numTanquesMarrones, numTanquesGrises, numTanquesAmarillos, " +
                    "numTanquesMorados, numTanquesBlancos, numTanquesNegros, numTanquesRojo, " +
                    "numTanquesVerdesClaros, numTanquesVerdesOscuros, jugadorNickname) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, numMapas);
                pstmt.setInt(2, numTanquesMarrones);
                pstmt.setInt(3, numTanquesGrises);
                pstmt.setInt(4, numTanquesAmarillos);
                pstmt.setInt(5, numTanquesMorados);
                pstmt.setInt(6, numTanquesBlancos);
                pstmt.setInt(7, numTanquesNegros);
                pstmt.setInt(8, numTanquesRojo);
                pstmt.setInt(9, numTanquesVerdesClaros);
                pstmt.setInt(10, numTanquesVerdesOscuros);
                pstmt.setString(11, jugadorNickname);

                pstmt.executeUpdate();

                conn.commit();
                System.out.println("Score guardado correctamente.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error al hacer el insert.");
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
    public static void insertarPartida(int numMapas, int numTanquesMarrones, int numTanquesGrises, int numTanquesAmarillos,
                                       int numTanquesMorados, int numTanquesBlancos, int numTanquesNegros,
                                       int numTanquesRojo, int numTanquesVerdesClaros, int numTanquesVerdesOscuros,
                                       String jugadorNickname) {

        try (Connection conn = ConexionBADA.conectar()) {

            conn.setAutoCommit(false);

            String sql = "INSERT INTO score (numMapas, numTanquesMarrones, numTanquesGrises, numTanquesAmarillos, " +
                    "numTanquesMorados, numTanquesBlancos, numTanquesNegros, numTanquesRojo, " +
                    "numTanquesVerdesClaros, numTanquesVerdesOscuros, jugadorNickname) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, numMapas);
                pstmt.setInt(2, numTanquesMarrones);
                pstmt.setInt(3, numTanquesGrises);
                pstmt.setInt(4, numTanquesAmarillos);
                pstmt.setInt(5, numTanquesMorados);
                pstmt.setInt(6, numTanquesBlancos);
                pstmt.setInt(7, numTanquesNegros);
                pstmt.setInt(8, numTanquesRojo);
                pstmt.setInt(9, numTanquesVerdesClaros);
                pstmt.setInt(10, numTanquesVerdesOscuros);
                pstmt.setString(11, jugadorNickname);

                pstmt.executeUpdate();

                conn.commit();
                System.out.println("Score guardado correctamente.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error al hacer el insert.");
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
