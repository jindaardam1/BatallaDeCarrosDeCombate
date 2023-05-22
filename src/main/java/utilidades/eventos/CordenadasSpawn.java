package utilidades.eventos;

public class CordenadasSpawn<TipoCasilla, Point> {
    private TipoCasilla casilla;
    private Point cordenadas;

    /**
     * Crea un objeto CordenadasSpawn con la casilla y las coordenadas especificadas.
     * @param objeto1 la casilla asociada a las coordenadas de aparición.
     * @param objeto2 las coordenadas de aparición representadas como un punto.
     */
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
