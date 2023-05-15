package juego.utils;

public record Partida(int NUM_MAPAS, int NUM_TANQUES_MARRONES, int NUM_TANQUES_GRISES,
                      int NUM_TANQUES_AMARILLOS, int NUM_TANQUES_MORADOS, int NUM_TANQUES_BLANCOS,
                      int NUM_TANQUES_NEGROS, int NUM_TANQUES_ROJO, int NUM_TANQUES_VERDES_CLAROS,
                      int NUM_TANQUES_VERDES_OSCUROS, String JUGADOR_NICKNAME) {}

