package servicio;

import dao.InsertsBADA;
import dao.records.Score;

public class ServicioScore {
    public static void registrarNuevoScore(Score score) {
        InsertsBADA.insertarRegistro("score", score);
    }
}
