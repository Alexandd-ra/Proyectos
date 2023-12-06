
package juegoPacman;

import Controlador.ControladorLogin;


public class JuegoPacman {  
    ControladorLogin controladorUsuario;
    
    public JuegoPacman(){       
        controladorUsuario = new ControladorLogin();
    }
    public static void main(String[] args){
       JuegoPacman pacman = new JuegoPacman();
    } 
}
