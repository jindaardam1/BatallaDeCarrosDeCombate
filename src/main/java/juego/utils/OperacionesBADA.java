package juego.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import juego.core.SerializadorMapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionesBADA {
    private static final String RUTA_BD = "jdbc:sqlite:tanquesbd.db";

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

    private static void crearBADA() {
        if (!OperacionesBADA.existeBADA()) {
            OperacionesBADA.crearBaseDeDatos();
            System.out.println("Base de datos creada.");
            return;
        }
        System.out.println("Ya existe la base de datos.");
    }

    private static boolean existeBADA() {
        File archivoBD = new File("C:\\_DiscoDatos-MA\\Programación\\UT8\\BatallaDeCarrosDeCombate\\src\\main\\java\\juego\\utils\\creacionBADA.sql");
        return archivoBD.exists();
    }

    private static void crearBaseDeDatos() {
        String url = "jdbc:sqlite:tanquesbd.db";
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
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\_DiscoDatos-MA\\Programación\\UT8\\BatallaDeCarrosDeCombate\\src\\main\\java\\juego\\utils\\creacionBADA.sql"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                script.append(linea).append('\n');
            }
        } catch (IOException e) {
            System.err.println("Error al leer el script de creación de la base de datos: " + e.getMessage());
        }
        return script.toString();
    }

    public static void insertarJugador(String nickname) {
        try (Connection conexion = conectar()) {
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

        try (Connection conn = conectar()) {

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

        try (Connection conn = conectar()) {

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


    public static ArrayList<String> obtenerJugadores() {
        ArrayList<String> jugadores = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = conectar(); // Aquí utiliza el método conectar() que ya hemos creado anteriormente
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT nickname FROM jugador");

            while (rs.next()) {
                String jugador = rs.getString("nickname");
                jugadores.add(jugador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return jugadores;
    }

    public static List<Score> obtenerScores() {
        List<Score> scores = new ArrayList<>();

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM score")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int numMapas = rs.getInt("numMapas");
                int numTanquesMarrones = rs.getInt("numTanquesMarrones");
                int numTanquesGrises = rs.getInt("numTanquesGrises");
                int numTanquesAmarillos = rs.getInt("numTanquesAmarillos");
                int numTanquesMorados = rs.getInt("numTanquesMorados");
                int numTanquesBlancos = rs.getInt("numTanquesBlancos");
                int numTanquesNegros = rs.getInt("numTanquesNegros");
                int numTanquesRojo = rs.getInt("numTanquesRojo");
                int numTanquesVerdesClaros = rs.getInt("numTanquesVerdesClaros");
                int numTanquesVerdesOscuros = rs.getInt("numTanquesVerdesOscuros");
                String jugadorNickname = rs.getString("jugadorNickname");

                Score score = new Score(id, numMapas, numTanquesMarrones, numTanquesGrises,
                        numTanquesAmarillos, numTanquesMorados, numTanquesBlancos,
                        numTanquesNegros, numTanquesRojo, numTanquesVerdesClaros,
                        numTanquesVerdesOscuros, jugadorNickname);
                scores.add(score);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los scores.");
        }

        return scores;
    }

    public static List<Partida> obtenerPartida() {
        List<Partida> partida = new ArrayList<>();

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM partida")) {

            while (rs.next()) {
                int numMapas = rs.getInt("numMapas");
                int numTanquesMarrones = rs.getInt("numTanquesMarrones");
                int numTanquesGrises = rs.getInt("numTanquesGrises");
                int numTanquesAmarillos = rs.getInt("numTanquesAmarillos");
                int numTanquesMorados = rs.getInt("numTanquesMorados");
                int numTanquesBlancos = rs.getInt("numTanquesBlancos");
                int numTanquesNegros = rs.getInt("numTanquesNegros");
                int numTanquesRojo = rs.getInt("numTanquesRojo");
                int numTanquesVerdesClaros = rs.getInt("numTanquesVerdesClaros");
                int numTanquesVerdesOscuros = rs.getInt("numTanquesVerdesOscuros");
                String jugadorNickname = rs.getString("jugadorNickname");

                Partida p = new Partida(numMapas, numTanquesMarrones, numTanquesGrises,
                        numTanquesAmarillos, numTanquesMorados, numTanquesBlancos,
                        numTanquesNegros, numTanquesRojo, numTanquesVerdesClaros,
                        numTanquesVerdesOscuros, jugadorNickname);
                partida.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los scores.");
        }

        return partida;
    }

    public static String obtenerMapa(int index) {
        String mapa = null;
        String query = "SELECT serializacion FROM mapa WHERE id = ?";
        try (Connection connection = conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, index);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                mapa = rs.getString("serializacion");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener el mapa: " + e.getMessage());
        }
        return mapa;
    }

    public static ArrayList<Skin> obtenerSkins() {
        ArrayList<Skin> skins = new ArrayList<>();
        String query = "SELECT * FROM skins";
        try (Connection connection = conectar();
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
        }
        return skins;
    }

    public static SerializadorMapa deserializador(String jsonString) {
        // Crear un objeto ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserializar el objeto a partir del JSON
        SerializadorMapa mapa = null;
        try {
            mapa = objectMapper.readValue(jsonString, SerializadorMapa.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return mapa;
    }
}
