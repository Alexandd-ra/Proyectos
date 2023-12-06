
package Controlador;
import java.util.Random;

import Modelo.Matriz;


public class ControladorJuego {
    private Matriz matriz;
    private int ancho, alto;
    private static Boolean jugando;
    private static Boolean gano;
    private int puntaje;
    private ControladorClienteServidor control;
    
    public ControladorJuego(ControladorClienteServidor cs) {
        this.control = cs;
        this.jugando = false;
        this.gano = false;
        this.matriz = new Matriz();
        this.alto = 10;
        this.ancho = 10;
        this.puntaje = 0;
    }
    
    private void subirPuntaje(int valor) {
        if (valor == 2) {
            this.puntaje++;
        }
    }
    
    public void empezarJuego() {
        this.jugando = true;
    }
    
    public Boolean jugando() {
        return this.jugando;
    }
    
    public int[][] getMatriz() {
        return this.matriz.getMatriz();
    }
    
    public int getAncho() {
        return this.ancho;
    }
    
    public int getAlto() {
        return this.alto;
    }

    public Boolean getGano() {
        return gano;
    }
    public static void nogano() {
        jugando = false;
        gano = false;
    }
    public int getPuntaje() {
        return this.puntaje;
    }
    
    
    public void moverArriba() {
        int y = this.matriz.pacy();
        int x = this.matriz.pacx();
        int arriba = ((((y - 1) % 10) + 10) % 10);
        int valorPrev = this.getMatriz()[arriba][x];
        if (valorPrev != 1) {
            this.getMatriz()[y][x] = 0;
            this.matriz.pacy(arriba);
            this.matriz.moverPacman();
            this.subirPuntaje(valorPrev);
        }
    }
    
    public void moverAbajo() {
        int y = this.matriz.pacy();
        int x = this.matriz.pacx();
        int abajo = ((((y + 1) % 10) + 10) % 10);
        int valorPrev = this.getMatriz()[abajo][x];
        if (valorPrev != 1) {
            this.getMatriz()[y][x] = 0;
            this.matriz.pacy(abajo);
            this.matriz.moverPacman();
            this.subirPuntaje(valorPrev);
        }
    }
    
    public void moverIzq() {
        int y = this.matriz.pacy();
        int x = this.matriz.pacx();
        int izq = ((((x - 1) % 10) + 10) % 10);
        int valorPrev = this.getMatriz()[y][izq];
        if (valorPrev != 1) {
            this.getMatriz()[y][x] = 0;
            this.matriz.pacx(izq);
            this.matriz.moverPacman();
            this.subirPuntaje(valorPrev);
        }
    }
    
    public void moverDer() {
        int y = this.matriz.pacy();
        int x = this.matriz.pacx();
        int der = ((((x + 1) % 10) + 10) % 10);
        int valorPrev = this.getMatriz()[y][der];
        if (valorPrev != 1) {
            this.getMatriz()[y][x] = 0;
            this.matriz.pacx(der);
            this.matriz.moverPacman();
            this.subirPuntaje(valorPrev);
        }
    }
   
    
    public void verificarGano() {
        if(this.puntaje >= 33 && jugando) {
            if(control.obtenerRed())
               control.enviarMensaje();
            this.gano = true;
            this.jugando = false;
        }
    }
    public void verificarFantasma() {
        if(Matriz.matrizAI[this.matriz.pacy()][this.matriz.pacx()] == 4){
            ControladorJuego.nogano();
        }
    }
    public void moverFantasmas(){
        int []dir = {-1, 1};
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int item = Matriz.matrizAI[i][j];
                if (item == 4) {
                    int intentos = 0;
                    int y = i;
                    int x = j;
                    int valorPrev = 0;
                    Boolean der = new Random().nextBoolean();
                    int ind = new Random().nextInt(2);
                    do {
                        y = i;
                        x = j;
                        if(der){
                            x = ((((j + dir[ind]) % 10) + 10) % 10);
                        } else {
                            y = ((((i + dir[ind]) % 10) + 10) % 10);
                        }
                        valorPrev = this.getMatriz()[y][x];
                        ind = new Random().nextInt(2);
                        der = new Random().nextBoolean();
                        intentos++;
                    }while((Matriz.matrizAI[y][x] == 4 || valorPrev == 1 || Matriz.matrizAI[y][x] == 10) && intentos !=4);
                    if (intentos != 4){
                        Matriz.matrizAI[i][j] = 0;
                        Matriz.matrizAI[y][x] = 10;
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int item = Matriz.matrizAI[i][j];
                if(item == 10) {
                    Matriz.matrizAI[i][j] = 4;
                }
            }
        }
    }
    
}