package juego.utils;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class PantallaUtil {

    public static Rectangle2D obtenerDimensionesPantalla() {
        // Obtiene el objeto Screen principal
        Screen pantalla = Screen.getPrimary();
        // Obtiene la dimension de la pantalla
        Rectangle2D dimensiones = pantalla.getBounds();
        return dimensiones;
    }

    public static double obtenerAnchoPantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return dimensiones.getWidth();
    }

    public static double obtenerAlturaPantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return dimensiones.getHeight();
    }

    // Otras funciones que pueden ser útiles

    public static double obtenerAnchoDisponiblePantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return dimensiones.getWidth() - dimensiones.getMinX();
    }

    public static double obtenerAlturaDisponiblePantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return dimensiones.getHeight() - dimensiones.getMinY();
    }

    public static Point2D obtenerCentroPantalla() {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        double centerX = bounds.getMinX() + (bounds.getWidth() / 2);
        double centerY = bounds.getMinY() + (bounds.getHeight() / 2);
        return new Point2D(centerX, centerY);
    }

}
