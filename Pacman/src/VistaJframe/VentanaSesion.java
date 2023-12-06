
package VistaJframe;

import Controlador.ControladorLogin;
import VistaPanel.PanelIniciarSesion;
import VistaPanel.PanelRegistro;
import javax.swing.JFrame;

/**
 *
 * @author YURANI
 */
public class VentanaSesion extends JFrame{
    PanelIniciarSesion pl;
    
    public VentanaSesion(ControladorLogin controladorlogin){  
        pl = new PanelIniciarSesion(controladorlogin);    
        setContentPane(pl);
        propiedadesLogin();   
    }
    
      public void propiedadesLogin() {
        setSize(500,300);
        setTitle("INICIAR SESION");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
   
}
