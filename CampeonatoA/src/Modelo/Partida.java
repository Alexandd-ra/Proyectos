/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author APL
 */
public class Partida {
    
    private Jugador j1;
    private Jugador j2;
    private Juez j;
    private float duracion;
    private String colorj1;
    private String colorj2;
    
    public Partida(Jugador j1, Jugador j2, Juez j, float duracion, String colorj1, String colorj2){
        this.j1=j1;
        this.j2=j2;
        this.j=j;
        this.duracion=duracion;
        this.colorj1=colorj1;
        this.colorj2=colorj2;
    }

    public Jugador getJ1() {
        return j1;
    }

    public void setJ1(Jugador j1) {
        this.j1 = j1;
    }

    public Jugador getJ2() {
        return j2;
    }

    public void setJ2(Jugador j2) {
        this.j2 = j2;
    }

    public Juez getJ() {
        return j;
    }

    public void setJ(Juez j) {
        this.j = j;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getColorj1() {
        return colorj1;
    }

    public void setColorj1(String colorj1) {
        this.colorj1 = colorj1;
    }

    public String getColorj2() {
        return colorj2;
    }

    public void setColorj2(String colorj2) {
        this.colorj2 = colorj2;
    }
    
   
    
}
