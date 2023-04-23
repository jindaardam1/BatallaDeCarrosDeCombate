package org.example.juego.core;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.example.juego.utils.TipoCasilla;

public class MapaGrafico extends JPanel {

    private final TipoCasilla[][] mapa;
    private final int anchoCelda = 20;
    private final int altoCelda = 25;
    private BufferedImage imgHoyo, imgNada, imgPared;

    public MapaGrafico(TipoCasilla[][] mapa) {
        this.mapa = mapa;
       /* try {
            imgHoyo = ImageIO.read(new File("C:\\Users\\Aitor\\Desktop\\JUEGO TANQUESS\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\Terreno\\hoyo.png"));
            imgNada = ImageIO.read(new File("C:\\Users\\Aitor\\Desktop\\JUEGO TANQUESS\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\Terreno\\nada.png"));
            imgPared = ImageIO.read(new File("C:\\Users\\Aitor\\Desktop\\JUEGO TANQUESS\\BatallaDeCarrosDeCombate\\src\\resources\\imagenes\\Terreno\\pared.png"));
        } catch (IOException ex) {
            System.err.println("Error al cargar las imágenes: " + ex.getMessage());
        }*/
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        // Dibuja las celdas del mapa
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                int x = j * anchoCelda;
                int y = i * altoCelda;
                switch (mapa[i][j]) {
                    case NADA -> g.setColor(Color.WHITE); //g.drawImage(imgNada, 0, 0, anchoCelda, altoCelda, null);
                    case PARED ->  g.setColor(Color.BLACK);//g.drawImage(imgPared, 0, 0, anchoCelda, altoCelda, null);
                    case HOYO ->  g.setColor(Color.BLUE);//g.drawImage(imgHoyo, 0, 0, anchoCelda, altoCelda, null);
                }

                g.fillRect(x, y, anchoCelda, altoCelda);
            }
        }
    }

    @Override
    public int getWidth() {
        return mapa[0].length * anchoCelda;
    }

    @Override
    public int getHeight() {
        return mapa.length * altoCelda;
    }
}