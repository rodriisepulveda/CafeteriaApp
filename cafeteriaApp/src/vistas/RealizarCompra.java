package vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controlador.ControladorCompra;

public class RealizarCompra extends BaseLayout {

    private static final long serialVersionUID = 1L;

    public RealizarCompra() {
        super();
        JPanel mainPanel = getMainPanel();

        JLabel lblTitle = new JLabel("Realizar Compra");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(191, 38, 200, 25);
        mainPanel.add(lblTitle);


        JButton btnRealizarCompra = new JButton("Realizar Compra");
        btnRealizarCompra.setBackground(new Color(51, 153, 255));
        btnRealizarCompra.setForeground(Color.WHITE);
        btnRealizarCompra.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRealizarCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorCompra controladorCompra = new ControladorCompra();
                controladorCompra.realizarCompra();
            }
        });
        btnRealizarCompra.setBounds(191, 74, 176, 30);
        mainPanel.add(btnRealizarCompra);

      
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(51, 153, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main mainFrame = new Main();
                mainFrame.setVisible(true);
                dispose();
            }
        });
        btnVolver.setBounds(191, 115, 176, 30);
        mainPanel.add(btnVolver);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RealizarCompra frame = new RealizarCompra();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
