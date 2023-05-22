package vista.menus;

import dao.records.Skin;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import servicio.ServicioSkins;
import utilidades.eventos.PulsarTeclasUtil;
import utilidades.eventos.RandomizadorFondo;
import utilidades.pantalla.PantallaUtil;

import java.util.ArrayList;
import java.util.Objects;


/**
 * La clase MenuSkins representa un menú que muestra las skins disponibles para el juego.
 * Permite al usuario seleccionar una skin y regresar al menú principal.
 */
public class MenuSkins {

    private Stage escenarioPrincipal;
    private Scene scene;

    /**
     * Constructor de la clase MenuSkins.
     *
     * @param escenarioPrincipal El escenario principal de la aplicación.
     */
    public MenuSkins(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(RandomizadorFondo.obtenerImagenAleatoria()))), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true))));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloTopScoresYSkins.css")).toExternalForm());

        GridPane gridPane = new GridPane();
        gridPane.setId("grid-pane");

        ArrayList<Skin> skins = ServicioSkins.getSkins();

        for (int i = 0; i < skins.size(); i++) {
            Skin skin = skins.get(i);

            Label labelScore = new Label(skin.NOMBRE() + " - " + skin.CONSEGUIDA() + " - " + skin.JUGADOR_NICKNAME());
            labelScore.getStyleClass().add("label-score");
            labelScore.getStyleClass().add("oro");

            gridPane.add(labelScore, 0, i);
        }

        root.getChildren().add(gridPane);

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

        VBox.setMargin(anchorPane, new Insets(10));

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
    }

    /**
     * Muestra el menú de selección de skins en el escenario principal.
     */
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
