package juego.core;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PuebasMenu extends Application {
    public void start(Stage stageOutput) {
        // setting the title to the application
        stageOutput.setTitle("Canvas Demo");

        // creating group object
        Group groupRef = new Group();

        // creating scene object and add the group
        Scene sceneRef = new Scene(groupRef);
        stageOutput.setScene(sceneRef);

        // Creating canvas object for add an image
        Canvas canvasRef = new Canvas(800, 800);
        groupRef.getChildren().add(canvasRef);

        // Adding 2d graphics to the canvas object
        GraphicsContext context = canvasRef.getGraphicsContext2D();

        // Adding color to the graphic
        context.setFill(Color.GREEN);

        // Adding stroke color to graphic
        context.setStroke(Color.DARKGREEN);

        // Adding line width
        context.setLineWidth(3);

        // Setting font
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        context.setFont(theFont);

        // Adding text to the application
        context.fillText("Batalla De Carros De Combate", 61, 52);
        context.strokeText("Batalla De Carros De Combate", 61, 52);

        // Creating an image object
        Image tanque = new Image("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\ImagenesPruebas\\tanque.jpg");
        Image hitler = new Image("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\ImagenesPruebas\\hitler.jpg");

        // Creating a button for playing the game
        Button playButton = new Button("PULSE AQUI PARA JUGAR");
        playButton.setFont(Font.font(24));


        // Agregamos un EventHandler al botón
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Image ifone = new Image("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\ImagenesPruebas\\ifone.jpg");



                // Adding 2d graphics to the canvas object
                GraphicsContext context2=canvasRef.getGraphicsContext2D();
                context2.drawImage(ifone,0,0);



            }
        });

// Agregamos el botón a la ventana principal
        Group group = new Group();
        group.getChildren().add(playButton);
        Scene scene = new Scene(group);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        // Adding the button to a layout pane for display
        StackPane buttonPane = new StackPane();
        buttonPane.getChildren().add(playButton);
        groupRef.getChildren().add(buttonPane);

        // Adding some more text to the application
        context.setFill(Color.RED);
        context.fillText("NO DURARAS ", 450, 700);
        context.fillText("NI 5 MINUTOS ", 450, 750);

        // Displaying the images
        context.drawImage(hitler, -50, 200);
        context.drawImage(tanque, 200, 200);

        // Display the output
        stageOutput.show();
    }

    public static void main(String args[]){
        // JVM calls start method automatically
        launch(args);
    }
}
