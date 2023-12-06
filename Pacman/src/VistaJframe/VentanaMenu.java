package VistaJframe;

import Controlador.ControladorLogin;
import VistaPanel.PanelMenu;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class VentanaMenu extends JFrame {

    PanelMenu pnlMenu;

    public VentanaMenu(ControladorLogin controladorUsuario) {

        pnlMenu = new PanelMenu(controladorUsuario);
        add(pnlMenu);
        propiedadesMenu();
    }

    public void propiedadesMenu() {
        setSize(560, 700);
        setTitle("MENÚ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
