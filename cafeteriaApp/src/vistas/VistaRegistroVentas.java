package vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import controlador.ControladorCompra;

public class VistaRegistroVentas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable tableVentas;
    private ControladorCompra controladorCompra;

    public VistaRegistroVentas() {
        controladorCompra = new ControladorCompra();

        setTitle("Registro de Ventas");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(60, 63, 65));
        add(mainPanel);

        // Título
        JLabel lblTitle = new JLabel("Registro de Ventas");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Tabla de ventas
        tableVentas = new JTable();
        tableVentas.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID Compra", "Cliente", "Productos", "Precio Total", "Fecha"}
        ));
        tableVentas.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tableVentas);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botón
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(60, 63, 65));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(Box.createVerticalStrut(20));

        JButton btnVolver = crearBoton("Volver", e -> {
            VistaAdministrador vistaAdmin = new VistaAdministrador();
            vistaAdmin.setVisible(true);
            dispose();
        });
        bottomPanel.add(btnVolver);

        bottomPanel.add(Box.createVerticalStrut(20));

        cargarDatosVentas();
    }

    private void cargarDatosVentas() {
        DefaultTableModel model = (DefaultTableModel) tableVentas.getModel();
        model.setRowCount(0); 

        List<String[]> ventas = controladorCompra.obtenerRegistroVentas();
        for (String[] venta : ventas) {
            model.addRow(venta);
        }
    }

    private JButton crearBoton(String texto, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(new Color(75, 110, 175));
        boton.setForeground(Color.black);
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



