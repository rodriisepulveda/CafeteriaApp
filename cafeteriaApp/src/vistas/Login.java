package vistas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controlador.ControladorUsuario;
import modelo.Usuario;

public class Login extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private ControladorUsuario controladorUsuario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        controladorUsuario = new ControladorUsuario(); 

        JPanel mainPanel = getMainPanel();

        JLabel lblTitle = new JLabel("Inicio de Sesión");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(125, 20, 200, 25);
        mainPanel.add(lblTitle);

        JLabel lblUsuario = new JLabel("Nombre de usuario:");
        lblUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        lblUsuario.setBounds(48, 70, 150, 20);
        mainPanel.add(lblUsuario);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textFieldUsuario.setBounds(200, 70, 150, 25);
        mainPanel.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        JLabel lblContraseña = new JLabel("Password:");
        lblContraseña.setFont(new Font("Nunito", Font.PLAIN, 14));
        lblContraseña.setBounds(48, 110, 150, 20);
        mainPanel.add(lblContraseña);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField.setBounds(200, 110, 150, 25);
        mainPanel.add(passwordField);

        JButton btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.setBackground(new Color(51, 153, 255));
        btnIniciarSesion.setForeground(Color.BLACK);
        btnIniciarSesion.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        btnIniciarSesion.setBounds(145, 160, 130, 30);
        mainPanel.add(btnIniciarSesion);

        JButton btnMenuPrincipal = new JButton("Menu principal");
        btnMenuPrincipal.setBackground(new Color(51, 153, 255));
        btnMenuPrincipal.setForeground(Color.BLACK);
        btnMenuPrincipal.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main mainFrame = new Main();
                mainFrame.setVisible(true);
                dispose();
            }
        });
        btnMenuPrincipal.setBounds(145, 210, 130, 30);
        mainPanel.add(btnMenuPrincipal);
    }
}

