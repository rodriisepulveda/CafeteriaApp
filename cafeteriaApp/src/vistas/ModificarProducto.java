package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import modelo.BaseDatos;
import modelo.Producto;

public class ModificarProducto extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxProductos;
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;
    private ArrayList<Producto> productos;

    public ModificarProducto() {
        super();
        JPanel mainPanel = getMainPanel();

        JLabel lblTitle = new JLabel("Modificar Producto");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(125, 20, 250, 25);
        mainPanel.add(lblTitle);

        comboBoxProductos = new JComboBox<>();
        comboBoxProductos.setBounds(50, 70, 300, 30);
        comboBoxProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatosProducto();
            }
        });
        mainPanel.add(comboBoxProductos);


        JLabel lblNombre = new JLabel("Nuevo Nombre:");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNombre.setBounds(50, 120, 100, 20);
        mainPanel.add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(160, 120, 190, 25);
        mainPanel.add(textFieldNombre);


        JLabel lblPrecio = new JLabel("Nuevo Precio:");
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPrecio.setBounds(50, 160, 100, 20);
        mainPanel.add(lblPrecio);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(160, 160, 190, 25);
        mainPanel.add(textFieldPrecio);


        JButton btnModificar = new JButton("Modificar Producto");
        btnModificar.setBackground(new Color(51, 153, 255));
        btnModificar.setForeground(Color.BLACK);
        btnModificar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnModificar.setBounds(150, 200, 180, 30);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        });
        mainPanel.add(btnModificar);


        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(51, 153, 255));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(150, 250, 180, 30);
        btnVolver.addActionListener(e -> {
            VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
            vistaGestionProductos.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);


        cargarProductos();
    }

    private void cargarProductos() {
        BaseDatos baseDatos = new BaseDatos();
        productos = baseDatos.obtenerProductos();

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
