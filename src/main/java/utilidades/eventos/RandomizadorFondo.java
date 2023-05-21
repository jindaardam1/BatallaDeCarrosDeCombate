package utilidades.eventos;


import java.util.*;

public class RandomizadorFondo {

    public static String obtenerImagenAleatoria() {
        List<String> imagenes = Arrays.asList(
                "/imagenes/VentanaPrincipal/FondoPrincipal1.jpg",
                "/imagenes/VentanaPrincipal/FondoPrincipal2.jpg",
                "/imagenes/VentanaPrincipal/FondoPrincipal3.jpg",
                "/imagenes/VentanaPrincipal/FondoPrincipal4.jpg"
        );

        return imagenes.get(new Random().nextInt(imagenes.size()));
    }

}
