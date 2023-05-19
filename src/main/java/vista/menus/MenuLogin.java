package vista.menus;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilidades.pantalla.PantallaUtil;

public class MenuLogin {
    private Stage escenaPrincipal;
    private Scene scene;

    public MenuLogin(Stage escenaPrincipal) {
        this.escenaPrincipal = escenaPrincipal;

        // Crear el Pane con fondo verde
        Pane root = new Pane();
        root.setStyle("-fx-background-color: green;");

        // Crear la escena con el Pane como nodo raíz
        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenaPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenaPrincipal.setTitle("Ventana Verde");

        // Mostrar la ventana
        escenaPrincipal.show();
    }
}
