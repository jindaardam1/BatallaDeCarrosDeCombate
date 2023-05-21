package vista.juego;

import controlador.input.KeyInputManager;
import controlador.input.MouseInputManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import modelo.Bala;
import modelo.Jugador;
import modelo.JugadorAuto;
import modelo.mapa.MapaProcedural;
import modelo.mapa.TipoCasilla;
import modelo.records.RectangleTipo;
import modelo.tanques.Contador;
import utilidades.pantalla.PantallaUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class CampoDeBatalla extends Application {
    private static final int CANTIDADFILAS = 20;
    private static final int CANTIDADCOLUMNAS = 27;

    private Scene escena;
    private StackPane stackPane;
    private TipoCasilla[][] casillas;

    private Image imgNada1;
    private Image imgNada2;
    private Image imgNada3;
    private Image imgHoyo;
    private Image imgPared1;
    private Image imgPared2;
    private Image imgPared3;
    private Image imgPared4;
    private Image imgPared5;
    private Image imgPared6;
    private Image imgOtro;
    private Image imgJug;
    private ImageView fondo;
    private Jugador jugador;
    private JugadorAuto bot;

    public static Canvas lienzo;
    public static GraphicsContext graficos;
    public static RectangleTipo[][] coordenadasImagenes;
    public GridPane gridPaneMapa;
    public Bala bala;
    public Contador panelContador;
    public static  int posSpawnJugadorX;
    public static int  posSpawnJugadorY;

    public static ArrayList[][] posSpawnsEnemigos ;


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




                pintar();

            }
        };
        animationTimer.start();
    }


    public void inizializarComponentes() {

        cargarImagenes();


        coordenadasImagenes = new RectangleTipo[CANTIDADFILAS][CANTIDADCOLUMNAS];
        this.gridPaneMapa = new GridPane();
        this.stackPane = new StackPane();


        lienzo = new Canvas(32 * CANTIDADCOLUMNAS, 32 * CANTIDADFILAS);
        this.graficos = lienzo.getGraphicsContext2D();
        this.panelContador = new Contador();


        stackPane.getChildren().addAll(crearFondo(), gridPaneMapa, lienzo, panelContador.panel);
        escena = new Scene(stackPane, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);




        pintarEscenario();
        calcularCodendadasSpawns();
        this.jugador = new Jugador(1, 6, 3, 4, 5, 2, 3);
        this.bot = new JugadorAuto(1, 6, 3, 4, 5, 2, 3);
        this.bala = new Bala(jugador.VELOCIDAD_BALA);

        verArray();
    }


    private void cargarImagenes() {

        this.imgNada1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Verde.png")));
        this.imgNada2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Naranja.png")));
        this.imgNada3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Azul.png")));
        this.imgJug = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Diamante.png")));
        this.imgHoyo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Lava.gif")));
        this.imgPared1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Madera1.png")));
        this.imgPared2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Madera2.png")));
        this.imgPared3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Madera3.png")));
        this.imgPared4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Madera4.png")));
        this.imgPared5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Madera5.png")));
        this.imgPared6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Madera6.png")));
        this.imgOtro = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/Oro.png")));
        this.fondo = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Terreno/fondoCampoBatalla.png"))));

    }


    private ImageView crearFondo() {
        this.fondo.fitWidthProperty().add(PantallaUtil.HEIGHT_VENTANA); // Ajusta el ancho de la imagen al ancho de la escena
        this.fondo.fitHeightProperty().add(PantallaUtil.WIDTH_VENTANA); // Ajusta la altura de la imagen al alto de la escena
        return fondo;
    }


    private void pintar() {
        //borra lo de detras
        graficos.clearRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
        panelContador.pintar(graficos);
        jugador.pintar(graficos);
        bot.pintar(graficos);


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

        Image imgSuelo = sueloRandom();

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                ImageView imageView = new ImageView();

                // Asigna la imagen correspondiente según el tipo de casilla
                TipoCasilla casilla = casillas[i][j];
                Image imagen = null;

                switch (casilla) {
                    case NADA -> {
                        imagen = imgSuelo;
                        tipo = TipoCasilla.NADA;
                    }
                    case HOYO -> {
                        imagen = imgHoyo;
                        tipo = TipoCasilla.HOYO;
                    }
                    case PARED -> {
                        imagen = paredRandom();
                        tipo = TipoCasilla.PARED;
                    }
                    case SPAWN_JUGADOR -> {
                        imagen = imgJug;
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

    private Image paredRandom() {
        int roll = getRoll(6);
        switch (roll) {
            case 1 -> {
                return imgPared1;
            }
            case 2 -> {
                return imgPared2;
            }
            case 3 -> {
                return imgPared3;
            }
            case 4 -> {
                return imgPared4;
            }
            case 5 -> {
                return imgPared5;
            }
            default -> {
                return imgPared6;
            }
        }
    }

    private Image sueloRandom() {
        int roll = getRoll(3);
        switch (roll) {
            case 1 -> {
                return imgNada1;
            }
            case 2 -> {
                return imgNada2;
            }
            default -> {
                return imgNada3;
            }
        }
    }

    private int getRoll(int numMax) {
        Random rand = new Random();
        return rand.nextInt(numMax) + 1;
    }

    public Scene getEscena() {
        return escena;
    }

    public void calcularCodendadasSpawns(){
        posSpawnsEnemigos = new ArrayList[][]{
        };
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
                if(tipo==TipoCasilla.SPAWN_JUGADOR){
                    posSpawnJugadorX = rectangulo.x;
                    posSpawnJugadorY = rectangulo.y;
                }
                if(tipo==TipoCasilla.SPAWN_JUGADOR){
                    posSpawnJugadorX = rectangulo.x;
                    posSpawnJugadorY = rectangulo.y;
                }
                if(tipo==TipoCasilla.SPAWN_TANQUE_AMARILLO||tipo==TipoCasilla.SPAWN_TANQUE_BLANCO||tipo==TipoCasilla.SPAWN_TANQUE_GRIS||tipo==TipoCasilla.SPAWN_TANQUE_MARRON||tipo==TipoCasilla.SPAWN_TANQUE_MORADO||tipo==TipoCasilla.SPAWN_TANQUE_NEGRO||tipo==TipoCasilla.SPAWN_TANQUE_ROJO||tipo==TipoCasilla.SPAWN_TANQUE_VERDE_CLARO||tipo==TipoCasilla.SPAWN_TANQUE_VERDE_OSCURO){


                }
            }}

    }

}
