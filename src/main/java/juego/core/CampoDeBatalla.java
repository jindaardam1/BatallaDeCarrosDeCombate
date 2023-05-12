package juego.core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import juego.input.InputManager;
import juego.utils.PantallaUtil;
import juego.utils.TipoCasilla;

import java.lang.reflect.GenericArrayType;
import java.util.Objects;

public class CampoDeBatalla extends Application {
    private static final double ANCHO_VENTANA =800;
    private static final double ALTO_VENTANA = 800;
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas lienzo;
    private Pane panel;
    private TipoCasilla[][] casillas;

    private Image imgNada;
    private Image imgHoyo;
    private Image imgPared;

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
        root = new Group();
        panel = new Pane();
        lienzo = new Canvas(ANCHO_VENTANA, ALTO_VENTANA);
        root.getChildren().add(lienzo);
        escena = new Scene(panel, ANCHO_VENTANA, ALTO_VENTANA);
        graficos = lienzo.getGraphicsContext2D();
        pintarEscenario(panel); // Llamar al método después de agregar el panel a la escena
    }



    public void pintarEscenario(Pane panel) {
        MapaProcedural mapa = new MapaProcedural(28,36);
        mapa.generarMapa();

        this.casillas = mapa.getMapa();
        this.imgNada = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/NADA.jpg")));
        this.imgHoyo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/HOYO.jpg")));
        this.imgPared = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/PARED.jpg")));
        // Obtener el tamaño de cada casilla en función del tamaño de la ventana y del tamaño del array
        double casillaWidth = panel.getWidth() / casillas[0].length;
        double casillaHeight = panel.getHeight() / casillas.length;

        // Recorrer el array de casillas y agregar cada imagen al pane
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
                }

                imageView.setFitWidth(casillaWidth);
                imageView.setFitHeight(casillaHeight);
                imageView.setX(j * casillaWidth);
                imageView.setY(i * casillaHeight);
                panel.getChildren().add(imageView);

            }
        }
    }

    public void gestionEventos() {
        escena.setOnKeyPressed(new InputManager());

    }
}
