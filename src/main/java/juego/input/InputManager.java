package juego.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManager implements EventHandler<KeyEvent> {
    public static boolean arriba = false;
    public static  boolean abajo = false;
    public static  boolean izquierda = false;
    public static  boolean derecha = false;

    @Override
    public void handle(KeyEvent evento) {
        if (evento.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (evento.getCode()) {
                case W:
                    arriba = true;
                    System.out.println("Se pulsó arriba");
                    break;
                case S:
                    abajo = true;
                    System.out.println("Se pulsó abajo");
                    break;
                case A:
                    izquierda = true;
                    System.out.println("Se pulsó izquierda");
                    break;
                case D:
                    derecha = true;
                    System.out.println("Se pulsó derecha");
                    break;
                default:
                    break;
            }
        } else if (evento.getEventType() == KeyEvent.KEY_RELEASED) {
            switch (evento.getCode()) {
                case W:
                    arriba = false;
                    System.out.println("Se soltó arriba");
                    break;
                case S:
                    abajo = false;
                    System.out.println("Se soltó abajo");
                    break;
                case A:
                    izquierda = false;
                    System.out.println("Se soltó izquierda");
                    break;
                case D:
                    derecha = false;
                    System.out.println("Se soltó derecha");
                    break;
                default:
                    break;
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
