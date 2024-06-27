package vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import modelo.BaseDatos;
import modelo.Producto;

public class VistaListarProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable tableProductos;

    public VistaListarProductos() {
        setTitle("Listado de Productos");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(60, 63, 65));
        add(mainPanel, BorderLayout.CENTER);

        JLabel lblTitle = new JLabel("Listado de Productos", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        tableProductos = new JTable();
        tableProductos.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nombre", "Precio"}
        ));
        JScrollPane scrollPane = new JScrollPane(tableProductos);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(60, 63, 65));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton btnVolver = crearBoton("Volver", e -> {
            VistaGestionProductos vistaGestionProductos = new VistaGestionProductos();
            vistaGestionProductos.setVisible(true);
            dispose();
        });
        buttonPanel.add(btnVolver);

        cargarDatosProductos();
    }

    private void cargarDatosProductos() {
        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
        model.setRowCount(0);

        BaseDatos baseDatos = new BaseDatos();
        ArrayList<Producto> productos = baseDatos.obtenerProductos();

        for (Producto producto : productos) {
            int id = producto.getId();
            String nombre = producto.getNombre();
            BigDecimal precio = producto.getPrecio();
            model.addRow(new Object[]{id, nombre, precio});
        }
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
}


