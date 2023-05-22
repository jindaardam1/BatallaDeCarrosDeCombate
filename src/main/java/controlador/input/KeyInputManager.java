package controlador.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyInputManager implements EventHandler<KeyEvent> {
    public static boolean arriba = false;
    public static  boolean abajo = false;
    public static  boolean izquierda = false;
    public static  boolean derecha = false;
    public static boolean escape = false;


    /**
     * Maneja los eventos de teclado para controlar el movimiento.
     * @param evento El evento de teclado recibido.
     */
    @Override
    public void handle(KeyEvent evento) {
        if (evento.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (evento.getCode()) {
                case W -> arriba = true;
                case S -> abajo = true;
                case A -> izquierda = true;
                case D -> derecha = true;
                default -> {
                }
            }
        } else if (evento.getEventType() == KeyEvent.KEY_RELEASED) {
            switch (evento.getCode()) {
                case W -> arriba = false;
                case S -> abajo = false;
                case A -> izquierda = false;
                case D -> derecha = false;
                default -> {
                }
            }
        }
    }




    /**
     * Devuelve el estado de la dirección "arriba".
     *
     * @return true si la dirección "arriba" está activada, false en caso contrario.
     */
    public static boolean isArriba() {
        return arriba;
    }

    /**
     * Devuelve el estado de la dirección "abajo".
     *
     * @return true si la dirección "abajo" está activada, false en caso contrario.
     */
    public static boolean isAbajo() {
        return abajo;
    }

    /**
     * Devuelve el estado de la dirección "izquierda".
     *
     * @return true si la dirección "izquierda" está activada, false en caso contrario.
     */
    public static boolean isIzquierda() {
        return izquierda;
    }

    /**
     * Devuelve el estado de la dirección "derecha".
     *
     * @return true si la dirección "derecha" está activada, false en caso contrario.
     */
    public static boolean isDerecha() {
        return derecha;
    }
}