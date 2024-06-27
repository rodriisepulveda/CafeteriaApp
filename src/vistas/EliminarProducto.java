package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.BaseDatos;
import modelo.Producto;

public class EliminarProducto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxProductos;
    private ArrayList<Producto> productos;

    public EliminarProducto() {
        setTitle("Eliminar Producto");
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
        JLabel lblTitle = new JLabel("Eliminar Producto", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createVerticalStrut(20));

        // ComboBox de productos
        comboBoxProductos = new JComboBox<>();
        comboBoxProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboBoxProductos.setMaximumSize(new Dimension(400, 30));
        mainPanel.add(comboBoxProductos);

        mainPanel.add(Box.createVerticalStrut(20));

        // Botón para eliminar producto
        JButton btnEliminar = crearBoton("Eliminar Producto", e -> eliminarProducto());
        mainPanel.add(btnEliminar);
        mainPanel.add(Box.createVerticalStrut(10));

        // Botón para volver
        JButton btnVolver = crearBoton("Volver", e -> {
            VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
            vistaGestionProductos.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);

        cargarProductos();
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

    private void cargarProductos() {
        BaseDatos baseDatos = new BaseDatos();
        productos = baseDatos.obtenerProductos();
        comboBoxProductos.removeAllItems();
        for (Producto producto : productos) {
            comboBoxProductos.addItem(producto.getNombre());
        }
    }

    private void eliminarProducto() {
        int selectedIndex = comboBoxProductos.getSelectedIndex();
        if (selectedIndex >= 0) {
            Producto productoSeleccionado = productos.get(selectedIndex);
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Desea eliminar este producto? Esta acción no se puede deshacer.", 
                "Confirmar Eliminación", 
                JOptionPane.OK_CANCEL_OPTION);

            if (confirm == JOptionPane.OK_OPTION) {
                BaseDatos baseDatos = new BaseDatos();
                boolean exito = baseDatos.eliminarProducto(productoSeleccionado.getId());

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.");
                    cargarProductos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
        }
    }
}

