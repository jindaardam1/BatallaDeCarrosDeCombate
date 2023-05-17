package modelo.tanques;

public abstract class TanqueMovil extends Tanque {

    private final boolean SE_MUEVE = true;

    private final double VELOCIDAD;

    public TanqueMovil(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int balas, double VELOCIDAD) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, balas);
        this.VELOCIDAD = VELOCIDAD;
    }

    public double getVELOCIDAD() {
        return VELOCIDAD;
    }
}
