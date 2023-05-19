package servicio;

import dao.ConsultasBADA;
import dao.InsertsBADA;

import java.util.List;

public class ServicioJugador {

    public static void logingJugador(String jugadorNickname) {
        if (!existeJugador(jugadorNickname)) {
            insertarJugador(jugadorNickname);
        }
    }

    private static boolean existeJugador(String jugadorNickname){
        List<String> jugadores = ConsultasBADA.obtenerJugadores();

        for (String jugador : jugadores) {
            if (jugador.equals(jugadorNickname)) {
                return true;
            }
        }
        return false;
    }

    private static void insertarJugador(String jugadorNickname) {
        InsertsBADA.insertarJugador(jugadorNickname);
    }
}
