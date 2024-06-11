package vistas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends BaseLayout {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public Main() {
        super();
        JPanel mainPanel = getMainPanel();

        JLabel lblTitle = new JLabel("Bienvenido CafeteríaAPP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(157, 23, 250, 25);
        mainPanel.add(lblTitle);


        JButton botonCompra = new JButton("Realizar una compra");
        botonCompra.setBackground(new Color(51, 153, 255));
        botonCompra.setForeground(new Color(0, 0, 0));
        botonCompra.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RealizarCompra realizarCompraFrame = new RealizarCompra();
                realizarCompraFrame.setVisible(true);
                dispose();
            }
        });
        botonCompra.setBounds(179, 70, 211, 30);
        mainPanel.add(botonCompra);
        JButton botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBackground(new Color(51, 153, 255));
        botonLogin.setForeground(Color.BLACK);
        botonLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                dispose();
            }
        });
        botonLogin.setBounds(179, 129, 211, 30);
        mainPanel.add(botonLogin);
    }
}


