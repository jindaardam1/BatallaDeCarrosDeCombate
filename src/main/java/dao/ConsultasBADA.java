package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.mapa.SerializadorMapa;
import dao.records.Score;
import dao.records.Skin;
import utilidades.log.Logs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasBADA {

    /**
     * Obtiene una lista de jugadores.
     *
     * @return La lista de jugadores.
     */
    public static List<String> obtenerJugadores() {
        List<String> jugadores = new ArrayList<>();

        try (Connection connection = ConexionBADA.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT nickname FROM jugador")) {

            while (resultSet.next()) {
                String jugador = resultSet.getString("nickname");
                jugadores.add(jugador);
            }

        } catch (SQLException e) {
            Logs.errorLogManager(e);
        }

        return jugadores;
    }


    /**
     * Obtiene todos los registros de puntuación almacenados.
     * @return una lista de objetos Score que representan los registros de puntuación
     */
    public static List<Score> obtenerScores() {
        String query = "SELECT * FROM score";
        return obtenerRegistros(query);
    }

    /**
     * Obtiene la información de la primera partida almacenada.
     * @return un objeto Score que representa la partida obtenida, o null si no hay partidas almacenadas
     */
    public static Score obtenerPartida() {
        String query = "SELECT * FROM partida LIMIT 1";
        List<Score> registros = obtenerRegistros(query);
        return registros.isEmpty() ? null : registros.get(0);
    }

    /**
     * Obtiene los registros de puntuación según una consulta dada.
     *
     * @param query la consulta SQL utilizada para obtener los registros de puntuación
     *
     * @return una lista de objetos Score que representan los registros de puntuación obtenidos
     */
    private static List<Score> obtenerRegistros(String query) {
        List<Score> registros = new ArrayList<>();

        try (Connection conn = ConexionBADA.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int numMapas = rs.getInt("numMapas");
                int numTanquesMarrones = rs.getInt("numTanquesMarrones");
                int numTanquesGrises = rs.getInt("numTanquesGrises");
                int numTanquesAmarillos = rs.getInt("numTanquesAmarillos");
                int numTanquesMorados = rs.getInt("numTanquesMorados");
                int numTanquesBlancos = rs.getInt("numTanquesBlancos");
                int numTanquesNegros = rs.getInt("numTanquesNegros");
                int numTanquesRojo = rs.getInt("numTanquesRojos");
                int numTanquesVerdesClaros = rs.getInt("numTanquesVerdesClaros");
                int numTanquesVerdesOscuros = rs.getInt("numTanquesVerdesOscuros");
                String jugadorNickname = rs.getString("jugadorNickname");

                Score score = new Score(numMapas, numTanquesMarrones, numTanquesGrises,
                        numTanquesAmarillos, numTanquesMorados, numTanquesBlancos,
                        numTanquesNegros, numTanquesRojo, numTanquesVerdesClaros,
                        numTanquesVerdesOscuros, jugadorNickname);
                registros.add(score);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los registros.");
            Logs.errorLogManager(e);
        }

        return registros;
    }

    /**
     * Obtiene la serialización de un mapa en base a su índice.
     * @param index el índice del mapa que se desea obtener
     * @return la serialización del mapa en formato de cadena de texto, o null si no se encontró el mapa
     */
    public static String obtenerMapa(int index) {
        String mapa = null;
        String query = "SELECT serializacion FROM mapa WHERE id = ?";
        try (Connection connection = ConexionBADA.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, index);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                mapa = rs.getString("serializacion");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener el mapa: " + e.getMessage());
            Logs.errorLogManager(e);
        }
        return mapa;
    }

    /**
     * Método para deserializar un objeto SerializadorMapa a partir de una cadena JSON.
     * Utiliza la librería Jackson para llevar a cabo la deserialización.
     *
     * @param jsonString La cadena JSON que contiene los datos a deserializar.
     * @return Un objeto SerializadorMapa deserializado a partir del JSON.
     */
    public static SerializadorMapa deserializador(String jsonString) {
        // Crear un objeto ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserializar el objeto a partir del JSON
        SerializadorMapa mapa = null;
        try {
            mapa = objectMapper.readValue(jsonString, SerializadorMapa.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Logs.errorLogManager(e);
        }

        return mapa;
    }

    /**
     * Obtiene todas las skins disponibles en la base de datos.
     *
     * @return ArrayList de objetos Skin que representa todas las skins disponibles.
     */
    public static ArrayList<Skin> obtenerSkins() {
        ArrayList<Skin> skins = new ArrayList<>();
        String query = "SELECT * FROM skins";
        try (Connection connection = ConexionBADA.conectar();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int conseguida = rs.getInt("conseguida");
                String jugadorNickname = rs.getString("jugadorNickname");

                Skin skin = new Skin(id, nombre, (conseguida == 1), jugadorNickname);
                skins.add(skin);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener las skins: " + e.getMessage());
            Logs.errorLogManager(e);
        }
        return skins;
    }
}
