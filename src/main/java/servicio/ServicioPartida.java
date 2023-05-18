package servicio;

import dao.AltersBADA;
import dao.ConsultasBADA;
import dao.InsertsBADA;
import dao.records.Score;

public class ServicioPartida {
    public static void guardarPartida(Score score) {
        InsertsBADA.insertarRegistro("partida", score);
    }

    public static Score cargarPartidaGuardada() {
        Score partidaGuardada = ConsultasBADA.obtenerPartida();
        AltersBADA.borrarPartidaGuardada();
        return partidaGuardada;
    }
}
