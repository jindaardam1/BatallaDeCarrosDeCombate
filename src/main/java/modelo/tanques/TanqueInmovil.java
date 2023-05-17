package modelo.tanques;

public abstract class TanqueInmovil extends Tanque {

    private final boolean SE_MUEVE = false;

    public TanqueInmovil(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int balas) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, balas);
    }

}
