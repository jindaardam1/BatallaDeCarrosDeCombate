package juego.core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import juego.utils.PantallaUtil;

/**
 * @author Aitor Zubillaga y Jagoba Inda
 */
public class Main extends Application {

    public static void main(String[] args) {
        // Inicia el programa llamando al método start
        launch(args);

    }

    @Override
    public void start(Stage escenaPrincipal) throws Exception {
        double ancho = PantallaUtil.obtenerAnchoPantalla();
        double altura = PantallaUtil.obtenerAlturaPantalla();
        System.out.println("Ancho de pantalla: " + ancho);
        System.out.println("Altura de pantalla: " + altura);



        System.out.println("Tanques pium pium");
        Group grupo = new Group();
        Ventana ventana = new Ventana(escenaPrincipal, grupo);

        MapaProcedural mapaPrcedural = new MapaProcedural(38, 39);
        mapaPrcedural.generarMapa();
        MapaGrafico mapa = new MapaGrafico(mapaPrcedural.getMapa());

        Scene escenaPrueba = new Scene(mapa, 800, 800);



        Circle circulo = new Circle(60,60,60,Color.RED);
        Pane panelCiruclo = new Pane();
        panelCiruclo.getChildren().add(circulo);
        Scene escenaPrueba2 = new Scene(panelCiruclo, 400, 400);


        escenaPrincipal.setScene(escenaPrueba2);
        escenaPrincipal.setScene(escenaPrueba);

        escenaPrincipal.show();

    }
    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}