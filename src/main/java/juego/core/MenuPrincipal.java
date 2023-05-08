package juego.core;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import juego.utils.PantallaUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuPrincipal extends Application {

    private static final double ANCHO_VENTANA = PantallaUtil.obtenerAnchoDisponiblePantalla();
    private static final double ALTO_VENTANA = PantallaUtil.obtenerAlturaDisponiblePantalla();
    private static double ANCHO_VENTANA_ACTUAL = ANCHO_VENTANA;
    private static double ALTO_VENTANA_ACUTAL = ALTO_VENTANA;

    double colWidth = ANCHO_VENTANA_ACTUAL * 0.5;
    double rowHeight = ALTO_VENTANA_ACUTAL * 0.3333;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Principal");
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);


        // crear paneles para los botones
        GridPane gridPane = new GridPane();

        //gridPane.setGridLinesVisible(true);//Sirve para ver los bordes

        // Crear las restricciones para las columnas
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(colWidth);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(colWidth);

        // Crear las restricciones para las filas
        RowConstraints row1 = new RowConstraints();
        row1.setPrefHeight(rowHeight);
        RowConstraints row2 = new RowConstraints();
        row2.setPrefHeight(rowHeight);
        RowConstraints row3 = new RowConstraints();
        row3.setPrefHeight(rowHeight);

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
        Image logoImage = new Image(getClass().getResourceAsStream("/imagenes/VentanaPrincipal/logo.png"));


        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(ANCHO_VENTANA_ACTUAL);
        logoImageView.setFitHeight(ALTO_VENTANA_ACUTAL / 3);


        // agregar botones al grid pane

        gridPane.add(campaignButton, 0, 1);
        gridPane.add(survivalButton, 1, 1);
        gridPane.add(exitButton, 0, 2);
        gridPane.add(statsButton, 1, 2);
        gridPane.add(logoImageView, 0, 0, 2, 1);

        // ajustar estilo de los botones
        campaignButton.setStyle("-fx-font-size: 24px;");
        survivalButton.setStyle("-fx-font-size: 24px;");
        exitButton.setStyle("-fx-font-size: 24px;");
        statsButton.setStyle("-fx-font-size: 24px;");


        //configura la alineación de los campos

        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(campaignButton, Pos.CENTER.getHpos());
        GridPane.setHalignment(survivalButton, Pos.CENTER.getHpos());
        GridPane.setHalignment(exitButton, Pos.CENTER.getHpos());
        GridPane.setHalignment(statsButton, Pos.CENTER.getHpos());


        // agregar imagen de fondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/imagenes/VentanaPrincipal/fondoMenuPrincipal.jpg"));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, true, true, true, true));
        VBox root = new VBox();
        root.setBackground(new Background(background));


        // crear contenedor principal y agregar paneles e imagen
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(gridPane);

        // crear escena y agregar al stage
        Scene scene = new Scene(root, ANCHO_VENTANA_ACTUAL, ALTO_VENTANA_ACUTAL);
        primaryStage.setScene(scene);
        primaryStage.show();


        //carga la imagen del boton
        try {
            File fontFile = new File("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\main\\resources\\tipografia\\CStipo.ttf");
            Font fontCS = Font.loadFont(new FileInputStream(fontFile), 12);
            exitButton.setFont(fontCS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
//        exitButton.setTextFill(Color.WHITE);
//        exitButton.setStyle("-fx-background-color: transparent;");
//        exitButton.setPrefSize(120, 40);


        //hace que cuando sales de pantalla completa se coloque a la resolucion deseada
        primaryStage.fullScreenProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {

                this.ANCHO_VENTANA_ACTUAL = ANCHO_VENTANA_ACTUAL / 2;
                this.ALTO_VENTANA_ACUTAL = ALTO_VENTANA_ACUTAL / 2;
                primaryStage.setWidth(ANCHO_VENTANA_ACTUAL);
                primaryStage.setHeight(ALTO_VENTANA_ACUTAL);
                logoImageView.setFitWidth(ANCHO_VENTANA_ACTUAL);
                logoImageView.setFitHeight(ALTO_VENTANA_ACUTAL / 3);


                //coloca la ventana en el medio
                double centerX = PantallaUtil.obtenerCentroPantalla().getX();
                double centerY = PantallaUtil.obtenerCentroPantalla().getY();
                primaryStage.setX(centerX - ANCHO_VENTANA_ACTUAL / 2);
                primaryStage.setY(centerY - ALTO_VENTANA_ACUTAL / 2);

            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}