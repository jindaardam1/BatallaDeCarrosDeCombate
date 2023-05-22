package vista.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloMuerte.css")).toExternalForm());
        root.setId("vbox-menu-muerte");

        Label label = new Label("GAME OVER");
        label.setId("label-game-over");

        root.getChildren().add(label);

        AnchorPane anchorPane = new AnchorPane();

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/VentanaPrincipal/atras.png"))));

        imageView.setFitWidth(60);
        imageView.setFitHeight(60);

        Label labelAtras = new Label("MENÚ PRINCIPAL");
        labelAtras.setId("label-atras");

        anchorPane.getChildren().addAll(imageView, labelAtras);

        anchorPane.setOnMouseClicked(event -> {
            MenuPrincipal mp = new MenuPrincipal(escenarioPrincipal);
            mp.mostrar();
        });

        root.getChildren().add(anchorPane);

        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
    }
    public void ocultar() {
        escenarioPrincipal.close();
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenarioPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenarioPrincipal.setTitle("Game Over");

        // Configurar el evento de pulsación de tecla para cerrar con "Escape"
        PulsarTeclasUtil.configurarVolverMenuPrincipal(scene, escenarioPrincipal);

        // Mostrar la ventana
        escenarioPrincipal.show();
    }
}
