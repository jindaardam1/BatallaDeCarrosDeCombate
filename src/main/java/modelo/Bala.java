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
    public int x;
    public int y;
    private double direccionX;  // Dirección X hacia la cual moverse
    private double direccionY;  // Dirección Y hacia la cual moverse
    private double posRatonAlDispararX;
    private double posRatonAlDispararY;
    private ImageView imagenBala;
    private final String SPRITEBALA = "/imagenes/sprites/tanques/bullet.png";
    public static boolean disparo;

    public Bala() {

        this.direccionX = 0;
        this.direccionY = 0;
        this.disparo = false;


        imagenBala = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBALA))));
    }

    public void reubicarBala() {
        this.x = Jugador.x;
        this.y = Jugador.y;
    }

    public void pintar(GraphicsContext graficos) {
        graficos.drawImage(imagenBala.getImage(), x, y);
        mover();
        disparar();
        if (!disparo) {

            reubicarBala();
        }
    }

    private void mover() {
        if (disparo) {
            double mouseX = posRatonAlDispararX;
            double mouseY = posRatonAlDispararY;
            double deltaX = mouseX - x;
            double deltaY = mouseY - y;
            double magnitud = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            direccionX = deltaX / magnitud;
            direccionY = deltaY / magnitud;
            x += direccionX * VELOCIDAD;
            y += direccionY * VELOCIDAD;
        }
    }

    private void disparar() {
        if (MouseInputManager.getClickIzquierdo()) {
            System.out.println("Has disparado");
            tomarPosRaton();
            disparo = true;

            if (chocoPared()) {
                System.out.println("La bala ha chocado con una pared");
                disparo = false;

            }
        }
    }

    private void tomarPosRaton() {
        posRatonAlDispararX =MouseInputManager.getMouseX();
        posRatonAlDispararY =MouseInputManager.getMouseY();
    }

    private boolean chocoPared() {
        for (int i = 0; i < CampoDeBatalla.coordenadasImagenes.length; i++) {
            for (int j = 0; j < CampoDeBatalla.coordenadasImagenes[i].length; j++) {
                RectangleTipo rectTipo = CampoDeBatalla.coordenadasImagenes[i][j];
                Rectangle rectangulo = rectTipo.REC();
                TipoCasilla tipo = rectTipo.TIPO();

                int balaX = x;
                int balaY = y;
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
                        // Ha ocurrido una colisión entre la bala y el rectángulo

                        return true;
                    }
                }
            }
        }
        return false;
    }


}
