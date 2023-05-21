package utilidades.eventos;

import javafx.scene.shape.Rectangle;

public class SpriteUtils {
    public static boolean isCollisionDetected(Rectangle sprite1, Rectangle sprite2) {
        return sprite1.getBoundsInParent().intersects(sprite2.getBoundsInParent());
    }


}