
package Controlador;

import VistaPanel.PanelUnJugador;


public class HiloTiempoJuego extends Thread{
    
   
    PanelUnJugador pnlJ;
    ControladorJuego juego;
    
    public HiloTiempoJuego(PanelUnJugador p,ControladorJuego j  ){
        juego = j;
        pnlJ = p;
    }
    
    public void run(){
        while (pnlJ.getTiempo() > 0 && juego.jugando()) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("error en el hilo");
            }
             pnlJ.reducirTiempo();
        }
        ControladorJuego.nogano();
    }
}
