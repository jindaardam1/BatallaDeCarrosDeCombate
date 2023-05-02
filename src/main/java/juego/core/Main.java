package juego.core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Aitor Zubillaga y Jagoba Inda
 */
public class Main extends Application {
    private Circulo circulo;
    public static void main(String[] args) {
        // Inicia el programa llamando al método start
        launch(args);
    }

    @Override
    public void start(Stage escenaPrincipal) throws Exception {


        System.out.println("Tanques pium pium");
        Group grupoRoot = new Group();
        // Crea una instancia de la clase Ventana
        Ventana ventana = new Ventana(escenaPrincipal,grupoRoot);


        // Crea una instancia de la clase MapaProcedural con un tamaño de (X, Y) y llama a su método generarMapa()
        MapaProcedural mapa = new MapaProcedural(38, 39);
        mapa.generarMapa();
        mapa.imprimeMapaProcedural(mapa);

        // Crea una instancia de la clase MapaGrafico y le pasa el mapa generado aleatoriamente como parámetro
        MapaGrafico mapaPanel = new MapaGrafico(mapa.getMapa());


        circulo = new Circulo();

        // Crear el pane y agregar el círculo
        Pane panel = new Pane();
        panel.getChildren().add(circulo);



        // Crea una nueva escena con el StackPane y un tamaño de 800x800
        Scene escenaMapaAleatorio = new Scene(mapaPanel, 800, 800);

        // Establece la escena como la escena principal del objeto Stage
        escenaPrincipal.setScene(escenaMapaAleatorio);


        escenaPrincipal.show();

    }





    public static SerializadorMapa desserializarMapa(String nombreArchivo) {
        SerializadorMapa listaPersonas = null;
        try (FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
             ObjectInputStream entrada = new ObjectInputStream(archivoEntrada)) {
            listaPersonas = (SerializadorMapa) entrada.readObject();
            System.out.println("La lista de personas se ha des serializado correctamente del archivo " + nombreArchivo);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al des serializar la lista de personas: " + e.getMessage());
        }
        return listaPersonas;
    }
}