package vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import modelo.BaseDatos;
import modelo.Producto;

public class VistaListarProductos extends BaseLayout {

    private static final long serialVersionUID = 1L;
    private JTable tableProductos;

    public VistaListarProductos() {
        super();
        JPanel mainPanel = getMainPanel();
        JLabel lblTitle = new JLabel("Listado de Productos");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(125, 20, 250, 25);
        mainPanel.add(lblTitle);
        tableProductos = new JTable();
        tableProductos.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nombre", "Precio"}
        ));
        JScrollPane scrollPane = new JScrollPane(tableProductos);
        scrollPane.setBounds(10, 70, 414, 200);
        mainPanel.add(scrollPane);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(51, 153, 255));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(165, 280, 130, 30);
        btnVolver.addActionListener(e -> {
            VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
            vistaGestionProductos.setVisible(true);
            dispose();
        });
        mainPanel.add(btnVolver);

        cargarDatosProductos();
    }

    private void cargarDatosProductos() {
        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
        model.setRowCount(0); //

        BaseDatos baseDatos = new BaseDatos();
        ArrayList<Producto> productos = baseDatos.obtenerProductos();

        for (Producto producto : productos) {
            int id = producto.getId();
            String nombre = producto.getNombre();
            BigDecimal precio = producto.getPrecio();
            model.addRow(new Object[]{id, nombre, precio});
        }
    }
}

