package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import controlador.ControladorUsuario;
import modelo.Usuario;

public class VistaModificarUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxUsuarios;
    private JTextField textFieldNombre;
    private JTextField textFieldContraseña;
    private JComboBox<String> comboBoxRol;
    private ControladorUsuario controladorUsuario;
    private List<Usuario> usuarios;

    public VistaModificarUsuario(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;

        setTitle("Modificar Usuario");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(60, 63, 65));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(mainPanel, BorderLayout.CENTER);

        JLabel lblTitle = new JLabel("Modificar Usuario", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(30));

        mainPanel.add(crearPanelCampo("Seleccionar Usuario:", comboBoxUsuarios = new JComboBox<>()));
        mainPanel.add(crearPanelCampo("Nuevo Nombre:", textFieldNombre = new JTextField(20)));
        mainPanel.add(crearPanelCampo("Nueva Contraseña:", textFieldContraseña = new JTextField(20)));
        mainPanel.add(crearPanelCampo("Nuevo Rol:", comboBoxRol = new JComboBox<>(new String[]{"Cliente", "Empleado", "Administrador"})));

        comboBoxUsuarios.setPreferredSize(new Dimension(200, 30));
        comboBoxUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatosUsuario();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(60, 63, 65));
        JButton btnModificar = crearBoton("Modificar Usuario", e -> modificarUsuario());
        panelBotones.add(btnModificar);
        JButton btnVolver = crearBoton("Volver", e -> {
            VistaGestionUsuarios vistaGestionUsuarios = new VistaGestionUsuarios(controladorUsuario);
            vistaGestionUsuarios.setVisible(true);
            dispose();
        });
        panelBotones.add(btnVolver);
        mainPanel.add(panelBotones);

        cargarUsuarios();
    }

    private JPanel crearPanelCampo(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(new Color(60, 63, 65));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(150, 30));
        field.setPreferredSize(new Dimension(200, 30));
        panel.add(label);
        panel.add(field);
        return panel;
    }

    private void cargarUsuarios() {
        usuarios = controladorUsuario.obtenerListaUsuarios();
        comboBoxUsuarios.removeAllItems();
        for (Usuario usuario : usuarios) {
            comboBoxUsuarios.addItem(usuario.getNombre());
        }
    }

    private void cargarDatosUsuario() {
        int selectedIndex = comboBoxUsuarios.getSelectedIndex();
        if (selectedIndex >= 0) {
            Usuario usuarioSeleccionado = usuarios.get(selectedIndex);
            textFieldNombre.setText(usuarioSeleccionado.getNombre());
            textFieldContraseña.setText(usuarioSeleccionado.getContraseña());
            comboBoxRol.setSelectedItem(usuarioSeleccionado.getRol());
        }
    }

    private void modificarUsuario() {
        int selectedIndex = comboBoxUsuarios.getSelectedIndex();
        if (selectedIndex >= 0) {
            Usuario usuarioSeleccionado = usuarios.get(selectedIndex);
            String nuevoNombre = textFieldNombre.getText().trim();
            String nuevaContraseña = textFieldContraseña.getText().trim();
            String nuevoRol = (String) comboBoxRol.getSelectedItem();

            if (nuevoNombre.isEmpty() || nuevaContraseña.isEmpty() || nuevoRol == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean exito = controladorUsuario.modificarUsuario(usuarioSeleccionado.getId(), nuevoNombre, nuevaContraseña, nuevoRol);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Usuario modificado con éxito.");
                cargarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para modificar.");
        }
    }

    private JButton crearBoton(String texto, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(75, 110, 175));
        boton.setForeground(Color.black);
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
}



