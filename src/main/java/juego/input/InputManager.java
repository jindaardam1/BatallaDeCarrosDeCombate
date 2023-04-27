package juego.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputManager {

    private final KeyboardHandler keyboardHandler;
    private final MouseHandler mouseHandler;

    public InputManager(Scene scene) {
        this.keyboardHandler = new KeyboardHandler();
        this.mouseHandler = new MouseHandler();

        scene.setOnKeyPressed(this::handleKeyDown);
        scene.setOnKeyReleased(this::handleKeyUp);
       // scene.setOnMouseClicked(this::handleMouseClick);
        //scene.setOnMouseMoved(this::handleMouseMove);
    }

    public void handleKeyDown(javafx.scene.input.KeyEvent event) {
        KeyCode keyCode = event.getCode();
        this.keyboardHandler.handleKeyDown(keyCode);
    }

    public void handleKeyUp(javafx.scene.input.KeyEvent event) {
        KeyCode keyCode = event.getCode();
        this.keyboardHandler.handleKeyUp(keyCode);
    }

 /*   public void handleMouseClick(javafx.scene.input.MouseEvent event) {
        this.mouseHandler.mouseClicked(event);
    }

    public void handleMouseMove(javafx.scene.input.MouseEvent event) {
        this.mouseHandler.mouseMoved(event);
    }*/
}
