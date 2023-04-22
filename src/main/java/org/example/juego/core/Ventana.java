package org.example.juego.core;

import org.example.juego.input.InputManager;
import org.example.juego.input.KeyboardHandler;
import org.example.juego.input.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Ventana extends JFrame {

    public Ventana() {
        // Configura la ventana
        setTitle("Batalla de carros de combate");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        // carga una imagen PNG desde un archivo en disco
        BufferedImage icono = null;
        try {
            icono = ImageIO.read(new File("C:\\Users\\Aitor\\Desktop\\JUEGO TANQUESS\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\iconImage.png"));
        } catch (IOException e) {
            // manejar la excepción si la carga de la imagen falla
        }

// establece la imagen como icono de la ventana
        if (icono != null) {
            setIconImage(icono);
        }

        // Crear los objetos que manejan los eventos del teclado y ratón
        KeyboardHandler keyboardHandler = new KeyboardHandler();
            MouseHandler mouseHandler = new MouseHandler();

        // Añadir los listeners a la ventana
        addKeyListener((KeyListener) keyboardHandler);
        addMouseListener((MouseListener) mouseHandler);
        addMouseMotionListener((MouseMotionListener) mouseHandler);



    }
    public void addMouseListener(InputManager.MouseHandler mouseHandler) {
    }




}