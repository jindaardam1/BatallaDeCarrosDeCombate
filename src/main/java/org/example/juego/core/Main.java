package org.example.juego.core;

import org.example.juego.utils.TipoCasilla;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Aitor Zubillaga y Jagoba Inda
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Tanques pium pium");

        Ventana ventana = new Ventana();
        ventana.setVisible(true);

        //probarMapaProcedural();

        MapaProcedural mapa = new MapaProcedural(20, 25);
        mapa.generarMapa();
        MapaGrafico mapaPanel = new MapaGrafico(mapa.getMapa());
        ventana.add(mapaPanel);
    }












    private static void probarMapaProcedural() {
        MapaProcedural mapa = new MapaProcedural(0, 0);
        for (int i = 0; i < 30; i++) {
            mapa.generarMapa();
            for (TipoCasilla[] casillas : mapa.getMapa()) {
                for (TipoCasilla casilla : casillas) {
                    switch (casilla) {
                        case HOYO -> System.out.printf("%11s", "\u001B[34mXX \u001B[0m");
                        case NADA -> System.out.printf("%11s", "\u001B[32mXX \u001B[0m");
                        case PARED -> System.out.printf("%11s", "\u001B[33mXX \u001B[0m");
                        case SPAWN_JUGADOR -> System.out.printf("%11s", "\u001B[46mXX\u001B[0m ");
                        default -> System.out.printf("%11s", "\u001B[31mXX \u001B[0m");
                    }
                }
                System.out.println();
            }
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static SerializadorMapa desserializarMapa(String nombreArchivo) {
        SerializadorMapa listaPersonas = null;
        try (FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
             ObjectInputStream entrada = new ObjectInputStream(archivoEntrada)) {
            listaPersonas = (SerializadorMapa) entrada.readObject();
            System.out.println("La lista de personas se ha des serializado correctamente del archivo " + nombreArchivo);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al des serializar la lista de personas: " + e.getMessage());
        }
        return listaPersonas;
    }
}