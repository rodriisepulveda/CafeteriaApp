package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import controlador.ControladorUsuario;
import modelo.Usuario;
import java.util.List;

public class VistaGestionUsuarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private ControladorUsuario controladorUsuario;

    public VistaGestionUsuarios(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;
        setTitle("Gestión de Usuarios");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(60, 63, 65));
        add(mainPanel, BorderLayout.CENTER);

        JLabel lblTitle = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(60, 63, 65));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        buttonPanel.add(crearBoton("Agregar Usuario", e -> {
            VistaAgregarUsuario vistaAgregarUsuario = new VistaAgregarUsuario(controladorUsuario);
            vistaAgregarUsuario.setVisible(true);
            dispose();
        }));

        buttonPanel.add(crearBoton("Modificar Usuario", e -> {
            VistaModificarUsuario vistaModificarUsuario = new VistaModificarUsuario(controladorUsuario);
            vistaModificarUsuario.setVisible(true);
            dispose();
        }));

        buttonPanel.add(crearBoton("Eliminar Usuario", e -> {
            VistaEliminarUsuario vistaEliminarUsuario = new VistaEliminarUsuario(controladorUsuario);
            vistaEliminarUsuario.setVisible(true);
            dispose();
        }));

        buttonPanel.add(crearBoton("Listar Usuarios", e -> listarUsuarios()));

        buttonPanel.add(crearBoton("Volver", e -> {
            VistaAdministrador vistaAdministrador = new VistaAdministrador();
            vistaAdministrador.setVisible(true);
            dispose();
        }));

        buttonPanel.add(Box.createVerticalStrut(20));
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = controladorUsuario.obtenerListaUsuarios();
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
            return;
        }

        String[] columnNames = {"ID", "Nombre", "Contraseña", "Rol"};
        Object[][] data = new Object[usuarios.size()][4];
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            data[i][0] = usuario.getId();
            data[i][1] = usuario.getNombre();
            data[i][2] = usuario.getContraseña() == null ? "No tiene" : usuario.getContraseña();
            data[i][3] = usuario.getRol();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(this, scrollPane, "Lista de Usuarios", JOptionPane.PLAIN_MESSAGE);
    }

    private JButton crearBoton(String texto, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(75, 110, 175));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setAlignmentX(CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(250, 50));
        boton.setMinimumSize(new Dimension(250, 50));
        boton.setPreferredSize(new Dimension(250, 50));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boton.addActionListener(actionListener);
        return boton;
    }
}



