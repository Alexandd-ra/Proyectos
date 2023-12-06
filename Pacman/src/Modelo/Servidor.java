
package Modelo;

import Controlador.ControladorClienteServidor;
import Controlador.ControladorJuego;
import VistaJframe.VentanaDosJugadores;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javax.swing.JOptionPane;


public class Servidor {
   
    private ServerSocket servidor;
    private Socket cliente;
    //FLUJOS DE ENTRADA
    private ObjectInputStream entrada;
    //FLUJOS DE SALIDA
    private ObjectOutputStream salida;
    private MensajeIp mensajes;
    private Boolean conectado;
    
    public Servidor(){
        conectado = false;
        mensajes = new MensajeIp();
    }
    
     public void crearServidor() {
        try {
            servidor = new ServerSocket(12345);
            JOptionPane.showMessageDialog(null, "Esperando Cliente...");
            servidor.setSoTimeout(10000);
            cliente = servidor.accept();
            abrirFlujos();
            conectado = true;
        }
        catch (SocketTimeoutException ex) {
            conectado = false;
            System.out.println(ex);
            cerrarConexion();
        }
        catch (IOException ex) {
            conectado = false;
            System.out.println(ex);
            cerrarConexion();
        }
        catch(Exception e){
            conectado = false;
            System.out.println(e);
            cerrarConexion();
        }
    }
    public Boolean conectado() {
        return conectado;
    }
   
    public void abrirFlujos(){
        
        try{
        entrada = new ObjectInputStream(cliente.getInputStream());
        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.flush();
        
        }catch(IOException e){
            
        }
    }
    
    public void enviarMensajeGanoAlCliente() {
        try {
            salida.writeObject("gano");
        }//fin del try
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            cerrarConexion();
        }//fin del catch
    }
    public void esperarMensaje(final ControladorClienteServidor control) {
        Thread espera = new Thread(
            new Runnable() {
                    
                @Override
                public void run() {
                    while (true) {
                        try {
                            String mensaje = (String) entrada.readObject();
                            if (mensaje.equals("gano")) {
                             ControladorJuego.nogano();
                            }
                        }catch (IOException | ClassNotFoundException ex) {
                        }finally {
                            control.asignarRed(false);
                            cerrarConexion();
                        break;
                        }
                    }
                }
            }   
            );
        espera.start();
    }
    
    public void cerrarConexion() {
        try {
            if(servidor != null) servidor.close();
            if(cliente != null) cliente.close();
            if(salida != null) salida.close();
            if(entrada != null) entrada.close();
        }
        catch (IOException ex) {
            System.out.print(ex);
        }
    }
    
}
