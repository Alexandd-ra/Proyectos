
package Modelo;


public class Usuario {
    int id,puntaje;
    String nombre,nUsuario,contrasena;

    public Usuario() {
    }

    public Usuario(String nombre, String nUsuario, String contrasena, int puntaje) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.nUsuario = nUsuario;
        this.contrasena = contrasena;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getnUsuario() {
        return nUsuario;
    }

    public void setnUsuario(String usuario) {
        this.nUsuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "puntaje=" + puntaje + ", nombre=" + nombre + ", usuario=" + nUsuario + ", contrasena=" + contrasena + '}';
    }
    
    
}

