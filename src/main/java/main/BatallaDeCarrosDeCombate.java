package main;

import dao.InicializarBADA;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.records.InfoUsuario;
import utilidades.log.Logs;
import utilidades.pantalla.PantallaUtil;
import vista.menus.MenuLogin;


/**
 * @author Aitor Zubillaga y Jagoba Inda
 * La clase BatallaDeCarrosDeCombate es una aplicación de JavaFX que representa una batalla de carros de combate.
 * Implementa la interfaz Application de JavaFX y proporciona métodos para iniciar y detener la aplicación.
 */
public class BatallaDeCarrosDeCombate extends Application {

    public static InfoUsuario nickname = null;

    /**
     * El método principal de la aplicación. Imprime un mensaje de inicio y luego llama al método start para iniciar la aplicación.
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        System.out.println("Tanques pium pium");
        Logs.infoLogManager("Tanques pium pium");
        // Inicia el programa llamando al método start
        launch(args);

    }

    /**
     * El método start de JavaFX, que se llama cuando la aplicación se inicia.
     * Obtiene el ancho y la altura de la pantalla, establece el estilo de decorador de la ventana principal
     * y muestra el menú de inicio de sesión.
     * @param escenarioPrincipal el escenario principal de la aplicación
     */
    @Override
    public void start(Stage escenarioPrincipal) {
        InicializarBADA.crearBADA();
        double ancho = PantallaUtil.obtenerAnchoPantalla();
        double altura = PantallaUtil.obtenerAlturaPantalla();
        System.out.println("Ancho de pantalla: " + ancho);
        System.out.println("Altura de pantalla: " + altura);
        Logs.debugLogManager("Ancho de pantalla: " + ancho);
        Logs.debugLogManager("Ancho de pantalla: " + ancho);

        // Establecer el estilo de decorador de ventana
        escenarioPrincipal.setResizable(false);
        escenarioPrincipal.initStyle(StageStyle.UNDECORATED);

        MenuLogin login = new MenuLogin(escenarioPrincipal);
        login.mostrar();
    }

    /**
     * El método stop de JavaFX, que se llama cuando la aplicación se detiene.
     * Sale del programa cerrando la aplicación.
     */
    @Override
    public void stop() {
        System.exit(0);
    }
}