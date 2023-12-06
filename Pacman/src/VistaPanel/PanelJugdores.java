
package VistaPanel;

import Controlador.ControladorClienteServidor;
import Controlador.ControladorLogin;
import Modelo.Cliente;
import Modelo.Servidor;
import VistaJframe.VentanaDosJugadores;
import VistaJframe.VentanaUnJugador;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingUtilities.getWindowAncestor;

public class PanelJugdores extends JPanel implements ActionListener{
    ControladorLogin controlL;
    ControladorClienteServidor controlClSer;
    private JLabel jlJugadores,jlClintOs;
    private JButton jugador1;
    private JComboBox clienteoS;
    private URL imagenFondo; //Direccion
    private Image image;  //traiga la imagen
    
    public PanelJugdores(ControladorLogin controladorLogin){
        
        controlClSer = new ControladorClienteServidor();
        controlL = controladorLogin;
        setLayout(null);
        
        jlJugadores = new JLabel("ELIJA CUANTOS QUIEREN JUGAR");
        Font font = jlJugadores.getFont(); 
        this.jlJugadores.setFont(new Font(font.getFontName(), font.getStyle(), 15));
        jlJugadores.setBounds(100, 20, 250, 150);
        add(jlJugadores);
        
        jlClintOs = new JLabel("CLIENTE O SERVIDOR");
        this.jlClintOs.setFont(new Font(font.getFontName(), font.getStyle(), 15));
        jlClintOs.setBounds(300, 180, 250, 150);
        add(jlClintOs);
        
        jugador1 = new JButton("UN JUGADOR");
        jugador1.setBounds(50, 250, 150, 80);
        jugador1.addActionListener(this);
        jugador1.setActionCommand("1");
        add(jugador1);
        
        
        clienteoS = new JComboBox();
        clienteoS.setBounds(300, 270, 120, 50);
	clienteoS.addItem("CLIENTE");
	clienteoS.addItem("SERVIDOR");
        clienteoS.addActionListener(this);
        add(clienteoS);
        
        imagenFondo = getClass().getResource("../imagen/imagenele.jpg");
        image = new ImageIcon(imagenFondo).getImage();
        setOpaque(false);
       
    }
    
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 500, 500, this);
        super.paint(g);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String itemSeleecionado = (String)clienteoS.getSelectedItem();
        if(ae.getActionCommand().equals("1")){
            VentanaUnJugador juego = new VentanaUnJugador(controlL, controlClSer);
            JFrame p = (JFrame)getWindowAncestor(this);
            p.dispose();   
        }else if(itemSeleecionado.equals("CLIENTE")){
            controlClSer.cliente(controlL);
            
        }else if(itemSeleecionado.equals("SERVIDOR")){
            controlClSer.servidor(controlL);;
        }
    }
}
