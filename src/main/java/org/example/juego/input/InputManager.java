package org.example.juego.input;

import org.example.juego.core.Main;
import org.example.juego.core.Ventana;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputManager {

    public InputManager(Ventana ventana) {
        // crea instancias de los listeners del teclado y del ratón
        KeyboardHandler keyboardHandler = new KeyboardHandler();
        MouseHandler mouseHandler = new MouseHandler();

        // añade los listeners al componente del juego
        ventana.addKeyListener(keyboardHandler);
        ventana.addMouseListener(mouseHandler);
        ventana.addMouseMotionListener(mouseHandler);
    }


    public void handleKeyDown(int keyCode) {
        // maneja el evento de tecla pulsada
        // ...
    }

    public void handleKeyUp(int keyCode) {
        // maneja el evento de tecla liberada
        // ...
    }

    public void handleMouseClick(int x, int y) {
        // maneja el evento de clic de ratón
        // ...
    }

    public void handleMouseMove(int x, int y) {
        // maneja el evento de movimiento de ratón
        // ...
    }

    private class KeyboardHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            handleKeyDown(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            handleKeyUp(e.getKeyCode());
        }
    }

    public class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            handleMouseClick(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            handleMouseMove(e.getX(), e.getY());
        }
    }
}