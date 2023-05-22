package controlador.input;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import vista.juego.CampoDeBatalla;


public class MouseInputManager implements EventHandler<MouseEvent> {

    private static double mouseX;
    private static double mouseY;

    public static Boolean clickIzquierdo = false;


    public MouseInputManager() {



    }

    /**
     * Maneja el evento del ratón.
     * Registra las coordenadas del ratón y el tipo de clic realizado.
     *
     * @param event El evento del ratón.
     */
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

    /**
     * Retorna la posición X actual del mouse.
     * @return La posición X del mouse.
     */
    public static double getMouseX() {
        return mouseX;
    }

    /**
     * Retorna la posición Y actual del mouse.
     * @return La posición Y del mouse.
     */
    public static double getMouseY() {
        return mouseY;
    }
    public static boolean getClickIzquierdo(){return clickIzquierdo;}
}
