package juego.core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import juego.entidades.tanques.TanqueJugador;
import juego.input.InputManager;

import java.util.Objects;

public class Jugador extends TanqueJugador {
    public int x;
    public int y;
    public int REBOTES_MAXIMOS;
    public int VELOCIDAD_BALA;
    public int MAXIMO_BALAS;
    public int MAXIMO_MINAS;
    public int balas;
    public int velocidad;
    public int minas;
    public static String SPRITEBASE = "/imagenes/sprites/tanques/tankBaseX.png";
    public static String SPRITETORRETA = "/imagenes/sprites/tanques/tankTurret.png";
    public InputManager inputManager =  new InputManager();


    public Jugador(int REBOTES_MAXIMOS, int VELOCIDAD_BALA, int MAXIMO_BALAS, int MAXIMO_MINAS, int balas, int velocidad, int minas) {
            super(REBOTES_MAXIMOS,VELOCIDAD_BALA,MAXIMO_BALAS,MAXIMO_MINAS,balas,velocidad,minas);
        this.velocidad = velocidad;
        this.balas = balas;
        this.minas = minas;
        this.x = 0;
        this.y = 0;



    }

    @Override
    public void dispararBala() {

    }

    @Override
    public void recargarBala() {

    }


    //Se jecuta por cada iteracion de ciclo de juego
    public void pintar(GraphicsContext graficos){
        graficos.drawImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBASE))), x, y);
        graficos.drawImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITETORRETA))), x, y);

    }


    //Se jecuta por cada iteracion de ciclo de juego
    public void mover(){
        double distancia = velocidad * 2.5; // Distancia que se moverá en cada iteración del ciclo de juego

        // Movimiento hacia arriba
        if (InputManager.isArriba()) {
            y -= distancia;
            SPRITEBASE = "/imagenes/sprites/tanques/tankBaseX.png";
        }

        // Movimiento hacia abajo
        if (InputManager.isAbajo()) {
            y += distancia;
            SPRITEBASE = "/imagenes/sprites/tanques/tankBaseX.png";
        }

        // Movimiento hacia la izquierda
        if (InputManager.isIzquierda()) {
            x -= distancia;
            SPRITEBASE = "/imagenes/sprites/tanques/tankBaseY.png";
        }

        // Movimiento hacia la derecha
        if (InputManager.isDerecha()) {
            x += distancia;
            SPRITEBASE = "/imagenes/sprites/tanques/tankBaseY.png";
        }

    }


}