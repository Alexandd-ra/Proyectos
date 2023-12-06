package Controlador;

import Modelo.Cliente;
import Modelo.Conexion;
import Modelo.Servidor;
import Modelo.Usuario;
import VistaJframe.VentanaRegistro;
import VistaJframe.VentanaUnJugador;
import VistaJframe.VentanaJugadores;
import VistaJframe.VentanaSesion;
import VistaJframe.VentanaMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class ControladorLogin{

    private Usuario usuario;
    private VentanaUnJugador juego;
    private VentanaMenu menu;
    private VentanaRegistro registro;
    private VentanaSesion ventanaS;
    private Connection conn;
    private Conexion cox;
    private ControladorU controlU;
    private VentanaJugadores juga;
   
    
    public ControladorLogin() {
     
       // Muestra login
        menu = new VentanaMenu(this);
        cox = new Conexion();
        controlU = new ControladorU();
       
    }
    
    
     ///este metodo nos aayuda a verificar si el usuario existe en nuestra base de datos si es asi podra entrar en el juego 
    // de lo contrario debera registrarse para poder ingresar al jugo.
    
    public void loguearUsuario(String nombreUsuario, String contrasenaUsuario){
        String resultado;
         if(estaLogueado()){
            resultado = "Ya hay un usuario logueado";
         } else {
             Usuario us = controlU.loguear(nombreUsuario, contrasenaUsuario);
             if(us != null){
                 resultado = "SE LOGUEO CORRECTAMENTE";
                 
                cargarUsuario(us); 
                cerrarMenu();
                // Carga tablero
                juga = new VentanaJugadores(this);
                
                
             }else {
                 resultado = "USUARIO O CONTRASEÑA INCORRECTOS";
             }
             
         }
         JOptionPane.showMessageDialog(menu ,resultado);
        
    }
    
   
    //este metodo nos ayuda a registra al usuario en la base de datos 
    
    public void controladorGuardar(String nombre,String nUsuario,String contrasena){
        String resultado;
        
        if (controlU.loguear(nombre, contrasena) != null){
            resultado = "El usuario ya esta guardado";
        }else {
            if (controlU.registrarUsuario(nombre,nUsuario, contrasena)){
                resultado = "Usuario GUARDADO y logueado exitosamente";
                loguearUsuario(nUsuario,contrasena);
                 
            }else {
              resultado = "Error al guardar el usuario";
           }
        }
        JOptionPane.showMessageDialog(registro ,resultado);
    }
    
    //METODO PARA ACTULIZAR EL PUNTAGE EN LA BASE DE DATOS
    
    public void actualizarPuntaje (String nUsuario ,int puntajeActual) {
        Conexion con = new Conexion();
        String sql= "UPDATE registro SET puntaje = ? WHERE usuario = ?";
        try{
            PreparedStatement stm = con.conectarMySQL().prepareStatement(sql);
            stm.setInt(1, puntajeActual);
            stm.setString(2, nUsuario);
            
            if(stm.executeUpdate()>0){
                System.out.print("Se guardo su puntaje");
            }else{
                System.out.print("no se pudo guardar su puntaje");
            }              
        }catch(Exception e){  
            e.printStackTrace();
            System.out.println("problema actualizando el puntaje");
        } 
    } 
    

    
    // METODO PARA CARGAR EL NOMBRE DEL USUARIO
    public void cargarUsuario(Usuario us) {
        usuario = us;
    }
    
    // ESTE METODO NOS RETORNA ES PARA SABER SI EL NOMBREUSUARIO ES DIFERENTE DE NULL
    public boolean estaLogueado() {
        return usuario != null;
    }

    /////////////////////////////////////////METODOS PARA ABRIR Y CERRAR VENTANAS EN EL JUEGO////////////////////////////
    public String nombreU(){
        return usuario.getnUsuario();
    }
    
    public void mostrarRegistro() {
        registro = new VentanaRegistro(this);
    }  
    public void cerrarRegistro() {
        registro.dispose();
    } 
    public void mostrarLogin(){   
        ventanaS = new VentanaSesion(this);
    }
    public void cerrarVentanaS(){
        ventanaS.dispose();
    }
    public void cerrarMenu(){
         menu.dispose();
    }
 
}
