package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modelo.BaseDatos;

public class AgregarProducto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;

    public AgregarProducto() {
        setTitle("Agregar Producto");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(60, 63, 65));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(mainPanel, BorderLayout.CENTER);

        // Título
        JLabel lblTitle = new JLabel("Agregar Producto", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createVerticalStrut(20));

        // Campos del formulario
        mainPanel.add(crearPanelCampo("Nombre del Producto:", textFieldNombre = new JTextField(20)));
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(crearPanelCampo("Precio:", textFieldPrecio = new JTextField(20)));
        mainPanel.add(Box.createVerticalStrut(20));

        // Botón para agregar producto
        JButton btnAgregar = crearBoton("Agregar Producto", e -> agregarProducto());
        mainPanel.add(btnAgregar);
        mainPanel.add(Box.createVerticalStrut(10));

        // Botón para volver
        JButton btnVolver = crearBoton("Volver", e -> {
            VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
            vistaGestionProductos.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);
    }

    private JPanel crearPanelCampo(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(new Color(60, 63, 65));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(150, 30));
        field.setPreferredSize(new Dimension(200, 30));
        field.setMaximumSize(field.getPreferredSize());
        panel.add(label);
        panel.add(field);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    private JButton crearBoton(String texto, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(75, 110, 175));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setAlignmentX(CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(200, 40));
        boton.setMinimumSize(new Dimension(200, 40));
        boton.setPreferredSize(new Dimension(200, 40));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boton.addActionListener(actionListener);
        return boton;
    }

    private void agregarProducto() {
        String nombre = textFieldNombre.getText().trim();
        String precioStr = textFieldPrecio.getText().trim();

        if (nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BigDecimal precio = new BigDecimal(precioStr);
            BaseDatos baseDatos = new BaseDatos();
            boolean exito = baseDatos.insertarProducto(nombre, precio);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Producto agregado con éxito.");
                textFieldNombre.setText("");
                textFieldPrecio.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

