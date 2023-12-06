
package VistaPanel;

import Controlador.ControladorClienteServidor;
import Controlador.ControladorLogin;
import GraficoJuego.Comida;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Controlador.ControladorJuego;
import Controlador.HiloSonidoJuego;
import Controlador.HiloTiempoJuego;
import Controlador.LoopAI;
import Controlador.LoopJuego;
import GraficoJuego.fantasma;
import GraficoJuego.Pacman;
import GraficoJuego.Pared;
import GraficoJuego.ParedBorde;
import Modelo.Matriz;
import VistaJframe.VentanaJugadores;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.SwingUtilities.getWindowAncestor;

/**
 *
 * @author juan
 */
public class PanelUnJugador extends JPanel implements KeyListener, ActionListener {
    private ControladorJuego juego;
    private int ancho, alto;
    private LoopJuego loop;
    private LoopAI ai;
    protected JPanel panelPuntaje;
    private JLabel puntajeLa;
    private JLabel Jugador1;
    private JLabel tiempo,txtTiempo;
    private JButton btonIntentar, btonsalir;
    private JButton btnsonidorep,btnsonidopa;
    private ControladorLogin control;
    private fantasma fantasma;
    private Pared pared;
    private Pacman pacman;
    private ParedBorde paredB;
    private Comida comida;
    private HiloTiempoJuego hiloJ;
    private HiloSonidoJuego hilosonidojuego;
    private int tiempoH = 30;
    
    public PanelUnJugador(ControladorLogin controladorLogin, ControladorClienteServidor cs) {
        this.juego = new ControladorJuego(cs);
        this.loop = new LoopJuego(this, juego);
        this.ai = new LoopAI(this, juego);  
        hiloJ = new HiloTiempoJuego(this,juego);
        hilosonidojuego = new HiloSonidoJuego();
        control = controladorLogin;
        this.ancho = 50;  // px
        this.alto = 50;  // px
        
        this.pared = new Pared();
        this.pacman = new Pacman();
        this.paredB = new ParedBorde();
        this.comida = new Comida();
        this.fantasma = new fantasma();
        
        this.panelPuntaje = new JPanel();
        this.panelPuntaje.setBounds(500, 0, 100, 550);
        this.panelPuntaje.setBackground(Color.BLACK);
        this.panelPuntaje.setSize(300, 550);
        this.panelPuntaje.setLayout(null);
        this.add(this.panelPuntaje);
        
        this.puntajeLa = new JLabel(String.valueOf(this.juego.getPuntaje()));
        this.puntajeLa.setForeground(Color.cyan);
        Font font =puntajeLa.getFont(); 
        this.puntajeLa.setFont(new Font(font.getFontName(), font.getStyle(), 30));
        this.puntajeLa.setBounds(90, 80, 70, 70);
        this.panelPuntaje.add(puntajeLa);
        
        this.btnsonidorep = new JButton("");
        this.btnsonidorep.setBounds(15,10,45,40);
        this.btnsonidorep.setIcon(new ImageIcon("C:\\Users\\USER\\Desktop\\PACMAN ARREGLADO PARTE 2\\PACMAN ARREGLADO CON LO ULTIMO DE ANA\\PACMAN ARREGLADO FALTA QUE ENVIE DATOS\\Pacman\\src\\Imagen\\reproducirsonido.PNG"));
        this.btnsonidorep.setActionCommand("sonidoreproducir");
        this.btnsonidorep.addActionListener(this);
        this.panelPuntaje.add(btnsonidorep);
        
        this.btnsonidopa = new JButton("");
        this.btnsonidopa.setBounds(15,10,45,40);
        this.btnsonidopa.setIcon(new ImageIcon("C:\\Users\\USER\\Desktop\\PACMAN ARREGLADO PARTE 2\\PACMAN ARREGLADO CON LO ULTIMO DE ANA\\PACMAN ARREGLADO FALTA QUE ENVIE DATOS\\Pacman\\src\\Imagen\\parasonido.PNG"));
        this.btnsonidopa.setActionCommand("sonidoparar");
        this.btnsonidopa.addActionListener(this);
        
        this.Jugador1 = new JLabel(control.nombreU());
        this.Jugador1.setForeground(Color.red);
        Font letra =Jugador1.getFont(); 
        this.Jugador1.setFont(new Font(letra.getFontName(), letra.getStyle(), 20));
        this.Jugador1.setBounds(80,30,100,25);
        this.panelPuntaje.add(Jugador1);

        
        this.tiempo = new JLabel("TIEMPO");
        this.tiempo.setForeground(Color.red);
        Font letra2 =tiempo.getFont(); 
        this.tiempo.setFont(new Font(letra2.getFontName(), letra2.getStyle(), 20));
        this.tiempo.setBounds(80,180,100,25);
        this.panelPuntaje.add(tiempo);
        
        this.txtTiempo = new JLabel();
        this.txtTiempo.setForeground(Color.YELLOW);
        Font auxFont=txtTiempo.getFont(); 
        this.txtTiempo.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
        this.txtTiempo.setBounds(90, 230, 70, 70);
        this.panelPuntaje.add(txtTiempo);
        
        this.btonIntentar = new JButton("REINTENTAR");
        this.btonIntentar.setBounds(60,350,125,25);
        this.btonIntentar.setActionCommand("intentar");
        this.btonIntentar.addActionListener(this);
        this.panelPuntaje.add(btonIntentar);
        
        this.btonsalir = new JButton("SALIR");
        this.btonsalir.setBounds(60,400,125,25);
        this.btonsalir.setActionCommand("salir");
        this.btonsalir.addActionListener(this);
        this.panelPuntaje.add(btonsalir);
        
        this.setLayout(null);
        this.iniciar();
        hiloJ.start();
        hilosonidojuego.start();
        ai.start();
    }
   
