package juego.core;

import juego.utils.TipoCasilla;

import java.io.*;


public class SerializadorMapa implements Serializable {
    private final int FILAS = 17;
    private final int COLUMNAS = 22;
    TipoCasilla[][] mapa;

    public SerializadorMapa() {
        mapa = new TipoCasilla[FILAS][COLUMNAS];
        setMapa();
    }

    public TipoCasilla[][] getMapa() {
        return mapa;
    }

    public void setMapa() {
        String archivo = "Coordenadas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    String[] valores = obtenerValores(linea);
                    int x = Integer.parseInt(valores[0].replaceAll("[^\\d.]", ""));
                    int y = Integer.parseInt(valores[1].replaceAll("[^\\d.]", ""));
                    valores[2] = valores[2].trim();

                    setCasilla(TipoCasilla.valueOf(valores[2]), x, y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] obtenerValores(String cadena) {
        return cadena.split("[,\\s-]+");
    }

    private void setCasilla(TipoCasilla tipoCasilla, int x, int y) {
        mapa[x - 1][y - 1] = tipoCasilla;
    }
}

