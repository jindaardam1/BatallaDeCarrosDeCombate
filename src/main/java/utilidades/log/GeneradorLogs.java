package utilidades.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Clase encargada de general los logs del programa.
 */
public class GeneradorLogs {
    /**
     * Método que se encarga de manejar y guardar en un archivo de registro los errores que ocurren en la aplicación.
     *
     * @param e excepción que se quiere registrar.
     */
    public static void errorLogManager(Exception e) {
        // Se llama al método addLog para guardar la fecha y hora actual, tipo de error y la traza del error en un archivo de registro.
        addLog("ERROR", Arrays.toString(e.getStackTrace()));
    }

    /**
     * Agrega un registro de error al archivo de registro del sistema.
     *
     * @param errorPersonalizado el mensaje de error personalizado a registrar
     */
    public static void errorLogManager(String errorPersonalizado) {
        // Llama al método addLog para agregar el registro de error al archivo de registro
        // del sistema con la fecha y hora actual y el mensaje personalizado del error.
        addLog("ERROR", errorPersonalizado);
    }

    /**
     * Registra un mensaje de registro con un tipo y mensaje especificados.
     * @param tipoLog El tipo de mensaje de registro que se va a registrar.
     * @param mensaje El mensaje de registro que se va a registrar.
     */
    public static void logManager(String tipoLog, String mensaje) {
        // Llama a la función addLog con la fecha y hora actuales, el tipo de registro y el mensaje
        addLog(tipoLog, mensaje);
    }

    /**
     * Agrega una entrada de registro (log) al archivo "logs.log".
     *
     * @param tipoLog el tipo de entrada de registro (log) en formato String.
     * @param mensaje el mensaje de la entrada de registro (log) en formato String.
     */
    private static void addLog(String tipoLog, String mensaje) {
        String fechaHoraActual = fechaHoraActual();

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Path.of("logs.log"), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            pw.printf("%s [%s] -> %s%n", fechaHoraActual, tipoLog, mensaje);
        } catch (IOException e) {
            System.out.println("No se ha podido cargar el log.");
        }
    }

    /**
     * Devuelve la fecha y hora actual formateada como una cadena.
     *
     * @return La fecha y hora actual en el formato "yyyy-MM-dd HH:mm:ss".
     */
    private static String fechaHoraActual() {
        // Obtiene la fecha y hora actual.
        LocalDateTime now = LocalDateTime.now();
        // Crea un objeto de formateo de fecha y hora.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Devuelve la fecha y hora formateada como una cadena.
        return now.format(formatter);
    }
}
