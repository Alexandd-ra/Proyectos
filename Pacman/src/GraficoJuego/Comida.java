
package GraficoJuego;

import java.awt.Graphics;
import javax.swing.ImageIcon;


public class Comida {
    int ancho;
    int alto;
    private ImageIcon imagen;
    
    public Comida() {
        
        this.imagen = new ImageIcon("src/Imagen/hongo1.png");
    }
    
    public void pintar(int i, int j, int ancho, int alto, Graphics g) {
        g.drawImage(this.imagen.getImage(), j * ancho, i * alto, ancho, alto, null);
    }
}
