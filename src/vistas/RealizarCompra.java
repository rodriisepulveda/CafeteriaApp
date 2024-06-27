package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import controlador.ControladorCompra;
import modelo.Producto;

public class RealizarCompra extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldNombreCliente;
    private JComboBox<String> comboBoxProductos;
    private JTextField textFieldCantidad;
    private JTextArea textAreaResumenCompra;
    private List<Producto> productos;
    private List<Producto> productosSeleccionados;
    private ControladorCompra controladorCompra;
    private double totalCompra;

    public RealizarCompra() {
        controladorCompra = new ControladorCompra();
        productosSeleccionados = new ArrayList<>();
        totalCompra = 0;

        setTitle("Realizar Compra");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(60, 63, 65));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(mainPanel, BorderLayout.CENTER);


        JLabel lblTitle = new JLabel("Realizar Compra", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createVerticalStrut(20));


        mainPanel.add(crearPanelCampo("Nombre del Cliente:", textFieldNombreCliente = new JTextField(20)));
        mainPanel.add(Box.createVerticalStrut(10));


        mainPanel.add(crearPanelCampo("Seleccione Producto:", comboBoxProductos = new JComboBox<>()));
        mainPanel.add(Box.createVerticalStrut(10));


        mainPanel.add(crearPanelCampo("Cantidad:", textFieldCantidad = new JTextField(10)));
        mainPanel.add(Box.createVerticalStrut(20));


        JButton btnAgregarProducto = crearBoton("Agregar Producto", e -> agregarProducto());
        mainPanel.add(btnAgregarProducto);
        mainPanel.add(Box.createVerticalStrut(10));


        JButton btnRealizarCompra = crearBoton("Realizar Compra", e -> confirmarCompra());
        mainPanel.add(btnRealizarCompra);
        mainPanel.add(Box.createVerticalStrut(10));


        JButton btnVolver = crearBoton("Volver", e -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);

        mainPanel.add(Box.createVerticalStrut(20));


        textAreaResumenCompra = new JTextArea(10, 40);
        textAreaResumenCompra.setEditable(false);
        textAreaResumenCompra.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textAreaResumenCompra.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(textAreaResumenCompra);
        mainPanel.add(scrollPane);

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
        boton.setForeground(Color.black);
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
        productos = controladorCompra.obtenerProductos();
        comboBoxProductos.removeAllItems();
        for (Producto producto : productos) {
            comboBoxProductos.addItem(producto.getNombre() + " - $" + producto.getPrecio());
        }
    }

    private void agregarProducto() {
        int selectedIndex = comboBoxProductos.getSelectedIndex();
        if (selectedIndex >= 0) {
            Producto productoSeleccionado = productos.get(selectedIndex);
            String cantidadStr = textFieldCantidad.getText().trim();
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad > 0) {
                    productosSeleccionados.add(productoSeleccionado);
                    double subtotal = productoSeleccionado.getPrecio().doubleValue() * cantidad;
                    totalCompra += subtotal;
                    actualizarResumenCompra();
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarResumenCompra() {
        textAreaResumenCompra.setText("");
        for (Producto producto : productosSeleccionados) {
            textAreaResumenCompra.append("Producto: " + producto.getNombre() + " - Cantidad: " + "1\n"); // Adjust as necessary
        }
        textAreaResumenCompra.append("\nTotal: $" + totalCompra + "\n");
    }

    private void confirmarCompra() {
        String nombreCliente = textFieldNombreCliente.getText().trim();
        if (nombreCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (productosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos seleccionados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea realizar la compra por: $" + totalCompra + "?\nProductos: " + obtenerListaProductos(), "Confirmar Compra", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            realizarCompra(nombreCliente);
        }
    }

    private void realizarCompra(String nombreCliente) {
        boolean exito = controladorCompra.realizarCompra(nombreCliente, productosSeleccionados);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
            textFieldNombreCliente.setText("");
            textFieldCantidad.setText("");
            textAreaResumenCompra.setText("");
            productosSeleccionados.clear();
            totalCompra = 0; // Resetear el total
        } else {
            JOptionPane.showMessageDialog(this, "Error al realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerListaProductos() {
        StringBuilder listaProductos = new StringBuilder();
        for (Producto producto : productosSeleccionados) {
            listaProductos.append(producto.getNombre()).append(", ");
        }
        return listaProductos.toString();
    }
}

