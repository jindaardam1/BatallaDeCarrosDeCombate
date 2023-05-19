package utilidades.pantalla;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class PantallaUtil {

    public static final int HEIGHT_VENTANA = 800;
    public static final int WIDTH_VENTANA = 1000;

    public static Rectangle2D obtenerDimensionesPantalla() {
        // Obtiene el objeto Screen principal
        Screen pantalla = Screen.getPrimary();
        // Obtiene la dimension de la pantalla
        return pantalla.getBounds();
    }

    public static int obtenerAnchoPantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return (int) dimensiones.getWidth();
    }

    public static int obtenerAlturaPantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return (int) dimensiones.getHeight();
    }

    // Otras funciones que pueden ser útiles

    public static int obtenerAnchoDisponiblePantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return (int) (dimensiones.getWidth() - dimensiones.getMinX());
    }

    public static int obtenerAlturaDisponiblePantalla() {
        Rectangle2D dimensiones = obtenerDimensionesPantalla();
        return (int) (dimensiones.getHeight() - dimensiones.getMinY());
    }

    public static Point2D obtenerCentroPantalla() {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        double centerX = bounds.getMinX() + (bounds.getWidth() / 2);
        double centerY = bounds.getMinY() + (bounds.getHeight() / 2);
        return new Point2D(centerX, centerY);
    }

}
