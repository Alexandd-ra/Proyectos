
package Controlador;

import Modelo.Conexion;
import java.sql.Connection;
import Modelo.Usuario;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class ControladorU {
    private Connection conn;
    private String nombreUsuarioLogueado;
    public String driver = "com.mysql.jdbc.Driver";
    // Nombre de la base de datos
    public String database = "registrousuario";
    // Host
    public String hostname = "localhost";
    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + "/" + database ;
    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "";
    
    
    public ControladorU(){
        
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            if (conn !=null){
                System.out.println("");
            }
        }catch (ClassNotFoundException | SQLException e){
            
            System.out.println("Error en la conexion " + e);
        }
    }
      
    // ESTE METODO NOS PERMITE VERIFICAR SI LA CONTRASENA Y EL USUARIO ESTAN EN NUESTRA BASE DE DATOS
    
    public Usuario loguear(String nUsuario, String contraseñaUsuario){
        Statement stmt = null;
        
        String query = "select usuario, contrasena, puntaje, nombre " +
                   "from registro " +
                    "where usuario='" + nUsuario + "' and " +
                    "contrasena='" + contraseñaUsuario + "'";
        
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()){
                stmt.close();
                return null;
            }
            String nombreU = rs.getString("usuario");
            String contra = rs.getString("contrasena");
            String nombre = rs.getString("nombre");
            int puntaje = rs.getInt("puntaje");
            stmt.close();
            
            return new Usuario(nombre, nombreU, contra, puntaje);
        } catch (SQLException e ) {
            System.err.println(e);
            return null;
        }
    }
    
     // ESTE METODO ES PARA REGISTRA EL USUARIO EN NUESTRA BASE DE DATOS
    public boolean registrarUsuario(String nombre, String usuario, String contrasena) {

        String sql = "insert into registro(nombre,usuario,contrasena,puntaje) values('" + nombre + "','" + usuario + "','" + contrasena + "','" + 0 + "')";
        try {
            Conexion con = new Conexion();
            PreparedStatement stm = con.conectarMySQL().prepareStatement(sql);
            stm.execute();
            JOptionPane.showMessageDialog(null,"Datos Guardados");
        } catch (Exception e) {
            
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    
    // METODO PARA QUE DOS JUGADORES NO TENGAN EL MISMO NOMBRE DE USUARIO
    public boolean usuarioUnico (String Usuario){
        
        Statement stmt = null;
        String query = "select usuario " +
                   "from registro " +
                    "where usuario='" + Usuario + "'";
        
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()){
                stmt.close();
                return false;
            }
            stmt.close();
            return true;
        } catch (SQLException e ) {
            System.err.println(e);
            return false;
        }
    }
}
