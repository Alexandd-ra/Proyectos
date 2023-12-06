
package VistaPanel;

import Controlador.ControladorLogin;
import Controlador.ControladorU;
import VistaJframe.VentanaUnJugador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.SwingUtilities.getWindowAncestor;

public class PanelRegistro extends JPanel implements ActionListener{
    
    private JTextField txtNombre, txtNombreUsuario;
    private JPasswordField contrasena;
    private JButton btnCrear, salir;
    private ControladorLogin controladorL;
    private ControladorU usua;
    private JPanel panel;
    private URL imagenFondo; //Direccion
    private Image image;  //traiga la imagen
     
    public PanelRegistro(ControladorLogin controladorUsuario){     
        
        this.controladorL = controladorUsuario;
        usua = new ControladorU();
        Color n =new Color(98,234,9);
        setLayout(new GridLayout(5,2,15,15));        
                        
        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setForeground(Color.WHITE);
        add(lblNombre);
        
        txtNombre = new JTextField();
        add(txtNombre);
        
        JLabel lblNUsuario = new JLabel("Nombre usuario: ");
        lblNUsuario.setForeground(Color.WHITE);
        add(lblNUsuario);
        
        txtNombreUsuario = new JTextField();
        add(txtNombreUsuario);
        
        JLabel lblContra = new JLabel("Contraseña: ");
        lblContra.setForeground(Color.WHITE);
        add(lblContra);
        
        contrasena = new JPasswordField();
        add(contrasena);
        
        btnCrear = new JButton("CREAR USUARIO");      
        btnCrear.setBackground(n);
        btnCrear.setActionCommand("crear");
        btnCrear.addActionListener(this);
        add(btnCrear);
        
        salir = new JButton("SALIR");      
        salir.setBackground(n);
        salir.setActionCommand("salir");
        salir.addActionListener(this);
        add(salir);
        

        setOpaque(false); //averiguar
        
        imagenFondo = getClass().getResource("../imagen/fondoregistro.png");
        image = new ImageIcon(imagenFondo).getImage();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("crear")){
                    
            String nombre = txtNombre.getText();
            String usuario = txtNombreUsuario.getText();
            String contrasenaU = String.valueOf(contrasena.getPassword());
            
            // SI EL USUARIO SE ENCUENTTRA REGISTRADO EN LA BASE DE DATOS ESTE NOS RETORNA UN TRUE Y ES CUANDO MANDAMOS UN MSJ 
            // INFORMANDO AL USUARIO QUE ESE NOMBRE DE USUARIO YA HA SIDO UTILIZADO
            // SI NOS REGRESA UN FALSE PODEMOS MANDAR LOS DATOS DEL USUARIO A LA BASE DE DATOS Y REGISTRARLO
            
            if(usua.usuarioUnico(usuario)){
                JOptionPane.showMessageDialog(null,"EL NOMBRE DE USUARIO YA HA SIDO UTILIZADO ");
            }else{
                usua.registrarUsuario(nombre, usuario, contrasenaU);
                controladorL.loguearUsuario(usuario, contrasenaU);
                controladorL.cerrarMenu();
                JFrame p = (JFrame)getWindowAncestor(this);
                p.dispose();  
            }      
        }else if(e.getActionCommand().equals("salir")){
            JFrame p = (JFrame)getWindowAncestor(this);
            p.dispose();
        }
    }
   
    public void paint(Graphics g){ 
        
        g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
        super.paint(g);
       
    }
}
