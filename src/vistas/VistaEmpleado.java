package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class VistaEmpleado extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VistaEmpleado() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = getMainPanel();
        contentPane.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Panel de Empleado");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;

        JButton btnGestionarPedidos = new JButton("Gestionar Pedidos");
        btnGestionarPedidos.setBackground(new Color(51, 153, 255));
        btnGestionarPedidos.setForeground(Color.BLACK);
        btnGestionarPedidos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionarPedidos.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Gestión de pedidos no implementada.");
        });
        gbc.gridx = 0;
        panel.add(btnGestionarPedidos, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;

        JButton btnGestionarInventario = new JButton("Gestionar Inventario");
        btnGestionarInventario.setBackground(new Color(51, 153, 255));
        btnGestionarInventario.setForeground(Color.BLACK);
        btnGestionarInventario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionarInventario.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Gestión de inventario no implementada.");
        });
        panel.add(btnGestionarInventario, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(51, 153, 255));
        btnCerrarSesion.setForeground(Color.BLACK);
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCerrarSesion.addActionListener(e -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
            dispose();
        });
        panel.add(btnCerrarSesion, gbc);

        contentPane.add(panel);
    }
}


