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

    private static final double ANCHO_VENTANA = PantallaUtil.obtenerAnchoPantalla();
    private static final double ALTO_VENTANA = PantallaUtil.obtenerAlturaPantalla();

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Principal");

        // crear paneles para los botones
        HBox topButtonsPane = new HBox();
        HBox bottomButtonsPane = new HBox();

        // crear botones
        Button campaignButton = new Button("Campaña");
        Button survivalButton = new Button("Supervivencia");
        Button exitButton = new Button("Salir");
        Button statsButton = new Button("Estadísticas");

        // agregar botones a los paneles correspondientes
        topButtonsPane.getChildren().addAll(campaignButton, survivalButton);
        bottomButtonsPane.getChildren().addAll(exitButton, statsButton);

        // ajustar estilo de los botones
        campaignButton.setStyle("-fx-font-size: 24pt;");
        survivalButton.setStyle("-fx-font-size: 24pt;");
        exitButton.setStyle("-fx-font-size: 24pt;");
        statsButton.setStyle("-fx-font-size: 24pt;");

        // agregar imagen del logo
        Image logoImage = new Image("C:\\_DiscoDatos-MA\\ASIGNATURAS\\PROGRAMACIÖN\\PROYECTO TANQUES\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\VentanaPrincipal\\logo.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(ANCHO_VENTANA);
        logoImageView.setFitHeight(ALTO_VENTANA/2);



        // agregar imagen de fondo
        Image backgroundImage = new Image("file:///C:/_DiscoDatos-MA/ASIGNATURAS/PROGRAMACIÖN/PROYECTO TANQUES/BatallaDeCarrosDeCombate/src/resources/imagenes/VentanaPrincipal/fondo.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
        VBox root = new VBox();
        root.setBackground(new Background(background));

        // crear contenedor principal y agregar paneles e imagen

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(logoImageView,topButtonsPane , bottomButtonsPane);

        // crear escena y agregar al stage
        Scene scene = new Scene(root, ANCHO_VENTANA, ALTO_VENTANA);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("fondoMenuPrincipal.css");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
