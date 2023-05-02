package juego.core;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Entidad extends ImageView
{
    protected Image spriteIdle;
    protected float anchoDeLaEscena;
    protected float altoDeLaEscena;
    protected int velocidad, distanciaAlSuelo, actualizacionesParaDesaparecer;
    protected static final double MARGEN_INTERSECCION = 5;

    public Entidad(float anchoDeLaEscena, float altoDeLaEscena, int distanciaAlSuelo)
    {
        super();
        this.anchoDeLaEscena = anchoDeLaEscena;
        this.altoDeLaEscena = altoDeLaEscena;
        this.distanciaAlSuelo = distanciaAlSuelo;
        try{
            inicializarMedios();
        }catch(Exception e){
            e.printStackTrace();
        }
        setImage(spriteIdle);
        posicionar();
    }

    public abstract void posicionar();
    public abstract void actualizar();
    public abstract void inicializarMedios();
}