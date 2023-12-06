
package VistaJframe;

import Controlador.ControladorLogin;
import VistaPanel.PanelRegistro;
import javax.swing.JFrame;

public class VentanaRegistro extends JFrame{
    
    PanelRegistro panel;
    
    public VentanaRegistro(ControladorLogin controladorUsuario){
        
        super("REGISTRO");
        panel = new PanelRegistro(controladorUsuario);    
        setContentPane(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,300);
        setVisible(true);                        
    }
}
