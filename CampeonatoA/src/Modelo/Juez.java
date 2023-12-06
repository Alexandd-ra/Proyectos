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
public class Juez extends Persona{
    private int partidas;
    
   public Juez(String nombre, int id, String direccion, int telefono, int partidas){
       super(nombre,id,direccion,telefono);
       this.partidas=partidas;
   }

    public int getPartidas() {
        return partidas;
    }

    public void setPartidas(int partidas) {
        this.partidas = partidas;
    }
   
    
}
