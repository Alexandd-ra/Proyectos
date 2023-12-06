
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    // Librería de MySQL
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
    private Connection conn;

    public Conexion() {
    }

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            if(conn!=null){
                System.out.println("conexion exitosa");
            }   
        } catch (ClassNotFoundException | SQLException e) {
           System.out.println("Error en la conexion");
        }

        return conn;
    }
    
    
    public void desconectar(){
        conn = null;
        if (conn == null){
            System.out.println("Conexion terminada");
        }
    }

}