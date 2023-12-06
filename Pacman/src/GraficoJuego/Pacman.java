
package GraficoJuego;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Pacman {
    private ImageIcon imagen;
    
    public Pacman() {
        this.imagen = new ImageIcon("src/Imagen/pacman.png");
    }
    
    public void pintar(int i, int j, int ancho, int alto, Graphics g) {
        g.drawImage(this.imagen.getImage(), j * ancho, i * alto, ancho, alto, null);
    }
}