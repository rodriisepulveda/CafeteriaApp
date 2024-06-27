package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import controlador.ControladorUsuario;
import modelo.Usuario;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private ControladorUsuario controladorUsuario;

    public Login() {
        controladorUsuario = new ControladorUsuario();
        setTitle("Inicio de Sesión");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Usar BackgroundPanel para el fondo
        JPanel mainPanel = new BackgroundPanel("/imgs/coffee.jpg");
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(60, 63, 65));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Inicio de Sesión", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblUsuario = new JLabel("Nombre de usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUsuario.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblUsuario, gbc);

        textFieldUsuario = new JTextField(15);
        textFieldUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(textFieldUsuario, gbc);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblContraseña.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(lblContraseña, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordField, gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnIniciarSesion = crearBoton("Iniciar sesión", e -> {
            String nombreUsuario = textFieldUsuario.getText();
            String contraseña = new String(passwordField.getPassword());

            Usuario usuario = controladorUsuario.autenticarUsuario(nombreUsuario, contraseña);
            if (usuario != null) {
                if (usuario.getRol().equals("Administrador")) {
                    JOptionPane.showMessageDialog(null, "Inicio de Sesión como ADMINISTRADOR exitoso");
                    VistaAdministrador vistaAdmin = new VistaAdministrador();
                    vistaAdmin.setVisible(true);
                } else if (usuario.getRol().equals("Empleado")) {
                    JOptionPane.showMessageDialog(null, "Inicio de Sesión como EMPLEADO exitoso");
                    VistaEmpleado vistaEmpleado = new VistaEmpleado();
                    vistaEmpleado.setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(btnIniciarSesion, gbc);

        JButton btnMenuPrincipal = crearBoton("Menu principal", e -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
            dispose();
        });
        gbc.gridy = 4;
        mainPanel.add(btnMenuPrincipal, gbc);
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
}

