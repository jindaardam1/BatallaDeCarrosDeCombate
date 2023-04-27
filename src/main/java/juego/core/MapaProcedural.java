package juego.core;

import juego.utils.TipoCasilla;

import java.util.Arrays;
import java.util.Random;

public class MapaProcedural {
    private final int FILAS_MIN = 20;
    private final int COLUMNAS_MIN = 25;
    private final int PROBABILIDAD_HOYO = 4;
    private int filas;
    private int columnas;
    TipoCasilla[][] mapa;

    public MapaProcedural(int filas, int columnas) {
        this.filas = Math.max(filas, FILAS_MIN);
        this.columnas = Math.max(columnas, COLUMNAS_MIN);
        mapa = new TipoCasilla[this.filas][this.columnas];
    }

    public TipoCasilla[][] getMapa() {
        return mapa;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void generarMapa() {
        for (int i = 0; i < filas; i++) {
            Arrays.fill(mapa[i], TipoCasilla.NADA);
        }
        crearHoyos();
        ponerParedesEnLosBordes();
        ponerParedes();
    }

    private void crearHoyos() {
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (random.nextInt(100) < PROBABILIDAD_HOYO) {
                    mapa[i][j] = TipoCasilla.HOYO;
                }
            }
        }
    }

    private void ponerParedesEnLosBordes() {
        for (int i = 0; i < filas; i++) {
            mapa[i][0] = TipoCasilla.PARED;
            mapa[i][columnas - 1] = TipoCasilla.PARED;
        }
        for (int j = 0; j < columnas; j++) {
            mapa[0][j] = TipoCasilla.PARED;
            mapa[filas - 1][j] = TipoCasilla.PARED;
        }
    }

    private void ponerParedes() {
        Random random = new Random();
        int numEstructuras = random.nextInt(5) + 5;
        for (int i = 0; i < numEstructuras; i++) {
            int x = random.nextInt(columnas - 2) + 1;
            int y = random.nextInt(filas - 2) + 1;
            int direccion = random.nextInt(4);
            int longitud = random.nextInt(10) + 5;
            for (int j = 0; j < longitud; j++) {
                if (x < 1 || x >= columnas - 1 || y < 1 || y >= filas - 1) {
                    break;
                }
                mapa[y][x] = TipoCasilla.PARED;
                switch (direccion) {
                    case 0 -> y--;
                    case 1 -> x++;
                    case 2 -> y++;
                    case 3 -> x--;
                }
            }
        }
    }
    /*Representación por terminal del mapa*/
    public  void imprimeMapaProcedural(MapaProcedural mapa) {

            for (TipoCasilla [] casillas : mapa.getMapa()) {
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
        }

    public  void generadorDeMapasDePrueba() {
        MapaProcedural mapa = new MapaProcedural(0, 0);
        for (int i = 0; i < 30; i++) {
            mapa.generarMapa();
            for (TipoCasilla [] casillas : mapa.getMapa()) {
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

}