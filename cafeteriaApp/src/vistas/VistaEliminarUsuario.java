package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.ControladorUsuario;
import modelo.Usuario;

public class VistaEliminarUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxUsuarios;
    private ControladorUsuario controladorUsuario;
    private List<Usuario> usuarios;

    public VistaEliminarUsuario(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;

        setTitle("Eliminar Usuario");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(60, 63, 65));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(mainPanel, BorderLayout.CENTER);

        JLabel lblTitle = new JLabel("Eliminar Usuario", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createVerticalStrut(20));


        comboBoxUsuarios = new JComboBox<>();
        comboBoxUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboBoxUsuarios.setMaximumSize(new Dimension(400, 30));
        mainPanel.add(comboBoxUsuarios);

        mainPanel.add(Box.createVerticalStrut(20));


        JButton btnEliminar = crearBoton("Eliminar Usuario", e -> eliminarUsuario());
        mainPanel.add(btnEliminar);
        mainPanel.add(Box.createVerticalStrut(10));

        JButton btnVolver = crearBoton("Volver", e -> {
            VistaGestionUsuarios vistaGestionUsuarios = new VistaGestionUsuarios(controladorUsuario);
            vistaGestionUsuarios.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);

        cargarUsuarios();
    }

    private JButton crearBoton(String texto, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(75, 110, 175));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setAlignmentX(CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(200, 40));
        boton.setMinimumSize(new Dimension(200, 40));
        boton.setPreferredSize(new Dimension(200, 40));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boton.addActionListener(actionListener);
        return boton;
    }

    private void cargarUsuarios() {
        usuarios = controladorUsuario.obtenerListaUsuarios();
        comboBoxUsuarios.removeAllItems();
        for (Usuario usuario : usuarios) {
            comboBoxUsuarios.addItem(usuario.getNombre());
        }
    }

    private void eliminarUsuario() {
        int selectedIndex = comboBoxUsuarios.getSelectedIndex();
        if (selectedIndex >= 0) {
            Usuario usuarioSeleccionado = usuarios.get(selectedIndex);
            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar a " + usuarioSeleccionado.getNombre() + " (" + usuarioSeleccionado.getRol() + ")?",
                "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);

            if (confirm == JOptionPane.OK_OPTION) {
                boolean exito = controladorUsuario.eliminarUsuario(usuarioSeleccionado.getNombre());
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
                    cargarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
        }
    }
}
