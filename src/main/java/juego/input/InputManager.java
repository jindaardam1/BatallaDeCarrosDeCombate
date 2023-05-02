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
        scene.setOnKeyPressed(event -> keyboardHandler.handleKeyDown(event.getCode()));
        scene.setOnKeyReleased(event -> keyboardHandler.handleKeyUp(event.getCode()));

        // Establece los listeners para el mouse
        scene.setOnMouseClicked(event -> mouseHandler.mouseClicked(event));
        scene.setOnMouseMoved(event -> mouseHandler.mouseMoved(event));
    }

    // Manejador de eventos para cuando se presiona una tecla
    public void handleKeyDown(javafx.scene.input.KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (keyCode == KeyCode.SPACE) {
            System.out.println("ImputManager: Se pulso tecla");
        }

        this.keyboardHandler.handleKeyDown(keyCode);


    }

    // Manejador de eventos para cuando se suelta una tecla
    public void handleKeyUp(javafx.scene.input.KeyEvent event) {
        System.out.println("ImputManager: Se solto tecla");
        KeyCode keyCode = event.getCode();
        this.keyboardHandler.handleKeyUp(keyCode);
    }

    // Manejador de eventos para cuando se hace clic en el mouse
    public void handleMouseClick(javafx.scene.input.MouseEvent event) {
        System.out.println("ImputManager: Se clico raton");
        this.mouseHandler.mouseClicked(event);
    }

    // Manejador de eventos para cuando se mueve el mouse
    public void handleMouseMove(javafx.scene.input.MouseEvent event) {
        System.out.println("ImputManager: Se movio raton");
        this.mouseHandler.mouseMoved(event);
    }
}
