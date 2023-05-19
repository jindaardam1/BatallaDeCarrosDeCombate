package modelo.tanques;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import modelo.Jugador;

import java.util.Objects;

public class Contador {
    public int cantidad;
    public GridPane panel;
    public int x;
    public int y;

    public Contador() {
        this.cantidad = 0;
        this.panel = new GridPane();
        this.panel.setPrefSize(400, 100);
        this.panel.setStyle("-fx-background-color: transparent;");
        this.x = 0;
        this.y = 0;
    }

    public void pintar(GraphicsContext graficos) {
        recargarContador();
        graficos.clearRect(x, y, panel.getPrefWidth(), panel.getPrefHeight());
        graficos.setFill(Color.WHITE);
        graficos.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        graficos.fillText("Cantidad: " + cantidad, x + 10, y + 30);
        // Carga la imagen desde un archivo (ajusta la ruta según tus necesidades)
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/sprites/tanques/bullet.gif")));
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        panel.getChildren().add(imageView);
    }

    public void recargarContador() {
        this.cantidad = Jugador.arrayBalas.size();
    }
}
