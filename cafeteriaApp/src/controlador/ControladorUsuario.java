package controlador;
import modelo.BaseDatos;
import modelo.Usuario;
import vistas.VistaAdministrador;
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

    public void mostrarMenuGestionUsuarios() {
        String[] opciones = {"Agregar Usuario", "Modificar Usuario", "Eliminar Usuario", "Listar Usuarios", "Volver"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestión de Usuarios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        switch (opcionSeleccionada) {
            case 0:
                agregarUsuario();
                break;
            case 1:
                modificarUsuario();
                break;
            case 2:
                eliminarUsuario();
                break;
            case 3:
                listarUsuarios();
                break;
            case 4:
                VistaAdministrador vistaAdmin = new VistaAdministrador();
                vistaAdmin.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
                mostrarMenuGestionUsuarios();
        }
    }

    private void agregarUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada o nombre vacío.");
            mostrarMenuGestionUsuarios();
            return;
        }

        String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña del usuario:");
        if (contraseña == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            mostrarMenuGestionUsuarios();
            return;
        }
        
        String[] roles;
        if (contraseña.trim().isEmpty()) {
            roles = new String[]{"Cliente"};
        } else {
            roles = new String[]{"Empleado", "Administrador"};
        }
        
        String rol = (String) JOptionPane.showInputDialog(null, "Seleccione el rol:", "Rol", JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
        if (rol == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            mostrarMenuGestionUsuarios();
            return;
        }
        
        String confirmMessage = "Nombre: " + nombre + "\n" +
                                "Contraseña: " + (contraseña.trim().isEmpty() ? "N/A" : contraseña) + "\n" +
                                "Rol: " + rol;
        int confirm = JOptionPane.showConfirmDialog(null, confirmMessage, "Confirmar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (confirm != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            mostrarMenuGestionUsuarios();
            return;
        }

        if (baseDatos.agregarUsuario(nombre, contraseña.trim().isEmpty() ? null : contraseña, rol)) {
            JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el usuario.");
        }
        mostrarMenuGestionUsuarios();
    }

    private void modificarUsuario() {
        List<Usuario> usuarios = obtenerListaUsuarios();
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            mostrarMenuGestionUsuarios();
            return;
        }

        String[] nombresUsuarios = new String[usuarios.size()];
        for (int i = 0; i < usuarios.size(); i++) {
            nombresUsuarios[i] = usuarios.get(i).getNombre();
        }

        JComboBox<String> comboBox = new JComboBox<>(nombresUsuarios);
        int result = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione el usuario a modificar", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            mostrarMenuGestionUsuarios();
            return;
        }

        String nombreSeleccionado = (String) comboBox.getSelectedItem();
        Usuario usuario = baseDatos.obtenerUsuarioPorNombre(nombreSeleccionado);

        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del usuario:", usuario.getNombre());
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada o nombre vacío.");
            mostrarMenuGestionUsuarios();
            return;
        }

        String nuevaContraseña = JOptionPane.showInputDialog("Ingrese la nueva contraseña del usuario:");
        if (nuevaContraseña == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            mostrarMenuGestionUsuarios();
            return;
        }

        String[] roles = {"Cliente", "Empleado", "Administrador"};
        String nuevoRol = (String) JOptionPane.showInputDialog(null, "Seleccione el nuevo rol:", "Rol", JOptionPane.QUESTION_MESSAGE, null, roles, usuario.getRol());
        if (nuevoRol == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            mostrarMenuGestionUsuarios();
            return;
        }

        if (!validarContraseña(nuevaContraseña)) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un número.");
            mostrarMenuGestionUsuarios();
            return;
        }

        if (baseDatos.modificarUsuario(usuario.getId(), nuevoNombre, nuevaContraseña.trim().isEmpty() ? null : nuevaContraseña, nuevoRol)) {
            JOptionPane.showMessageDialog(null, "Usuario modificado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar el usuario.");
        }
        mostrarMenuGestionUsuarios();
    }


    private void eliminarUsuario() {
        List<Usuario> usuarios = obtenerListaUsuarios();
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios para eliminar.");
            mostrarMenuGestionUsuarios();
            return;
        }

        String[] nombresUsuarios = new String[usuarios.size()];
        for (int i = 0; i < usuarios.size(); i++) {
            nombresUsuarios[i] = usuarios.get(i).getNombre();
        }

        JComboBox<String> comboBox = new JComboBox<>(nombresUsuarios);
        int result = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione el usuario a eliminar", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            mostrarMenuGestionUsuarios();
            return;
        }

        String nombreSeleccionado = (String) comboBox.getSelectedItem();
        Usuario usuario = baseDatos.obtenerUsuarioPorNombre(nombreSeleccionado);

        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar a " + usuario.getNombre() + " (" + usuario.getRol() + ")?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
        if (confirm != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            mostrarMenuGestionUsuarios();
            return;
        }

        if (baseDatos.eliminarUsuarioPorNombre(nombreSeleccionado)) {
            JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.");
        }

        mostrarMenuGestionUsuarios();
    }


    private void listarUsuarios() {
        List<Usuario> usuarios = obtenerListaUsuarios();
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            mostrarMenuGestionUsuarios();
            return;
        }

        String mensaje = "Usuarios registrados:\n\n";
        for (Usuario usuario : usuarios) {
            String contraseña = usuario.getContraseña() == null ? "No tiene" : usuario.getContraseña();
            mensaje += "ID: " + usuario.getId() + "\n";
            mensaje += "Nombre: " + usuario.getNombre() + "\n";
            mensaje += "Contraseña: " + contraseña + "\n";
            mensaje += "Rol: " + usuario.getRol() + "\n\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
        mostrarMenuGestionUsuarios();
    }

    public Usuario autenticarUsuario(String nombre, String contraseña) {
        return baseDatos.autenticarUsuario(nombre, contraseña);
    }


}
