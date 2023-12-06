/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Modelo.*;
import Excepciones.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author APL
 */
public abstract class ControlCampeonato {
    
    public static Vector guardar= new Vector();
    
    public static void registrarPartidas(Jugador j1, Jugador j2, Juez j, float duracion, String colorj1, String colorj2){
        try{
            if(j1.getNivel() < 1 || j1.getNivel() > 10){
                throw new ExcepcionNivelNoValido("Nivel No Valido.");
        }else{
                guardar.add(new Partida(j1,j2,j,duracion,colorj1,colorj2));
                JOptionPane.showMessageDialog(null, "Partida Registrada Exitosamente.");
            }
    }catch(ExcepcionNivelNoValido e){
            JOptionPane.showMessageDialog(null, e.getMensaje());
        };
    
}
    public static String informacionPartidas(int id){
        
        
        
        int posicion;
        Partida p;
        String salida = "";
        Vector save = new Vector();
        boolean verificar = false;
        try{
        
        for(int i =0; i<guardar.size(); i++){
            p = (Partida) guardar.elementAt(i);
            
            if(p.getJ().getId() == id){
                verificar = true;
               save.add(i);
            }
            
        }
        
        if(verificar == true){
            
            for(int i=0; i<save.size(); i++){
                posicion = (int) save.elementAt(i);
                p = (Partida) guardar.elementAt(posicion);
                
                salida += " PARTIDA N " + (i+1) + "\n" + "Información JugadorN1: " + p.getJ1().getId() + " " + p.getJ1().getNombre() + " " + p.getJ1().getNivel() + " " 
                       + p.getJ1().getDireccion() + " " + p.getJ1().getTelefono() + " " + p.getColorj1() + "\n";
                
                salida += "Información JugadorN2: " + p.getJ2().getId() + " " + p.getJ2().getNombre() + " " + p.getJ2().getNivel() + " " 
                       + p.getJ2().getDireccion() + " " + p.getJ2().getTelefono() + " " + p.getColorj2() + "\n";
                
                salida += "Informacion Juez: " + p.getJ().getId() + " " + p.getJ().getNombre() + " " + p.getJ().getPartidas() + " " 
                       + p.getJ().getDireccion() + " " + p.getJ().getTelefono() + "\n";
                
                salida += "Duración: " + p.getDuracion() + "\n \n";
            }
            save.removeAllElements();
            
        }else{
            throw new ExcepcionPartidaNoEncontrada("La Partida No Fue Encontrada. \n Verifique La ID Del Juez.");
        }
        
       }
       catch(ExcepcionPartidaNoEncontrada epne){
           JOptionPane.showMessageDialog(null, epne.getMensaje());
       }
        catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "El Programa Espera Un Numero.");
        }
        return salida;
        
    }
}
