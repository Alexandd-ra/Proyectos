
package Controlador;

import VistaPanel.PanelDosJugadores;
import VistaPanel.PanelUnJugador;

public class LoopAI extends Thread {
    private PanelUnJugador panel;
    private ControladorJuego juego;
    
    public LoopAI(PanelUnJugador p, ControladorJuego j) {
        this.panel = p;
        this.juego = j;
        
    }
    
    @Override
    public void run() {
        while(this.juego.jugando()) {
            this.juego.moverFantasmas();
            try {
                this.sleep(500);
            } catch(InterruptedException e){
                System.out.println("error en el hilo");
            }
        }
    }
}
