package utilidades.eventos;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import vista.menus.MenuPrincipal;

public class PulsarTeclasUtil {
    public static void configurarCerrarConEscape(Scene scene, Stage stage) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }

    public static void configurarVolverMenuPrincipal(Scene scene, Stage stage) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                MenuPrincipal mp = new MenuPrincipal(stage);
                mp.mostrar();
            }
        });
    }
}

