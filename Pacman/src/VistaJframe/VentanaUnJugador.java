
package VistaJframe;

import Controlador.ControladorClienteServidor;
import Controlador.ControladorLogin;
import javax.swing.JFrame;
import VistaPanel.PanelUnJugador;

public class VentanaUnJugador extends JFrame {
    
    private PanelUnJugador panel;
    
    public VentanaUnJugador(ControladorLogin controladorLogin, ControladorClienteServidor cs) {
        this.panel = new PanelUnJugador(controladorLogin, cs);
        
        this.setContentPane(panel);
        this.setTitle("PACMAN");
        this.setSize(750, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(panel);
        this.setFocusable(true);
        this.setVisible(true);
    }
}
