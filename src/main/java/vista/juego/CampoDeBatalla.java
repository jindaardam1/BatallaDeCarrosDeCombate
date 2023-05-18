package vista.juego;

import controlador.input.KeyInputManager;
import controlador.input.MouseInputManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import modelo.Bala;
import modelo.Jugador;
import modelo.mapa.MapaProcedural;
import modelo.mapa.TipoCasilla;
import modelo.records.RectangleTipo;

import java.awt.*;
import java.util.Objects;

public class CampoDeBatalla extends Application {
    private static final int ANCHO_VENTANA = 1000;
    private static final int ALTO_VENTANA = 1000;
    private static final int CANTIDADFILAS = 20;
    private static final int CANTIDADCOLUMNAS = 27;

    private Scene escena;
    private StackPane stackPane;
    private TipoCasilla[][] casillas;

    private Image imgNada;
    private Image imgHoyo;
    private Image imgPared;
    private Image imgOtro;
    private ImageView fondo;
    private Jugador jugador;

    public static Canvas lienzo;
    public static GraphicsContext graficos;
    public static RectangleTipo[][] coordenadasImagenes;
    public GridPane gridPaneMapa;
    public Bala bala;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ventana) {
        inizializarComponentes();
        ventana.setScene(escena);
        ventana.setTitle("CampoDeBatalla");
        gestionEventos();
        ventana.show();
        cicloJuego();
    }


    public void cicloJuego() {
        long tiempoInicial = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long tiempoActual) {
                double t = (tiempoActual - tiempoInicial) / 1000000000.0;


                jugador.mover();
                pintar();

            }
        };
        animationTimer.start();
    }


    public void inizializarComponentes() {

        cargarImagenes();
        this.jugador = new Jugador(1, 2, 3, 4, 2, 2, 3);

        coordenadasImagenes = new RectangleTipo[CANTIDADFILAS][CANTIDADCOLUMNAS];
        this.gridPaneMapa = new GridPane();
        this.stackPane = new StackPane();


        lienzo = new Canvas(32 * CANTIDADCOLUMNAS, 32 * CANTIDADFILAS);
        this.graficos = lienzo.getGraphicsContext2D();


        stackPane.getChildren().addAll(crearFondo(), gridPaneMapa, lienzo);
        escena = new Scene(stackPane, ALTO_VENTANA, ANCHO_VENTANA);

        pintarEscenario();

        verArray();
    }


    private void cargarImagenes() {

        this.imgNada = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/NADA.png")));
        this.imgHoyo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/HOYO.png")));
        this.imgPared = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/PARED.png")));
        this.imgOtro = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/OTRO.png")));
        this.fondo = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/fondoCampoBatalla.png"))));

    }


    private ImageView crearFondo() {
        this.fondo.fitWidthProperty().add(ANCHO_VENTANA); // Ajusta el ancho de la imagen al ancho de la escena
        this.fondo.fitHeightProperty().add(ALTO_VENTANA); // Ajusta la altura de la imagen al alto de la escena
        return fondo;
    }


    private void pintar() {
        //borra lo de detras
        graficos.clearRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
        bala.pintar(graficos);
        jugador.pintar(graficos);

    }


    public void gestionEventos() {
        escena.setOnKeyPressed(new KeyInputManager());
        escena.setOnKeyReleased(new KeyInputManager());
        escena.setOnMouseMoved(new MouseInputManager());
        escena.setOnMouseClicked(new MouseInputManager());
    }

    public void pintarEscenario() {
        MapaProcedural mapa = new MapaProcedural(CANTIDADFILAS, CANTIDADCOLUMNAS);
        mapa.generarMapa();
        TipoCasilla tipo = TipoCasilla.NADA;
        this.casillas = mapa.getMapa();


        gridPaneMapa.setGridLinesVisible(true); // Opcional: muestra las líneas del grid
        gridPaneMapa.setAlignment(Pos.CENTER);


        for (int i = 0; i < CANTIDADFILAS; i++) {
            RowConstraints rowConstraints = new RowConstraints(32); // Tamaño de celda (32x32)
            gridPaneMapa.getRowConstraints().add(rowConstraints);
        }

        for (int j = 0; j < CANTIDADCOLUMNAS; j++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(32); // Tamaño de celda (32x32)
            gridPaneMapa.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                ImageView imageView = new ImageView();

                // Asigna la imagen correspondiente según el tipo de casilla
                TipoCasilla casilla = casillas[i][j];
                Image imagen = null;

                switch (casilla) {
                    case NADA -> {
                        imagen = imgNada;
                        tipo = TipoCasilla.NADA;
                    }
                    case HOYO -> {
                        imagen = imgHoyo;
                        tipo = TipoCasilla.HOYO;
                    }
                    case PARED -> {
                        imagen = imgPared;
                        tipo = TipoCasilla.PARED;
                    }
                    case SPAWN_JUGADOR -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_JUGADOR;
                    }
                    case SPAWN_TANQUE_AMARILLO -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_AMARILLO;
                    }
                    case SPAWN_TANQUE_BLANCO -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_BLANCO;
                    }
                    case SPAWN_TANQUE_GRIS -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_GRIS;
                    }
                    case SPAWN_TANQUE_MARRON -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_MARRON;
                    }
                    case SPAWN_TANQUE_VERDE_CLARO -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_VERDE_CLARO;
                    }
                    case SPAWN_TANQUE_ROJO -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_ROJO;
                    }
                    case SPAWN_TANQUE_NEGRO -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_NEGRO;
                    }
                    case SPAWN_TANQUE_MORADO -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_MORADO;
                    }
                    case SPAWN_TANQUE_VERDE_OSCURO -> {
                        imagen = imgOtro;
                        tipo = TipoCasilla.SPAWN_TANQUE_VERDE_OSCURO;
                    }
                    default -> System.out.println("La casilla que se quiere poner no existe");
                }

                if (imagen != null) {
                    imageView.setImage(imagen);
                    imageView.setFitWidth(32); // Ajusta el ancho de la imagen a 32 (tamaño de celda)
                    imageView.setFitHeight(32); // Ajusta la altura de la imagen a 32 (tamaño de celda)
                    gridPaneMapa.add(imageView, j, i); // Agrega la imagen a la celda correspondiente
                } else {
                    System.err.println("No se pudo cargar la imagen.");
                }

                int heightImagen = (int) imageView.getImage().getHeight();
                int widthImagen = (int) imageView.getImage().getWidth();
                int cordenadaX = heightImagen * j + 1;
                int cordenadaY = heightImagen * i + 1;

                Rectangle rectangulo = new Rectangle(cordenadaX, cordenadaY, heightImagen, widthImagen);
                coordenadasImagenes[i][j] = new RectangleTipo(rectangulo, tipo);
            }
        }
    }


    private void verArray() {
        for (int i = 0; i < coordenadasImagenes.length; i++) {
            for (int j = 0; j < coordenadasImagenes[i].length; j++) {
                RectangleTipo rectTipo = coordenadasImagenes[i][j];
                Rectangle rectangulo = rectTipo.REC();
                TipoCasilla tipo = rectTipo.TIPO();

                // Obtener las propiedades del rectángulo
                int cordenadaX = (int) rectangulo.getX();
                int cordenadaY = (int) rectangulo.getY();
                int heightImagen = (int) rectangulo.getHeight();
                int widthImagen = (int) rectangulo.getWidth();

                // Mostrar los datos en la consola
                System.out.println("Coordenadas del rectángulo en [" + i + "][" + j + "]:");
                System.out.println(" - Coordenada X: " + cordenadaX);
                System.out.println(" - Coordenada Y: " + cordenadaY);
                System.out.println(" - Altura de la imagen: " + heightImagen);
                System.out.println(" - Ancho de la imagen: " + widthImagen);
                System.out.println("Tipo de casilla en [" + i + "][" + j + "]: " + tipo);
                System.out.println("-----------------------------------");
            }
        }


    }


    public Scene getEscena() {
        return escena;
    }


}
