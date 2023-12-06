
package VistaJframe;

import Controlador.ControladorClienteServidor;
import Controlador.ControladorLogin;
import VistaPanel.PanelDosJugadores;
import VistaPanel.PanelUnJugador;
import javax.swing.JFrame;


public class VentanaDosJugadores extends JFrame {
    
    private PanelDosJugadores panelDos;
    
    public VentanaDosJugadores(ControladorLogin controladorLogin, ControladorClienteServidor cs) {
        this.panelDos = new PanelDosJugadores(controladorLogin, cs);
        
        this.setContentPane(panelDos);
        this.setTitle("PACMAN");
        this.setSize(750, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(panelDos);
        this.setFocusable(true);
        this.setVisible(true);
    }
}
