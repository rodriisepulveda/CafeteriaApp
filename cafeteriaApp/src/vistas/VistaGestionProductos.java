package vistas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class VistaGestionProductos extends JFrame {

    private static final long serialVersionUID = 1L;

    public VistaGestionProductos() {
        setTitle("Gestión de Productos");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(60, 63, 65));
        add(mainPanel);

        // Título
        JLabel lblTitle = new JLabel("Gestión de Productos");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(30));

        // Botones
        mainPanel.add(crearBoton("Agregar Producto", e -> {
            AgregarProducto agregarProductoFrame = new AgregarProducto();
            agregarProductoFrame.setVisible(true);
            dispose();
        }));

        mainPanel.add(crearBoton("Modificar Producto", e -> {
            ModificarProducto modificarProductoFrame = new ModificarProducto();
            modificarProductoFrame.setVisible(true);
            dispose();
        }));

        mainPanel.add(crearBoton("Eliminar Producto", e -> {
            EliminarProducto eliminarProductoFrame = new EliminarProducto();
            eliminarProductoFrame.setVisible(true);
            dispose();
        }));

        mainPanel.add(crearBoton("Listar Productos", e -> {
            VistaListarProductos vistaListarProductos = new VistaListarProductos();
            vistaListarProductos.setVisible(true);
            dispose();
        }));

        mainPanel.add(crearBoton("Volver", e -> {
            VistaAdministrador vistaAdminFrame = new VistaAdministrador();
            vistaAdminFrame.setVisible(true);
            dispose();
        }));

        mainPanel.add(Box.createVerticalStrut(20));
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

