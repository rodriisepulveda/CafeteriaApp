package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import controlador.ControladorUsuario;

public class VistaAgregarUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldNombre;
    private JTextField textFieldContraseña;
    private JComboBox<String> comboBoxRol;
    private ControladorUsuario controladorUsuario;

    public VistaAgregarUsuario(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;

        setTitle("Agregar Usuario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(60, 63, 65));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(mainPanel, BorderLayout.CENTER);


        JLabel lblTitle = new JLabel("Agregar Usuario", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createVerticalStrut(20));


        mainPanel.add(crearPanelCampo("Nombre:", textFieldNombre = new JTextField(20)));
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(crearPanelCampo("Contraseña:", textFieldContraseña = new JTextField(20)));
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(crearPanelCampo("Rol:", comboBoxRol = new JComboBox<>(new String[]{"Cliente", "Empleado", "Administrador"})));
        mainPanel.add(Box.createVerticalStrut(20));


        JButton btnAgregar = crearBoton("Agregar Usuario", e -> agregarUsuario());
        mainPanel.add(btnAgregar);
        mainPanel.add(Box.createVerticalStrut(10));


        JButton btnVolver = crearBoton("Volver", e -> {
            VistaGestionUsuarios vistaGestionUsuarios = new VistaGestionUsuarios(controladorUsuario);
            vistaGestionUsuarios.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);
    }

    private JPanel crearPanelCampo(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(new Color(60, 63, 65));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(150, 30));
        field.setPreferredSize(new Dimension(200, 30));
        field.setMaximumSize(field.getPreferredSize());
        panel.add(label);
        panel.add(field);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
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

    private void agregarUsuario() {
        String nombre = textFieldNombre.getText().trim();
        String contraseña = textFieldContraseña.getText().trim();
        String rol = (String) comboBoxRol.getSelectedItem();

        if (nombre.isEmpty() || contraseña.isEmpty() || rol == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = controladorUsuario.agregarUsuario(nombre, contraseña, rol);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario agregado con éxito.");
            textFieldNombre.setText("");
            textFieldContraseña.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


