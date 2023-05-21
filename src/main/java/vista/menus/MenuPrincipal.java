package vista.menus;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utilidades.eventos.RandomizadorFondo;
import utilidades.eventos.PulsarTeclasUtil;
import utilidades.pantalla.PantallaUtil;

import java.util.Objects;

public class MenuPrincipal {
    private Stage escenarioPrincipal;
    private Scene scene;

    public MenuPrincipal(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;

        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(RandomizadorFondo.obtenerImagenAleatoria()))), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true))));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloPrincipal.css")).toExternalForm());
        root.setId("vbox-menu-principal");

        Hyperlink boton1 = new Hyperlink("CLÁSICO");
        Hyperlink boton2 = new Hyperlink("SURVIVAL");
        Hyperlink boton3 = new Hyperlink("TOP SCORES");
        Hyperlink boton4 = new Hyperlink("SALIR");

        boton1.getStyleClass().add("hyperlink-custom");
        boton2.getStyleClass().add("hyperlink-custom");
        boton3.getStyleClass().add("hyperlink-custom");
        boton4.getStyleClass().add("hyperlink-custom");

        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(boton1, boton2, boton3, boton4);

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);

        boton1.setOnAction(actionEvent -> {

        });
        boton2.setOnAction(actionEvent -> {

        });
        boton3.setOnAction(actionEvent -> {
            MenuTopScores mts = new MenuTopScores(escenarioPrincipal);
            mts.mostrar();
        });
        boton4.setOnAction(actionEvent -> escenarioPrincipal.close());
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenarioPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenarioPrincipal.setTitle("Menú principal");

        // Configurar el evento de pulsación de tecla para cerrar con "Escape"
        PulsarTeclasUtil.configurarCerrarConEscape(scene, escenarioPrincipal);

        // Mostrar la ventana
        escenarioPrincipal.show();
    }
}