    public void pintarFondo(Graphics g) {
        int[][] matrizAux = this.juego.getMatriz();
        for (int i = 0; i < this.juego.getAlto(); i++) {
            for (int j = 0; j < this.juego.getAncho(); j++) {
                switch(matrizAux[i][j]) {
                    case 0:
                        this.pared.pintar(i, j, ancho, alto, g);
                    break;
                    case 1:
                        this.paredB.pintar(i, j, ancho, alto, g);
                    break;
                    case 2:
                        this.comida.pintar(i , j , ancho, alto , g);
                    break;
                    case 3:
                        this.pacman.pintar(i, j, ancho, alto, g);
                    break;
                }
                if (Matriz.matrizAI[i][j]==4) {
                    this.fantasma.pintar(i, j, ancho, alto, g);
                }
            }
        }
    }
    
   
    // PARA EXTRAER EL TIMEPO DEL JUEGO
    public int getTiempo() {
        return tiempoH;
    }
    
    // METODO PARA DISMINUIR EL TIEMPO DEL JUEGO
    public void reducirTiempo() {
        tiempoH--;
        this.actualizarTiempo();
    }
    
    //METODO QUE NOS PERMITE ACTUALIZAR EL TIEMPO MOSTRARLO EN TIEMPO REAL
    public void actualizarTiempo() {
        this.txtTiempo.setText(String.valueOf(tiempoH));
    }
    
    // MENSAJE QUE SE MUESTRA CUANDO PIERDES EN EL JUEGO
    public void perdiste() {
        Icon imagen = new ImageIcon(getClass().getResource("../imagen/fin.jpg")); 
        JOptionPane.showMessageDialog(this, "","YOU LOST",JOptionPane.INFORMATION_MESSAGE,imagen);
    }
    
    // MENSAJE QUE SE MUESTRA CUANDO GANAS EN EL JUEGO
    public void ganaste() {
        Icon imagen = new ImageIcon(getClass().getResource("../imagen/ganaste.jpg")); 
        JOptionPane.showMessageDialog(this, "","YOU WIN",JOptionPane.INFORMATION_MESSAGE,imagen);
    }
    
    // REINICIA EL TIEMPO SI LA PERSONA YA PASO DE NIVEL// PENDIENTE PARA MODIFICAR
    public void reiniciar() {
        tiempoH = 30;
        this.actualizarTiempo();
    }
    
    public void puntajeGanar(){ 
        int puntos = juego.getPuntaje();
        String nombreUsu = control.nombreU();
        ganaste();
        control.actualizarPuntaje(nombreUsu, puntos);
    }
    
    public void puntajePerder(){
        String nombreUsu = control.nombreU();
        int puntos = juego.getPuntaje();
        control.actualizarPuntaje(nombreUsu, puntos);
        perdiste();
    }
    
     @Override
    public void update(Graphics g){
        super.update(g);
        paint(g);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.pintarFondo(g);
    }
    
    public void iniciar() {
        this.juego.empezarJuego();
        this.loop.start();
    }
    
    
    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.juego.moverArriba();
                break;
            case KeyEvent.VK_DOWN:
                this.juego.moverAbajo();
                break;
            case KeyEvent.VK_LEFT:
                this.juego.moverIzq();
                break;
            case KeyEvent.VK_RIGHT:
                this.juego.moverDer();
                break;
        }
        this.puntajeLa.setText(String.valueOf(this.juego.getPuntaje()));
    }

    @Override
    public void keyReleased(KeyEvent ke) {  
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getActionCommand().equals("intentar")){
            VentanaJugadores v = new VentanaJugadores(this.control);
            hilosonidojuego.detener();
            JFrame p = (JFrame)getWindowAncestor(this);
            p.dispose();
            
        }else if(ae.getActionCommand().equals("salir")){
            System.exit(0);
            
        }else if (ae.getActionCommand().equals("sonidoreproducir")) {
            btnsonidorep.setVisible(false);
            this.panelPuntaje.add(btnsonidopa);
            hilosonidojuego.detener();
            btnsonidopa.setVisible(true);

        } else if (ae.getActionCommand().equals("sonidoparar")) {
            boolean estado =false;
            btnsonidopa.setVisible(estado);
            btnsonidorep.setVisible(true);
           if(estado == false){
                hilosonidojuego.reiniciar();
           }            
        }
    }
}

