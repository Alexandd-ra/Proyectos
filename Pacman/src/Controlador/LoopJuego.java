
package Controlador;

import VistaPanel.PanelDosJugadores;
import VistaPanel.PanelUnJugador;

public class LoopJuego extends Thread {
    private PanelUnJugador panel;
    private ControladorJuego juego;
    private PanelDosJugadores pnelDosJ;
    
    public LoopJuego(PanelUnJugador p, ControladorJuego j) {
        this.panel = p;
        this.juego = j;
        
    }
    
    @Override
    public void run() {
        while(this.juego.jugando()) {
            this.panel.repaint();
            this.juego.verificarFantasma();
            this.juego.verificarGano();
            try {
                this.sleep(15);
            } catch(InterruptedException e){
                System.out.println("error en el hilo");
            }
        }
        if(juego.getGano()) {
            panel.puntajeGanar();
        } else {
            panel.puntajePerder();
        }
    }
}
