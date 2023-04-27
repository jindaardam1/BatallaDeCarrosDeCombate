package juego.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Aitor Zubillaga y Jagoba Inda
 */
public class Main extends Application {

    public static void main(String[] args) {
        // Inicia el programa llamando al método start
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        System.out.println("Tanques pium pium");

        // Crea una instancia de la clase Ventana y llama a su método start() pasando el objeto Stage
        Ventana ventana = new Ventana();
        ventana.start(primaryStage);

        // Crea una instancia de la clase MapaProcedural con un tamaño de (X, Y) y llama a su método generarMapa()
        MapaProcedural mapa = new MapaProcedural(38, 39);
        mapa.generarMapa();
        mapa.imprimeMapaProcedural(mapa);

        // Crea una instancia de la clase MapaGrafico y le pasa el mapa generado aleatoriamente como parámetro
        MapaGrafico mapaPanel = new MapaGrafico(mapa.getMapa());

        // Crea un StackPane que contiene el mapaPanel
        StackPane contenedorMapa = new StackPane(mapaPanel);

        // Crea una nueva escena con el StackPane y un tamaño de 800x800
        Scene escenaMapaAleatorio = new Scene(contenedorMapa, 800, 800);

        // Establece la escena como la escena principal del objeto Stage
        primaryStage.setScene(escenaMapaAleatorio);

        // Muestra la ventana
        primaryStage.show();





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