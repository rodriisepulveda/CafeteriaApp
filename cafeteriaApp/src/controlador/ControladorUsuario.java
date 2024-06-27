package controlador;
import modelo.BaseDatos;
import modelo.Usuario;
import vistas.VistaGestionUsuarios;
import javax.swing.*;
import java.util.List;

public class ControladorUsuario {
    private BaseDatos baseDatos;

    public ControladorUsuario() {
        baseDatos = new BaseDatos();
    }

    public boolean agregarUsuario(String nombre, String contraseña, String rol) {
        if (!validarContraseña(contraseña)) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un número.");
            return false;
        }
        return baseDatos.agregarUsuario(nombre, contraseña, rol);
    }

    public boolean modificarUsuario(int id, String nombre, String contraseña, String rol) {
        if (!validarContraseña(contraseña)) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un número.");
            return false;
        }
        return baseDatos.modificarUsuario(id, nombre, contraseña, rol);
    }

    public boolean eliminarUsuario(String nombre) {
        return baseDatos.eliminarUsuarioPorNombre(nombre);
    }

    public List<Usuario> obtenerListaUsuarios() {
        return baseDatos.listarUsuarios();
    }

    private boolean validarContraseña(String contraseña) {
        return contraseña.matches(".*\\d.*");
    }

    public Usuario autenticarUsuario(String nombre, String contraseña) {
        return baseDatos.autenticarUsuario(nombre, contraseña);
    }

    public void mostrarGestionUsuarios() {
        VistaGestionUsuarios vistaGestionUsuarios = new VistaGestionUsuarios(this);
        vistaGestionUsuarios.setVisible(true);
    }
}
