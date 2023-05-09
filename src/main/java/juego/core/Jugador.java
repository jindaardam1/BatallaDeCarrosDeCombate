package juego.core;

import javafx.scene.image.Image;
import juego.entidades.tanques.TanqueJugador;

import java.nio.file.Files;
import java.nio.file.Path;

public class Jugador extends  Entidad{
    private static final int LADO_DEL_SPRITE = 32;
    private static final String RUTA_SPRITE_IDLE = "imagenes/sprites/tanques/tanqueQuieto.gif";
    private static final String RUTA_SPRITE_CORRIENDO = "imagenes/sprites/tanques/tanqueMoviendose.gif";
    private Image spriteMoviendo;
    private float limiteDerecho, limiteIzquierdo, limiteSuperior, limiteInferior;
    private int velocidadX, velocidadY;
    private int velocidadEnCarrera;
    private String nombre;
    private TanqueJugador tanqueJugador;

    public Jugador(String nombre,float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo) {
        super(anchoDeLaEscena, altoDeLaEscena, distanciaAlSuelo);
        this.nombre = nombre;
        this.tanqueJugador = new TanqueJugador();
        this.limiteDerecho = anchoDeLaEscena;
        this.limiteIzquierdo = 0;
        this.limiteSuperior = 0;
        this.limiteInferior= altoDeLaEscena;
        this.velocidadX = 0;
        this.velocidadY = 0;
        this.velocidadEnCarrera = (int)tanqueJugador.getVELOCIDAD();
    }


    public String getNombre() {
        return nombre;
    }

    public TanqueJugador getTanqueJugador() {
        return tanqueJugador;
    }

    @Override
    public void posicionar() {
        setY(LADO_DEL_SPRITE);
        setX(anchoDeLaEscena/2 - LADO_DEL_SPRITE/2);
    }

    @Override
    public void actualizar() {
        setX(getX() + velocidadX);
        setY(getY() + velocidadY);
        if (getBoundsInParent().getMinX() <= limiteIzquierdo || getBoundsInParent().getMaxX() >= limiteDerecho
                || getBoundsInParent().getMinY() <= limiteSuperior || getBoundsInParent().getMaxY() >= limiteInferior) {
            velocidadX = 0;
            velocidadY = 0;
        }
    }

    public void cambiarDireccion(Direccion direccion){
        switch(direccion){
            case DERECHA:
                if(!(getBoundsInParent().getMaxX() >= limiteDerecho)){
                    velocidadX = 1;
                    setScaleX(1);
                    velocidadY = 0;
                }
                break;
            case IZQUIERDA:
                if(!(getBoundsInParent().getMinX() <= limiteIzquierdo)){
                    velocidadX = -1;
                    setScaleX(-1);
                    velocidadY = 0;
                }
                break;
            case ARRIBA:
                if(!(getBoundsInParent().getMinY() <= limiteSuperior)){
                    velocidadX = 0;
                    velocidadY = -1;
                }
                break;
            case ABAJO:
                if(!(getBoundsInParent().getMaxY() >= limiteInferior)){
                    velocidadX = 0;
                    velocidadY = 1;
                }
                break;
        }
        setImage(spriteMoviendo);
    }


    public void moverse(boolean moviendose){
        if(moviendose){
            velocidadX = velocidadEnCarrera;
            velocidadY = velocidadEnCarrera;
        }else{
            velocidadX = 0;
            velocidadY = 0;
        }
    }



    @Override
    public void inicializarMedios() {
        try {
            spriteIdle = new Image(Files.newInputStream(Path.of(RUTA_SPRITE_IDLE)));
            spriteMoviendo = new Image(Files.newInputStream(Path.of(RUTA_SPRITE_CORRIENDO)));
        } catch (Exception e) {
            System.out.println("ALGO NO HA IDO BIEN");
            e.printStackTrace();
        }

    }
}
