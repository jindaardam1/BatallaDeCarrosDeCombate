package org.example.tanques;

public abstract class TanqueSinMinas extends TanqueMovil {

    public TanqueSinMinas(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int balas, double VELOCIDAD) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, balas, VELOCIDAD);
    }
}
