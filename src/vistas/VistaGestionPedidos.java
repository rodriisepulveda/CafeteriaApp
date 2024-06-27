package vistas;

import modelo.BaseDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaGestionPedidos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable tablePedidos;
    private JComboBox<String> comboBoxPedidos;
    private JComboBox<String> comboBoxEstado;
    private BaseDatos baseDatos;

    public VistaGestionPedidos() {
        baseDatos = new BaseDatos();
        setTitle("Gestión de Pedidos");
        setSize(1020, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(60, 63, 65));
        getContentPane().add(mainPanel);


        JLabel lblTitle = new JLabel("Gestión de Pedidos");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblTitle, BorderLayout.NORTH);


        tablePedidos = new JTable();
        actualizarTablaPedidos("vista_compras_en_proceso");
        JScrollPane scrollPane = new JScrollPane(tablePedidos);
        mainPanel.add(scrollPane, BorderLayout.CENTER);


        JPanel panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.setBackground(new Color(60, 63, 65));
        panelControles.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(panelControles, BorderLayout.EAST);


        JLabel lblSeleccionarPedido = new JLabel("Seleccionar Pedido:");
        lblSeleccionarPedido.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccionarPedido.setForeground(Color.white);
        lblSeleccionarPedido.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panelControles.add(lblSeleccionarPedido);
        comboBoxPedidos = new JComboBox<>();
        actualizarComboBoxPedidos();
        comboBoxPedidos.setMaximumSize(new Dimension(200, 30));
        panelControles.add(comboBoxPedidos);

        JLabel lblNuevoEstado = new JLabel("Nuevo Estado:");
        lblNuevoEstado.setForeground(Color.WHITE);
        lblNuevoEstado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panelControles.add(lblNuevoEstado);
        comboBoxEstado = new JComboBox<>(new String[]{"En proceso", "Pedido terminado", "Archivado"});
        comboBoxEstado.setMaximumSize(new Dimension(200, 30));
        panelControles.add(comboBoxEstado);

        panelControles.add(crearBoton("Actualizar Estado", new Color(102, 255, 102), e -> actualizarEstadoPedido()));


        panelControles.add(crearBoton("Pedidos en Proceso", new Color(75, 110, 175), e -> actualizarTablaPedidos("vista_compras_en_proceso")));
        panelControles.add(crearBoton("Pedidos Completados", new Color(75, 110, 175), e -> actualizarTablaPedidos("vista_compras_terminadas")));
        panelControles.add(crearBoton("Pedidos Archivados", new Color(75, 110, 175), e -> actualizarTablaPedidos("vista_compras_archivadas")));


        panelControles.add(crearBoton("Volver", new Color(75, 110, 175), e -> {
            VistaAdministrador vistaAdmin = new VistaAdministrador();
            vistaAdmin.setVisible(true);
            dispose();
        }));

        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setEnabled(false);
        panelControles.add(verticalStrut);
    }

    private void actualizarTablaPedidos(String vista) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Cliente", "Productos", "Precio Total", "Fecha"}, 0);
        List<String[]> registros = baseDatos.obtenerRegistrosDesdeVista(vista);
        for (String[] registro : registros) {
            model.addRow(registro);
        }
        tablePedidos.setModel(model);
    }

    private void actualizarComboBoxPedidos() {
        comboBoxPedidos.removeAllItems();
        List<String[]> registros = baseDatos.obtenerRegistrosDesdeVista("vista_compras_en_proceso");
        for (String[] registro : registros) {
            comboBoxPedidos.addItem(registro[0] + " - " + registro[1]);
        }
    }

    private void actualizarEstadoPedido() {
        String seleccion = (String) comboBoxPedidos.getSelectedItem();
        if (seleccion != null) {
            int idCompra = Integer.parseInt(seleccion.split(" - ")[0]);
            String nuevoEstado = (String) comboBoxEstado.getSelectedItem();
            baseDatos.actualizarEstadoCompra(idCompra, nuevoEstado);
            actualizarTablaPedidos("vista_compras_en_proceso");
            actualizarComboBoxPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton crearBoton(String texto, Color colorFondo, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.black);
        boton.setFocusPainted(false);
        boton.setAlignmentX(CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(250, 40));
        boton.setMinimumSize(new Dimension(250, 40));
        boton.setPreferredSize(new Dimension(250, 40));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boton.addActionListener(actionListener);
        return boton;
    }
}

