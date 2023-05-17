package controlador.input;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Bala;
import modelo.Jugador;
import vista.juego.CampoDeBatalla;


public class MouseInputManager implements EventHandler<MouseEvent> {

    private static double mouseX;
    private static double mouseY;

    public static Boolean clickIzquierdo = false;


    public MouseInputManager() {



    }

    @Override
    public void handle(MouseEvent event) {


        // obtener las coordenadas x e y del ratón en la escena



        // Convertir las coordenadas del ratón a coordenadas en relación al GraphicsContext
        double canvasMouseX = CampoDeBatalla.lienzo.sceneToLocal(event.getSceneX(), event.getSceneY()).getX();
        double canvasMouseY =CampoDeBatalla.lienzo.sceneToLocal(event.getSceneX(), event.getSceneY()).getY();

        // obtener las coordenadas x e y del ratón en relación al GraphicsContext
        mouseX =  canvasMouseX;
        mouseY = canvasMouseY ;
       // System.out.println("Raton:X:" + mouseX + ";" + "Y: " + mouseY);




        if (event.getButton() == javafx.scene.input.MouseButton.PRIMARY) {
            System.out.println("Hiciste click izquierdo");
                clickIzquierdo = true;

        }else{
                clickIzquierdo = false;
        }
    }






    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }
    public static boolean getClickIzquierdo(){return clickIzquierdo;}
}
