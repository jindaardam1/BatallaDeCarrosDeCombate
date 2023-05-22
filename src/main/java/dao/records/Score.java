package dao.records;

/**
 * Representa el puntaje obtenido por un jugador en el juego.
 * Esta clase de registro (record) almacena la información del puntaje del jugador, incluyendo el número de mapas
 * completados, así como la cantidad de tanques de cada color eliminados por el jugador. También registra el apodo
 * (nickname) del jugador.
 * Los atributos de esta clase son inmutables, es decir, una vez que se crea un objeto de tipo Score, no se pueden
 * modificar sus valores.
 * @param NUM_MAPAS El número de mapas completados por el jugador.
 * @param NUM_TANQUES_MARRONES El número de tanques marrones eliminados por el jugador.
 * @param NUM_TANQUES_GRISES El número de tanques grises eliminados por el jugador.
 * @param NUM_TANQUES_AMARILLOS El número de tanques amarillos eliminados por el jugador.
 * @param NUM_TANQUES_MORADOS El número de tanques morados eliminados por el jugador.
 * @param NUM_TANQUES_BLANCOS El número de tanques blancos eliminados por el jugador.
 * @param NUM_TANQUES_NEGROS El número de tanques negros eliminados por el jugador.
 * @param NUM_TANQUES_ROJO El número de tanques rojos eliminados por el jugador.
 * @param NUM_TANQUES_VERDES_CLAROS El número de tanques verdes claros eliminados por el jugador.
 * @param NUM_TANQUES_VERDES_OSCUROS El número de tanques verdes oscuros eliminados por el jugador.
 * @param JUGADOR_NICKNAME El apodo (nickname) del jugador.
 */
public record Score(int NUM_MAPAS, int NUM_TANQUES_MARRONES, int NUM_TANQUES_GRISES,
                    int NUM_TANQUES_AMARILLOS, int NUM_TANQUES_MORADOS, int NUM_TANQUES_BLANCOS,
                    int NUM_TANQUES_NEGROS, int NUM_TANQUES_ROJO, int NUM_TANQUES_VERDES_CLAROS,
                    int NUM_TANQUES_VERDES_OSCUROS, String JUGADOR_NICKNAME) {}

