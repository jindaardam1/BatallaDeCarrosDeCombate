package juego.core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import juego.input.InputManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ventana extends Application {

    private InputManager inputManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Configura la ventana
        primaryStage.setTitle("Batalla de carros de combate");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.setResizable(false);

        // carga una imagen PNG desde un archivo en disco y establece la imagen como icono de la ventana
        try {
            Image icono = new Image(new FileInputStream("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO FINAL TANQUES\\BatallaDeCarrosDeCombate-main\\src\\resources\\imagenes\\iconImage.png"));
            primaryStage.getIcons().add(icono);
        } catch (FileNotFoundException e) {
            // manejar la excepción si la carga de la imagen falla
        }

        // Crear un Group vacío como contenido de la escena
        Group grupoRoot = new Group();

        // Crear la escena con el Group como contenido y las dimensiones de la ventana
        Scene scene = new Scene(grupoRoot, 800, 800);

        // Crear el objeto InputManager para enlazarlo con cualquier Input
        this.inputManager = new InputManager(scene);

        // Añadir la escena a la ventana
        primaryStage.setScene(scene);

        // Mostrar la ventana
        primaryStage.show();
    }

    // Detener cualquier cosa que esté corriendo en el juego
    @Override
    public void stop() throws Exception {

        super.stop();
    }
}
