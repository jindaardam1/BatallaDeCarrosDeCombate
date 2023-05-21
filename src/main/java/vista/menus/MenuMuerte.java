package vista.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilidades.eventos.PulsarTeclasUtil;
import utilidades.pantalla.PantallaUtil;

import java.util.Objects;

public class MenuMuerte {
    private Stage escenarioPrincipal;
    private Scene scene;

    public MenuMuerte(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;

        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloPrincipal.css")).toExternalForm());
        root.setId("vbox-menu-muerte");

        Label gameoverLabel = new Label("GAME OVER");
        gameoverLabel.setTextFill(Color.RED);
        gameoverLabel.getStyleClass().add("gameover-label");

        Button volverBtn = new Button("Volver al Menú Principal");
        volverBtn.getStyleClass().add("volver-btn");
        volverBtn.setOnAction(actionEvent -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal(escenarioPrincipal);
            menuPrincipal.mostrar();
        });

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(gameoverLabel, volverBtn);

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenarioPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenarioPrincipal.setTitle("Game Over");

        // Configurar el evento de pulsación de tecla para cerrar con "Escape"
        PulsarTeclasUtil.configurarCerrarConEscape(scene, escenarioPrincipal);

        // Mostrar la ventana
        escenarioPrincipal.show();
    }
}
