package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilidades.pantalla.PantallaUtil;
import vista.menus.MenuLogin;


/**
 * @author Aitor Zubillaga y Jagoba Inda
 */
public class BatallaDeCarrosDeCombate extends Application {

    public static void main(String[] args) {
        System.out.println("Tanques pium pium");
        // Inicia el programa llamando al método start
        launch(args);

    }

    @Override
    public void start(Stage escenaPrincipal) {
        double ancho = PantallaUtil.obtenerAnchoPantalla();
        double altura = PantallaUtil.obtenerAlturaPantalla();
        System.out.println("Ancho de pantalla: " + ancho);
        System.out.println("Altura de pantalla: " + altura);

        MenuLogin ventanaVerde = new MenuLogin(escenaPrincipal);
        ventanaVerde.mostrar();
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}