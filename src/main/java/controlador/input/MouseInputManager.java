package controlador.input;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Jugador;


public class MouseInputManager implements EventHandler<MouseEvent> {
    private static ImageView tankImage;
    private static double mouseX;
    private static double mouseY;
    private double tankCenterX;
    private double tankCenterY;


    public MouseInputManager(ImageView tanquerImagen) {

        this.tankImage = tanquerImagen;

    }

    @Override
    public void handle(MouseEvent event) {


        // obtener las coordenadas x e y del ratón en la escena
         mouseX = event.getSceneX();
         mouseY = event.getSceneY();
        System.out.println("Raton:X:" + mouseX + ";" + "Y: " + mouseY);


        // obtener las coordenadas x e y del centro de la imagen del tanque
        tankCenterX = Jugador.x;
        tankCenterY = Jugador.y;

        System.out.println("Tanque:X:" + tankCenterX + ";" + "Y: " + tankCenterY);


    }

    public static ImageView getTankImage() {
        return tankImage;
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }
}
