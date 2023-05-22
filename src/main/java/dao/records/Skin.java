package dao.records;

/**
 * La clase Skin representa una skin en el juego.
 * Esta clase utiliza el patrón de registro (record) de Java, que
 * automáticamente genera los métodos equals(), hashCode(), y toString()
 * basados en los campos declarados.
 * @param ID El ID de la skin.
 * @param NOMBRE El nombre de la skin.
 * @param CONSEGUIDA Indica si la skin ha sido conseguida o no.
 * @param JUGADOR_NICKNAME El nombre de usuario del jugador que posee la skin.
 */
public record Skin(int ID, String NOMBRE, Boolean CONSEGUIDA, String JUGADOR_NICKNAME) {
}
