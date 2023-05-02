package juego.core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import juego.input.InputManager;
import juego.input.KeyboardHandler;


import java.io.FileInputStream;
import java.io.FileNotFoundException;


import static jdk.jfr.FlightRecorder.addListener;


public class Ventana extends Main  {

    private InputManager inputManager;
    private Scene escena;
public  Ventana(Stage escenaPrimaria, Group grupo) {
    escenaPrimaria.setTitle("Batalla de carros de combate");
    escenaPrimaria.setWidth(800);
    escenaPrimaria.setHeight(800);
    escenaPrimaria.setResizable(false);

    // carga una imagen PNG desde un archivo en disco y establece la imagen como icono de la ventana
    try {
        Image icono = new Image(new FileInputStream("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO FINAL TANQUES\\BatallaDeCarrosDeCombate-main\\src\\resources\\imagenes\\iconImage.png"));
        escenaPrimaria.getIcons().add(icono);
    } catch (FileNotFoundException e) {
        // manejar la excepción si la carga de la imagen falla
    }

    // Crear un Group vacío como contenido de la escena


    // Crear la escena con el Group como contenido y las dimensiones de la ventana
    escena = new Scene(grupo, 800, 800);

    //Vincula la escena con los inputs del teclado
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    escena.setOnKeyPressed(event -> keyboardHandler.handleKeyDown(event.getCode()));
    escena.setOnKeyReleased(event -> keyboardHandler.handleKeyUp(event.getCode()));




    // Añadir la escena a la ventana
    escenaPrimaria.setScene(escena);


}


    // Detener cualquier cosa que esté corriendo en el juego
    @Override
    public void stop() throws Exception {

        super.stop();
    }
}
