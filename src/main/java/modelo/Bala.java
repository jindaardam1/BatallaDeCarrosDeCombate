package modelo;

import controlador.input.MouseInputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.mapa.TipoCasilla;
import modelo.records.RectangleTipo;
import vista.juego.CampoDeBatalla;

import java.awt.*;
import java.util.Objects;

public class Bala {
    private static final double VELOCIDAD = 2.0;  // Velocidad de la bala
    public double x;
    public double y;
    private double direccionX;  // Dirección X hacia la cual moverse
    private double direccionY;  // Dirección Y hacia la cual moverse
    private double posRatonAlDispararX;
    private double posRatonAlDispararY;

    private double posBalaAlDispararX;
    private double posBalaAlDispararY;
    private ImageView imagenBala;
    private final String SPRITEBALA = "/imagenes/sprites/tanques/bullet.png";
    public boolean disparo;
    private double mouseX;
    private double mouseY;
    private double deltaX;
    private double deltaY;
    private double magnitud;

    public Bala() {

        reubicarBala();
        disparo = false;
        this.imagenBala = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBALA))));
    }

    public void reubicarBala() {
        this.x = Jugador.x+8;
        this.y = Jugador.y+8;

    }

    public void calcularAnguloDisparo() {
        deltaX = (posRatonAlDispararX) - posBalaAlDispararX;
        deltaY = (posRatonAlDispararY) - posBalaAlDispararY;
        magnitud = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Calcular dirección solo si la magnitud es mayor a cero para evitar divisiones por cero

            direccionX = deltaX / magnitud;
            direccionY = deltaY / magnitud;

    }


    public void actualizadorDeCordRaton() {
        posRatonAlDispararX = mouseX;
        posRatonAlDispararY = mouseY;

    }

    public void pintar(GraphicsContext graficos) {
        graficos.drawImage(imagenBala.getImage(), x, y);
        actualizadorDeCordRaton();

        disparar();
        if (disparo) {
            mover();
        } else {
            reubicarBala();
        }
    }


    private void mover() {

        x += direccionX * VELOCIDAD;
        y += direccionY * VELOCIDAD;

    }

    private void disparar() {
        if (MouseInputManager.clickIzquierdo) {
            //Calcula el angulo de disparo evitando darle dos veces seguidas
            if (!disparo) {
                tomarPosRatonYBala();
                calcularAnguloDisparo();
            }

            System.out.println("Has disparado");


            disparo = true;
            //para que si no mueves el raton despues de disparar, la bala no salga otra vez.
            MouseInputManager.clickIzquierdo = false;
        }
        if (chocoPared()) {
            System.out.println("La bala ha chocado con una pared");
            disparo = false;


        }
    }

    private void tomarPosRatonYBala() {
        posRatonAlDispararX = MouseInputManager.getMouseX();
        posRatonAlDispararY = MouseInputManager.getMouseY();
        posBalaAlDispararX = this.x;
        posBalaAlDispararY = this.y;
    }

    private boolean chocoPared() {
        for (int i = 0; i < CampoDeBatalla.coordenadasImagenes.length; i++) {
            for (int j = 0; j < CampoDeBatalla.coordenadasImagenes[i].length; j++) {
                RectangleTipo rectTipo = CampoDeBatalla.coordenadasImagenes[i][j];
                Rectangle rectangulo = rectTipo.REC();
                TipoCasilla tipo = rectTipo.TIPO();

                double balaX = x;
                double balaY = y;
                int balaHeight = (int) imagenBala.getImage().getHeight();
                int balaWidth = (int) imagenBala.getImage().getWidth();

                int rectanguloX = (int) rectangulo.getX();
                int rectanguloY = (int) rectangulo.getY();
                int rectanguloHeight = (int) rectangulo.getHeight();
                int rectanguloWidth = (int) rectangulo.getWidth();

                if (tipo == TipoCasilla.PARED) {
                    if (balaX < rectanguloX + rectanguloWidth &&
                            balaX + balaWidth > rectanguloX &&
                            balaY < rectanguloY + rectanguloHeight &&
                            balaY + balaHeight > rectanguloY) {
                        System.out.println("CHOCOOOOOOOooooooooo");
                        // Ha ocurrido una colisión entre la bala y el rectángulo

                        return true;
                    }
                }
            }
        }
        return false;
    }


}
