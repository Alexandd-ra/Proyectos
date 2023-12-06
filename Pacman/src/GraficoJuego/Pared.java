
package GraficoJuego;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Pared {
    private ImageIcon imagen;
    
    public Pared() {
        this.imagen = new ImageIcon("src/Imagen/fondo.jpg");
    }
    
    public void pintar(int i, int j, int ancho, int alto, Graphics g) {
        g.drawImage(this.imagen.getImage(),j * ancho, i * alto, ancho, alto,null);
    }
}