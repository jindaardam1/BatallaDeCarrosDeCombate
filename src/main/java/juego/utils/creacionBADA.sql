CREATE TABLE mapa(
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     serializacion TEXT NOT NULL);

CREATE TABLE jugador(
    nickname TEXT PRIMARY KEY);

CREATE TABLE score(
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      numMapas INTEGER NOT NULL,
                      numTanquesMarrones INTEGER NOT NULL,
                      numTanquesGrises INTEGER NOT NULL,
                      numTanquesAmarillos INTEGER NOT NULL,
                      numTanquesMorados INTEGER NOT NULL,
                      numTanquesBlancos INTEGER NOT NULL,
                      numTanquesNegros INTEGER NOT NULL,
                      numTanquesRojos INTEGER NOT NULL,
                      numTanquesVerdesClaros INTEGER NOT NULL,
                      numTanquesVerdesOscuros INTEGER NOT NULL,
                      jugadorNickname TEXT NOT NULL,
                      FOREIGN KEY (jugadorNickname) REFERENCES jugador(nickname)
);

CREATE TABLE skins(
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      nombre TEXT UNIQUE NOT NULL,
                      conseguida INTEGER NOT NULL DEFAULT 0,
                      jugadorNickname TEXT NOT NULL,
                      FOREIGN KEY (jugadorNickname) REFERENCES jugador(nickname)
);

CREATE TABLE partida(
                        numMapas INTEGER PRIMARY KEY,
                        numTanquesMarrones INTEGER NOT NULL,
                        numTanquesGrises INTEGER NOT NULL,
                        numTanquesAmarillos INTEGER NOT NULL,
                        numTanquesMorados INTEGER NOT NULL,
                        numTanquesBlancos INTEGER NOT NULL,
                        numTanquesNegros INTEGER NOT NULL,
                        numTanquesRojo INTEGER NOT NULL,
                        numTanquesVerdesClaros INTEGER NOT NULL,
                        numTanquesVerdesOscuros INTEGER NOT NULL,
                        jugadorNickname TEXT NOT NULL,
                        FOREIGN KEY (jugadorNickname) REFERENCES jugador(nickname)
);

INSERT INTO mapa (id, serializacion) VALUES (1, '{"mapa":[["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","SPAWN_TANQUE_MARRON","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","SPAWN_JUGADOR","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"]]}');

INSERT INTO mapa (id, serializacion) VALUES (2, '{"mapa":[["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","SPAWN_TANQUE_MARRON","NADA","NADA","HOYO","NADA","NADA","NADA","SPAWN_TANQUE_GRIS","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","NADA","HOYO","NADA","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","SPAWN_TANQUE_GRIS","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","SPAWN_TANQUE_MARRON","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO","NADA","HOYO","NADA","HOYO","HOYO","HOYO","HOYO","HOYO","HOYO"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","SPAWN_JUGADOR","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"]]}');

INSERT INTO mapa (id, serializacion) VALUES (3, '{"mapa":[["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","SPAWN_TANQUE_VERDE_OSCURO","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","PARED","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","PARED","PARED","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","SPAWN_TANQUE_VERDE_OSCURO","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","PARED","PARED","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","PARED","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","SPAWN_JUGADOR","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"]]}');

INSERT INTO mapa (id, serializacion) VALUES (4, '{"mapa":[["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","SPAWN_TANQUE_GRIS","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","SPAWN_JUGADOR","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","SPAWN_TANQUE_VERDE_OSCURO","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","HOYO","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","PARED","NADA","NADA","SPAWN_TANQUE_VERDE_OSCURO","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","SPAWN_TANQUE_GRIS","NADA","NADA","NADA","NADA"],["NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA","NADA"]]}');
