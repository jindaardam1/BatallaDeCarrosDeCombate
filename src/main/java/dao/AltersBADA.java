package dao;

import utilidades.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AltersBADA {

    /**
     * Actualiza el estado de una skin en la base de datos.
     * Si el ID especificado corresponde a una skin existente, se marca como "conseguida" y se asigna el nombre del jugador.
     *
     * @param id el ID de la skin a actualizar
     *
     * @param jugadorNickname el nombre del jugador que ha conseguido la skin
     */
    public static void alterSkin(int id, String jugadorNickname) {
        String updateQuery = "UPDATE skins SET conseguida = 1, jugadorNickname = ? WHERE id = ?";
        try (Connection connection = ConexionBADA.conectar();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, jugadorNickname);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro actualizado correctamente");
                Logs.infoLogManager("Skin " + id + "marcada como conseguida.");
            } else {
                System.out.println("No se encontró un registro con el ID especificado");
                Logs.debugLogManager("No se encontró un registro con el ID especificado");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el registro: " + e.getMessage());
            Logs.errorLogManager(e);
        }
    }

    /**
     * Borra la partida guardada de la base de datos.
     * Este método elimina todos los registros de la tabla "partida" en la base de datos.
     * Si la operación es exitosa, se imprime un mensaje de éxito en la consola.
     * En caso de producirse algún error, se imprime un mensaje de error junto con el mensaje de excepción correspondiente.
     */
    public static void borrarPartidaGuardada() {
        String query = "DELETE FROM partida";
        try (Connection connection = ConexionBADA.conectar();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);
            System.out.println("Partida guardada borrada exitosamente");

            Logs.infoLogManager("Partida guardada borrada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al borrar la partida guardada: " + e.getMessage());
            Logs.errorLogManager(e);
        }
    }
}
