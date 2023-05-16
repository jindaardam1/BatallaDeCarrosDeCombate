package juego.core;

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
import javafx.stage.Stage;
import juego.input.KeyInputManager;
import juego.input.MouseInputManager;
import juego.utils.PantallaUtil;
import juego.utils.RectangleTipo;
import juego.utils.TipoCasilla;

import java.awt.*;
import java.util.Objects;

public class CampoDeBatalla extends Application {
    private static final int ANCHO_VENTANA = PantallaUtil.obtenerAnchoDisponiblePantalla();
    private static final int ALTO_VENTANA = PantallaUtil.obtenerAlturaDisponiblePantalla();
    private static final int CANTIDADFILAS = 20;
    private static final int CANTIDADCOLUMNAS = 27;

    private Scene escena;
    private StackPane contenedorPanel;
    public GridPane panel;
    private TipoCasilla[][] casillas;

    private Image imgNada;
    private Image imgHoyo;
    private Image imgPared;
    private Image imgOtro;
    private ImageView fondo;
    private Jugador jugador;
    private Group root;
    private Canvas lienzo;
    private GraphicsContext graficos;
    public static RectangleTipo[][] coordenadasImagenes;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ventana) throws Exception {
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


                actualizarEstado();
                pintar();

            }
        };
        animationTimer.start();
    }


    public void actualizarEstado() {
        jugador.mover();
    }

    ;

    public void inizializarComponentes() {

        //RESUMEN:
        //root es el grupo en el que se van guardando los elementos que luego se represenaran con Escena y un tamaño
        //contenedorPanel es un StackPane el cual agrega uno encima del otro
        //lienzo es la parte en la cual se dibuja el personaje
        //pintarEscenario(panel) dibuja el mapa en el panel
        //ponerFondo agrega a contenedorPanel el fondo detras del tod0.
        cargarImagenes();
        this.jugador = new Jugador(1, 2, 3, 4, 2, 2, 3);
        this.coordenadasImagenes = new RectangleTipo[CANTIDADFILAS][CANTIDADCOLUMNAS];
        this.root = new Group();

        this.contenedorPanel = new StackPane();
        this.lienzo = new Canvas(ALTO_VENTANA, ANCHO_VENTANA);
        this.graficos = lienzo.getGraphicsContext2D();
        this.panel = new GridPane();


        contenedorPanel.getChildren().add(panel);
        contenedorPanel.setAlignment(Pos.BOTTOM_RIGHT);
        escena = new Scene(root, ANCHO_VENTANA, ALTO_VENTANA);
        pintarEscenario(panel);
        root.getChildren().add(contenedorPanel);
        root.getChildren().add(lienzo);
        ponerFondo();
        verArray();
    }



    private void cargarImagenes() {

        this.imgNada = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/NADA.png")));
        this.imgHoyo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/HOYO.png")));
        this.imgPared = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/PARED.png")));
        this.imgOtro = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/OTRO.png")));
        this.fondo = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/fondoCampoBatalla.png"))));

    }


    private void ponerFondo() {
        fondo.fitWidthProperty().set(ANCHO_VENTANA);
        fondo.fitHeightProperty().set(ALTO_VENTANA);
        contenedorPanel.getChildren().add(fondo);
        fondo.toBack();
    }


    private void pintar() {
        Image imagenFondo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/fondoCampoBatalla.png")));
        graficos.drawImage(imagenFondo, 0, 0);
        Image imagenMapa = fotoDeGridPane();
        graficos.drawImage(imagenMapa, 0, 0);
        jugador.pintar(graficos);
    }


    public void gestionEventos() {
        escena.setOnKeyPressed(new KeyInputManager());
        escena.setOnKeyReleased(new KeyInputManager());
        escena.setOnMouseMoved(new MouseInputManager(jugador.imagenTorreta));
    }

    public void pintarEscenario(GridPane gridPane) {
        MapaProcedural mapa = new MapaProcedural(CANTIDADFILAS, CANTIDADCOLUMNAS);
        mapa.generarMapa();
        TipoCasilla tipo = TipoCasilla.NADA;
        this.casillas = mapa.getMapa();



        // Recorrer el array de casillas y agregar cada imagen al gridPane
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                ImageView imageView = new ImageView();

                // Escalar la imagen en función del tamaño de la casilla
                if (casillas[i][j] == TipoCasilla.NADA) {
                    if (imgNada != null) {
                        imageView.setImage(imgNada);
                        tipo = TipoCasilla.NADA;
                    } else {
                        System.err.println("No se pudo cargar la imagen de NADA.");
                    }
                } else if (casillas[i][j] == TipoCasilla.HOYO) {
                    if (imgHoyo != null) {
                        imageView.setImage(imgHoyo);
                        tipo = TipoCasilla.HOYO;
                    } else {
                        System.err.println("No se pudo cargar la imagen de HOYO.");
                    }
                } else if (casillas[i][j] == TipoCasilla.PARED) {
                    if (imgPared != null) {
                        imageView.setImage(imgPared);
                        tipo = TipoCasilla.PARED;
                    } else {
                        System.err.println("No se pudo cargar la imagen de PARED.");
                    }
                } else {
                    if (imgOtro != null) {
                        imageView.setImage(imgOtro);
                        tipo = TipoCasilla.SPAWN_JUGADOR;
                    } else {
                        System.err.println("No se pudo cargar la imagen de PARED.");
                    }
                }

                // Ajustar la imagen para mantener su relación de aspecto
                imageView.setPreserveRatio(true);


                // Ajustar el tamaño de la imagen a la celda
                imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(casillas[0].length));
                imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(casillas.length));

                // Agregar la imagen al GridPane en la posición desplazada
                gridPane.add(imageView, j, i);

                int heightImagen = (int) imageView.getImage().getHeight();
                int widthImagen = (int) imageView.getImage().getWidth();
                int cordenadaX = heightImagen * i;
                int cordenadaY = heightImagen * j;
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

    public Image fotoDeGridPane() {
// Crear un objeto SnapshotParameters
        SnapshotParameters sp = new SnapshotParameters();
        sp.setFill(Color.TRANSPARENT); // Indicar que el fondo es transparente

// Crear una imagen vacía con el ancho y alto del GridPane


        WritableImage image = new WritableImage( ALTO_VENTANA,  ANCHO_VENTANA);

// Tomar una instantánea del GridPane y guardarla en la imagen


        return panel.snapshot(sp, image);

    }

    public Scene getEscena() {
        return escena;
    }


}
