package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BaseLayout extends JFrame {
    private static final long serialVersionUID = 1L; // Agrega esta línea
    private JPanel contentPane;
    private JPanel mainPanel;

    public BaseLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Panel superior común (por ejemplo, un título)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel lblTitle = new JLabel("Cafetería - Gestión del Sistema");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        topPanel.add(lblTitle);

        // Panel principal donde se añadirán los componentes específicos de cada vista
        mainPanel = new JPanel();
        mainPanel.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        contentPane.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
