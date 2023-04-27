package juego.input;

import javafx.scene.input.KeyCode;

public class KeyboardHandler {
    public void handleKeyDown(KeyCode keyCode) {
        // Manejar el evento de tecla pulsada
        switch (keyCode) {
            case UP:
                // Mover el personaje hacia arriba
                break;
            case DOWN:
                // Mover el personaje hacia abajo
                break;
            case LEFT:
                // Mover el personaje hacia la izquierda
                break;
            case RIGHT:
                // Mover el personaje hacia la derecha
                break;
            case SPACE:
                // Disparar
                break;
            default:
                // No hacer nada
        }
    }

    public void handleKeyUp(KeyCode keyCode) {
        // Manejar el evento de tecla liberada
        switch (keyCode) {
            case UP:
                // Detener el movimiento hacia arriba
                break;
            case DOWN:
                // Detener el movimiento hacia abajo
                break;
            case LEFT:
                // Detener el movimiento hacia la izquierda
                break;
            case RIGHT:
                // Detener el movimiento hacia la derecha
                break;
            default:
                // No hacer nada
        }
    }
}
