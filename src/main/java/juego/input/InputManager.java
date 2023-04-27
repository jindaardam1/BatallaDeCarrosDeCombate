package juego.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputManager {

    // Declaración de variables para el manejador de teclado y manejador de mouse
    private final KeyboardHandler keyboardHandler;
    private final MouseHandler mouseHandler;

    // Constructor que toma una escena y crea los manejadores de teclado y mouse y establece los listeners necesarios
    public InputManager(Scene scene) {
        this.keyboardHandler = new KeyboardHandler();
        this.mouseHandler = new MouseHandler();

        // Establece los listeners para el teclado
        scene.setOnKeyPressed(this::handleKeyDown);
        scene.setOnKeyReleased(this::handleKeyUp);

        // Establece los listeners para el mouse
        scene.setOnMouseClicked(this::handleMouseClick);
        scene.setOnMouseMoved(this::handleMouseMove);
    }

    // Manejador de eventos para cuando se presiona una tecla
    public void handleKeyDown(javafx.scene.input.KeyEvent event) {
        KeyCode keyCode = event.getCode();
        this.keyboardHandler.handleKeyDown(keyCode);

    }

    // Manejador de eventos para cuando se suelta una tecla
    public void handleKeyUp(javafx.scene.input.KeyEvent event) {
        KeyCode keyCode = event.getCode();
        this.keyboardHandler.handleKeyUp(keyCode);
    }

    // Manejador de eventos para cuando se hace clic en el mouse
    public void handleMouseClick(javafx.scene.input.MouseEvent event) {
        this.mouseHandler.mouseClicked(event);
    }

    // Manejador de eventos para cuando se mueve el mouse
    public void handleMouseMove(javafx.scene.input.MouseEvent event) {
        this.mouseHandler.mouseMoved(event);
    }
}
