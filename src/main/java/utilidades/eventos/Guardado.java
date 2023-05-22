package utilidades.eventos;

import dao.records.Score;
import main.BatallaDeCarrosDeCombate;

public class Guardado {
    public static int tanqueNegro;
    public static int tanqueRojo;
    public static int tanqueBlanco;
    public static int tanqueVerde;
    public static int tanqueVerdeOscuro;
    public static int tanqueMorado;
    public static int tanqueAmarillo;
    public static int tanqueGris;
    public static int tanqueMarron;
    public static int nivel;

    public Guardado() {
        this.tanqueNegro = 0;
        this.tanqueRojo = 0;
        this.tanqueBlanco = 0;
        this.tanqueVerde = 0;
        this.tanqueVerdeOscuro = 0;
        this.tanqueMorado = 0;
        this.tanqueAmarillo = 0;
        this.tanqueGris = 0;
        this.tanqueMarron = 0;
        this.nivel = 0;
    }


    public static Score getScore() {

        return new Score(nivel, tanqueMarron, tanqueGris, tanqueAmarillo, tanqueMorado, tanqueBlanco, tanqueNegro, tanqueRojo, tanqueVerde, tanqueVerdeOscuro, BatallaDeCarrosDeCombate.nickname.NICKNAME());
    }
    public static void reset(){

            tanqueNegro = 0;
            tanqueRojo = 0;
            tanqueBlanco = 0;
            tanqueVerde = 0;
            tanqueVerdeOscuro = 0;
            tanqueMorado = 0;
            tanqueAmarillo = 0;
            tanqueGris = 0;
            tanqueMarron = 0;
            nivel = 0;

    }
}
