package modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.tanques.TanqueJugador;
import controlador.input.KeyInputManager;
import controlador.input.MouseInputManager;
import modelo.records.RectangleTipo;
import modelo.mapa.TipoCasilla;
import vista.juego.CampoDeBatalla;

import java.awt.*;
import java.util.Objects;

public class Jugador extends TanqueJugador {
    public static int x;
    public static int y;
    public int REBOTES_MAXIMOS;
    public int VELOCIDAD_BALA;
    public int MAXIMO_BALAS;
    public int MAXIMO_MINAS;
    public int balas;
    public int velocidad;
    public int minas;
    public static final String SPRITEBASE = "/imagenes/sprites/tanques/tankBaseX.png";
    public static final String SPRITETORRETA = "/imagenes/sprites/tanques/tankTurret.png";

    public static final String SPRITEBASEHORIZONTAL = "/imagenes/sprites/tanques/tankBaseY.png";
    public static final String SPRITETORRETAVERTICAL = "/imagenes/sprites/tanques/tankBaseX.png";
    public ImageView imagenBase;
    public ImageView imagenTorreta;
    public ImageView imagenBaseVertical;
    public ImageView imagenBaseHorizontal;
    private boolean puedeMoverse;



    public Jugador(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int MAXIMO_MINAS, int balas, int velocidad, int minas) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, MAXIMO_MINAS, balas, velocidad, minas);
        this.velocidad = velocidad;
        this.balas = balas;
        this.minas = minas;
        this.x = 500;
        this.y = 500;
        this.imagenBaseHorizontal = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASEHORIZONTAL))));
        this.imagenBaseVertical = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETAVERTICAL))));
        this.imagenBase = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASE))));
        this.imagenTorreta = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETA))));
        puedeMoverse = true;


    }

    public void pintar(GraphicsContext graficos) {
        comprobarColision();
        ajustesPantallaCompleta();
        actualizarRotacion();

        graficos.drawImage(imagenBase.getImage(), x, y);

        double torretaX = x - imagenTorreta.getImage().getWidth() / 2 + 32;
        double torretaY = y - imagenTorreta.getImage().getHeight() / 2 + 32;
        graficos.save(); //guardar estado de graficos
        graficos.translate(torretaX, torretaY); //mover al centro de la torreta
        graficos.rotate(imagenTorreta.getRotate()); //rotar en base al angulo
        graficos.drawImage(imagenTorreta.getImage(), -imagenTorreta.getImage().getWidth() / 2, -imagenTorreta.getImage().getHeight() / 2); //centrar imagen en coordenadas (0,0)
        graficos.restore(); //recuperar estado de graficos

    }

    public void ajustesPantallaCompleta() {
        imagenBase.setPreserveRatio(true);
        imagenTorreta.setPreserveRatio(true);
        imagenBaseHorizontal.setPreserveRatio(true);
        imagenBaseVertical.setPreserveRatio(true);
        double desiredSize = 128; // Tamaño deseado de la imagen

        imagenBase.setFitWidth(desiredSize);
        imagenBase.setFitHeight(desiredSize);

        imagenTorreta.setFitWidth(desiredSize);
        imagenTorreta.setFitHeight(desiredSize);

        imagenBaseHorizontal.setFitWidth(desiredSize);
        imagenBaseHorizontal.setFitHeight(desiredSize);

        imagenBaseVertical.setFitWidth(desiredSize);
        imagenBaseVertical.setFitHeight(desiredSize);

        imagenBase.resize(128, 128);
        imagenTorreta.resize(128, 128);
        imagenBaseHorizontal.resize(128, 128);
        imagenBaseVertical.resize(128, 128);


    }

    public void comprobarColision() {
        this.puedeMoverse = true; // Variable para rastrear si el jugador puede moverse o no

        for (int i = 0; i < CampoDeBatalla.coordenadasImagenes.length; i++) {
            for (int j = 0; j < CampoDeBatalla.coordenadasImagenes[i].length; j++) {
                RectangleTipo rectTipo = CampoDeBatalla.coordenadasImagenes[i][j];
                Rectangle rectangulo = rectTipo.REC();
                TipoCasilla tipo = rectTipo.TIPO();

                // Obtener las propiedades del rectángulo
                int cordenadaX = (int) rectangulo.getX() ;
                int cordenadaY = (int) rectangulo.getY() ;
                int heightImagen = (int) rectangulo.getHeight() ;
                int widthImagen = (int) rectangulo.getWidth() ;

                if (tipo==TipoCasilla.PARED && x >= cordenadaX && x < cordenadaX + widthImagen &&
                        y >= cordenadaY && y < cordenadaY + heightImagen) {
                    puedeMoverse = false;
                    break; // Romper el bucle interno si hay una colisión
                }
            }

            if (!puedeMoverse) {
                break; // Romper el bucle externo si hay una colisión
            }
        }
    }



    //Se jecuta por cada iteracion de ciclo de juego
    public void mover() {
        double distancia = velocidad * 2.5; // Distancia que se moverá en cada iteración del ciclo de juego

        if (puedeMoverse) {

            // Movimiento hacia arriba
            if (KeyInputManager.isArriba()) {
                y -= distancia;
                imagenBase = imagenBaseVertical;
            }
            // Movimiento hacia abajo
            if (KeyInputManager.isAbajo()) {
                y += distancia;
                imagenBase = imagenBaseVertical;
            }

            // Movimiento hacia la izquierda
            if (KeyInputManager.isIzquierda()) {
                x -= distancia;
                imagenBase = imagenBaseHorizontal;
            }
            // Movimiento hacia la derecha
            if (KeyInputManager.isDerecha()) {
                x += distancia;
                imagenBase = imagenBaseHorizontal;
            }
        }else{
            System.out.println("No te puedes mover");
        }


    }

    public void actualizarRotacion() {
        double mouseX = MouseInputManager.getMouseX();
        double mouseY = MouseInputManager.getMouseY();
        double angle = Math.atan2(mouseY - y, mouseX - x) * 180 / Math.PI + 90;

        // crear una instancia de Rotate y establecer el ángulo de rotación
        this.imagenTorreta.setRotate(angle);

        // establecer la posición de la imagen de la torreta
        double torretaX = x - imagenTorreta.getImage().getWidth() / 2;
        double torretaY = y - imagenTorreta.getImage().getHeight() / 2;
        this.imagenTorreta.setX(torretaX);
        this.imagenTorreta.setY(torretaY);

    }


}