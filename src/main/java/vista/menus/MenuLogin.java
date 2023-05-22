package vista.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.BatallaDeCarrosDeCombate;
import modelo.records.InfoUsuario;
import servicio.ServicioJugador;
import utilidades.eventos.PulsarTeclasUtil;
import utilidades.pantalla.PantallaUtil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 *  Clase MenuLogin.
 *  Esta clase representa el menú de inicio de sesión de la aplicación.
 *  Permite al usuario ingresar su nickname y comenzar el proceso de inicio de sesión.
 */
public class MenuLogin {
    private Stage escenarioPrincipal;
    private Scene scene;

    /**
     * Constructor de la clase MenuLogin.
     * @param escenarioPrincipal El escenario principal de la aplicación.
     */
    public MenuLogin(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;

        // Crear el VBox como raíz
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(20));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloLogin.css")).toExternalForm());
        root.setId("vbox-login");

        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/login/logo.png")));
        ImageView logo = new ImageView(imagen);
        logo.setFitWidth(780);
        logo.setFitHeight(171.8);
        logo.setId("logo");

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

        // Configurar el evento de pulsación de tecla "Enter" en el TextField
        textFieldNickname.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonIniciarSesion.fire(); // Simular un clic en el botón de inicio de sesión
            }
        });

        buttonIniciarSesion.setOnAction(event -> {
            String nickname = textFieldNickname.getText();
            if (!nickname.isEmpty()) {
                labelMensaje.setText("Inicio de sesión exitoso: " + nickname);
                ServicioJugador.logingJugador(nickname);
                BatallaDeCarrosDeCombate.nickname = new InfoUsuario(nickname);

                MenuPrincipal mp = new MenuPrincipal(escenarioPrincipal);
                mp.mostrar();
            } else {
                labelMensaje.setText("Ingrese un nickname válido");
            }
        });

        // Agregar los elementos al VBox
        root.getChildren().addAll(logo, textFieldNickname, buttonIniciarSesion, labelMensaje);

        // Crear la escena con el VBox como nodo raíz
        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloLogin.css")).toExternalForm());
    }

    /**
     * Método mostrar.
     * Muestra la ventana del menú de inicio de sesión en el escenario principal.
     * Configura el título de la ventana y el evento de pulsación de tecla para cerrar con "Escape".
     */
    public void mostrar() {
        // Asignar la escena al Stage principal
        escenarioPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenarioPrincipal.setTitle("Login");

        // Configurar el evento de pulsación de tecla para cerrar con "Escape"
        PulsarTeclasUtil.configurarCerrarConEscape(scene, escenarioPrincipal);

        // Mostrar la ventana
        escenarioPrincipal.show();
    }
}
