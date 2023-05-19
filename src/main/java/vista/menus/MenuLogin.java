package vista.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.BatallaDeCarrosDeCombate;
import modelo.records.InfoUsuario;
import servicio.ServicioJugador;
import utilidades.pantalla.PantallaUtil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class MenuLogin {
    private Stage escenaPrincipal;
    private Scene scene;

    public MenuLogin(Stage escenaPrincipal) {
        this.escenaPrincipal = escenaPrincipal;

        // Crear el VBox como raíz
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER); // Alinear al centro verticalmente
        root.setSpacing(10);
        root.setPadding(new Insets(20));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloLogin.css")).toExternalForm());
        root.setId("vbox-login");



        // Crear el campo de entrada de nickname
        TextField textFieldNickname = new TextField();
        textFieldNickname.setPromptText("Ingrese su nickname. NOTA: SE TE ASIGNARÁ ESTE NICKNAME HASTA QUE REINICIES EL JUEGO.");
        textFieldNickname.setMaxWidth(0.55 * PantallaUtil.WIDTH_VENTANA);
        textFieldNickname.setPrefWidth(0.55 * PantallaUtil.WIDTH_VENTANA);
        textFieldNickname.setAlignment(Pos.CENTER);
        textFieldNickname.setPrefHeight(40);


        // Crear la etiqueta para mostrar el mensaje de inicio de sesión
        Label labelMensaje = new Label();

        // Crear el botón para iniciar sesión
        Button buttonIniciarSesion = new Button("Iniciar sesión");
        buttonIniciarSesion.getStyleClass().add("boton-login");
        buttonIniciarSesion.setOnAction(event -> {
            String nickname = textFieldNickname.getText();
            if (!nickname.isEmpty()) {
                labelMensaje.setText("Inicio de sesión exitoso: " + nickname);
                ServicioJugador.logingJugador(nickname);
                BatallaDeCarrosDeCombate.nickname = new InfoUsuario(nickname);
                System.out.println(BatallaDeCarrosDeCombate.nickname);


            } else {
                labelMensaje.setText("Ingrese un nickname válido");
            }
        });

        // Agregar los elementos al VBox
        root.getChildren().addAll(textFieldNickname, buttonIniciarSesion, labelMensaje);

        // Crear la escena con el VBox como nodo raíz
        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloLogin.css")).toExternalForm());
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenaPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenaPrincipal.setTitle("Login");

        // Mostrar la ventana
        escenaPrincipal.show();
    }
}
