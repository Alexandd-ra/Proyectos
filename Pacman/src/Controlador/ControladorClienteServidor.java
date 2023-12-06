
package Controlador;

import Modelo.Cliente;
import Modelo.Servidor;
import VistaJframe.VentanaDosJugadores;
import javax.swing.JOptionPane;

public class ControladorClienteServidor {
    
    private Servidor servidor;
    private Cliente cliente;
    private boolean red;
    private boolean banderaCliente;
    
    public ControladorClienteServidor(){
        servidor = new Servidor();
        cliente = new Cliente();
    }   
  ////////////////////////////////////////////METODOS PARA MANEJAR EL CLIENTE SERVIDOR //////////////////////////////////////
    
    public void enviarMensaje(){
        if(banderaCliente){
            cliente.enviarMensajeGanoAlServidor();
        }else{
            servidor.enviarMensajeGanoAlCliente();
        }
        asignarRed(false);
    }
    
    public boolean obtenerRed(){
        return red;
    }
    
    public void asignarRed(boolean conexion){
        red = conexion;
    }
    
    public void cliente(ControladorLogin controlL){
        cliente.crearCliente();
        if(cliente.conectado()){
            red=true;
            banderaCliente=true;
            cliente.esperarMensaje(this);
            VentanaDosJugadores juegos = new VentanaDosJugadores(controlL, this);
        }else {
            JOptionPane.showMessageDialog(null, "problemaal buscar servidor");
        }
    }
    
    public void servidor(ControladorLogin controlL){
        servidor.crearServidor();
        if(servidor.conectado()){
            banderaCliente=false;
            red=true;
            servidor.esperarMensaje(this);
            VentanaDosJugadores juegos = new VentanaDosJugadores(controlL, this);
        }else {
            JOptionPane.showMessageDialog(null, "se agto el tiepo de espera");
        }
    }
    
}
