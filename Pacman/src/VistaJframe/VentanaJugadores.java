
package VistaJframe;

import Controlador.ControladorLogin;
import VistaPanel.PanelUnJugador;
import VistaPanel.PanelJugdores;
import javax.swing.JFrame;


public class VentanaJugadores extends JFrame {
    private PanelJugdores panelJG;
    
    public VentanaJugadores(ControladorLogin controladorLogin){
         this.panelJG = new PanelJugdores(controladorLogin);
        
        this.setContentPane(panelJG);
        this.setTitle("CUANTOS QUIEREN JUGAR");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setVisible(true);
    }
}
