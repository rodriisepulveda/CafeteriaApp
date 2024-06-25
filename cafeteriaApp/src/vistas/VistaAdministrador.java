package vistas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controlador.ControladorUsuario;
import controlador.ControladorCompra;

public class VistaAdministrador extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ControladorUsuario controladorUsuario;
    private ControladorCompra controladorCompra;

    public VistaAdministrador() {
        controladorUsuario = new ControladorUsuario();
        controladorCompra = new ControladorCompra();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 456, 510);
        contentPane = getMainPanel();
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        panel.setBounds(10, 11, 414, 402);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("Panel de Administrador");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(125, 20, 200, 25);
        panel.add(lblTitle);

        JButton btnGestionarInventario = new JButton("Gestionar Inventario");
        btnGestionarInventario.setBackground(new Color(51, 153, 255));
        btnGestionarInventario.setForeground(Color.BLACK);
        btnGestionarInventario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionarInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
                vistaGestionProductos.setVisible(true);
                dispose(); 
            }
        });
        btnGestionarInventario.setBounds(125, 80, 180, 30);
        panel.add(btnGestionarInventario);

        JButton btnGestionarPedidos = new JButton("Gestionar Pedidos");
        btnGestionarPedidos.setBackground(new Color(51, 153, 255));
        btnGestionarPedidos.setForeground(Color.BLACK);
        btnGestionarPedidos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionarPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Gestión de pedidos no implementada.");
            }
        });
        btnGestionarPedidos.setBounds(125, 130, 180, 30);
        panel.add(btnGestionarPedidos);

        JButton btnRegistroVentas = new JButton("Registro de Ventas");
        btnRegistroVentas.setBackground(new Color(51, 153, 255));
        btnRegistroVentas.setForeground(Color.BLACK);
        btnRegistroVentas.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistroVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorCompra.mostrarRegistroVentas();
            }
        });
        btnRegistroVentas.setBounds(125, 180, 180, 30);
        panel.add(btnRegistroVentas);


        JButton btnGestionarUsuarios = new JButton("Gestionar Usuarios");
        btnGestionarUsuarios.setBackground(new Color(51, 153, 255));
        btnGestionarUsuarios.setForeground(Color.BLACK);
        btnGestionarUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorUsuario.mostrarMenuGestionUsuarios();
            }
        });
        btnGestionarUsuarios.setBounds(125, 230, 180, 30);
        panel.add(btnGestionarUsuarios);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(51, 153, 255));
        btnCerrarSesion.setForeground(Color.BLACK);
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        btnCerrarSesion.setBounds(125, 280, 180, 30);
        panel.add(btnCerrarSesion);
    }
}
