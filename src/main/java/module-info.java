module com.example.pruebafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;


    opens com.example.pruebafx to javafx.fxml;
    exports com.example.pruebafx;
    exports juego.core;
}