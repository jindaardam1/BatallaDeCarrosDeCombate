package juego.core;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import juego.utils.PantallaUtil;

public class MenuPrincipal extends Application {

    private static final double ANCHO_VENTANA = PantallaUtil.obtenerAnchoPantalla() / 2;
    private static final double ALTO_VENTANA = PantallaUtil.obtenerAlturaPantalla() / 2;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Principal");
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);

        // crear paneles para los botones
        GridPane gridPane = new GridPane();

        // Crear las restricciones para las columnas
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        // Crear las restricciones para las filas
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(33.33);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(33.33);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(33.33);

        // Agregar las restricciones a las columnas del GridPane
        gridPane.getColumnConstraints().addAll(col1, col2);

        // Agregar las restricciones a las filas del GridPane
        gridPane.getRowConstraints().addAll(row1, row2, row3);

        // crear botones
        Button campaignButton = new Button("Campaña");
        Button survivalButton = new Button("Supervivencia");
        Button exitButton = new Button("Salir");
        Button statsButton = new Button("Estadísticas");

        // agregar imagen del logo
        Image logoImage = new Image("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\VentanaPrincipal\\logo.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(ANCHO_VENTANA);
        logoImageView.setFitHeight(ALTO_VENTANA/3);

        // agregar botones al grid pane

        gridPane.add(campaignButton, 0, 1);
        gridPane.add(survivalButton, 1, 1);
        gridPane.add(exitButton, 0, 2);
        gridPane.add(statsButton, 1, 2);
        gridPane.add(logoImageView, 0, 0, 2, 1);

        // ajustar estilo de los botones
        campaignButton.setStyle("-fx-font-size: 24pt;");
        survivalButton.setStyle("-fx-font-size: 24pt;");
        exitButton.setStyle("-fx-font-size: 24pt;");
        statsButton.setStyle("-fx-font-size: 24pt;");

        //configura la alineación de los campos
        gridPane.setAlignment(Pos.CENTER);




        // agregar imagen de fondo
        Image backgroundImage = new Image("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\VentanaPrincipal\\fondoMenuPrincipal.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, true, true, true, true));
        VBox root = new VBox();
        root.setBackground(new Background(background));



        // crear contenedor principal y agregar paneles e imagen
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(gridPane);

        // crear escena y agregar al stage
        Scene scene = new Scene(root, ANCHO_VENTANA, ALTO_VENTANA);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\resources\\css\\fondoMenuPrincipal.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}