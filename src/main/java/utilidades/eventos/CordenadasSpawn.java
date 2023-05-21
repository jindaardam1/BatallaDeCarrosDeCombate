package utilidades.eventos;

public class CordenadasSpawn<TipoCasilla, Point> {
    private TipoCasilla casilla;
    private Point cordenadas;

    public CordenadasSpawn(TipoCasilla objeto1, Point objeto2) {
        this.casilla = objeto1;
        this.cordenadas = objeto2;
    }

    public TipoCasilla getTipoCasilla() {
        return casilla;
    }

    public Point getCordenadas() {
        return cordenadas;
    }

}
