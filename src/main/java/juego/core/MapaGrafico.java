package juego.core;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import juego.utils.TipoCasilla;

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

    @Override
    protected void layoutChildren() {
        // Dibuja las celdas del mapa
        Canvas canvas = new Canvas(getWidth(), getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                int x = j * anchoCelda;
                int y = i * altoCelda;
                switch (mapa[i][j]) {
                    case NADA -> gc.setFill(Color.WHITE);
                    case PARED -> gc.setFill(Color.BLACK);
                    case HOYO -> gc.setFill(Color.BLUE);
                }

                gc.fillRect(x, y, anchoCelda, altoCelda);
            }
        }
        getChildren().setAll(canvas);
    }
}
