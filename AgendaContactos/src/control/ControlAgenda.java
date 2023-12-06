package control;

import modelo.*;
import java.util.*;

public abstract class ControlAgenda {  
    
    public static Vector contactos = new Vector();
    public static boolean registrarContacto(String nombre, String telefono, String email, int edad){
        try{
        contactos.add(new Contacto(nombre,telefono,email,edad));
        return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public static Contacto buscarContacto(String nombre){
        
        for(int i=0; i<contactos.size(); i++){
            
            if(((Contacto)contactos.elementAt(i)).getNombre().equals(nombre))
                return ((Contacto)contactos.elementAt(i));    
        }
        return null;
    }
    
}
