
package GraficoJuego;

import java.awt.Graphics;
import javax.swing.ImageIcon;


public class fantasma {
       private ImageIcon imagen;
    
    public fantasma() {
        this.imagen = new ImageIcon("src/Imagen/Enemigo1.png");
    }
    
    public void pintar(int i, int j, int ancho, int alto, Graphics g) {
        g.drawImage(this.imagen.getImage(), j * ancho, i * alto, ancho, alto, null);
    }
}
