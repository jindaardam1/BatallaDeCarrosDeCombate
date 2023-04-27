package juego.input;

import javafx.scene.input.KeyCode;

public class KeyboardHandler {
    public void handleKeyDown(KeyCode keyCode) {
        // Manejar el evento de tecla pulsada
        switch (keyCode) {
            case UP:
                System.out.println("Se pulso Up");
                // Mover el personaje hacia arriba
                break;
            case DOWN:
                System.out.println("Se pulso DOWN");
                // Mover el personaje hacia abajo
                break;
            case LEFT:
                System.out.println("Se pulso LEFT");
                // Mover el personaje hacia la izquierda
                break;
            case RIGHT:
                System.out.println("Se pulso RIGHT");
                // Mover el personaje hacia la derecha
                break;
            case SPACE:
                System.out.println("Se pulso SPACE");
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
                System.out.println("Se solto Up");
                // Detener el movimiento hacia arriba
                break;
            case DOWN:
                System.out.println("Se solto RIGHT");
                // Detener el movimiento hacia abajo
                break;
            case LEFT:
                System.out.println("Se solto LEFT");
                // Detener el movimiento hacia la izquierda
                break;
            case RIGHT:
                System.out.println("Se solto RIGHT");
                // Detener el movimiento hacia la derecha
                break;
            default:
                // No hacer nada
        }
    }
}
