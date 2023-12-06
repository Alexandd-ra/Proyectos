
package VistaPanel;

import Controlador.ControladorClienteServidor;
import Controlador.ControladorJuego;
import Controlador.ControladorLogin;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelDosJugadores extends PanelUnJugador {
    private JLabel Jugador2;
    
    public PanelDosJugadores(ControladorLogin controladorLogin, ControladorClienteServidor cs) {
        super(controladorLogin, cs);
        this.Jugador2 = new JLabel("JUGADOR 2");
        this.Jugador2.setForeground(Color.RED);
        this.Jugador2.setBounds(80,350,100,25);
        this.panelPuntaje.add(Jugador2);
    }
    
}