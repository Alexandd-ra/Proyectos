
package Modelo;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class MensajeIp {
    private String mensaje;
     String IP;
     
     public MensajeIp(){
         
     }
     
     
     // METODO QUE ENVIA EL MSJ DE QUIEN GANO Y MUETSRA LA IMAGEN A CADA JUGADOR 
     public void mostrarMensaje(String paramMensaje){
        mensaje = paramMensaje;
        
        if(mensaje.equals("gano")){
            Icon imagen = new ImageIcon(getClass().getResource("../imagen/ganaste.jpg")); 
            JOptionPane.showMessageDialog(null, "","YOU WIN",JOptionPane.INFORMATION_MESSAGE,imagen);
            
        }else if (mensaje.equals("perdiste")){
            Icon imagen = new ImageIcon(getClass().getResource("../imagen/fin.jpg")); 
            JOptionPane.showMessageDialog(null, "","YOU LOST",JOptionPane.INFORMATION_MESSAGE,imagen);
        }  
    }
     
     public String obtenerIP(){
        IP = JOptionPane.showInputDialog("Porfavor digite el IP al que desea "
                + "hacer conexion");
        return IP;
    }
}
