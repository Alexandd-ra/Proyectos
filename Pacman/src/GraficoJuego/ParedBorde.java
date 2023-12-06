
package GraficoJuego;

import java.awt.Graphics;
import javax.swing.ImageIcon;


public class ParedBorde {
    
    private ImageIcon imagen;
    
    public ParedBorde() {
        this.imagen = new ImageIcon("src/Imagen/Ladrillo.png");
    }
    
    public void pintar(int i, int j, int ancho, int alto, Graphics g) {
        g.drawImage(this.imagen.getImage(), j * ancho, i * alto, ancho, alto, null);
    }
}
