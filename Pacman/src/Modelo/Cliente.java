
package Modelo;


import Controlador.ControladorClienteServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Cliente {
    
    private MensajeIp mensajes;
    private Socket cliente;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Boolean conectado;
    
    public Cliente() {
        mensajes = new MensajeIp();
        
    }
    
    public void crearCliente() {
        String ipServidor = mensajes.obtenerIP();
        try {
            cliente = new Socket(ipServidor, 12345);
            obtenerFlujos();
            conectado = true;     
            
        }catch (UnknownHostException ex) {
            conectado = false;
            System.out.println(ex);
            cerrarConexion();
            
        }catch (IOException ex) {
            conectado = false;
            System.out.println(ex);
            cerrarConexion();
            
        }catch(Exception e){
            conectado = false;
            System.out.println(e);
            cerrarConexion();
        }
    }
    
    public Boolean conectado() {
        return conectado;
    }
   
    public void enviarMensajeGanoAlServidor() {
        try {
            salida.writeObject("gano");
        }
        catch (IOException ex) {
            System.out.println(ex);
            cerrarConexion();
        }
    }

    private void obtenerFlujos() throws IOException{      
      salida = new ObjectOutputStream( cliente.getOutputStream() );
      salida.flush();
      entrada = new ObjectInputStream( cliente.getInputStream() );
      System.out.println( "\nSe establecieron los flujos de E/S\n" );
   }
    
    public void esperarMensaje(final ControladorClienteServidor conntrol) {
        Thread espera = new Thread(
                new Runnable() { 
            @Override
            public void run() {
                while (true) {
                    try {
                        String mensaje = (String) entrada.readObject();
                        if (mensaje.equals("gano")) {
                            mensajes.mostrarMensaje("perdiste");
                        }
                    }
                    catch (IOException | ClassNotFoundException ex) {
                    }
                    finally {
                        conntrol.asignarRed(false);
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
            if(cliente != null) cliente.close();
            if(salida != null) salida.close();
            if(entrada != null) entrada.close();
        }
        catch (IOException ex) {
        }
    }
}
