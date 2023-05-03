package juego.utils;

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

}
