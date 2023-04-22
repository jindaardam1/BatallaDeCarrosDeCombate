package org.example.juego.entidades.tanques;

public abstract class Tanque {

    private final int REBOTES_MAXIMOS;
    private final int VELOCIDAD_BALA;
    private final int MAXIMO_BALAS;
    private boolean vivo;
    private int balas;

    public Tanque(int REBOTES_MAXIMOS, int VELOCIDAD_BALA,  int MAXIMO_BALAS, int balas) {
        this.REBOTES_MAXIMOS = REBOTES_MAXIMOS;
        this.VELOCIDAD_BALA = VELOCIDAD_BALA;
        this.MAXIMO_BALAS = MAXIMO_BALAS;
        this.vivo = true;
        this.balas = balas;
    }

    public int getREBOTES_MAXIMOS() {
        return REBOTES_MAXIMOS;
    }

    public int getVELOCIDAD_BALA() {
        return VELOCIDAD_BALA;
    }

    public int getMAXIMO_BALAS() {
        return MAXIMO_BALAS;
    }

    public boolean isVivo() {
        return vivo;
    }

    public int getBalas() {
        return balas;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setBalas(int balas) {
        this.balas = balas;
    }

    public abstract void dispararBala();

    public abstract void recargarBala();
}
