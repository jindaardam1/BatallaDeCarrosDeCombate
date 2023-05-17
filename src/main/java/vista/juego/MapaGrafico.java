package vista.juego;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import modelo.mapa.TipoCasilla;

public class MapaGrafico extends Region {

    private final TipoCasilla[][] mapa;
    private final int anchoCelda = 20;
    private final int altoCelda = 20;

    public MapaGrafico(TipoCasilla[][] mapa) {
        this.mapa = mapa;
    }

  /*  @Override
    public double prefWidth(double height) {
        return mapa[0].length * anchoCelda;
    }

    @Override
    public double prefHeight(double width) {
        return mapa.length * altoCelda;
    }*/

    //dibuja el mapa
    @Override
    protected void layoutChildren() {
        // Se crea un objeto Canvas del tamaño de la región
        Canvas canvas = new Canvas(getWidth(), getHeight());

        // Contexto gráfico del Canvas
        GraphicsContext lienzo = canvas.getGraphicsContext2D();
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                int x = j * anchoCelda;
                int y = i * altoCelda;
                switch (mapa[i][j]) {
                    case NADA -> lienzo.setFill(Color.WHITE);
                    case PARED -> lienzo.setFill(Color.BLACK);
                    case HOYO -> lienzo.setFill(Color.BLUE);
                }
                // Se dibuja la celda
                lienzo.fillRect(x, y, anchoCelda, altoCelda);
            }
        }
        // Se establece el Canvas como el único hijo de la región
        getChildren().setAll(canvas);
    }

}
