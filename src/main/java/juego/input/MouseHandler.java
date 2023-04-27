package juego.input;

import javafx.scene.input.MouseEvent;

public class MouseHandler {

    private boolean isMousePressed = false;
    private double mouseX;
    private double mouseY;

    public void mouseClicked(MouseEvent event) {
        // maneja el evento de clic de ratón
        // aquí puedes usar los valores de event.getX() y event.getY()
    }
    public void mouseMoved(MouseEvent event) {
        // maneja el evento de movimiento de ratón
        // aquí puedes usar los valores de event.getX() y event.getY()
    }

    public void mousePressed(MouseEvent event) {
        // maneja el evento de pulsación de ratón
        // aquí puedes usar los valores de event.getX() y event.getY()
        mouseX = event.getX();
        mouseY = event.getY();
        isMousePressed = true;
    }

    public void mouseReleased(MouseEvent event) {
        // maneja el evento de liberación de ratón
        // aquí puedes usar los valores de event.getX() y event.getY()
        isMousePressed = false;
    }

    public void mouseDragged(MouseEvent event) {
        // maneja el evento de arrastrar el ratón
        // aquí puedes usar los valores de event.getX() y event.getY()
        mouseX = event.getX();
        mouseY = event.getY();
    }

    public boolean isMousePressed() {
        // devuelve verdadero si el botón del ratón está presionado actualmente
        return isMousePressed;
    }

    public double getMouseX() {
        // devuelve la posición X actual del cursor del ratón en la ventana
        return mouseX;
    }

    public double getMouseY() {
        // devuelve la posición Y actual del cursor del ratón en la ventana
        return mouseY;
    }




}
