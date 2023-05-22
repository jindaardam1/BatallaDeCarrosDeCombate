package vista.menus;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import servicio.ServicioPartida;
import servicio.ServicioScore;
import servicio.ServicioSkins;
import utilidades.eventos.Guardado;
import utilidades.eventos.RandomizadorFondo;
import utilidades.eventos.PulsarTeclasUtil;
import utilidades.pantalla.PantallaUtil;
import vista.juego.CampoDeBatalla;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class MenuPrincipal {
    private static Stage escenarioPrincipal;
    private Scene scene;
    public static CampoDeBatalla campoDeBatalla;

    public MenuPrincipal(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;

        String audioPath = Objects.requireNonNull(getClass().getResource("/musica/MenuPrincipalCancion.mp3")).toExternalForm();

        Media media = new Media(audioPath);

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.setVolume(0.05);

        mediaPlayer.play();

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
        Hyperlink boton4 = new Hyperlink("SKINS");
        Hyperlink boton5 = new Hyperlink("SALIR");

        boton1.getStyleClass().add("hyperlink-custom");
        boton2.getStyleClass().add("hyperlink-custom");
        boton3.getStyleClass().add("hyperlink-custom");
        boton4.getStyleClass().add("hyperlink-custom");
        boton5.getStyleClass().add("hyperlink-custom");

        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(boton1, boton2, boton3, boton4, boton5);

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);

        boton1.setOnAction(actionEvent -> {
            MenuMuerte mm = new MenuMuerte(escenarioPrincipal);
            mm.mostrar();
            mediaPlayer.stop();
        });
        boton2.setOnAction(actionEvent -> {

            campoDeBatalla = new CampoDeBatalla();
            campoDeBatalla.start(escenarioPrincipal);
            mediaPlayer.stop();
        });
        boton3.setOnAction(actionEvent -> {
            MenuTopScores mts = new MenuTopScores(escenarioPrincipal);
            mts.mostrar();
            mediaPlayer.stop();
        });
        boton4.setOnAction(actionEvent -> {
            MenuSkins ms = new MenuSkins(escenarioPrincipal);
            ms.mostrar();
            mediaPlayer.stop();
        });
        boton5.setOnAction(actionEvent -> escenarioPrincipal.close());
    }

    public static void pasarNivel() {
        ServicioPartida.cargarPartidaGuardada();
        ServicioPartida.guardarPartida(Guardado.getScore());


        campoDeBatalla.borrarElementos();
        campoDeBatalla.acabarCiclo();
        campoDeBatalla = new CampoDeBatalla();

        campoDeBatalla.iniciar(escenarioPrincipal);
        if ((int) (Math.random() * 20) == 1) {
            ServicioSkins.desbloquearSkin((int) (Math.random() * 10));
        }


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
