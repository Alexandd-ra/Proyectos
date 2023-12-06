/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author YURANI
 */
public class HiloSonidoJuego extends Thread {
    boolean parar = true;
    public Clip sonido;

    public HiloSonidoJuego() {
       
    }

    public void run() {
        try {
            obtenerSonido();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(HiloSonidoJuego.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void obtenerSonido() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        sonido = AudioSystem.getClip();
        sonido.open(AudioSystem.getAudioInputStream(new File("C:\\Users\\USER\\Desktop\\PACMAN ARREGLADO PARTE 2\\PACMAN ARREGLADO CON LO ULTIMO DE ANA\\PACMAN ARREGLADO FALTA QUE ENVIE DATOS\\Pacman\\src\\Sonido\\Inicio.wav")));
        sonido.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void detener(){
        sonido.stop();
    }
    public void reiniciar(){
        sonido.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    
}
