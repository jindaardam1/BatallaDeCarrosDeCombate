package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.records.InfoUsuario;
import utilidades.pantalla.PantallaUtil;
import vista.menus.MenuLogin;


/**
 * @author Aitor Zubillaga y Jagoba Inda
 */
public class BatallaDeCarrosDeCombate extends Application {

    public static InfoUsuario nickname = null;

    public static void main(String[] args) {
        System.out.println("Tanques pium pium");
        // Inicia el programa llamando al método start
        launch(args);

    }

    @Override
    public void start(Stage escenarioPrincipal) {
        double ancho = PantallaUtil.obtenerAnchoPantalla();
        double altura = PantallaUtil.obtenerAlturaPantalla();
        System.out.println("Ancho de pantalla: " + ancho);
        System.out.println("Altura de pantalla: " + altura);

        // Establecer el estilo de decorador de ventana
        escenarioPrincipal.setResizable(false);
        escenarioPrincipal.initStyle(StageStyle.UNDECORATED);

        MenuLogin login = new MenuLogin(escenarioPrincipal);
        login.mostrar();
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}