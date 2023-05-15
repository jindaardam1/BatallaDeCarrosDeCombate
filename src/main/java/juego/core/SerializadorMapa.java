package juego.core;

import juego.utils.TipoCasilla;

import java.io.Serializable;

public class SerializadorMapa implements Serializable {
    private final int FILAS = 17;
    private final int COLUMNAS = 22;
    TipoCasilla[][] mapa;

    public SerializadorMapa() {
        mapa = new TipoCasilla[FILAS][COLUMNAS];
    }

    public TipoCasilla[][] getMapa() {
        return mapa;
    }

}