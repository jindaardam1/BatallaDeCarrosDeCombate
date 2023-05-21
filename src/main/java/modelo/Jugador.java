package modelo;

import controlador.input.KeyInputManager;
import controlador.input.MouseInputManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;
import modelo.mapa.TipoCasilla;
import modelo.records.RectangleTipo;
import modelo.tanques.TanqueJugador;
import utilidades.log.Logs;
import vista.juego.CampoDeBatalla;

import java.awt.*;
import java.util.*;

import static utilidades.eventos.SpriteUtils.isCollisionDetected;


public class Jugador extends TanqueJugador {
    public static int x;
    public  static int y;
    public int REBOTES_MAXIMOS;
    public static int VELOCIDAD_BALA;
    public int MAXIMO_BALAS;
    public int MAXIMO_MINAS;
    public static int balas;
    public int velocidad;
    public int minas;

    public static final String SPRITEBASE = "/imagenes/sprites/tanques/original/tankBaseX.png";
    public static final String SPRITETORRETA = "/imagenes/sprites/tanques/original/tankTurret.png";

    public static final String SPRITEBASEHORIZONTAL = "/imagenes/sprites/tanques/original/tankBaseY.png";
    public static final String SPRITETORRETAVERTICAL = "/imagenes/sprites/tanques/original/tankBaseX.png";
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
    public static ArrayList<Bala> arrayBalas;
    public static int balasActivas;
    public static boolean recargando = false;



