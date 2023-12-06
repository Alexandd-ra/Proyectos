package VistaPanel;

import Controlador.ControladorLogin;
import Controlador.HiloSonidoInicial;
import VistaJframe.VentanaRegistro;
import VistaJframe.VentanaUnJugador;
import VistaJframe.VentanaSesion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelMenu extends JPanel implements ActionListener {

    private URL imagenFondo; //Direccion
    private Image image;  //traiga la imagen
    private JButton btnsonido;
    private JButton btnsonido2;
    private JButton btnIniciaSesion;
    private JButton btnRegistrarse;
    private JButton btnsalir;
    private JLabel usuario;
    private JLabel contrasena1;
    private JPasswordField contrasena;
    private JTextField textousu;
    private ControladorLogin controladorlogin;
    Image imagenInterna = new ImageIcon("C:\\Users\\USER\\Desktop\\PACMAN ARREGLADO PARTE 2\\PACMAN ARREGLADO CON LO ULTIMO DE ANA\\PACMAN ARREGLADO FALTA QUE ENVIE DATOS\\Pacman\\src\\Imagen\\parasonido.PNG").getImage();
    Image imagenInterna2 = new ImageIcon("C:\\Users\\USER\\Desktop\\PACMAN ARREGLADO PARTE 2\\PACMAN ARREGLADO CON LO ULTIMO DE ANA\\PACMAN ARREGLADO FALTA QUE ENVIE DATOS\\Pacman\\src\\Imagen\\reproducirsonido.PNG").getImage();
    private HiloSonidoInicial hilomenu;
    
    
    public PanelMenu(ControladorLogin controladorL) {

        Color n = new Color(208, 245, 5);
        this.controladorlogin = controladorL;
        hilomenu = new HiloSonidoInicial();
        hilomenu.start();
        
        btnsonido = new JButton("");
        btnsonido.setIcon(new ImageIcon(imagenInterna2));
        btnsonido.setActionCommand("sonido");
        btnsonido.addActionListener(this);
        btnsonido.setBounds(480, 30, 50, 50);
        add(btnsonido);

        btnsonido2 = new JButton();
        btnsonido2.setIcon(new ImageIcon(imagenInterna));
        btnsonido2.setActionCommand("sonido2");
        btnsonido2.addActionListener(this);
        btnsonido2.setBounds(480, 30, 50, 50);       


        btnIniciaSesion = new JButton("INICIAR SESIÓN");
        btnIniciaSesion.setActionCommand("inicia sesion");
        btnIniciaSesion.setBackground(n);
        btnIniciaSesion.setFont(new Font("PacFont", 70, 30));
        btnIniciaSesion.addActionListener(this);
        btnIniciaSesion.setBounds(100, 300, 350, 75);
        add(btnIniciaSesion);

        btnRegistrarse = new JButton("REGISTRARSE");
        btnRegistrarse.setBackground(n);
        btnRegistrarse.setFont(new Font("PacFont", 70, 30));
        btnRegistrarse.addActionListener(this);
        btnRegistrarse.setActionCommand("registrarse");
        btnRegistrarse.setBounds(100, 400, 350, 75);
        add(btnRegistrarse);

        btnsalir = new JButton("SALIR");
        btnsalir.setFont(new Font("PacFont", 70, 30));
        btnsalir.setActionCommand("salir");
        btnsalir.setBackground(n);
        btnsalir.addActionListener(this);
        btnsalir.setBounds(100, 500, 350, 75);
        add(btnsalir);

        imagenFondo = getClass().getResource("../Imagen/Menu.png");
        image = new ImageIcon(imagenFondo).getImage();
        
        setLayout(null);
        setOpaque(false);  
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 600, 850, this);
        super.paint(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("inicia sesion")) {
            hilomenu.detener();
            VentanaSesion vl = new VentanaSesion(this.controladorlogin);
        }
        if (e.getActionCommand().equals("registrarse")) {
            VentanaRegistro v = new VentanaRegistro(this.controladorlogin);
        } else if (e.getActionCommand().equals("salir")) {
            System.exit(0);
        }else if (e.getActionCommand().equals("sonido")) {
            btnsonido.setVisible(false);
            add(btnsonido2);
            hilomenu.detener();
            btnsonido2.setVisible(true);

        } else if (e.getActionCommand().equals("sonido2")) {
            boolean estado =false;
            btnsonido2.setVisible(estado);
            btnsonido.setVisible(true);
           if(estado == false){
                hilomenu.reiniciar();
           }            
        }
    }
}
