package juego.core;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Circulo extends Entidad{

    private Circle circulo;
    public Circulo(){
        super(10,10,10);
        circulo = new Circle(250, 250, 250, Color.RED);


    }
        public Circle getJugadorGrafico(){

        return circulo;

        }
    public  void moveCircleUp() {
        circulo.setCenterY(circulo.getCenterY() - 10);
    }

    public  void moveCircleDown() {
        circulo.setCenterY(circulo.getCenterY() + 10);
    }

    public  void moveCircleLeft() {
        circulo.setCenterX(circulo.getCenterX() - 10);
    }

    public   void moveCircleRight() {
        circulo.setCenterX(circulo.getCenterX() + 10);
    }

    @Override
    public void posicionar() {

    }

    @Override
    public void actualizar() {

    }

    @Override
    public void inicializarMedios() {

    }
}