    public Jugador(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int MAXIMO_MINAS, int balas, int velocidad, int minas) {
        super(REBOTES_MAXIMOS, VELOCIDAD_BALA, MAXIMO_BALAS, MAXIMO_MINAS, balas, velocidad, minas);
        this.velocidad = velocidad;
        this.balas = balas;
        this.minas = minas;
        this.distancia = 0;
        this.x = CampoDeBatalla.posSpawnJugadorX;
        this.y = CampoDeBatalla.posSpawnJugadorY;
        this.VELOCIDAD_BALA = VELOCIDAD_BALA;
        this.imagenBaseHorizontal = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASEHORIZONTAL))));
        this.imagenBaseVertical = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETAVERTICAL))));
        this.imagenBase = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASE))));
        this.imagenTorreta = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETA))));
        this.puedeMoverse = true;
        this.puedeMoverseArriba = true;
        this.puedeMoverseAbajo = true;
        this.puedeMoverseDerecha = true;
        this.puedeMoverseIzquierda = true;
        this.arrayBalas = new ArrayList<>();
        this.balasActivas = 0;
        cargarCargador();




    }

    public static void cargarCargador() {
        for (int i = 0; i < balas; i++) {

            Bala bala = new Bala(VELOCIDAD_BALA);
            arrayBalas.add(bala);

        }
    }

    public static void crearBala() {

        Bala bala = new Bala(VELOCIDAD_BALA);
        arrayBalas.add(bala);


    }


    public static void eliminarBala() {
        arrayBalas.remove(arrayBalas.size()-1);
    }


    public void pintar(GraphicsContext graficos) {
        mover();

        comprobarColision();
        actualizarRotacion();

        if (arrayBalas.size() < 1 && !recargando) {
            recargando = true;
            recargar();
        }

     //  if(estanColisionanadoContraBala()){
     //      System.out.println("HAS PERDIDO");
     //  }


        try {
            for (Bala bala : arrayBalas) {
                if (arrayBalas.size()>0) {

                    bala.pintar(graficos);
                }
            }
        } catch (ConcurrentModificationException e) {
            // Manejar la excepción
            e.printStackTrace();
            // O realizar alguna otra acción apropiada
            Logs.errorLogManager(e);
        }





        graficos.drawImage(imagenBase.getImage(), x, y);
        double torretaX = x - imagenTorreta.getImage().getWidth() / 2 + 32;
        double torretaY = y - imagenTorreta.getImage().getHeight() / 2 + 32;
        graficos.save(); //guardar estado de graficos
        graficos.translate(torretaX, torretaY); //mover al centro de la torreta
        graficos.rotate(imagenTorreta.getRotate()); //rotar en base al angulo
        graficos.drawImage(imagenTorreta.getImage(), -imagenTorreta.getImage().getWidth() / 2, -imagenTorreta.getImage().getHeight() / 2); //centrar imagen en coordenadas (0,0)
        graficos.restore(); //recuperar estado de graficos

    }





    public void comprobarColision() {
        boolean colisionSuperior = false;
        boolean colisionInferior = false;
        boolean colisionLateralDerecho = false;
        boolean colisionLateralIzquierdo = false;

        // variables auxiliares para la posición futura del jugador
        int jugadorXFuturo = x;
        int jugadorYFuturo = y;
        if (KeyInputManager.arriba) {
            jugadorYFuturo -= velocidad;
        }
        if (KeyInputManager.abajo) {
            jugadorYFuturo += velocidad;
        }
        if (KeyInputManager.izquierda) {
            jugadorXFuturo -= velocidad;
        }
        if (KeyInputManager.derecha) {
            jugadorXFuturo += velocidad;
        }

        for (int i = 0; i < CampoDeBatalla.coordenadasImagenes.length; i++) {
            for (int j = 0; j < CampoDeBatalla.coordenadasImagenes[i].length; j++) {
                RectangleTipo rectTipo = CampoDeBatalla.coordenadasImagenes[i][j];
                Rectangle rectangulo = rectTipo.REC();
                TipoCasilla tipo = rectTipo.TIPO();

                int jugadorX = jugadorXFuturo;
                int jugadorY = jugadorYFuturo;
                int jugadorHeight = getJugadorImagenHeight();
                int jugadorWidth = getJugadorImagenWidth();

                int rectanguloX = (int) rectangulo.getX();
                int rectanguloY = (int) rectangulo.getY();
                int rectanguloHeight = (int) rectangulo.getHeight();
                int rectanguloWidth = (int) rectangulo.getWidth();

                if (tipo == TipoCasilla.PARED || tipo == TipoCasilla.HOYO) {
                    colisionLateralDerecho = colisionLateralDerecho || (jugadorX + jugadorWidth > rectanguloX) && (jugadorX + jugadorWidth <= rectanguloX + rectanguloWidth) && (jugadorY + jugadorHeight > rectanguloY) && (jugadorY < rectanguloY + rectanguloHeight);
                    colisionLateralIzquierdo = colisionLateralIzquierdo || (jugadorX < rectanguloX + rectanguloWidth) && (jugadorX >= rectanguloX) && (jugadorY + jugadorHeight > rectanguloY) && (jugadorY < rectanguloY + rectanguloHeight);
                    colisionInferior = colisionInferior || (jugadorY + jugadorHeight > rectanguloY) && (jugadorY + jugadorHeight <= rectanguloY + rectanguloHeight) && (jugadorX + jugadorWidth > rectanguloX) && (jugadorX < rectanguloX + rectanguloWidth);
                    colisionSuperior = colisionSuperior || (jugadorY < rectanguloY + rectanguloHeight) && (jugadorY >= rectanguloY) && (jugadorX + jugadorWidth > rectanguloX) && (jugadorX < rectanguloX + rectanguloWidth);
                }
            }
        }

        if (colisionSuperior) {
            System.out.println("Colisiona superior");

            puedeMoverseArriba = false;
        } else {
            puedeMoverseArriba = true;
        }

        if (colisionInferior) {
            System.out.println("Colisiona inferior");
            puedeMoverseAbajo = false;
        } else {
            puedeMoverseAbajo = true;
        }

        if (colisionLateralDerecho) {
            System.out.println("Colisiona derecha");
            puedeMoverseDerecha = false;
        } else {
            puedeMoverseDerecha = true;
        }

        if (colisionLateralIzquierdo) {
            System.out.println("Colisiona izquierda");
            puedeMoverseIzquierda = false;
        } else {
            puedeMoverseIzquierda = true;
        }
    }

    public void mover() {
        distancia = velocidad * 1; // Distancia que se moverá en cada iteración del ciclo de juego


        // Movimiento hacia arriba
        if (KeyInputManager.isArriba() && puedeMoverseArriba) {
            y -= distancia;
            imagenBase = imagenBaseVertical;
        }
        // Movimiento hacia abajo
        else if (KeyInputManager.isAbajo() && puedeMoverseAbajo) {
            y += distancia;
            imagenBase = imagenBaseVertical;
        }

        // Movimiento hacia la izquierda
        else if (KeyInputManager.isIzquierda() && puedeMoverseIzquierda) {
            x -= distancia;
            imagenBase = imagenBaseHorizontal;
        }
        // Movimiento hacia la derecha
        else if (KeyInputManager.isDerecha() && puedeMoverseDerecha) {
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

    private int getJugadorImagenWidth() {
        return (int) imagenBase.getImage().getWidth();
    }

    private int getJugadorImagenHeight() {
        return (int) imagenBase.getImage().getHeight();
    }

    public void recargar() {
        System.out.println("Está recargando");

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000), actionEvent -> {
            System.out.println("INVOCAR RECARGAR");
            cargarCargador();
            System.out.println("RECARGADO");
            recargando = false; // Reiniciar el estado de recargando después de los 10 segundos
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

//public boolean estanColisionanadoContraBala(){
// javafx.scene.shape.Rectangle rectanguloJugador = new javafx.scene.shape.Rectangle(this.x, this.y, 30, 30);
//    javafx.scene.shape.Rectangle rectanguloBalaAuto = new javafx.scene.shape.Rectangle(BalaAuto.x, BalaAuto.y, 30, 30);

//    return isCollisionDetected(rectanguloJugador, rectanguloBalaAuto);
//}




}