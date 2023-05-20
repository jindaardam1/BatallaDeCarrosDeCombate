package utilidades.eventos;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class TeclaEscapeUtil {
    public static void configurarCerrarConEscape(Scene scene, Stage stage) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }
}

