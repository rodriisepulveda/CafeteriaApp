package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import modelo.BaseDatos;
import modelo.Producto;

public class EliminarProducto extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxProductos;
    private ArrayList<Producto> productos;

    public EliminarProducto() {
        super();
        JPanel mainPanel = getMainPanel();


        JLabel lblTitle = new JLabel("Eliminar Producto");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(125, 20, 250, 25);
        mainPanel.add(lblTitle);


        comboBoxProductos = new JComboBox<>();
        comboBoxProductos.setBounds(50, 70, 300, 30);
        mainPanel.add(comboBoxProductos);


        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.setBackground(new Color(51, 153, 255));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setBounds(150, 120, 180, 30);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
        mainPanel.add(btnEliminar);


        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(51, 153, 255));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(150, 170, 180, 30);
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
