package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AltersBADA {
    public static void alterSkin(int id, String jugadorNickname) {
        String updateQuery = "UPDATE skins SET conseguida = 1, jugadorNickname = ? WHERE id = ?";
        try (Connection connection = ConexionBADA.conectar();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, jugadorNickname);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro actualizado correctamente");
            } else {
                System.out.println("No se encontró un registro con el ID especificado");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el registro: " + e.getMessage());
        }
    }

    public static void borrarPartidaGuardada() {
        String query = "DELETE FROM partida";
        try (Connection connection = ConexionBADA.conectar();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);
            System.out.println("Partida guardada borrada exitosamente");

        } catch (SQLException e) {
            System.err.println("Error al borrar la partida guardada: " + e.getMessage());
        }
    }
}
