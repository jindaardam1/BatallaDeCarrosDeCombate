package modelo.tanques;


public abstract class TanquePlantaminas extends TanqueMovil {

    private final int MAXIMO_MINAS;
    private int minas;

    public TanquePlantaminas(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int balas, double VELOCIDAD, int MAXIMO_MINAS, int minas) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, balas, VELOCIDAD);
        this.MAXIMO_MINAS = MAXIMO_MINAS;
        this.minas = minas;
    }

    public int getMAXIMO_MINAS() {
        return MAXIMO_MINAS;
    }

    public int getMinas() {
        return minas;
    }

    public void setMinas(int minas) {
        this.minas = minas;
    }

    public abstract void recargarMina();

    public abstract void plantarMina();
}
