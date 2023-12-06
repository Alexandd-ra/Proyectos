/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaPanel;

import Controlador.ControladorLogin;
import VistaJframe.VentanaUnJugador;
import VistaJframe.VentanaSesion;
import VistaJframe.VentanaRegistro;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import static javax.swing.SwingUtilities.getWindowAncestor;

/**
 *
 * @author YURANI
 */
public class PanelIniciarSesion extends JPanel implements ActionListener {

    private JTextField txtusuario;
    private JPasswordField contrasena;
    private ControladorLogin controlador;
    private JButton btnjugar, btnsalir;
    private URL imagenFondo; //Direccion
    private Image image;  //traiga la imagen

    public PanelIniciarSesion(ControladorLogin controladorlogin) {
        this.controlador = controladorlogin;
        
        Color n = new Color(98, 234, 9);
        setLayout(new GridLayout(4, 2, 15, 15));

        JLabel lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setForeground(Color.BLACK);
        add(lblUsuario);

        txtusuario = new JTextField();
        add(txtusuario);

        JLabel lblcontraseña = new JLabel("Contraseña: ");
        lblcontraseña.setForeground(Color.BLACK);
        add(lblcontraseña);

        contrasena = new JPasswordField();
        add(contrasena);

        btnjugar = new JButton("JUGAR");
        btnjugar.setBackground(n);
        btnjugar.setActionCommand("jugar");
        btnjugar.addActionListener(this);
        add(btnjugar);

        btnsalir = new JButton("SALIR");
        btnsalir.setActionCommand("salir");
        btnsalir.addActionListener(this);
        btnsalir.setBackground(n);
        add(btnsalir);

        setOpaque(false); //averiguar

        imagenFondo = getClass().getResource("../imagen/person.png");
        image = new ImageIcon(imagenFondo).getImage();
        setOpaque(false);

    }
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 500, 300, this);
        super.paint(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("jugar")){
            
            String usuario = txtusuario.getText();
            String contrasenaU = String.valueOf(contrasena.getPassword());
            
            if(usuario.isEmpty() || contrasenaU.isEmpty()){
               JOptionPane.showMessageDialog(this, "Debe ingresar el usuario y contraseña");
            }else{
               controlador.loguearUsuario(usuario, contrasenaU);
               JFrame p = (JFrame)getWindowAncestor(this);
               p.dispose();
            }
            
        }else if(e.getActionCommand().equals("salir")){
            JFrame p = (JFrame)getWindowAncestor(this);
            p.dispose();
        }
    }
}



