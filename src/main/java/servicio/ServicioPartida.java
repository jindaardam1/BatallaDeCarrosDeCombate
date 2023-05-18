package servicio;

import dao.AltersBADA;
import dao.ConsultasBADA;
import dao.InsertsBADA;
import dao.records.Partida;

public class ServicioPartida {
    public static void guardarPartida(Partida partida) {
        InsertsBADA.insertarPartida(partida);
    }

    public static Partida cargarPartidaGuardada() {
        Partida partidaGuardada = ConsultasBADA.obtenerPartida().get(0);
        AltersBADA.borrarPartidaGuardada();
        return partidaGuardada;
    }
}
