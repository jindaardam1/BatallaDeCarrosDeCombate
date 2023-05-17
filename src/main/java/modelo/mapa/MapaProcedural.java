package modelo.mapa;

import java.util.Arrays;
import java.util.Random;

public class MapaProcedural {
    private final int FILAS_MIN = 20;
    private final int COLUMNAS_MIN = 25;
    private final int PROBABILIDAD_HOYO = 3;
    private int filas;
    private int columnas;
    private int nivelActual;
    TipoCasilla[][] mapa;

    public MapaProcedural(int filas, int columnas) {
        this.filas = Math.max(filas, FILAS_MIN);
        this.columnas = Math.max(columnas, COLUMNAS_MIN);
        mapa = new TipoCasilla[this.filas][this.columnas];
        this.nivelActual = 1;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
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
        ponerParedes();
        ponerParedesEnLosBordes();
        ponerSpawnsEnemigos();
        ponerSpawnJugador();
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
            int x = random.nextInt(columnas - 4) + 2;
            int y = random.nextInt(filas - 4) + 2;
            int direccion = random.nextInt(4);
            int longitud = random.nextInt(4) + 2;
            boolean esValida = true;
            for (int j = 0; j < longitud; j++) {
                if (x < 1 || x >= columnas - 1 || y < 1 || y >= filas - 1 || mapa[y][x] == TipoCasilla.PARED ||
                        mapa[y-1][x] == TipoCasilla.PARED || mapa[y+1][x] == TipoCasilla.PARED ||
                        mapa[y][x-1] == TipoCasilla.PARED || mapa[y][x+1] == TipoCasilla.PARED) {
                    esValida = false;
                    break;
                }
                switch (direccion) {
                    case 0 -> y--;
                    case 1 -> x++;
                    case 2 -> y++;
                    case 3 -> x--;
                }
            }
            if (esValida) {
                for (int j = 0; j < longitud; j++) {
                    mapa[y][x] = TipoCasilla.PARED;
                    switch (direccion) {
                        case 0 -> y++;
                        case 1 -> x--;
                        case 2 -> y--;
                        case 3 -> x++;
                    }
                }
            }
        }
    }

    private void ponerSpawnsEnemigos() {
        int numSpawns;
        if (nivelActual >= 1 && nivelActual <= 5) {
            numSpawns = 2;
        } else if (nivelActual >= 6 && nivelActual <= 15) {
            numSpawns = 3;
        } else if (nivelActual >= 16 && nivelActual <= 30) {
            numSpawns = 4;
        } else if (nivelActual >= 31 && nivelActual <= 70) {
            numSpawns = 5;
        } else {
            numSpawns = 6;
        }
        Random random = new Random();
        for (int i = 0; i < numSpawns; i++) {
            int x = random.nextInt(columnas);
            int y = random.nextInt(filas);
            if (mapa[y][x] == TipoCasilla.NADA) {
                elegirTanque(x, y);
            } else {
                i--;
            }
        }
    }

    private void elegirTanque(int x, int y) {
        if (nivelActual < 21) {
            mapa[y][x] = tanquesSub20();
        } else if (nivelActual < 41) {
            mapa[y][x] = tanquesSub40();
        } else {
            mapa[y][x] = tanquesSobre41();
        }
    }

    /**
     * Método que devuelve un tanque aleatorio cuando el nivel actual
     * es menor o igual a 20.
     * @return TipoCasilla tanque aleatorio.
     */
    private TipoCasilla tanquesSub20() {
        Random random = new Random();
        int rnd = random.nextInt(100);
        if (rnd < 40) {
            return TipoCasilla.SPAWN_TANQUE_MARRON;
        } else if (rnd < 60) {
            return TipoCasilla.SPAWN_TANQUE_AMARILLO;
        } else if (rnd < 75) {
            return TipoCasilla.SPAWN_TANQUE_GRIS;
        } else if (rnd < 90) {
            return TipoCasilla.SPAWN_TANQUE_VERDE_CLARO;
        } else {
            return TipoCasilla.SPAWN_TANQUE_VERDE_OSCURO;
        }
    }

    /**
     * Método que devuelve un tanque aleatorio cuando el nivel actual
     * es menor o igual a 40.
     * @return TipoCasilla tanque aleatorio.
     */
    private TipoCasilla tanquesSub40() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if (randomNumber < 25) {
            return TipoCasilla.SPAWN_TANQUE_MARRON;
        } else if (randomNumber < 40) {
            return TipoCasilla.SPAWN_TANQUE_AMARILLO;
        } else if (randomNumber < 50) {
            return TipoCasilla.SPAWN_TANQUE_GRIS;
        } else if (randomNumber < 70) {
            return TipoCasilla.SPAWN_TANQUE_VERDE_CLARO;
        } else if (randomNumber < 85) {
            return TipoCasilla.SPAWN_TANQUE_VERDE_OSCURO;
        } else if (randomNumber < 95) {
            return TipoCasilla.SPAWN_TANQUE_ROJO;
        } else {
            return TipoCasilla.SPAWN_TANQUE_MORADO;
        }
    }

    /**
     * Método que devuelve un tanque aleatorio cuando el nivel actual
     * es mayor a 41.
     * @return TipoCasilla tanque aleatorio.
     */
    private TipoCasilla tanquesSobre41() {
        Random random = new Random();
        int valor = random.nextInt(100);
        if (valor < 10) {
            return TipoCasilla.SPAWN_TANQUE_MARRON;
        } else if (valor < 20) {
            return TipoCasilla.SPAWN_TANQUE_AMARILLO;
        } else if (valor < 30) {
            return TipoCasilla.SPAWN_TANQUE_BLANCO;
        } else if (valor < 40) {
            return TipoCasilla.SPAWN_TANQUE_GRIS;
        } else if (valor < 50) {
            return TipoCasilla.SPAWN_TANQUE_NEGRO;
        } else if (valor < 60) {
            return TipoCasilla.SPAWN_TANQUE_VERDE_CLARO;
        } else if (valor < 70) {
            return TipoCasilla.SPAWN_TANQUE_VERDE_OSCURO;
        } else if (valor < 80) {
            return TipoCasilla.SPAWN_TANQUE_ROJO;
        } else {
            return TipoCasilla.SPAWN_TANQUE_MORADO;
        }
    }

    private void ponerSpawnJugador() {
        Random rand = new Random();
        int fila, columna;

        do {
            fila = rand.nextInt(mapa.length);
            columna = rand.nextInt(mapa[0].length);
        } while (mapa[fila][columna] != TipoCasilla.NADA);

        mapa[fila][columna] = TipoCasilla.SPAWN_JUGADOR;
    }
}
