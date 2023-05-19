package servicio;

import dao.ConsultasBADA;
import modelo.mapa.SerializadorMapa;

public class ServicioMapa {
    public static SerializadorMapa cargarMapa(int numMapa) {
        return ConsultasBADA.deserializador(ConsultasBADA.obtenerMapa(numMapa));
    }
}
