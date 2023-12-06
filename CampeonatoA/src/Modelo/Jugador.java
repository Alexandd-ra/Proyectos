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
public class Jugador extends Persona{
    private int nivel;

    
    public Jugador(String nombre, int id, String direccion, int telefono, int nivel){
        super(nombre,id,direccion,telefono);
        this.nivel=nivel;
       
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
