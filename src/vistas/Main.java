package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;

    public Main() {
        setTitle("Bienvenido CafeteríaAPP");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel de fondo
        JPanel mainPanel = new BackgroundPanel("/imgs/coffee.jpg");
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        add(mainPanel, BorderLayout.CENTER);

        JLabel lblTitle = new JLabel("Bienvenido CafeteríaAPP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        JButton botonCompra = new JButton("Realizar una compra");
        botonCompra.setBackground(new Color(51, 153, 255));
        botonCompra.setForeground(new Color(0, 0, 0));
        botonCompra.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(botonCompra, gbc);

        botonCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RealizarCompra realizarCompraFrame = new RealizarCompra();
                realizarCompraFrame.setVisible(true);
                dispose();
            }
        });

        JButton botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBackground(new Color(51, 153, 255));
        botonLogin.setForeground(Color.BLACK);
        botonLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridy = 2;
        mainPanel.add(botonLogin, gbc);

        botonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



