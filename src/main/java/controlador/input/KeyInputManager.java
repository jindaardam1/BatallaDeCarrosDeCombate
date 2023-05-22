package controlador.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyInputManager implements EventHandler<KeyEvent> {
    public static boolean arriba = false;
    public static  boolean abajo = false;
    public static  boolean izquierda = false;
    public static  boolean derecha = false;
    public static boolean escape = false;


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




    public static boolean isArriba() {
        return arriba;
    }

    public static boolean isAbajo() {
        return abajo;
    }

    public static boolean isIzquierda() {
        return izquierda;
    }

    public static boolean isDerecha() {
        return derecha;
    }
}