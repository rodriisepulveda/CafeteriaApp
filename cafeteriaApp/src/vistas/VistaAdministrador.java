package vistas;


import java.awt.Color;	
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.Dimension;


import controlador.ControladorUsuario;
import controlador.ControladorCompra;

public class VistaAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ControladorUsuario controladorUsuario;

    public VistaAdministrador() {
        controladorUsuario = new ControladorUsuario();
        new ControladorCompra();

        setTitle("Panel de Administrador");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(60, 63, 65));
        contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        




        JLabel lblTitle = new JLabel("Panel de Administrador");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        contentPane.add(Box.createVerticalStrut(30));
        contentPane.add(lblTitle);
        contentPane.add(Box.createVerticalStrut(30));


        contentPane.add(crearBoton("Gestionar Inventario", e -> {
            VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
            vistaGestionProductos.setVisible(true);
            dispose();
        }));

        contentPane.add(crearBoton("Gestionar Pedidos", e -> {
            VistaGestionPedidos vistaGestionPedidos = new VistaGestionPedidos();
            vistaGestionPedidos.setVisible(true);
            dispose();
        }));

        contentPane.add(crearBoton("Registro de Ventas", e -> {
            VistaRegistroVentas vistaRegistroVentas = new VistaRegistroVentas();
            vistaRegistroVentas.setVisible(true);
            dispose();
        }));

        contentPane.add(crearBoton("Gestionar Usuarios", e -> {
            controladorUsuario.mostrarGestionUsuarios();
            dispose();
        }));


        contentPane.add(crearBoton("Cerrar Sesión", e -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
            dispose();
        }));

        contentPane.add(Box.createVerticalStrut(20));

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



