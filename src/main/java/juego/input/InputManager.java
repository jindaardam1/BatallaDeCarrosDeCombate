package juego.input;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManager implements EventHandler<KeyEvent> {




    @Override
    public void handle(KeyEvent evento) {
        //Se ejecuta cada vez que se presiona una tecla
        System.out.println("Se presiono la tecla:" + evento.getCode());
    }
}
