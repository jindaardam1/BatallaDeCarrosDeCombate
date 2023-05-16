package juego.core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import juego.entidades.tanques.TanqueJugador;
import juego.input.KeyInputManager;
import juego.input.MouseInputManager;

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
    public static String SPRITEBASE = "/imagenes/sprites/tanques/tankBaseX.png";
    public static String SPRITETORRETA = "/imagenes/sprites/tanques/tankTurret.png";
    public ImageView imagenBase;
    public ImageView imagenTorreta;
    public ImageView imagenBaseVertical;
    public ImageView imagenBaseHorizontal;


    public Jugador(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int MAXIMO_MINAS, int balas, int velocidad, int minas) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, MAXIMO_MINAS, balas, velocidad, minas);
        this.velocidad = velocidad;
        this.balas = balas;
        this.minas = minas;
        this.x = 0;
        this.y = 0;
        this.imagenBaseHorizontal = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/sprites/tanques/tankBaseY.png"))));
        this.imagenBaseVertical = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/sprites/tanques/tankBaseX.png"))));
        this.imagenBase = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASE))));
        this.imagenTorreta = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETA))));

    }

    public void pintar(GraphicsContext graficos) {
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
        if (MenuPrincipal.ESTA_PANTALLA_COMPLETA) {
            imagenBase.setFitWidth(128);
            imagenBase.setFitHeight(128);
            imagenTorreta.setFitWidth(128);
            imagenTorreta.setFitHeight(128);
            imagenBaseHorizontal.setFitWidth(128);
            imagenBaseHorizontal.setFitHeight(128);
            imagenBaseVertical.setFitWidth(128);
            imagenBaseVertical.setFitHeight(128);
        }

    }


    //Se jecuta por cada iteracion de ciclo de juego
    public void mover() {
        double distancia = velocidad * 2.5; // Distancia que se moverá en cada iteración del ciclo de juego



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