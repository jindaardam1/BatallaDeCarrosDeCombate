module com.example.pruebafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;
    requires java.desktop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires sqlite.jdbc;
    requires javafx.media;


    opens com.example.pruebafx to javafx.fxml;
    opens modelo.mapa to com.fasterxml.jackson.databind;
    exports vista.juego;
    exports modelo.mapa;
    exports main;
    exports modelo;
    exports vista.menus;
}