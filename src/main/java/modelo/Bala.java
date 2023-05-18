package modelo;

import controlador.input.MouseInputManager;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;
import modelo.mapa.TipoCasilla;
import modelo.records.RectangleTipo;
import vista.juego.CampoDeBatalla;

import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Transparency.TRANSLUCENT;

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
    private ImageView imagenExplosion;
    private final String SPRITEBALA = "/imagenes/sprites/tanques/bullet.gif";
    private final String SPRITEEXPLOSION = "/imagenes/sprites/tanques/explotando.gif";
    public boolean enDisparo;
    private double mouseX;
    private double mouseY;
    private double deltaX;
    private double deltaY;
    private double magnitud;
    private double explosionX;
    private double explosionY;
    private boolean explotando = false;

    public Bala() {

        reubicarBala();
        enDisparo = false;
        this.imagenBala = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEBALA))));
        this.imagenExplosion = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(SPRITEEXPLOSION))));

    }

    public void reubicarBala() {
        this.x = Jugador.x - 3;
        this.y = Jugador.y - 3;

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

    public double rotarSprite() {
        if (enDisparo) {


            double angle = Math.atan2(direccionY, direccionX) * 180 / Math.PI + 90;

            return angle;
        }

        return 0;


    }

    public void ponerExplosion(){
        explotar();

    }

    public void pintar(GraphicsContext graficos) {
        CampoDeBatalla.graficos.save();
        imagenBala.setRotate(rotarSprite()); // Cambiar la rotación a 90 grados

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(javafx.scene.paint.Color.TRANSPARENT); // Establecer el fondo transparente

        WritableImage rotatedImage = imagenBala.snapshot(params, null);
        graficos.drawImage(rotatedImage, x, y);
        CampoDeBatalla.graficos.restore();

        actualizadorDeCordRaton();
            if(explotando){
                graficos.drawImage(imagenExplosion.getImage(),explosionX,explosionY);
            }
        disparar();
        if (enDisparo) {
            rotarSprite();
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
            if (!enDisparo) {

                tomarPosRatonYBala();
                calcularAnguloDisparo();
            }

            System.out.println("Has disparado");


            enDisparo = true;
            //para que si no mueves el raton despues de disparar, la bala no salga otra vez.
            MouseInputManager.clickIzquierdo = false;
        }
        if (chocoPared()) {
            System.out.println("La bala ha chocado con una pared");
            enDisparo = false;


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
                            explosionX = balaX;
                            explosionY = balaY;
                        System.out.println(explosionX+";"+explosionY);
                        ponerExplosion();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void explotar() {
        explotando = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                explotando = false;
                timer.cancel();
            }
        }, 3000); // Establecer el tiempo en milisegundos (1000 ms = 1 segundo)
    }


}
