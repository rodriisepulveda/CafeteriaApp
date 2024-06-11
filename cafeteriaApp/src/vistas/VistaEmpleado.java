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

public class VistaEmpleado extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VistaEmpleado() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = getMainPanel();
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        panel.setBounds(10, 11, 414, 239);
        contentPane.add(panel);
        panel.setLayout(null);


        JLabel lblTitle = new JLabel("Panel de Empleado");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(125, 20, 200, 25);
        panel.add(lblTitle);


        JButton btnGestionarPedidos = new JButton("Gestionar Pedidos");
        btnGestionarPedidos.setBackground(new Color(51, 153, 255));
        btnGestionarPedidos.setForeground(Color.WHITE);
        btnGestionarPedidos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionarPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Gestión de pedidos no implementada.");
            }
        });
        btnGestionarPedidos.setBounds(125, 80, 180, 30);
        panel.add(btnGestionarPedidos);

        JButton btnGestionarInventario = new JButton("Gestionar Inventario");
        btnGestionarInventario.setBackground(new Color(51, 153, 255));
        btnGestionarInventario.setForeground(Color.WHITE);
        btnGestionarInventario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionarInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Gestión de inventario no implementada.");
            }
        });
        btnGestionarInventario.setBounds(125, 130, 180, 30);
        panel.add(btnGestionarInventario);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(51, 153, 255));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        btnCerrarSesion.setBounds(125, 180, 180, 30);
        panel.add(btnCerrarSesion);
    }
}

