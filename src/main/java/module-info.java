module com.example.pruebafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pruebafx to javafx.fxml;
    exports com.example.pruebafx;
    exports juego.core;
}