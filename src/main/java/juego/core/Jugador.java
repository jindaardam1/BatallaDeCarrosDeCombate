package juego.core;

import juego.entidades.tanques.TanqueJugador;

public class Jugador{
    private String nombre;
    private TanqueJugador tanqueJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tanqueJugador = new TanqueJugador();
    }

    public String getNombre() {
        return nombre;
    }

    public TanqueJugador getTanqueJugador() {
        return tanqueJugador;
    }

    // Métodos adicionales para controlar el jugador en el juego
    public void moverArriba() {
        // Código para mover el tanque hacia arriba
    }

    public void moverAbajo() {
        // Código para mover el tanque hacia abajo
    }

    public void moverIzquierda() {
        // Código para mover el tanque hacia la izquierda
    }

    public void moverDerecha() {
        // Código para mover el tanque hacia la derecha
    }
}
