package juego.core;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import juego.input.InputManager;
import juego.utils.PantallaUtil;
import juego.utils.TipoCasilla;

import java.lang.reflect.GenericArrayType;
import java.util.Objects;

public class CampoDeBatalla extends Application {
    private static final double ANCHO_VENTANA = PantallaUtil.obtenerAnchoDisponiblePantalla();
    private static final double ALTO_VENTANA = PantallaUtil.obtenerAlturaDisponiblePantalla();
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas lienzo;
    private GridPane panel;
    private TipoCasilla[][] casillas;

    private Image imgNada;
    private Image imgHoyo;
    private Image imgPared;
    private Image imgOtro;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ventana) throws Exception {
        inizializarComponentes();
        gestionEventos();
        ventana.setScene(escena);
        ventana.setTitle("CampoDeBatalla");
        ventana.show();

    }

    public void inizializarComponentes() {
        panel = new GridPane();
        StackPane contenedorPanel = new StackPane();

        contenedorPanel.getChildren().add(panel);
        contenedorPanel.setAlignment(Pos.BOTTOM_RIGHT);

        escena = new Scene(contenedorPanel, ANCHO_VENTANA, ALTO_VENTANA);

        pintarEscenario(panel); // Llamar al método después de agregar el panel a la escena
        panel.setGridLinesVisible(true);
        // Crear un ImageView con la imagen de fondo
        ponerFondo(contenedorPanel);

    }

    private void ponerFondo(StackPane contenedorPanel) {
        ImageView fondo = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/fondoCampoBatalla.png"))));
        fondo.fitWidthProperty().bind(escena.widthProperty());
        fondo.fitHeightProperty().bind(escena.heightProperty());
        contenedorPanel.getChildren().add(fondo);
        fondo.toBack();
    }


    public void pintarEscenario(GridPane gridPane) {
        MapaProcedural mapa = new MapaProcedural(20, 20);
        mapa.generarMapa();

        this.casillas = mapa.getMapa();
        this.imgNada = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/NADA.jpg")));
        this.imgHoyo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/HOYO.jpg")));
        this.imgPared = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/PARED.jpg")));
        this.imgOtro = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/OTRO.jpg")));



        // Recorrer el array de casillas y agregar cada imagen al gridPane
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                ImageView imageView = new ImageView();

                // Escalar la imagen en función del tamaño de la casilla
                if (casillas[i][j] == TipoCasilla.NADA) {
                    if (imgNada != null) {
                        imageView.setImage(imgNada);
                    } else {
                        System.err.println("No se pudo cargar la imagen de NADA.");
                    }
                } else if (casillas[i][j] == TipoCasilla.HOYO) {
                    if (imgHoyo != null) {
                        imageView.setImage(imgHoyo);
                    } else {
                        System.err.println("No se pudo cargar la imagen de HOYO.");
                    }
                } else if (casillas[i][j] == TipoCasilla.PARED) {
                    if (imgPared != null) {
                        imageView.setImage(imgPared);
                    } else {
                        System.err.println("No se pudo cargar la imagen de PARED.");
                    }
                } else {
                    if (imgOtro != null) {
                        imageView.setImage(imgOtro);
                    } else {
                        System.err.println("No se pudo cargar la imagen de PARED.");
                    }
                }

                // Ajustar la imagen para mantener su relación de aspecto
                imageView.setPreserveRatio(true);

                // Ajustar el tamaño de la imagen a la celda
                imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(casillas[0].length));
                imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(casillas.length ));


                // Agregar la imagen al GridPane en la posición desplazada
                gridPane.add(imageView, j  , i);

            }
        }
    }





    public void gestionEventos() {
        escena.setOnKeyPressed(new InputManager());

    }
}
