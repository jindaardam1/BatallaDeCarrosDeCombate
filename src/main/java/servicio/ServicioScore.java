package servicio;

import dao.ConsultasBADA;
import dao.InsertsBADA;
import dao.records.Score;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServicioScore {
    public static void registrarNuevoScore(Score score) {
        InsertsBADA.insertarRegistro("score", score);
    }

    public static List<Score> los10Mejores() {
        List<Score> scores = ConsultasBADA.obtenerScores();

        if (scores.isEmpty()) {
            return Collections.emptyList(); // Devolver lista vacía si no hay scores
        }

        Comparator<Score> scoreComparator = Comparator.comparingInt(Score::NUM_MAPAS).thenComparing(Score::JUGADOR_NICKNAME);
        scores.sort(scoreComparator.reversed());

        int numScores = Math.min(scores.size(), 10);

        return scores.subList(0, numScores);
    }

}
