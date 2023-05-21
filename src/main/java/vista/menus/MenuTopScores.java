package vista.menus;

import dao.records.Score;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import servicio.ServicioScore;
import utilidades.eventos.RandomizadorFondo;
import utilidades.eventos.PulsarTeclasUtil;
import utilidades.pantalla.PantallaUtil;

import java.util.List;
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

        GridPane gridPane = new GridPane();
        gridPane.setId("scores-grid-pane");

        List<Score> mejoresScores = ServicioScore.los10Mejores();

        for (int i = 0; i < mejoresScores.size(); i++) {
            Score score = mejoresScores.get(i);

            Label labelScore = new Label(score.JUGADOR_NICKNAME() + " - " + score.NUM_MAPAS());
            labelScore.getStyleClass().add("label-score");

            switch (i) {
                case 0 -> labelScore.getStyleClass().add("oro");
                case 1 -> labelScore.getStyleClass().add("plata");
                case 2 -> labelScore.getStyleClass().add("bronce");
                default -> labelScore.getStyleClass().add("casi-pero-no");
            }

            gridPane.add(labelScore, 0, i);
        }

        root.getChildren().add(gridPane);

        AnchorPane anchorPane = new AnchorPane();

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/VentanaPrincipal/atras.png"))));

        imageView.setFitWidth(60);  // Ancho deseado de la imagen
        imageView.setFitHeight(60);

        Label labelAtras = new Label("MENÚ PRINCIPAL");
        labelAtras.setId("label-atras");

        anchorPane.getChildren().addAll(imageView, labelAtras);

        anchorPane.setOnMouseClicked(event -> {
            MenuPrincipal mp = new MenuPrincipal(escenarioPrincipal);
            mp.mostrar();
        });

        root.getChildren().add(anchorPane);

        VBox.setMargin(anchorPane, new Insets(10));

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenarioPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenarioPrincipal.setTitle("Menú principal");

        // Volver al menú principal con enter
        PulsarTeclasUtil.configurarVolverMenuPrincipal(scene, escenarioPrincipal);

        // Mostrar la ventana
        escenarioPrincipal.show();
    }
}
