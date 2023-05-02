DROP DATABASE IF EXISTS tanquesbd;
CREATE DATABASE tanquesbd;

USE tanquesbd;

CREATE TABLE mapa(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    serializacion VARCHAR(10000) NOT NULL);

CREATE TABLE jugador(
    nickname VARCHAR(30) PRIMARY KEY);

CREATE TABLE score(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    numMapas INTEGER NOT NULL,
    numTanquesMarrones INTEGER NOT NULL,
    numTanquesGrises INTEGER NOT NULL,
    numTanquesAmarillos INTEGER NOT NULL,
    numTanquesMorados INTEGER NOT NULL,
    numTanquesBlancos INTEGER NOT NULL,
    numTanquesNegros INTEGER NOT NULL,
    numTanquesRojo INTEGER NOT NULL,
    numTanquesVerdesClaros INTEGER NOT NULL,
    numTanquesVerdesOscuros INTEGER NOT NULL,
    jugadorNickname VARCHAR(30) NOT NULL,
    FOREIGN KEY (jugadorNickname) REFERENCES jugador(nickname)
);

CREATE TABLE skins(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(30) UNIQUE NOT NULL,
    conseguida TINYINT NOT NULL DEFAULT 0,
    jugadorNickname VARCHAR(30) NOT NULL,
    FOREIGN KEY (jugadorNickname) REFERENCES jugador(nickname));

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
    jugadorNickname VARCHAR(30) NOT NULL,
    FOREIGN KEY (jugadorNickname) REFERENCES jugador(nickname));