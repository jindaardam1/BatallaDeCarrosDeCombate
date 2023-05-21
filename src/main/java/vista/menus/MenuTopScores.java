package vista.menus;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utilidades.eventos.RandomizadorFondo;
import utilidades.eventos.TeclaEscapeUtil;
import utilidades.pantalla.PantallaUtil;

import java.util.Objects;

public class MenuTopScores {

    private Stage escenarioPrincipal;
    private Scene scene;
    public MenuTopScores(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;

        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(RandomizadorFondo.obtenerImagenAleatoria()))), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true))));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloTopScores.css")).toExternalForm());
        root.setId("vbox-menu-top-scores");

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenarioPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenarioPrincipal.setTitle("Menú principal");

        // Configurar el evento de pulsación de tecla para cerrar con "Escape"
        TeclaEscapeUtil.configurarCerrarConEscape(scene, escenarioPrincipal);

        // Mostrar la ventana
        escenarioPrincipal.show();
    }
}
