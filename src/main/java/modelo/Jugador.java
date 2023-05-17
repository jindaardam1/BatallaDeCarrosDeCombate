package modelo;

import controlador.input.KeyInputManager;
import controlador.input.MouseInputManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import modelo.mapa.TipoCasilla;
import modelo.records.RectangleTipo;
import modelo.tanques.TanqueJugador;
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
    private double distancia;
    private boolean puedeMoverseArriba;
    boolean puedeMoverseAbajo;
    boolean puedeMoverseIzquierda;
    boolean puedeMoverseDerecha;


    public Jugador(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int MAXIMO_MINAS, int balas, int velocidad, int minas) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, MAXIMO_MINAS, balas, velocidad, minas);
        this.velocidad = velocidad;
        this.balas = balas;
        this.minas = minas;
        this.distancia = 0;
        this.x = 500;
        this.y = 500;
        this.imagenBaseHorizontal = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASEHORIZONTAL))));
        this.imagenBaseVertical = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETAVERTICAL))));
        this.imagenBase = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASE))));
        this.imagenTorreta = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETA))));
        this.puedeMoverse = true;
        this.puedeMoverseArriba = true;
        this.puedeMoverseAbajo = true;
        this.puedeMoverseDerecha = true;
        this.puedeMoverseIzquierda = true;


    }

    public void pintar(GraphicsContext graficos) {
        comprobarColision2();
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


    public void comprobarColision2() {
        boolean colisionDetectada = false;
        boolean colisionSuperior = false;
        boolean colisionInferior = false;
        boolean colisionLateralDerecho = false;
        boolean colisionLateralIzquierdo = false;

        for (int i = 0; i < CampoDeBatalla.coordenadasImagenes.length; i++) {
            for (int j = 0; j < CampoDeBatalla.coordenadasImagenes[i].length; j++) {
                RectangleTipo rectTipo = CampoDeBatalla.coordenadasImagenes[i][j];
                Rectangle rectangulo = rectTipo.REC();
                TipoCasilla tipo = rectTipo.TIPO();

                int jugadorX = x;
                int jugadorY = y;
                int jugadorHeight = getJugadorImagenHeight();
                int jugadorWidth = getJugadorImagenWidth();

                int rectanguloX = (int) rectangulo.getX();
                int rectanguloY = (int) rectangulo.getY();
                int rectanguloHeight = (int) rectangulo.getHeight();
                int rectanguloWidth = (int) rectangulo.getWidth();

                if (tipo == TipoCasilla.PARED) {
                    colisionLateralDerecho = colisionLateralDerecho || (jugadorX + jugadorWidth > rectanguloX) && (jugadorX + jugadorWidth <= rectanguloX + rectanguloWidth) && (jugadorY + jugadorHeight > rectanguloY) && (jugadorY < rectanguloY + rectanguloHeight);
                    colisionLateralIzquierdo = colisionLateralIzquierdo || (jugadorX < rectanguloX + rectanguloWidth) && (jugadorX >= rectanguloX) && (jugadorY + jugadorHeight > rectanguloY) && (jugadorY < rectanguloY + rectanguloHeight);
                    colisionInferior = colisionInferior || (jugadorY + jugadorHeight > rectanguloY) && (jugadorY + jugadorHeight <= rectanguloY + rectanguloHeight) && (jugadorX + jugadorWidth > rectanguloX) && (jugadorX < rectanguloX + rectanguloWidth);
                    colisionSuperior = colisionSuperior || (jugadorY < rectanguloY + rectanguloHeight) && (jugadorY >= rectanguloY) && (jugadorX + jugadorWidth > rectanguloX) && (jugadorX < rectanguloX + rectanguloWidth);
                }
            }
        }

        if (colisionSuperior && KeyInputManager.arriba) {
            System.out.println("Colisiona superior");
            puedeMoverseArriba = false;
        } else {
            puedeMoverseArriba = true;
        }

        if (colisionInferior && KeyInputManager.abajo) {
            System.out.println("Colisiona inferior");
            puedeMoverseAbajo = false;
        } else {
            puedeMoverseAbajo = true;
        }

        if (colisionLateralDerecho && KeyInputManager.derecha) {
            System.out.println("Colisiona derecha");
            puedeMoverseDerecha = false;
        } else {
            puedeMoverseDerecha = true;
        }

        if (colisionLateralIzquierdo && KeyInputManager.izquierda) {
            System.out.println("Colisiona izquierda");
            puedeMoverseIzquierda = false;
        } else {
            puedeMoverseIzquierda = true;
        }
    }

    public void mover() {
        distancia = velocidad * 1; // Distancia que se moverá en cada iteración del ciclo de juego

        if (puedeMoverse) {
            // Movimiento hacia arriba
            if (KeyInputManager.isArriba() && puedeMoverseArriba) {
                y -= distancia;
                imagenBase = imagenBaseVertical;
            }
            // Movimiento hacia abajo
            if (KeyInputManager.isAbajo() && puedeMoverseAbajo) {
                y += distancia;
                imagenBase = imagenBaseVertical;
            }

            // Movimiento hacia la izquierda
            if (KeyInputManager.isIzquierda() && puedeMoverseIzquierda) {
                x -= distancia;
                imagenBase = imagenBaseHorizontal;
            }
            // Movimiento hacia la derecha
            if (KeyInputManager.isDerecha() && puedeMoverseDerecha) {
                x += distancia;
                imagenBase = imagenBaseHorizontal;
            }

        } else {
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

    private int getJugadorImagenWidth() {
        return (int) imagenBase.getImage().getWidth();
    }

    private int getJugadorImagenHeight() {
        return (int) imagenBase.getImage().getHeight();
    }


}