package main;

import servicio.ServicioMapa;

import java.util.Arrays;

public class Pruebas {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(ServicioMapa.cargarMapa(2).getMapa()));
    }
}
