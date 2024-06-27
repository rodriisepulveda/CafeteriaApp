package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import modelo.BaseDatos;
import modelo.Producto;

public class ModificarProducto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxProductos;
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;
    private ArrayList<Producto> productos;

    public ModificarProducto() {
        setTitle("Modificar Producto");
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
        JLabel lblTitle = new JLabel("Modificar", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createVerticalStrut(20));

        // ComboBox de productos
        comboBoxProductos = new JComboBox<>();
        comboBoxProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboBoxProductos.setMaximumSize(new Dimension(400, 30));
        comboBoxProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatosProducto();
            }
        });
        mainPanel.add(comboBoxProductos);

        mainPanel.add(Box.createVerticalStrut(20));

        // Campos del formulario
        mainPanel.add(crearPanelCampo("Nuevo Nombre:", textFieldNombre = new JTextField(20)));
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(crearPanelCampo("Nuevo Precio:", textFieldPrecio = new JTextField(20)));
        mainPanel.add(Box.createVerticalStrut(20));

        // Botón para modificar producto
        JButton btnModificar = crearBoton("Modificar Producto", e -> modificarProducto());
        mainPanel.add(btnModificar);
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

    private void cargarProductos() {
        BaseDatos baseDatos = new BaseDatos();
        productos = baseDatos.obtenerProductos();
        comboBoxProductos.removeAllItems();
        for (Producto producto : productos) {
            comboBoxProductos.addItem(producto.getNombre());
        }
    }

    private void cargarDatosProducto() {
        int selectedIndex = comboBoxProductos.getSelectedIndex();
        if (selectedIndex >= 0) {
            Producto productoSeleccionado = productos.get(selectedIndex);
            textFieldNombre.setText(productoSeleccionado.getNombre());
            textFieldPrecio.setText(productoSeleccionado.getPrecio().toString());
        }
    }

    private void modificarProducto() {
        int selectedIndex = comboBoxProductos.getSelectedIndex();
        if (selectedIndex >= 0) {
            Producto productoSeleccionado = productos.get(selectedIndex);
            String nuevoNombre = textFieldNombre.getText().trim();
            String nuevoPrecioStr = textFieldPrecio.getText().trim();

            if (nuevoNombre.isEmpty() || nuevoPrecioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                BigDecimal nuevoPrecio = new BigDecimal(nuevoPrecioStr);
                BaseDatos baseDatos = new BaseDatos();
                boolean exito = baseDatos.modificarProducto(productoSeleccionado.getId(), nuevoNombre, nuevoPrecio);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Producto modificado con éxito.");
                    cargarProductos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.");
        }
    }
}


