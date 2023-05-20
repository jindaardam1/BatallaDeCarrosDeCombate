package vista.menus;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilidades.eventos.TeclaEscapeUtil;
import utilidades.pantalla.PantallaUtil;

import java.util.Objects;

public class MenuPrincipal2 {
    private Stage escenarioPrincipal;
    private Scene scene;

    public MenuPrincipal2(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        VBox root = new VBox();
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/estiloPrincipal.css")).toExternalForm());
        root.setId("vbox-menu-principal");

        Hyperlink boton1 = new Hyperlink("CLÁSICO");
        Hyperlink boton2 = new Hyperlink("SURVIVAL");
        Hyperlink boton3 = new Hyperlink("TOP SCORES");
        Hyperlink boton4 = new Hyperlink("SALIR");

        boton1.getStyleClass().add("hyperlink-custom");
        boton2.getStyleClass().add("hyperlink-custom");
        boton3.getStyleClass().add("hyperlink-custom");
        boton4.getStyleClass().add("hyperlink-custom");

        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(boton1, boton2, boton3, boton4);

        scene = new Scene(root, PantallaUtil.WIDTH_VENTANA, PantallaUtil.HEIGHT_VENTANA);
    }

    public void mostrar() {
        // Asignar la escena al Stage principal
        escenarioPrincipal.setScene(scene);

        // Configurar el título de la ventana
        escenarioPrincipal.setTitle("Menú principal");

        // Configurar el evento de pulsación de tecla para cerrar con "Escape"
        TeclaEscapeUtil.configurarCerrarConEscape(scene, escenarioPrincipal);

        // Mostrar la ventana
        escenarioPrincipal.show();
    }
}
